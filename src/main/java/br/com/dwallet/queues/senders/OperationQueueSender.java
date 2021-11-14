package br.com.dwallet.queues.senders;

import br.com.dwallet.queues.messages.AsyncOperationMessage;

public interface OperationQueueSender {

    void sendMessage(AsyncOperationMessage asyncOperationMessage);

}
