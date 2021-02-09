package kr.hs.entrydsm.notification.usecase.dto;

import kr.hs.entrydsm.notification.domain.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticationMessageResponse {

    private Type type;

    private String content;

}
