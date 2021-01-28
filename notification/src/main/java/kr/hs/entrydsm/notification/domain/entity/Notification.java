package kr.hs.entrydsm.notification.domain.entity;

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

    public Notification update(Type type, String content) {
        this.type=type;
        this.content=content;
        return this;
    }

}
