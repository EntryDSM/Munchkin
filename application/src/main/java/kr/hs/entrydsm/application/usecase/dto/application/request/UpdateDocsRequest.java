package kr.hs.entrydsm.application.usecase.dto.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UpdateDocsRequest {

    @Size(max = 1600)
    private String content;
}
