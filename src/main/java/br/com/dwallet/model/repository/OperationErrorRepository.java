package br.com.dwallet.model.repository;

import br.com.dwallet.model.OperationError;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationErrorRepository extends MongoRepository<OperationError, String> {

    List<OperationError> findByIdUser(String idUser, Pageable paging);
}
