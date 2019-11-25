package com.xt.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 消息队列
 */
@Component
public class MockQueue{

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);
    private String placeOrder; // 下单消息
    private String completeOrder; // 订单完成消息

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            logger.info("接到下单请求，" + placeOrder);
            // 暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            this.completeOrder = placeOrder;
            logger.info("下单请求处理完毕，" + placeOrder);
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
