package com.yonon.demo.design.account.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.yonon.demo.design.account.common.entity.*;
import com.yonon.demo.design.account.common.service.TransAccountBaseService;
import com.yonon.demo.design.account.common.service.TransAccountService;
import com.yonon.demo.design.account.enums.TransCodeEnum;
import com.yonon.demo.design.account.enums.TransFeeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-02.
 */
@Service("transAccountService")
public class TransAccountServiceImpl implements TransAccountService {
    private static Logger logger = LoggerFactory.getLogger(TransAccountServiceImpl.class);
    private final String beanName = "Service";

    @Override
    public void transAccount(TransCodeEnum transCodeEnum, Loan loan, Object... objects) {
        try {
            logger.info("fas_记账_开始_loanNo:{},partnerCode:{},transCodeEnum:{}", loan.getId(), loan.getThird(), transCodeEnum.getCode());
            // 1、获取交易规则 从表读取json(加载在缓存中) 2、转换合作方编码fas
            PartnerAccountRule partnerAccountRule = getPartnerAccountRule(loan.getThird());

            // 获取计费规则
            TransFeeEnum transFeeEnum = getPartnerAccountFee(partnerAccountRule.getPartnerCode(), transCodeEnum);
            // bean 001 AC
            TransAccountBaseService baseGen = getPartnerTransService(transCodeEnum.getCode());
            MQDomain mqDomain = new MQDomain();
            mqDomain = baseGen.genMQDomain(transCodeEnum, loan, partnerAccountRule, transFeeEnum);
            // Copy
            // save
            // send mq
            logger.info("fas_记账_正常结束_loanNo:{},partnerCode:{},transCodeEnum:{}", loan.getId(), loan.getThird(), transCodeEnum.getCode());
        } catch (Exception ex) {
            logger.error("fas_记账_异常结束_loanNo:{},partnerCode:{},transCodeEnum:{},异常信息:", loan.getId(), loan.getThird(), transCodeEnum.getCode(), ex);
        }
    }

    private TransAccountBaseService getPartnerTransService(String transCode) {
        TransAccountBaseService transAccountBaseService;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
        transAccountBaseService = (TransAccountBaseService) ctx.getBean(transCode.concat(beanName));
        return transAccountBaseService;
    }

    // cache
    private PartnerAccountRule getPartnerAccountRule(String thirdCode) {
        // 根据thirdCode，业务模式 获取json串
        String jsonStr = "{\"transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}";
        PartnerAccountRuleTable table = new PartnerAccountRuleTable("013", "03", jsonStr);
        PartnerAccountRule partnerAccountRule = JSON.parseObject(table.getContent(), PartnerAccountRule.class);
        return partnerAccountRule;
    }

    // cache
    private TransFeeEnum getPartnerAccountFee(String partnerCode, TransCodeEnum transCodeEnum) {
        // 根据partnerCode ,transCodeEnum，业务模式取得配置
        String feeStr = "intNotOint";
        return TransFeeEnum.getEnumByCode(feeStr);
    }


    private static ArrayList<TempRuleTb> getCustSeg() {
        ArrayList<TempRuleTb> dbDate = new ArrayList<>();

        TempRuleTb tempRuleTb1 = new TempRuleTb();
        tempRuleTb1.setContent("{\"Sort1_transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}");
        tempRuleTb1.setContentName("001Name-1");
        tempRuleTb1.setCustSeg("[E19][E20]");
        tempRuleTb1.setId(1);
        tempRuleTb1.setPartnerCode("001");
        tempRuleTb1.setRpyType("00");
        tempRuleTb1.setProduceCode("360jietiao");
        dbDate.add(tempRuleTb1);

        TempRuleTb tempRuleTb2 = new TempRuleTb();
        tempRuleTb2.setContent("{\"Sort2_transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}");
        tempRuleTb2.setContentName("001Name-2");
        tempRuleTb2.setCustSeg("[E30][31]");
        tempRuleTb2.setId(2);
        tempRuleTb2.setPartnerCode("001");
        tempRuleTb2.setRpyType("00");
        tempRuleTb2.setProduceCode("360jietiao");
        dbDate.add(tempRuleTb2);

        TempRuleTb tempRuleTb3 = new TempRuleTb();
        tempRuleTb3.setContent("{\"transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}");
        tempRuleTb3.setContentName("001Name-2");
        tempRuleTb3.setCustSeg("[E30][E31]");
        tempRuleTb3.setId(3);
        tempRuleTb3.setPartnerCode("002");
        tempRuleTb3.setRpyType("00");
        tempRuleTb3.setProduceCode("360jietiao");
        dbDate.add(tempRuleTb3);

        TempRuleTb tempRuleTb4 = new TempRuleTb();
        tempRuleTb4.setContent("{\"transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}");
        tempRuleTb4.setContentName("001Name-2");
        tempRuleTb4.setCustSeg("[E30][E31]");
        tempRuleTb4.setId(4);
        tempRuleTb4.setPartnerCode("003");
        tempRuleTb4.setRpyType("00");
        tempRuleTb4.setProduceCode("360jietiao");
        dbDate.add(tempRuleTb4);

        TempRuleTb tempRuleTb5 = new TempRuleTb();
        tempRuleTb5.setContent("{\"transCodeDB\":\"normal\",\"transCodeAC\":\"third\",\"transCodeRP\":\"normal\",\"transCodeNAC\":\"normal\",\"transCodeCP\":\"third\"}");
        tempRuleTb5.setContentName("001Name-2");
        tempRuleTb5.setCustSeg("[E30][E31]");
        tempRuleTb5.setId(5);
        tempRuleTb5.setPartnerCode("004");
        tempRuleTb5.setRpyType("00");
        tempRuleTb5.setProduceCode("360jietiao");
        dbDate.add(tempRuleTb5);
        return dbDate;
    }

    private static String getContent(String produceCode, String partnerCode, String rpyType, String custSeg) {
        String content = "";
        ArrayList<TempRuleTb> dbDate = getCustSeg();
        Map<String, List<TempRuleTb>> cacheMap = new HashMap<>();
        String keyStr = "%s:ACCOUNTRULE";
        //        for(ArrayList<TempRuleTb> tempRuleTbs : dbDate.stream().collect(Collectors.groupingBy(TempRuleTb::getProduceCode,TempRuleTb::getPartnerCode,TempRuleTb::getRpyType)))
        Map<String, List<TempRuleTb>> sortMap = dbDate.stream().collect(Collectors.groupingBy(TempRuleTb::getPartnerCode));
        for (String s : sortMap.keySet()) {
            System.out.println(sortMap.get(s));
            cacheMap.put(String.format(keyStr, s), sortMap.get(s));
        }
        List<TempRuleTb> results = cacheMap.get(String.format(keyStr, partnerCode));
        for (TempRuleTb tempRuleTb : results) {
            if (tempRuleTb.getProduceCode().equals(produceCode) && tempRuleTb.getRpyType().equals(rpyType) && tempRuleTb.getCustSeg().contains(custSeg)) {
                return tempRuleTb.getContent();
            }
        }
        return content;
    }

    public static void main(String[] args) {
        System.out.println(getContent("360jietiao", "001", "00","[E20]"));
    }
}
