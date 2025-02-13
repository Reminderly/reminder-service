package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderListResponse;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.reminderly.reminder.mapper.ReminderMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllRemindersService{

    private final ReminderRepository reminderRepository;

    public ReminderListResponse execute() {
        List<ReminderResponse> reminders = reminderRepository.findAll()
                .stream()
                .map(ReminderMapper::toResponse)
                .collect(Collectors.toList());
        return new ReminderListResponse(reminders, reminders.size());
    }

}
