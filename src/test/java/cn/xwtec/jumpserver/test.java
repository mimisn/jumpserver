package cn.xwtec.jumpserver;

import cn.xwtec.jumpserver.Pojo.defaultJson;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class test {

    public static void main(String[] args) {
        List<String> cookieList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.1.248/api/perms/v1/users/nodes-assets/tree/?cache_policy=1";
        HttpHeaders requestHeaders = new HttpHeaders();
        cookieList.add("csrftoken=zqs91qzdQoMUZL2ZQaLsh853ZIl4f8a0RVJ92mn9qvhubFxID9dH8ZwqvpIj7yBG");
        cookieList.add("loglevel=0");
        cookieList.add("sessionid=dpzij7rmtmh03t0qe1yhhokavvesl3uo");
        cookieList.add("IN_ADMIN_PAGE=No");
        requestHeaders.put("Cookie",cookieList);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Object.class);
//        if (exchange.getStatusCode().is2xxSuccessful()){
//            List <Objects> nodeList = (List<Objects>)exchange.getBody();
//            String listTxt = JSON.toJSONString(nodeList);
//            System.out.println(listTxt);
//            List<defaultJson> nodeLists = JSON.parseArray(listTxt, defaultJson.class);
//        }
        List<String> ms_port =new ArrayList<>();
        ms_port.add("12345");
        ms_port.add("25000");
        ms_port.add("9945");
        int ranPort = getRanPort(ms_port, 9945);
        System.out.println("hhh"+String.valueOf(ranPort));
        System.out.println("：：：+   "+String.valueOf(f(6)));

        Map<String,String> m=new HashMap<>();
        m.put("hello","world");
        if(m.get("hello")!=null){
            m.put("hello","hello");
        }
        System.out.println(m);


    }



    public static int getRanPort(List<String> ms_port,int number){
        Random random = new Random();
        int _number = number;
        for (String v:ms_port){
            System.out.println("kkk"+v);
            if (v.equals(String.valueOf(number))){
                _number = random.nextInt(65535-1000+1)+1000;
                System.out.println(String.valueOf(_number));
                _number = getRanPort(ms_port,_number);
            }
        }
        return _number;
    }


    public static int f(int x) {

        if (x == 1 || x == 2) {
            System.out.println("||||||   "+ String.valueOf(x));
            return 1;
        } else {
            int y = f(x - 1) + f(x - 2);
            System.out.println("===   "+ String.valueOf(y));
            return y;
        }
    }



}
