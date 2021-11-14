package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import org.springframework.stereotype.Component;

@Component("pixSender")
public class PixQueueSender extends AbstractOperationQueueSender implements OperationQueueSender {

    @Override
    public String getQueue() {
        return Queues.PIX_OPERATION_QUEUE;
    }
}
