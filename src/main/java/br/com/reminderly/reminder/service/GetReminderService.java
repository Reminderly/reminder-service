package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.exception.ReminderNotFoundException;
import br.com.reminderly.reminder.mapper.ReminderMapper;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(String reminderId) {

        ReminderEntity reminderEntity = reminderRepository.findById(UUID.fromString(reminderId)).
                orElseThrow(() -> new ReminderNotFoundException("Reminder not found for id: " + reminderId));

        return ReminderMapper.toResponse(reminderEntity);
    }

}
