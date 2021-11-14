package br.com.dwallet.exception;

import br.com.dwallet.model.dto.ErrorHandleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class DWalletExceptionHandlerTest {

    private final DWalletExceptionHandler dWalletExceptionHandler = new DWalletExceptionHandler();

    @Test
    public void should_return_response_entity_for_not_found() {
        RuntimeException runtimeException = mock(RuntimeException.class);
        WebRequest webRequest = mock(WebRequest.class);
        ResponseEntity<ErrorHandleDTO> errorHandleDTOResponseEntity = dWalletExceptionHandler.handleNotFound(runtimeException, webRequest);
        assertNotNull(errorHandleDTOResponseEntity);
        assertEquals(errorHandleDTOResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void should_return_response_entity_for_business() {
        RuntimeException runtimeException = mock(RuntimeException.class);
        WebRequest webRequest = mock(WebRequest.class);
        ResponseEntity<ErrorHandleDTO> errorHandleDTOResponseEntity = dWalletExceptionHandler.handleBusinessError(runtimeException, webRequest);
        assertNotNull(errorHandleDTOResponseEntity);
        assertEquals(errorHandleDTOResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void should_return_response_entity_for_conflict() {
        RuntimeException runtimeException = mock(RuntimeException.class);
        WebRequest webRequest = mock(WebRequest.class);
        ResponseEntity<ErrorHandleDTO> errorHandleDTOResponseEntity = dWalletExceptionHandler.handleConflict(runtimeException, webRequest);
        assertNotNull(errorHandleDTOResponseEntity);
        assertEquals(errorHandleDTOResponseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void should_return_response_entity_for_some_error() {
        RuntimeException runtimeException = mock(RuntimeException.class);
        WebRequest webRequest = mock(WebRequest.class);
        ResponseEntity<ErrorHandleDTO> errorHandleDTOResponseEntity = dWalletExceptionHandler.handleErrors(runtimeException, webRequest);
        assertNotNull(errorHandleDTOResponseEntity);
        assertEquals(errorHandleDTOResponseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}