package br.com.dwallet.service;

import br.com.dwallet.model.OperationError;
import br.com.dwallet.model.OperationErrorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OperationErrorServiceTest {

    @Mock
    private OperationErrorRepository operationErrorRepository;

    @InjectMocks
    private OperationErrorService operationErrorService;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_get_errors_on_repository() {
        when(operationErrorRepository.findByIdUser("123", Pageable.unpaged())).thenReturn(List.of(OperationError.builder().build()));
        List<OperationError> operationErrorsByUser = operationErrorService.getOperationErrorsByUser("123", Pageable.unpaged());
        assertNotNull(operationErrorsByUser);
        assertEquals(1, operationErrorsByUser.size());

        verify(operationErrorRepository).findByIdUser("123", Pageable.unpaged());
    }

}