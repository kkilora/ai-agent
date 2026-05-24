package cn.bugstack.ai.domain.agent.model.valobj.properties;

import cn.bugstack.ai.domain.agent.model.valobj.AiAgnetConfigTableVO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties( prefix = "ai.agent.config", ignoreInvalidFields = true )
public class AiAgentAutoConfigProperties {
    private boolean enabled = false;
    private Map<String, AiAgnetConfigTableVO> tables;
}
