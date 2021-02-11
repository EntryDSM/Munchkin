package kr.hs.entrydsm.notification.usecase.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MessagesResponse {

    List<NotificationMessageResponse> messageResponses;

}
