package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component("docSender")
public class DocQueueSender extends AbstractOperationQueueSender implements OperationQueueSender {

    @Override
    public String getQueue() {
        return Queues.DOC_OPERATION_QUEUE;
    }
}
