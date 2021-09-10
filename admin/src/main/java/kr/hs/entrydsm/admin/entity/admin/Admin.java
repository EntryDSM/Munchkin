package kr.hs.entrydsm.admin.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_admin")
public class Admin {

    @Id
    @Column(length = 8)
    private String id;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 5, nullable = false)
    private String name;

}
