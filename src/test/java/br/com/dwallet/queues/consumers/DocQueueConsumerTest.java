package br.com.dwallet.queues.consumers;

import br.com.dwallet.queues.messages.AsyncOperationMessage;
import br.com.dwallet.service.operation.DocService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DocQueueConsumerTest {

    @Mock
    private DocService docService;

    @InjectMocks
    private DocQueueConsumer docQueueConsumer;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_call_service() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        docQueueConsumer.consume(asyncOperationMessage);
        verify(docService).doProcess(asyncOperationMessage);
    }

}