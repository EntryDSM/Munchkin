package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class UpdateDocsRequest {

    @Size(max = 1600)
    private String content;
}
