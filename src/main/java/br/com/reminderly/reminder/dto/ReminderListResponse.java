package br.com.reminderly.reminder.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReminderListResponse {
    List<ReminderResponse> remindersList;
    Integer listSize;
}
