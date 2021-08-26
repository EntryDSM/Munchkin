package kr.hs.entrydsm.application.usecase.dto.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PdfResponse {

    private final byte[] pdf;

}
