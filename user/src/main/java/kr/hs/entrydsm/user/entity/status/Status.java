package kr.hs.entrydsm.user.entity.status;

import kr.hs.entrydsm.user.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_status")
public class Status {

    @Id
    private long receiptCode;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_code", referencedColumnName = "receipt_code")
    private User user;

    private boolean isPrintedArrived;

    private boolean isSubmit;

    private LocalDateTime submittedAt;

    @Column(columnDefinition = "char(5)")
    private String examCode;

    @ColumnDefault("0")
    @Column(nullable = false)
    private boolean isFirstRoundPass;

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public void setPrintedArrived(boolean isPrintedArrived) {
        this.isPrintedArrived = isPrintedArrived;
    }

    public void setSubmitStatus(boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public void setFirstRoundPass() {
        this.isFirstRoundPass = true;
    }

    public void finalSubmit() {
        this.isSubmit = true;
        this.submittedAt = LocalDateTime.now();
    }

}
