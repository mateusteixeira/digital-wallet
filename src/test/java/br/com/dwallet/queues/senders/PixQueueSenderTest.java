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

class PixQueueSenderTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PixQueueSender pixQueueSender;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        pixQueueSender.setRabbitTemplate(rabbitTemplate);
    }

    @Test
    public void should_send_message_and_verify() {
        AsyncOperationMessage asyncOperationMessage = AsyncOperationMessage.builder().build();
        pixQueueSender.sendMessage(asyncOperationMessage);
        verify(rabbitTemplate).convertAndSend(Queues.PIX_OPERATION_QUEUE, asyncOperationMessage);
    }

    @Test
    public void should_assert_queue_for_sender() {
        assertEquals(Queues.PIX_OPERATION_QUEUE, pixQueueSender.getQueue());
    }

}