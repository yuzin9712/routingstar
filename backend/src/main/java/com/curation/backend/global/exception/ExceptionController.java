package com.curation.backend.global.exception;

import com.curation.backend.global.dto.ExceptionResponseDto;
import com.curation.backend.global.service.ResponseGenerateService;
import com.curation.backend.route.exception.NoRouteException;
import com.curation.backend.user.exception.NoUserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionController {

    private final ResponseGenerateService responseGenerateService;
    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<ExceptionResponseDto> handleEmptyResultDataAccess(EmptyResultDataAccessException e) {
        logger.error("[EmptyResultDataAccessException] ", e);

        String message = "잘못된 데이터 접근입니다.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(status, message);

        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, status);
    }

    @ExceptionHandler(NoUserException.class)
    protected ResponseEntity<ExceptionResponseDto> handleNoUser(NoUserException e) {
        logger.error("[No User Exception] ", e);

        String message = "없는 사용자입니다.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(status, message);

        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, status);
    }

    @ExceptionHandler(NoRouteException.class)
    public ResponseEntity<ExceptionResponseDto> noRouteHandler(NoRouteException e) {
        logger.error("[No Route Exception] ", e);
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "없는 루트입니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }
}
