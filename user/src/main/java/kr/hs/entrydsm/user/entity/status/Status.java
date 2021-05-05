package kr.hs.entrydsm.user.entity.status;

import kr.hs.entrydsm.user.entity.user.User;
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
@Entity(name = "tbl_status")
public class Status {

    @Id
    private long receiptCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_code")
    private User user;

    private boolean isPaid;

    private boolean isPrintedArrived;

    private boolean isSubmit;

    private LocalDateTime submittedAt;

    @Column(columnDefinition = "char(5)")
    private String examCode;

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public void setPrintedArrived(boolean isPrintedArrived) {
        this.isPrintedArrived = isPrintedArrived;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setSubmit(boolean isSubmit) {
        this.isSubmit = isSubmit;

        if (isSubmit) submittedAt = LocalDateTime.now();
        else submittedAt = null;
    }

}
