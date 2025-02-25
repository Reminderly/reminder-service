package br.com.reminderly.reminder.dto;

import br.com.reminderly.reminder.enums.NotificationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReminderRequest {
    @NotNull
    @Size(max = 999)
    private String message;
    @Size(max = 100)
    @NotNull
    private String title;
    @NotNull
    private String sendingTo;
    @NotNull
    private Instant reminderTime;
    @NotNull
    private NotificationType notificationType;
}
