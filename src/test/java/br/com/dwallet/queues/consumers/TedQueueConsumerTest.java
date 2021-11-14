package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.TedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class TedQueueConsumerTest {

    @Mock
    private TedService tedService;

    @InjectMocks
    private TedQueueConsumer tedQueueConsumer;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_call_service() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        tedQueueConsumer.consume(asyncOperationMessage);
        verify(tedService).doProcess(asyncOperationMessage);
    }


}