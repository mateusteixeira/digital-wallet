package br.com.dwallet.service.operation.type;

import java.util.Arrays;
import java.util.Optional;

public enum TransferType {

    PIX("pixSender"),
    TED("tedSender"),
    DOC("docSender");

    private final String strategyName;

    TransferType(String strategyName) {
        this.strategyName = strategyName;
    }

    public static Optional<TransferType> getStrategyNameFor(String type) {
        return Arrays.stream(values())
                .filter(transferType -> transferType.name().equals(type))
                .findFirst();
    }

    public String getStrategyName() {
        return strategyName;
    }
}
