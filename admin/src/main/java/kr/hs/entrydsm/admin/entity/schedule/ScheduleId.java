package kr.hs.entrydsm.admin.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ScheduleId implements Serializable {

    @Id
    private String year;

    @Enumerated(EnumType.STRING)
    @Id
    private Type type;

}
