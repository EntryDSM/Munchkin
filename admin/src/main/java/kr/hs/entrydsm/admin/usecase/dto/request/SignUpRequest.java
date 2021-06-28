package kr.hs.entrydsm.admin.usecase.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @Length(min = 8, max = 8)
    private String id;

    private String password;

    @Length(min = 5, max = 5)
    private String name;

}
