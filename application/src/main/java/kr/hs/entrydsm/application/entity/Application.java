package kr.hs.entrydsm.application.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Application extends BaseTimeEntity {

    @Id
    private Long receiptCode;

    public abstract boolean isGraduation();
}
