package kr.hs.entrydsm.application.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_school")
public class School {

    @Id
    @Column(length = 7)
    private String code;

    @Column(length = 30)
    private String name;

    @Column(length = 50)
    private String information;

    @Column(length = 150)
    private String address;
}
