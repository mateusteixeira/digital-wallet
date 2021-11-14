package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import org.springframework.stereotype.Component;

@Component("docSender")
public class DocQueueSender extends AbstractOperationQueueSender implements OperationQueueSender {

    @Override
    public String getQueue() {
        return Queues.DOC_OPERATION_QUEUE;
    }
}
