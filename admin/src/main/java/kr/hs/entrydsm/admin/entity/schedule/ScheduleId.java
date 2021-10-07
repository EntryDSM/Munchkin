package kr.hs.entrydsm.admin.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleId implements Serializable {

    @Id
    private String year;

    @Enumerated(EnumType.STRING)
	@Column(length = 19)
    @Id
    private Type type;

}
