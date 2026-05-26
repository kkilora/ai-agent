package cn.bugstack.ai.domain.agent.service.armory.node;

import cn.bugstack.ai.domain.agent.model.entity.ArmoryCommandEntity;
import cn.bugstack.ai.domain.agent.model.valobj.AiAgentRegisterVO;
import cn.bugstack.ai.domain.agent.model.valobj.AiAgnetConfigTableVO;
import cn.bugstack.ai.domain.agent.service.armory.AbstractArmorySupport;
import cn.bugstack.ai.domain.agent.service.armory.factor.DefaultArmoryFactory.DynamicContext;
import cn.bugstack.wrench.design.framework.tree.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jvnet.hk2.annotations.Service;
import org.springframework.ai.openai.api.OpenAiApi;

@Slf4j
@Service
public class AiApiNode extends AbstractArmorySupport {


    @Override
    protected AiAgentRegisterVO doApply(ArmoryCommandEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        //编写api示例化的操作
        log.info("Ai Agent 装配操作 - AiApiNode");

        AiAgnetConfigTableVO aiAgnetConfigTableVO = requestParameter.getAiAgnetConfigTableVO();
        AiAgnetConfigTableVO.Module.AiApi aiApiConfig = aiAgnetConfigTableVO.getModule().getAiApi();

        OpenAiApi openAiApi = OpenAiApi.builder()
                .baseUrl(aiApiConfig.getBaseUrl())
                .apiKey(aiApiConfig.getApiKey())
                .completionsPath(StringUtils.isNoneBlank(aiApiConfig.getCompletionsPath()) ? aiApiConfig.getCompletionsPath() : "v1/chat/completions")
                .embeddingsPath(StringUtils.isNoneBlank(aiApiConfig.getEmbeddingsPath()) ? aiApiConfig.getEmbeddingsPath() : "v1/embeddings")
                .build();

        dynamicContext.setOpenAiApi(openAiApi);

        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<ArmoryCommandEntity, DynamicContext, AiAgentRegisterVO> get(ArmoryCommandEntity requestParameter, DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
