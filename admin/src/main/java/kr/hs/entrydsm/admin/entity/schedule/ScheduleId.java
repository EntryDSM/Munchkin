package kr.hs.entrydsm.admin.entity.schedule;

import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
public class ScheduleId implements Serializable {

    @Id
    private String year;

    @Id
    private Type type;

}
