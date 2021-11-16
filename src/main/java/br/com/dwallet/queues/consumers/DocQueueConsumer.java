package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocQueueConsumer {

    private final DocService docService;

    public DocQueueConsumer(DocService docService) {
        this.docService = docService;
    }

    @RabbitListener(queues = Queues.DOC_OPERATION_QUEUE)
    public void consume(AsyncOperationMessage asyncOperationMessage) {
        log.info("Consuming message at {}. Message: {}", Queues.DOC_OPERATION_QUEUE, asyncOperationMessage);
        docService.doProcess(asyncOperationMessage);
    }

}
