package br.com.dwallet.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletAccountRepository extends MongoRepository<WalletAccount, String> {

    WalletAccount findByAccountNumber(Long accountNumber);
}
