package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderRequest;
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

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateReminderService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateReminderService.class);
    private static final String SERVICE_ACTION_LOG = "Update Reminder";

    private final ReminderRepository reminderRepository;

    public ReminderResponse execute(String reminderId, ReminderRequest reminderRequest) {

        try {
            logger.info(LogMessage.SERVICE_PROCESS_START.getMessage(SERVICE_ACTION_LOG));

            logger.debug(LogMessage.RETRIEVING_REMINDER_FROM_DATABASE.getMessage());
            ReminderEntity reminderEntity = reminderRepository.findById(UUID.fromString(reminderId)).
                    orElseThrow(() -> new ReminderNotFoundException(LogMessage.REMINDER_NOT_FOUND_BY_ID.getMessage(reminderId)));

            var updatedReminder = partialUpdateReminder(ReminderMapper.toEntity(reminderRequest), reminderEntity);

            logger.debug(LogMessage.SAVING_REMINDER_IN_DATABASE.getMessage(SERVICE_ACTION_LOG));
            var reminderResponse = reminderRepository.save(updatedReminder);

            logger.info(LogMessage.SERVICE_PROCESS_FINISH.getMessage(SERVICE_ACTION_LOG));

            return ReminderMapper.toResponse(reminderResponse);
        } catch (Exception e) {
            logger.error(LogMessage.ERROR_PROCESSING_SERVICE_ACTION.getMessage(SERVICE_ACTION_LOG));
            throw e;
        }

    }

    public ReminderEntity partialUpdateReminder(ReminderEntity reminderRequest, ReminderEntity reminderResponse) {
        logger.debug(LogMessage.UPDATING_REMINDER_ENTITY.getMessage());

        Optional.ofNullable(reminderRequest.getReminderTime()).ifPresent(reminderResponse::setReminderTime);
        Optional.ofNullable(reminderRequest.getMessage()).ifPresent(reminderResponse::setMessage);
        Optional.ofNullable(reminderRequest.getTitle()).ifPresent(reminderResponse::setTitle);
        Optional.ofNullable(reminderRequest.getSendingTo()).ifPresent(reminderResponse::setSendingTo);
        Optional.ofNullable(reminderRequest.getNotificationType()).ifPresent(reminderResponse::setNotificationType);

        return reminderResponse;
    }

}
