package br.com.dwallet.model.repository;

import br.com.dwallet.model.OperationTimeLine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationTimeLineRepository extends MongoRepository<OperationTimeLine, String> {

    List<OperationTimeLine> findByUserId(String idUser, Pageable paging);

    List<OperationTimeLine> findByUserIdAndWalletAccountId(String idUser, String idWalletAccount, Pageable paging);
}
