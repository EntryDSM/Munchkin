package kr.hs.entrydsm.application.entity;

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
    @Column(length = 7, nullable = false)
    private String code;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String information;

    @Column(length = 150, nullable = false)
    private String address;
}
