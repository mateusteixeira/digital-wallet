package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.TedService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TedQueueConsumer {

    private final TedService tedService;

    public TedQueueConsumer(TedService tedService) {
        this.tedService = tedService;
    }

    @RabbitListener(queues = Queues.TED_OPERATION_QUEUE)
    public void consume(AsyncOperationMessage asyncOperationMessage) {
        tedService.doProcess(asyncOperationMessage);
    }

}
