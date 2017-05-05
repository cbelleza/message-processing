package com.jpmorgan.handler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmorgan.support.vo.MessageType1;
import com.jpmorgan.support.vo.MessageType2;
import com.jpmorgan.support.vo.MessageType3;
import com.jpmorgan.support.vo.ProductType;

/**
 * Handler to process messages sent to Rabbit queue (sale) and report status of
 * product types in history background
 * 
 * @author Carlos Alberto
 *
 */
@Component
@RabbitListener(queues = "${queue.name}")
public class SaleMessageTypeHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(SaleMessageTypeHandler.class);

    private final ObjectMapper objectMapper;

    private Map<ProductType, Integer> productTypeMap;

    public SaleMessageTypeHandler(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        productTypeMap = new HashMap<ProductType, Integer>();
    }

    @RabbitHandler
    public void process(final Object object) throws Exception {
        LOGGER.info("Received a new message to process");

        final Message message = (Message) object;
        final String payload = new String(message.getBody());
        final Class<?> messageTypeClass = Class
                .forName((String) message.getMessageProperties().getHeaders().get("__TypeId__"));

        LOGGER.info("Message type is: " + messageTypeClass.getCanonicalName());

        if (messageTypeClass.isAssignableFrom(MessageType1.class)) {
            final MessageType1 messageType1 = (MessageType1) objectMapper.readValue(payload, messageTypeClass);

            final ProductType productType = messageType1.getProductType();
            productTypeMap.put(productType, 1);

        } else if (messageTypeClass.isAssignableFrom(MessageType2.class)) {
            final MessageType2 messageType2 = (MessageType2) objectMapper.readValue(payload, messageTypeClass);

            final ProductType productType = messageType2.getProductType();
            final Integer occurrences = messageType2.getOccurrences();
            productTypeMap.put(productType, occurrences);

        } else if (messageTypeClass.isAssignableFrom(MessageType3.class)) {
            final MessageType3 messageType3 = (MessageType3) objectMapper.readValue(payload, messageTypeClass);

            final ProductType productType = messageType3.getProductType();
            final Integer occurrences = messageType3.getOccurrences();
            productTypeMap.put(productType, occurrences);
        }

        if (productTypeMap.size() < 10) {
            final Map<String, BigDecimal> sumProductMap = new HashMap<String, BigDecimal>();
            productTypeMap
                    .forEach((v1, v2) -> sumProductMap.merge(v1.getName(), v1.getValue(), (p1, p2) -> p1.add(p2)));

            System.out.println(sumProductMap);

        }
    }
}