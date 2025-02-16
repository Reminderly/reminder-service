package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.exception.ReminderNotFoundException;
import br.com.reminderly.reminder.mapper.ReminderMapper;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(String reminderId, ReminderRequest reminderRequest) {

        ReminderEntity reminderEntity = reminderRepository.findById(UUID.fromString(reminderId)).
                orElseThrow(() -> new ReminderNotFoundException("Reminder not found for id: " + reminderId));

        var updatedReminder = partialUpdateReminder(ReminderMapper.toEntity(reminderRequest), reminderEntity);

        return ReminderMapper.toResponse(reminderRepository.save(updatedReminder));
    }


    public ReminderEntity partialUpdateReminder(ReminderEntity reminderRequest, ReminderEntity reminderResponse) {

        Optional.ofNullable(reminderRequest.getReminderTime()).ifPresent(reminderResponse::setReminderTime);
        Optional.ofNullable(reminderRequest.getMessage()).ifPresent(reminderResponse::setMessage);
        Optional.ofNullable(reminderRequest.getUserId()).ifPresent(reminderResponse::setUserId);
        Optional.ofNullable(reminderRequest.getNotificationType()).ifPresent(reminderResponse::setNotificationType);

        return reminderResponse;
    }

}
