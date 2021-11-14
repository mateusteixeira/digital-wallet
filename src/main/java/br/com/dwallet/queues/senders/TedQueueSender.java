package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import org.springframework.stereotype.Component;

@Component("tedSender")
public class TedQueueSender extends AbstractOperationQueueSender implements OperationQueueSender {

    @Override
    public String getQueue() {
        return Queues.TED_OPERATION_QUEUE;
    }
}
