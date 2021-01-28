package kr.hs.entrydsm.admin.domain.entity;

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
    private String year;

    @Enumerated(EnumType.STRING)
    @Id
    private Type type;

    @Column(nullable = false)
    private LocalDate date;

}
