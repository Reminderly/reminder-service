package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class GetRemindersService {

    private final ReminderRepository reminderRepository;

    public ReminderRequest execute(ReminderRequest reminderRequest){

        ReminderEntity reminderEntity = new ReminderEntity();
        BeanUtils.copyProperties(reminderRequest, reminderEntity);

        reminderRepository.save(reminderEntity);

        return reminderRequest;
    }
}
