package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.enums.LogMessage;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import br.com.reminderly.reminder.mapper.ReminderMapper;


@Service
@RequiredArgsConstructor
public class CreateReminderService {

    private static final Logger logger = LoggerFactory.getLogger(CreateReminderService.class);
    private static final String SERVICE_ACTION_LOG = "Create Reminder";

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(ReminderRequest reminderRequest) {
        try {
            logger.info(LogMessage.SERVICE_PROCESS_START.getMessage(SERVICE_ACTION_LOG));

            ReminderEntity reminderEntity = ReminderMapper.toEntity(reminderRequest);

            logger.debug(LogMessage.SAVING_REMINDER_IN_DATABASE.getMessage(SERVICE_ACTION_LOG));
            ReminderEntity savedReminderEntity = reminderRepository.save(reminderEntity);

            logger.info(LogMessage.SERVICE_PROCESS_FINISH.getMessage(SERVICE_ACTION_LOG));

            return ReminderMapper.toResponse(savedReminderEntity);
        } catch (Exception e) {
            logger.error(LogMessage.ERROR_PROCESSING_SERVICE_ACTION.getMessage(SERVICE_ACTION_LOG));
            throw e;
        }

    }

}
