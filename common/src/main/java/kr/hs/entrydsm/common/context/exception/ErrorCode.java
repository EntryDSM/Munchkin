package kr.hs.entrydsm.common.context.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
@Getter
@AllArgsConstructor
public enum ErrorCode {
    APPLICATION_TYPE_UNMATCHED(403, "SCORE403-0", "Application Type is unmatched"),

    INVALID_TOKEN(401, "ADMIN401-0","This token is invalid"),
    EXPIRED_TOKEN(401, "ADMIN401-1","Token is Expired"),
    ADMIN_NOT_FOUND(404, "ADMIN404-0","The account does not exist"),
    APPLICATION_NOT_FOUND(404, "ADMIN404-1","The application could not be found"),
    APPLICANT_NOT_FOUND(404, "ADMIN404-2","The applicant does not exist"),
    NOT_ACCESSIBLE(401, "ADMIN401-2","Check the token");

    private final int status;
    private final String code;
    private final String message;
}
