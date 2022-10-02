package com.moon.drools.service;

import com.moon.drools.entity.InsuranceInfo;
import com.moon.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 规则处理业务类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-10-02 9:33
 * @description
 */
@Service
public class RuleService {

    public List<String> insuranceInfoCheck(InsuranceInfo insuranceInfo) throws Exception {
        // 使用工具类读取决策表
        KieSession session = KieSessionUtils.getKieSessionFromXLS("D:\\code\\java-technology-stack\\java-stack-drools\\drools-demo-insurance-check\\src\\main\\resources\\decisiontables\\insuranceInfoCheck.xls");
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(insuranceInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }
}
