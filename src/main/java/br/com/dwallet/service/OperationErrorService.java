package br.com.dwallet.service;

import br.com.dwallet.model.OperationError;
import br.com.dwallet.model.repository.OperationErrorRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationErrorService {

    private final OperationErrorRepository operationErrorRepository;

    public OperationErrorService(OperationErrorRepository operationErrorRepository) {
        this.operationErrorRepository = operationErrorRepository;
    }

    public List<OperationError> getOperationErrorsByUser(String idUser, Pageable paging) {
        return operationErrorRepository.findByIdUser(idUser, paging);
    }
}
