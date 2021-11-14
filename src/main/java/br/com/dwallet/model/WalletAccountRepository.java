package br.com.dwallet.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletAccountRepository extends MongoRepository<WalletAccount, String> {

    WalletAccount findByAccountNumber(Long accountNumber);

    WalletAccount findByIdAndUserId(String idWalletAccount, String idUser);

    List<WalletAccount> findByUserId(String idUser);
}
