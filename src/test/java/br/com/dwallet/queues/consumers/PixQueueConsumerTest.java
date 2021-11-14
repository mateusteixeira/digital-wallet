package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.PixService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class PixQueueConsumerTest {

    @Mock
    private PixService pixService;

    @InjectMocks
    private PixQueueConsumer pixQueueConsumer;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_call_service() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        pixQueueConsumer.consume(asyncOperationMessage);
        verify(pixService).doProcess(asyncOperationMessage);
    }


}