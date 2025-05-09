package com.greedy.leaderboard.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResultResponse methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return new ErrorResultResponse("잘못된 요청 값", 400, getErrorMessageString(e), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidGameNameException.class)
    public ErrorResultResponse invalidGameNameException(InvalidGameNameException e, HttpServletRequest request) {
        return new ErrorResultResponse(e.getTitle(), 400, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotFoundUserException.class)
    public ErrorResultResponse notFoundUserException(NotFoundUserException e, HttpServletRequest request) {
        return new ErrorResultResponse(e.getTitle(), 400, e.getMessage(), request.getRequestURI());
    }

    private static String getErrorMessageString(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> "[" + error.getField() +"] " + error.getDefaultMessage())
                .toList();
        return String.join(", ", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DuplicateNicknameException.class)
    public ErrorResultResponse runtimeException(DuplicateNicknameException e, HttpServletRequest request) {
        return new ErrorResultResponse(e.getTitle(), 400, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ErrorResultResponse httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        return new ErrorResultResponse("잘못된 요청 값", 400, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ErrorResultResponse runtimeException(RuntimeException e, HttpServletRequest request) {
        System.out.println(e.getClass().getName());
        return new ErrorResultResponse("알 수 없는 요류 (관리자 문의)", 400, e.getMessage(), request.getRequestURI());
    }
}
