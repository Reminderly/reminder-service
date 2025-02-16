package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.enums.LogMessage;
import br.com.reminderly.reminder.exception.ReminderNotFoundException;
import br.com.reminderly.reminder.mapper.ReminderMapper;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteReminderService {

    private static final Logger logger = LoggerFactory.getLogger(DeleteReminderService.class);
    private static final String SERVICE_ACTION_LOG = "Delete Reminder";

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(String reminderId) {
        try {
            logger.info(LogMessage.SERVICE_PROCESS_START.getMessage(SERVICE_ACTION_LOG));

            logger.debug(LogMessage.RETRIEVING_REMINDER_FROM_DATABASE.getMessage());
            ReminderEntity reminderEntity = reminderRepository.findById(UUID.fromString(reminderId)).
                    orElseThrow(() -> new ReminderNotFoundException(LogMessage.REMINDER_NOT_FOUND_BY_ID.getMessage(reminderId)));

            logger.debug(LogMessage.DELETING_REMINDER_FROM_DATABASE.getMessage());
            reminderRepository.deleteById(reminderEntity.getId());

            logger.info(LogMessage.SERVICE_PROCESS_FINISH.getMessage(SERVICE_ACTION_LOG));

            return ReminderMapper.toResponse(reminderEntity);
        } catch (Exception e) {
            logger.error(LogMessage.ERROR_PROCESSING_SERVICE_ACTION.getMessage(SERVICE_ACTION_LOG));
            throw e;
        }
    }


}
