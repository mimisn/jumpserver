package cn.xwtec.jumpserver.Controller;


import cn.xwtec.jumpserver.Config.MstscConfig;
import cn.xwtec.jumpserver.Pojo.ResponseJson;
import cn.xwtec.jumpserver.Pojo.defaultJson;
import cn.xwtec.jumpserver.Pojo.mstsc;
import cn.xwtec.jumpserver.Pojo.user;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
public class jumpServerController {
    @Autowired
    private MstscConfig mstscConfig;

    @Value("${mstsc.server}")
    private String mstscServerIp;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpSession session, Map<String,Object> map){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.1.248/api/perms/v1/users/nodes-assets/tree/?cache_policy=1";

//        List<String> cookieList = new ArrayList<>();
//        cookieList.add("csrftoken=7sveUQYwmVVXidSWF3Q1CJvgWBytHbDn9lowbhj1p29eGqa8oS70i7kqd0qeIPGF");
//        cookieList.add("loglevel=0");
//        cookieList.add("sessionid=tauzn0p124xxi6ijj5cm97lminhsp88g");
//        cookieList.add("IN_ADMIN_PAGE=No");

        HttpHeaders requestHeaders = new HttpHeaders();
        Map<String,String> cookieMap = jumpServerController.getCookieMap(request);
        List<String> cookieList = jumpServerController.getCookieList(cookieMap);
        requestHeaders.put("Cookie", cookieList);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        try {
            ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
            List <Objects> nodeList = (List<Objects>)exchange.getBody();
            String listTxt = JSON.toJSONString(nodeList);
            List<defaultJson> nodeLists = JSON.parseArray(listTxt, defaultJson.class);
            map.put("node",nodeLists);


        }catch (Exception e){
            map.put("Err","未登录或无权限请联系管理员!");
        }
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/mstsc",method = RequestMethod.GET)
    public ResponseJson mstsc(@RequestParam("ip") String ip, @RequestParam("port")  String port,HttpSession session, HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        ResponseJson responseJson = new ResponseJson();
        Process process=null;

        String url = "http://192.168.1.248/api/users/v1/profile/";

//        List<String> cookieList = new ArrayList<>();
//        cookieList.add("csrftoken=7sveUQYwmVVXidSWF3Q1CJvgWBytHbDn9lowbhj1p29eGqa8oS70i7kqd0qeIPGF");
//        cookieList.add("loglevel=0");
//        cookieList.add("sessionid=tauzn0p124xxi6ijj5cm97lminhsp88g");
//        cookieList.add("IN_ADMIN_PAGE=No");


        HttpHeaders requestHeaders = new HttpHeaders();
        Map<String,String> cookieMap = jumpServerController.getCookieMap(request);
        List<String> cookieList = jumpServerController.getCookieList(cookieMap);
        requestHeaders.put("Cookie", cookieList);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        try {
            ResponseEntity<user> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, user.class);
            user user = exchange.getBody();
            String _port = port.split("/")[1].split("]")[0];
            Random random = new Random();
            mstsc mstsc = null;
            String _cmd = null;
            int number = random.nextInt(65535-1000+1)+1000;
            Map<String, mstsc> _mstscMap = mstscConfig.getMstscLoginMap();
            List<String> ms_port = new ArrayList<>();
            if (_mstscMap==null || _mstscMap.isEmpty()){
                Map<String,mstsc> _logMap = new HashMap<>();
                mstsc = new mstsc();
                mstsc.setIp(ip);
                mstsc.setPort(String.valueOf(number));
                mstsc.setLog_time(new Date().getTime());
                _logMap.put(ip,mstsc);
                mstscConfig.setMstscLoginMap(_logMap);
            }else {
                for (Map.Entry<String,mstsc> value: _mstscMap.entrySet()) {
                    if (value.getKey().equals(ip)){
                        mstsc = value.getValue();
                    }
                    ms_port.add(value.getValue().getPort());
                }
                if ( mstsc != null){
                    long log_time = mstsc.getLog_time();
                    long ex_time  = mstsc.getEx_time();
                    long now_time  = new Date().getTime();
                    if (log_time+ex_time < now_time){
                        int ranPort = getRanPort(ms_port, number);
                        mstsc.setPort(String.valueOf(ranPort));
                        mstsc.setLog_time(new Date().getTime());
                    }
                }else {
                    mstsc = new mstsc();
                    mstsc.setIp(ip);
                    mstsc.setPort(String.valueOf(number));
                    mstsc.setLog_time(new Date().getTime());
                }
                _mstscMap.put(ip,mstsc);
                mstscConfig.setMstscLoginMap(_mstscMap);
            }
            _cmd = String.format("firewall-cmd --add-forward-port=port=%s:proto=tcp:toaddr=%s:toport=%s --timeout=%s",mstsc.getPort(),mstsc.getIp(),_port,mstsc.getEx_time()/1000);
            process = Runtime.getRuntime().exec(_cmd, null, null);
            int i = process.waitFor();
            //int i=0;
            if (i !=0){
                responseJson.setStatus("fail");
            }else {
                responseJson.setIp(mstscServerIp);
                responseJson.setPort(mstsc.getPort());
                responseJson.setStatus("success");
            }

        }catch (Exception e){
            responseJson.setStatus("fail");
        }finally {
            if (process != null) {
                process.destroy();
            }
        }

        return responseJson;
    }






    @ResponseBody
    @RequestMapping(value = "/v1/api",method = RequestMethod.GET)
    public ResponseJson mstscApi(@RequestParam("ip") String ip, @RequestParam("port")  String port, @RequestParam("token") String token){
        RestTemplate restTemplate = new RestTemplate();
        ResponseJson responseJson = new ResponseJson();
        Process process=null;

        String url = "http://192.168.1.248/api/users/v1/users/";

        HttpHeaders requestHeaders = new HttpHeaders();
        List<String> authList = new ArrayList<>();
        authList.add(String.format("Bearer %s", token));
        requestHeaders.put("Authorization", authList);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);

        try {
            ResponseEntity<user> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, user.class);
            user user = exchange.getBody();
            String _port = port.split("/")[1].split("]")[0];
            Random random = new Random();
            mstsc mstsc = null;
            String _cmd = null;
            int number = random.nextInt(65535-1000+1)+1000;
            Map<String, mstsc> _mstscMap = mstscConfig.getMstscLoginMap();
            List<String> ms_port = new ArrayList<>();
            if (_mstscMap==null || _mstscMap.isEmpty()){
                Map<String,mstsc> _logMap = new HashMap<>();
                mstsc = new mstsc();
                mstsc.setIp(ip);
                mstsc.setPort(String.valueOf(number));
                mstsc.setLog_time(new Date().getTime());
                _logMap.put(ip,mstsc);
                mstscConfig.setMstscLoginMap(_logMap);
            }else {
                for (Map.Entry<String,mstsc> value: _mstscMap.entrySet()) {
                    if (value.getKey().equals(ip)){
                        mstsc = value.getValue();
                    }
                    ms_port.add(value.getValue().getPort());
                }
                if ( mstsc != null){
                    long log_time = mstsc.getLog_time();
                    long ex_time  = mstsc.getEx_time();
                    long now_time  = new Date().getTime();
                    if (log_time+ex_time < now_time){
                        int ranPort = getRanPort(ms_port, number);
                        mstsc.setPort(String.valueOf(ranPort));
                        mstsc.setLog_time(new Date().getTime());
                    }
                }else {
                    mstsc = new mstsc();
                    mstsc.setIp(ip);
                    mstsc.setPort(String.valueOf(number));
                    mstsc.setLog_time(new Date().getTime());
                }
                _mstscMap.put(ip,mstsc);
                mstscConfig.setMstscLoginMap(_mstscMap);
            }
            _cmd = String.format("firewall-cmd --add-forward-port=port=%s:proto=tcp:toaddr=%s:toport=%s --timeout=%s",mstsc.getPort(),mstsc.getIp(),_port,mstsc.getEx_time()/1000);
            process = Runtime.getRuntime().exec(_cmd, null, null);
            int i = process.waitFor();
            //int i=0;
            if (i !=0){
                responseJson.setStatus("fail");
            }else {
                responseJson.setIp(mstscServerIp);
                responseJson.setPort(mstsc.getPort());
                responseJson.setStatus("success");
            }

        }catch (Exception e){
            responseJson.setStatus("fail");
        }finally {
            if (process != null) {
                process.destroy();
            }
        }

        return responseJson;
    }

    @ResponseBody
    @RequestMapping("/reload")
    public String reload(){
        String _cmd = "firewall-cmd --reload";
        Process process=null;
        try {
            process = Runtime.getRuntime().exec(_cmd, null, null);
            int i = process.waitFor();
            if (i != 0){
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(process !=null){
                process.destroy();
            }
        }
        return "success";
    }


    public static List<String> getCookieList(Map<String,String> cookieMap) {
        List<String> cookieList = new ArrayList<>();

        if (cookieMap == null || cookieMap.isEmpty()) {
            return cookieList;
        }
        for (Map.Entry<String,String> cookie : cookieMap.entrySet()) {
            cookieList.add(cookie.getKey() + "=" + cookie.getValue());
        }
        return cookieList;
    }

    public static int getRanPort(List<String> ms_port,int number){
        Random random = new Random();
        int _number = number;
        for (String v:ms_port){
            if (v.equals(String.valueOf(number))){
                _number = random.nextInt(65535-1000+1)+1000;
                _number = getRanPort(ms_port,_number);
            }
        }
        return _number;
    }


    public static Map<String,String> getCookieMap(HttpServletRequest request) {
        Map<String,String> cookieMap = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return cookieMap;
        }

        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(),cookie.getValue());
        }
        return cookieMap;
    }




    public static void setSession(HttpServletRequest request,HttpSession session) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
        }else {
            for (Cookie cookie : cookies) {
                session.setAttribute(cookie.getName(),cookie.getValue());
            }
        }

    }
}
