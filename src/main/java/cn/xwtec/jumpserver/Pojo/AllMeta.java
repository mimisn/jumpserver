package cn.xwtec.jumpserver.Pojo;

import java.util.List;

public class AllMeta {
    private Node node;
    private String type;
    private List<System_users> system_users;
    private Asset asset;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<System_users> getSystem_users() {
        return system_users;
    }

    public void setSystem_users(List<System_users> system_users) {
        this.system_users = system_users;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
