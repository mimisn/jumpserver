package cn.xwtec.jumpserver.Pojo;

public class mstsc {
    private String name;
    private String port;
    private String ip;
    private String local_ip;
    private String session;
    private long ex_time = 10*60*1000;
    private long log_time;
    private String csrftoken;
    private String loglevel;
    private String IN_ADMIN_PAGE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocal_ip() {
        return local_ip;
    }

    public void setLocal_ip(String local_ip) {
        this.local_ip = local_ip;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public long getEx_time() {
        return ex_time;
    }

    public void setEx_time(long ex_time) {
        this.ex_time = ex_time;
    }

    public long getLog_time() {
        return log_time;
    }

    public void setLog_time(long log_time) {
        this.log_time = log_time;
    }

    public String getCsrftoken() {
        return csrftoken;
    }

    public void setCsrftoken(String csrftoken) {
        this.csrftoken = csrftoken;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public String getIN_ADMIN_PAGE() {
        return IN_ADMIN_PAGE;
    }

    public void setIN_ADMIN_PAGE(String IN_ADMIN_PAGE) {
        this.IN_ADMIN_PAGE = IN_ADMIN_PAGE;
    }
}
