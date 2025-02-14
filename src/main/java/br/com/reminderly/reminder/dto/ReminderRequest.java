package br.com.reminderly.reminder.dto;

import br.com.reminderly.reminder.enums.NotificationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReminderRequest {
    @NotNull
    private UUID userId;
    @NotNull
    @Size(max = 999)
    private String message;
    @NotNull
    private Instant reminderTime;
    @NotNull
    private NotificationType notificationType;
}
