package kr.hs.entrydsm.admin.entity.schedule;

import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
public class ScheduleId implements Serializable {

    @Id
    private String year;

    @Enumerated(EnumType.STRING)
    @Id
    private Type type;

}
