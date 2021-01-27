package kr.hs.entrydsm.common.context.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
@Getter
@AllArgsConstructor
public enum ErrorCode {
    APPLICATION_TYPE_UNMATCHED(403, "SCORE403-0", "Application Type is unmatched");

    private final int status;
    private final String code;
    private final String message;
}
