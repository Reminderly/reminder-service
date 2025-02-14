package br.com.reminderly.reminder.dto;

import br.com.reminderly.reminder.enums.NotificationType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReminderResponse {
    private UUID reminderId;
    private UUID userId;
    private Instant createdAt;
    private String message;
    private Instant reminderTime;
    private NotificationType notificationType;
}
