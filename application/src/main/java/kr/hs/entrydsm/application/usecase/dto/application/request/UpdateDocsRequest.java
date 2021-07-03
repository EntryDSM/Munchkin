package kr.hs.entrydsm.application.usecase.dto.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateDocsRequest {

    @Size(max = 1600)
    private String content;
}
