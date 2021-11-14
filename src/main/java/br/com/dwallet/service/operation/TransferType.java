package br.com.dwallet.service.operation;

import java.util.Arrays;
import java.util.Optional;

public enum TransferType {

    PIX("pixSender"),
    TED("tedSender"),
    DOC("docSender");

    TransferType(String strategyName) {
        this.strategyName = strategyName;
    }

    private final String strategyName;

    public static Optional<TransferType> getStrategyNameFor(String type) {
        return Arrays.stream(values())
                .filter(transferType -> transferType.name().equals(type))
                .findFirst();
    }

    public String getStrategyName() {
        return strategyName;
    }
}
