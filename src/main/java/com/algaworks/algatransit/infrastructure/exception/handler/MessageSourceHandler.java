package com.algaworks.algatransit.infrastructure.exception.handler;

import static java.util.stream.Collectors.toMap;

import com.algaworks.algatransit.infrastructure.exception.BaseException;
import java.net.URI;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
@RequiredArgsConstructor
public class MessageSourceHandler {
    private final MessageSource messageSource;

    @Value("${alga-transit.api-url-help}")
    private String apiUrlHelp;

    public ResponseEntity<Object> buildResponseEntityException(BaseException ex, HttpStatus httpStatus){
        ProblemDetail problemDetail = buildProblemDetail(httpStatus, "problem-request");
        problemDetail.setDetail(getMessage(ex.getMessage(), ex.getArgs()));
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    public ResponseEntity<Object> buildResponseEntityException(MethodArgumentNotValidException ex, HttpStatus httpStatus){
        Map<String, String> fields = ex.getBindingResult().getAllErrors()
            .stream()
            .collect(toMap(e -> ((FieldError) e).getField(), e -> messageSource.getMessage(e, LocaleContextHolder.getLocale())));

        ProblemDetail problemDetail = buildProblemDetail(httpStatus, "invalid-fields");
        problemDetail.setProperty("fields", fields);
        return ResponseEntity.status(httpStatus).body(problemDetail);
    }

    private ProblemDetail buildProblemDetail(HttpStatus httpStatus, String title){
        ProblemDetail problemDetail = buildProblemDetail(httpStatus);
        problemDetail.setTitle(getMessage(title));
        return problemDetail;
    }

    private ProblemDetail buildProblemDetail(HttpStatus httpStatus){
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setType(URI.create(apiUrlHelp));
        return problemDetail;
    }

    private String getMessage(String msg){
        return getMessage(msg, new String[]{});
    }

    private String getMessage(String msg, String...args){
        return messageSource.getMessage(msg, args, LocaleContextHolder.getLocale());
    }
}