package br.com.dwallet.exception;

import br.com.dwallet.model.dto.ErrorHandleDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DWalletExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, WalletAccountNotFoundException.class})
    protected ResponseEntity<ErrorHandleDTO> handleNotFound(RuntimeException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, ErrorHandleDTO.builder().message(String.format("Resource Not Found: %s", ex.getMessage())).build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class, WalletAccountAlreadyExistsException.class})
    protected ResponseEntity<ErrorHandleDTO> handleConflict(RuntimeException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, ErrorHandleDTO.builder().message(String.format("Resource Already Exists: %s", ex.getMessage())).build(),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    protected ResponseEntity<ErrorHandleDTO> handleExceptionInternal(Exception ex, @Nullable ErrorHandleDTO body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, 0);
        }
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(body);
    }
}
