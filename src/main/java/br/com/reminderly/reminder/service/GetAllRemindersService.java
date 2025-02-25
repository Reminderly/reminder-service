package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.enums.LogMessage;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllRemindersService {

    private static final Logger logger = LoggerFactory.getLogger(GetAllRemindersService.class);
    private static final String SERVICE_ACTION_LOG = "Get All Reminders";

    private final ReminderRepository reminderRepository;

    public Page<ReminderEntity> execute(Pageable pageable) {

        try {
            logger.info(LogMessage.SERVICE_PROCESS_START.getMessage(SERVICE_ACTION_LOG));

            logger.debug(LogMessage.RETRIEVING_REMINDER_FROM_DATABASE.getMessage());
            Page<ReminderEntity> reminders = reminderRepository.findAll(pageable);

            logger.info(LogMessage.SERVICE_PROCESS_FINISH.getMessage(SERVICE_ACTION_LOG));

            return reminders;
        } catch (Exception e) {
            logger.error(LogMessage.ERROR_PROCESSING_SERVICE_ACTION.getMessage(SERVICE_ACTION_LOG));
            throw e;
        }

    }

}
