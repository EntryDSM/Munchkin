package kr.hs.entrydsm.admin.entity;

import kr.hs.entrydsm.admin.entity.enums.Type;
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
