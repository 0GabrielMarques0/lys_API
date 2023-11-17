package br.com.lys.infra.exceptions;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage noResultExceptionHandler(NoResultException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException() {
        return new ResponseEntity<>(new ErrorMessage("Entity not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handleValidationError(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return new ResponseEntity<>(erros.stream().map(DadosErroValidacao::new).collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handleJSONError(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ErrorMessage("Bad input format"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentials() {
        return new ResponseEntity<>(new ErrorMessage("Invalid credentials"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException() {
        return new ResponseEntity<>(new ErrorMessage("Authentication failed"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDenied() {
        return new ResponseEntity<>(new ErrorMessage("Access denied"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericError(Exception ex) {
        logger.error("Internal server error", ex);
        return new ResponseEntity<>(new ErrorMessage("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ErrorMessage(String message) {

    }

    @Getter
    public static class DadosErroValidacao {
        private final String campo;
        private final String mensagem;

        public DadosErroValidacao(FieldError erro) {
            this.campo = erro.getField();
            this.mensagem = erro.getDefaultMessage();
        }

    }
}