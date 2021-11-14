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

class TedQueueSenderTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private TedQueueSender tedQueueSender;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        tedQueueSender.setRabbitTemplate(rabbitTemplate);
    }

    @Test
    public void should_send_message_and_verify() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        tedQueueSender.sendMessage(asyncOperationMessage);
        verify(rabbitTemplate).convertAndSend(Queues.TED_OPERATION_QUEUE, asyncOperationMessage);
    }

    @Test
    public void should_assert_queue_for_sender() {
        assertEquals(Queues.TED_OPERATION_QUEUE, tedQueueSender.getQueue());
    }

}