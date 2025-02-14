package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.reminderly.reminder.mapper.ReminderMapper;


@Service
@RequiredArgsConstructor
public class CreateReminderService {

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(ReminderRequest reminderRequest) {

        ReminderEntity reminderEntity = ReminderMapper.toEntity(reminderRequest);
        ReminderEntity savedReminderEntity = reminderRepository.save(reminderEntity);

        return ReminderMapper.toResponse(savedReminderEntity);

    }


}
