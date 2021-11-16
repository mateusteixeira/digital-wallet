package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.TedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TedQueueConsumer {

    private final TedService tedService;

    public TedQueueConsumer(TedService tedService) {
        this.tedService = tedService;
    }

    @RabbitListener(queues = Queues.TED_OPERATION_QUEUE)
    public void consume(AsyncOperationMessage asyncOperationMessage) {
        log.info("Consuming message at {}. Message: {}", Queues.TED_OPERATION_QUEUE, asyncOperationMessage);
        tedService.doProcess(asyncOperationMessage);
    }

}
