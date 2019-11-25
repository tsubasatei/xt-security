package com.xt.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(() -> {
            while (true) {
            if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                String orderNumber = mockQueue.getCompleteOrder();
                logger.info("返回订单处理结果：" + orderNumber);
                DeferredResult<String> result = deferredResultHolder.getMap().get(orderNumber);
                result.setResult("place order success");

                mockQueue.setCompleteOrder(null);
            } else {
                // 暂停一会儿线程
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }}).start();


    }
}
