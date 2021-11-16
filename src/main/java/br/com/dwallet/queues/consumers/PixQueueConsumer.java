package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.PixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PixQueueConsumer {

    private final PixService pixService;

    public PixQueueConsumer(PixService pixService) {
        this.pixService = pixService;
    }

    @RabbitListener(queues = Queues.PIX_OPERATION_QUEUE)
    public void consume(AsyncOperationMessage asyncOperationMessage) {
        log.info("Consuming message at {}. Message: {}", Queues.PIX_OPERATION_QUEUE, asyncOperationMessage);
        pixService.doProcess(asyncOperationMessage);
    }

}
