package kr.hs.entrydsm.admin.usecase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {

    private Integer totalElements;

    private Integer totalPages;

}
