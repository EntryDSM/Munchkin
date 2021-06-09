package kr.hs.entrydsm.common.context.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_TOKEN(401, "COMMON401-0", "Invalid Token"),
    UNAUTHENTICATED(401, "COMMON401-1", "UnAuthenticated"),
    NOT_FOUND(404, "COMMON404-0", "Not Found"),

    INVALID_AUTH_CODE(401, "USER401-0", "Invalid Auth Code"),
    USER_NOT_FOUND(404, "USER404-0", "User Not Found"),
    USER_ALREADY_EXISTS(409, "USER409-0", "User Already Exists"),
    AUTH_CODE_ALREADY_VERIFIED(409, "USER409-1", "Auth Code Already Verified"),
    AUTH_CODE_REQUEST_OVER_LIMIT(429, "USER429-0", "Auth Code Request Over Limit"),

    APPLICATION_TYPE_UNMATCHED(403, "SCORE403-0", "Application Type is unmatched"),

    INVALID_ADMIN_TOKEN(401, "ADMIN401-0","This token is invalid"),
    ADMIN_NOT_FOUND(404, "ADMIN404-0","The account does not exist"),
    NOT_ACCESSIBLE(403, "ADMIN401-2","Check the token"),
    ALREADY_EXIST_ADMIN_ID(409, "ADMIN409-0", "Already admin id exist"),

    TYPE_NOT_FOUND(404,"NOTIFICATION404-0","The corresponding message type does not exist"),
    NOT_AUTHORIZED(401, "NOTIFICATION401-0","Check the authority"),

    APPLICATION_NOT_FOUND(404, "APPLICATION404-0","The application could not be found"),
    SCHOOL_NOT_FOUND(404, "APPLICATION404-1", "The school could not be found"),
    FILE_IS_EMPTY(400, "APPLICATION400-1", "The file could not be found"),
    FINAL_SUBMIT_REQUIRED(406, "APPLICATION406-0", "Final submit required");

    private final int status;
    private final String code;
    private final String message;
}
