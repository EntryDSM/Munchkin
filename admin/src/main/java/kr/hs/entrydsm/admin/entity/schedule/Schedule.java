package kr.hs.entrydsm.admin.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Type type;

    @Column(nullable = false)
    private LocalDate date;

    public void update(String year, LocalDate date) {
        this.year = year;
        this.date = date;
    }

}
