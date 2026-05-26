package cn.bugstack.ai.domain.agent.service.armory.factor;

import cn.bugstack.ai.domain.agent.model.entity.ArmoryCommandEntity;
import cn.bugstack.ai.domain.agent.model.valobj.AiAgentRegisterVO;
import cn.bugstack.ai.domain.agent.service.armory.AbstractArmorySupport;
import cn.bugstack.ai.domain.agent.service.armory.node.AiApiNode;
import cn.bugstack.ai.domain.agent.service.armory.node.RootNode;
import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import lombok.*;
import org.jvnet.hk2.annotations.Service;
import org.springframework.ai.openai.api.OpenAiApi;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultArmoryFactory{

    @Resource
    private RootNode rootNode;

    public StrategyHandler<ArmoryCommandEntity, DynamicContext, AiAgentRegisterVO> armoryStrategyHandler() {
        return rootNode;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        /**
         * LLM Api
         */
        private OpenAiApi openAiApi;

        private Map<String, Object> dataObjects = new HashMap<>();

        public <T> void setValue(String key,T value) {
            dataObjects.put(key, value);
        }

        public <T> T getValue(String key) {
            return (T) dataObjects.get(key);
        }
    }
}
