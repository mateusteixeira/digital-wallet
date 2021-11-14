package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractOperationQueueSender {

    protected RabbitTemplate rabbitTemplate;

    public void sendMessage(AsyncOperationMessage asyncOperationMessage) {
        rabbitTemplate.convertAndSend(getQueue(), asyncOperationMessage);
    }

    protected abstract String getQueue();

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
