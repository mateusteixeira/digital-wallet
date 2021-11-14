package br.com.dwallet.queues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueuesTest {

    private static final String TED_OPERATION_QUEUE = "TED_OPERATION";
    private static final String PIX_OPERATION_QUEUE = "PIX_OPERATION";
    private static final String DOC_OPERATION_QUEUE = "DOC_OPERATION";

    @Test
    public void should_assert_ted_queue_name() {
        assertEquals(TED_OPERATION_QUEUE, Queues.TED_OPERATION_QUEUE);
    }

    @Test
    public void should_assert_pix_queue_name() {
        assertEquals(PIX_OPERATION_QUEUE, Queues.PIX_OPERATION_QUEUE);
    }

    @Test
    public void should_assert_doc_queue_name() {
        assertEquals(DOC_OPERATION_QUEUE, Queues.DOC_OPERATION_QUEUE);
    }

}