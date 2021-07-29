package com.curation.backend.user.web;

import com.curation.backend.global.dto.ExceptionResponseDto;
import com.curation.backend.global.dto.SuccessResponseDto;
import com.curation.backend.global.service.ResponseGenerateService;
import com.curation.backend.route.exception.NoRouteException;
import com.curation.backend.user.exception.NoUserException;
import com.curation.backend.user.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/reaction")
@RestController
public class ReactionController {

    private final ReactionService reactionService;
    private final ResponseGenerateService responseGenerateService;

    @GetMapping("/like/{userId}/{routeId}")
    public ResponseEntity<SuccessResponseDto> setLikeReaction(@PathVariable Long userId, @PathVariable Long routeId) throws NoUserException, NoRouteException {

        String message = reactionService.setLike(userId, routeId);

        SuccessResponseDto successResponseDto = responseGenerateService.generateSuccessResponse(message);

        return new ResponseEntity<SuccessResponseDto>(successResponseDto, HttpStatus.OK);
    }

    @ExceptionHandler(NoUserException.class)
    public ResponseEntity<ExceptionResponseDto> noUserHandler() {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "없는 사용자입니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }

    @ExceptionHandler(NoRouteException.class)
    public ResponseEntity<ExceptionResponseDto> noRouteHandler() {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        String message = "없는 루트입니다.";

        ExceptionResponseDto exceptionResponseDto = responseGenerateService.generateExceptionResponse(httpStatus, message);
        return new ResponseEntity<ExceptionResponseDto>(exceptionResponseDto, httpStatus);
    }

}
