package kr.hs.entrydsm.admin.entity;

import kr.hs.entrydsm.admin.entity.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_admin")
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 8)
    private String id;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Permission permission;

}
