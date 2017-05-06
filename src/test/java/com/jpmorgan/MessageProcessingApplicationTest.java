package com.jpmorgan;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpmorgan.model.AdjustmentOperationValue;
import com.jpmorgan.model.MessageType1;
import com.jpmorgan.model.MessageType2;
import com.jpmorgan.model.MessageType3;
import com.jpmorgan.model.ProductType;
import com.jpmorgan.support.AdjustmentOperation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageProcessingApplicationTest {

    @Value("${queue.name}")
    private String queueName;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void messageType1() {
        final ProductType productTypeVO = new ProductType();
        productTypeVO.setName("Banana");
        productTypeVO.setValue(BigDecimal.valueOf(1.23));

        final MessageType1 messageType1VO = new MessageType1();
        messageType1VO.setProductTypeVO(productTypeVO);

        rabbitTemplate.convertAndSend(queueName, messageType1VO);
    }

    @Test
    public void messageType2() {
        final ProductType productTypeVO = new ProductType();
        productTypeVO.setName("Cookie");
        productTypeVO.setValue(BigDecimal.valueOf(3.45));

        final MessageType2 messageType2VO = new MessageType2();
        messageType2VO.setProductTypeVO(productTypeVO);
        messageType2VO.setOccurrences(10);

        rabbitTemplate.convertAndSend(queueName, messageType2VO);
    }

    @Test
    public void messageType3() {
        final ProductType productTypeVO = new ProductType();
        productTypeVO.setName("Shoe");
        productTypeVO.setValue(BigDecimal.valueOf(123.12));

        final AdjustmentOperationValue adjustmentOperationValueVO = new AdjustmentOperationValue();
        adjustmentOperationValueVO.setAdjustmentOperation(AdjustmentOperation.ADD);
        adjustmentOperationValueVO.setValue(BigDecimal.valueOf(5.5));

        final MessageType3 messageType3VO = new MessageType3();
        messageType3VO.setProductTypeVO(productTypeVO);
        messageType3VO.setOccurrences(10);
        messageType3VO.setAdjustmentOperationValue(adjustmentOperationValueVO);

        rabbitTemplate.convertAndSend(queueName, messageType3VO);
    }
}