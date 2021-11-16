package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractOperationQueueSender {

    protected RabbitTemplate rabbitTemplate;

    public void sendMessage(AsyncOperationMessage asyncOperationMessage) {
        log.info("Sending message {} to queue {}", asyncOperationMessage, getQueue());
        rabbitTemplate.convertAndSend(getQueue(), asyncOperationMessage);
    }

    protected abstract String getQueue();

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
