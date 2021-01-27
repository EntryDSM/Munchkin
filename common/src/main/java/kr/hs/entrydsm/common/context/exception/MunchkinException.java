package kr.hs.entrydsm.common.context.exception;

import lombok.Getter;

@Getter
public class MunchkinException extends RuntimeException{
    private final ErrorCode errorCode;

    public MunchkinException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
