package kr.hs.entrydsm.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_status")
public class Status implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "receipt_code")
    private User user;

    private boolean isPaid;

    private boolean isPrintedArrived;

    private boolean isSubmit;

    private LocalDateTime submittedAt;

    @Column(columnDefinition = "char(5)")
    private String examCode;

}
