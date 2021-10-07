package kr.hs.entrydsm.admin.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScheduleId.class)
@Entity(name = "tbl_schedule")
public class Schedule {

    @Id
    @Column(length = 4)
    private String year;

    @Enumerated(EnumType.STRING)
    @Id
	@Column(length = 19)
    private Type type;

    @Column(nullable = false)
    private LocalDateTime date;

    public void update(String year, LocalDateTime date) {
        this.year = year;
        this.date = date;
    }

}
