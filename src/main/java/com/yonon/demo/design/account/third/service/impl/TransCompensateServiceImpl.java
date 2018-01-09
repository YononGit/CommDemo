package com.yonon.demo.design.account.third.service.impl;

import com.yonon.demo.design.account.common.entity.MQDomain;
import com.yonon.demo.design.account.enums.PartnerCodeEnum;
import com.yonon.demo.design.account.third.service.PartnerCompensateService;
import com.yonon.demo.design.account.third.service.TransCompensateService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: JiangYinghan
 * @Description:
 * @Date: Create on 2018-01-03.
 */
@Service("transCompensateService")
public class TransCompensateServiceImpl implements TransCompensateService {
    private final String beanName = "partnerCompensateService_";

    @Override
    public MQDomain transCompensate(String partnerCode, Object object) {
        PartnerCompensateService partnerCompensateService = getPartnerCompensateService(partnerCode);
        MQDomain mqDomain = partnerCompensateService.compensate(object);
        return mqDomain;
    }

    private PartnerCompensateService getPartnerCompensateService(String partnerCode) {
        PartnerCompensateService partnerCompensateService;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:ApplicationContext.xml");
        PartnerCodeEnum partnerCodeEnum = PartnerCodeEnum.getEnumByCode(partnerCode);
        if (null == partnerCodeEnum) {
            return null;
        }
        partnerCompensateService = (PartnerCompensateService) ctx.getBean(beanName.concat(partnerCodeEnum.getSimpleName()));

        return partnerCompensateService;
    }

}
