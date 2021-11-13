package br.com.dwallet.translator;

import br.com.dwallet.model.WalletAccount;
import br.com.dwallet.model.dto.WalletAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class WalletAccountTranslator {

    public WalletAccountDTO toDTO(WalletAccount walletAccount) {
        return WalletAccountDTO.builder()
                .id(walletAccount.getId())
                .accountName(walletAccount.getAccountName())
                .accountNumber(walletAccount.getAccountNumber())
                .balance(walletAccount.getBalance())
                .idUser(walletAccount.getUser().getId())
                .build();
    }

    public WalletAccount fromDTO(WalletAccountDTO walletAccountDTO) {
        return WalletAccount.builder()
                .id(walletAccountDTO.getId())
                .accountName(walletAccountDTO.getAccountName())
                .accountNumber(walletAccountDTO.getAccountNumber())
                .balance(walletAccountDTO.getBalance())
                .build();
    }

}
