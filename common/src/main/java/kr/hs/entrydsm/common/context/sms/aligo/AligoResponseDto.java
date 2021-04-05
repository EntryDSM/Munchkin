package kr.hs.entrydsm.common.context.sms.aligo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AligoResponseDto {
    private Integer resultCode;
    private String message;
    private Integer msgId;
    private Integer successCnt;
    private Integer errorCnt;
    private String msg_Type;
}
