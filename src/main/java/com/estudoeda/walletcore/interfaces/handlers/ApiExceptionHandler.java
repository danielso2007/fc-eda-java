package com.estudoeda.walletcore.interfaces.handlers;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.estudoeda.walletcore.application.exception.AccountNotFoundException;
import com.estudoeda.walletcore.application.exception.BusinessException;
import com.estudoeda.walletcore.application.exception.ClientNotFoundException;
import com.estudoeda.walletcore.application.exception.DomainException;
import com.estudoeda.walletcore.infrastructure.exceptions.InternalErrorException;
import com.estudoeda.walletcore.infrastructure.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings({ "PMD.GuardLogStatement" })
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    // --- Tratamento de Erros 400 (Bad Request) ---

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValidExceptionHandler(
            final MethodArgumentNotValidException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Argumento do método não válido");
        problem.setStatus(HttpStatus.BAD_REQUEST.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomain(final DomainException ex, final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de domínio"); // Título ajustado para refletir a exceção
        problem.setStatus(HttpStatus.BAD_REQUEST.value()); // Status ajustado para BAD_REQUEST
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    // --- Tratamento de Erros 404 (Not Found) ---

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> notFoundExceptionHandler(final NotFoundException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Entidade não encontrada");
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ProblemDetail> accountNotFoundExceptionHandler(final AccountNotFoundException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Conta não encontrada");
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(final ClientNotFoundException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Cliente não encontrado"); // Título ajustado
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> httpMessageNotReadableHandler(final HttpMessageNotReadableException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Corpo da requisição inválido ou campo não reconhecido"); // Título ajustado
        problem.setStatus(HttpStatus.NOT_FOUND.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    // --- Tratamento de Erros 409 (Conflict) ---

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> businessExceptionHandler(final BusinessException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de negócio");
        problem.setStatus(HttpStatus.CONFLICT.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    // --- Tratamento de Erros 502 (Bad Gateway) ---

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ProblemDetail> internalErrorExceptionHandler(final InternalErrorException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro interno");
        problem.setStatus(HttpStatus.BAD_GATEWAY.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    // --- Tratamento de Erros 500 (Internal Server Error) ---

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ProblemDetail> dataAccessResourceFailureException(
            final DataAccessResourceFailureException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de comumicação com o database");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ProblemDetail> cannotCreateTransactionException(final CannotCreateTransactionException ex,
            final HttpServletRequest request) {
        logger.error(ex.getMessage(), ex);
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro de comumicação com o database");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> exceptionHandler(final Exception ex, final HttpServletRequest request) {
        logger.error("Erro interno não identificado: " + ex.getMessage(), ex); // Melhor log
        final ProblemDetail problem = new ProblemDetail();
        problem.setType(request.getRequestURL().toString());
        problem.setTitle("Erro interno não identificado");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setDetail(ex.getMessage());
        problem.setInstance(request.getRequestURI());
        problem.setTimestamp(Instant.now());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}