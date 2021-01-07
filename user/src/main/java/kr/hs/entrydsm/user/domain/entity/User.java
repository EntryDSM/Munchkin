package kr.hs.entrydsm.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long receiptCode;
    private String telephone_number;
    private String name;
    private String password;
}
