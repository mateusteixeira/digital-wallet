package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.Queues;
import br.com.dwallet.queues.messages.AsyncOperationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class DocQueueSenderTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private DocQueueSender docQueueSender;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        docQueueSender.setRabbitTemplate(rabbitTemplate);
    }

    @Test
    public void should_send_message_and_verify() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        docQueueSender.sendMessage(asyncOperationMessage);
        verify(rabbitTemplate).convertAndSend(Queues.DOC_OPERATION_QUEUE, asyncOperationMessage);
    }

    @Test
    public void should_assert_queue_for_sender() {
        assertEquals(Queues.DOC_OPERATION_QUEUE, docQueueSender.getQueue());
    }

}