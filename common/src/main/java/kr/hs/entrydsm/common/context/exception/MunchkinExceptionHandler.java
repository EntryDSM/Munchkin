package kr.hs.entrydsm.common.context.exception;

import java.util.Arrays;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorResponse> handleNotReadableException(final HttpMessageNotReadableException e) {
    	final ErrorCode errorCode = ErrorCode.REQUEST_BODY_IS_NULL;
		return new ResponseEntity<>(new ErrorResponse(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()),
				HttpStatus.valueOf(errorCode.getStatus()));
	}

    @ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException e) {
    	e.printStackTrace();
		return new ResponseEntity<>(new ErrorResponse(500, "Internal Server Error", Arrays.toString(e.getStackTrace())),
				HttpStatus.valueOf(500));
	}

}
