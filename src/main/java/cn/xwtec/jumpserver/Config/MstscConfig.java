package cn.xwtec.jumpserver.Config;

import cn.xwtec.jumpserver.Pojo.mstsc;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MstscConfig {
    private Map<String, mstsc> mstscLoginMap;

    public Map<String, mstsc> getMstscLoginMap() {
        return mstscLoginMap;
    }

    public void setMstscLoginMap(Map<String, mstsc> mstscLoginMap) {
        this.mstscLoginMap = mstscLoginMap;
    }
}
