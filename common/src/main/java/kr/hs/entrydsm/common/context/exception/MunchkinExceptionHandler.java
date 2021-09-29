package kr.hs.entrydsm.common.context.exception;

import java.util.Arrays;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;

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

//    @ExceptionHandler(RuntimeException.class)
//	protected ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException e) {
//		return new ResponseEntity<>(new ErrorResponse(500, "Internal Server Error", Arrays.toString(e.getStackTrace())),
//				HttpStatus.valueOf(500));
//	}

	@ExceptionHandler(SizeLimitExceededException.class)
	protected ResponseEntity<ErrorResponse> handleSizeLimitExceededException(SizeLimitExceededException e) {
    	return new ResponseEntity<>(new ErrorResponse(400, "APPLICATION400-5", "File size too big."),
				HttpStatus.BAD_REQUEST);
	}

}
