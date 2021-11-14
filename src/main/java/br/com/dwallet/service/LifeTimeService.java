package br.com.dwallet.service;

import br.com.dwallet.model.OperationTimeLine;
import br.com.dwallet.model.dto.*;
import br.com.dwallet.service.operation.OperationTimeLineService;
import br.com.dwallet.translator.LifeTimeTranslator;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LifeTimeService {

    private final LifeTimeTranslator lifeTimeTranslator;

    private final WalletAccountService walletAccountService;

    private final OperationTimeLineService operationTimeLineService;

    public LifeTimeService(LifeTimeTranslator lifeTimeTranslator, WalletAccountService walletAccountService, OperationTimeLineService operationTimeLineService) {
        this.lifeTimeTranslator = lifeTimeTranslator;
        this.walletAccountService = walletAccountService;
        this.operationTimeLineService = operationTimeLineService;
    }

    public LifeTimeDTO getLifeTime(String idUser, Pageable paging) {
        List<OperationTimeLine> operationTimeLines = operationTimeLineService.getByIdUser(idUser, paging);
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = operationTimeLines.stream()
                .map(lifeTimeTranslator::toOperationLifeTimeDTO)
                .collect(Collectors.toList());

        int quantity = operationTimeLines.size();

        return LifeTimeDTO.builder()
                .operationQuantity(quantity)
                .operationLifeTimeDTOS(operationLifeTimeDTOS)
                .build();
    }

    public WalletAccountLifeTimeDTO getWalletAccountLifeTime(String idUser, String idWalletAccount, Pageable paging) {
        List<OperationTimeLine> operationTimeLines = operationTimeLineService.getByIdUserAndIdWalletAccount(idUser, idWalletAccount, paging);
        List<OperationLifeTimeDTO> operationLifeTimeDTOS = operationTimeLines.stream()
                .map(lifeTimeTranslator::toOperationLifeTimeDTOWithOutAccountName)
                .collect(Collectors.toList());

        WalletAccountDTO walletAccountById = walletAccountService.getWalletAccountById(idWalletAccount);
        return WalletAccountLifeTimeDTO.builder()
                .accountName(walletAccountById.getAccountName())
                .balance(walletAccountById.getBalance())
                .operationQuantity(operationLifeTimeDTOS.size())
                .operationLifeTimeDTOS(operationLifeTimeDTOS)
                .build();

    }
}
