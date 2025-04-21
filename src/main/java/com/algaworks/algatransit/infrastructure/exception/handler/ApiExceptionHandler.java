package com.algaworks.algatransit.infrastructure.exception.handler;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.domain.exception.BusinessException;
import com.algaworks.algatransit.domain.exception.ResourceNotFoundException;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("http://algatransito.com/errors/invalid-fields"));
        problemDetail.setTitle(messageSource.getMessage("invalid-fields", null, LocaleContextHolder.getLocale()));

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
            .stream()
            .collect(Collectors.toMap(e -> ((FieldError) e).getField(), e -> messageSource.getMessage(e, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields", fields);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("http://algatransito.com/errors/resources-not-found"));
        problemDetail.setTitle(messageSource.getMessage("rule-errors", null, LocaleContextHolder.getLocale()));
        problemDetail.setDetail(messageSource.getMessage(ex.getMessage(), ex.getArgs(), LocaleContextHolder.getLocale()));
        return super.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExistsException(AlreadyExistsException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setType(URI.create("http://algatransito.com/errors/using-resources"));
        problemDetail.setTitle(messageSource.getMessage("rule-errors", null, LocaleContextHolder.getLocale()));
        problemDetail.setDetail(messageSource.getMessage(ex.getMessage(), ex.getArgs(), LocaleContextHolder.getLocale()));
        return super.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.CONFLICT, null);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.PRECONDITION_FAILED);
        problemDetail.setType(URI.create("http://algatransito.com/errors/business-rules"));
        problemDetail.setTitle(messageSource.getMessage("rule-errors", null, LocaleContextHolder.getLocale()));
        problemDetail.setDetail(messageSource.getMessage(ex.getMessage(), ex.getArgs(), LocaleContextHolder.getLocale()));
        return super.handleExceptionInternal(ex, problemDetail, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, null);
    }
}