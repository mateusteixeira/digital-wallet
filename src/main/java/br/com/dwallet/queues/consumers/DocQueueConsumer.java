package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.DocService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DocQueueConsumer {

    private final DocService docService;

    public DocQueueConsumer(DocService docService) {
        this.docService = docService;
    }

    @RabbitListener(queues = Queues.DOC_OPERATION_QUEUE)
    public void consume(AsyncOperationMessage asyncOperationMessage) {
        docService.doProcess(asyncOperationMessage);
    }

}
