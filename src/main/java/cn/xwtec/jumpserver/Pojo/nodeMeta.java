package cn.xwtec.jumpserver.Pojo;

import javax.validation.constraints.Max;
import java.util.List;

public class nodeMeta {
    private List<System_users> system_users;
    private String type;
    private Asset asset;

    public List<System_users> getSystem_users() {
        return system_users;
    }

    public void setSystem_users(List<System_users> system_users) {
        this.system_users = system_users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
