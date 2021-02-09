package kr.hs.entrydsm.notification.domain.entity;

import kr.hs.entrydsm.notification.usecase.dto.UpdateMessageRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_notification")
public class Notification {

    @Enumerated(EnumType.STRING)
    @Id
    private Type type;

    @Column(nullable = false)
    private String content;

    public Notification update(UpdateMessageRequest messageRequest) {
        this.type=Type.valueOf(messageRequest.getType());
        this.content= messageRequest.getContent();
        return this;
    }

}
