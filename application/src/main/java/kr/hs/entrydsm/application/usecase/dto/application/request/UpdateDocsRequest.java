package kr.hs.entrydsm.application.usecase.dto.application.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UpdateDocsRequest {

    @Size(max = 1600)
    private final String content;
}
