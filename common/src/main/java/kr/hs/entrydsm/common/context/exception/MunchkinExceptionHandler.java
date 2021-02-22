package kr.hs.entrydsm.common.context.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MunchkinExceptionHandler {

    @ExceptionHandler(MunchkinException.class)
    protected ResponseEntity<ErrorResponse> handleMunchkinException(final MunchkinException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus()));
    }

}
