package cn.xwtec.jumpserver.Pojo;

public class System_users {
    private String id;
    private String name;
    private String username;
    private String protocol;
    private int priority;
    private String login_mode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLogin_mode() {
        return login_mode;
    }

    public void setLogin_mode(String login_mode) {
        this.login_mode = login_mode;
    }
}
