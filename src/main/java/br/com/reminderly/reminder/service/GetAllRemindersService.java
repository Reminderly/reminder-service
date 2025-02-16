package br.com.reminderly.reminder.service;

import br.com.reminderly.reminder.dto.ReminderListResponse;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.enums.LogMessage;
import br.com.reminderly.reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import br.com.reminderly.reminder.mapper.ReminderMapper;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllRemindersService {

    private static final Logger logger = LoggerFactory.getLogger(GetAllRemindersService.class);
    private static final String SERVICE_ACTION_LOG = "Get All Reminders";

    private final ReminderRepository reminderRepository;

    public ReminderListResponse execute() {

        try {
            logger.info(LogMessage.SERVICE_PROCESS_START.getMessage(SERVICE_ACTION_LOG));

            logger.debug(LogMessage.RETRIEVING_REMINDER_FROM_DATABASE.getMessage());
            List<ReminderResponse> reminders = reminderRepository.findAll()
                    .stream()
                    .map(ReminderMapper::toResponse)
                    .collect(Collectors.toList());

            logger.info(LogMessage.SERVICE_PROCESS_FINISH.getMessage(SERVICE_ACTION_LOG));

            return new ReminderListResponse(reminders, reminders.size());
        } catch (Exception e) {
            logger.error(LogMessage.ERROR_PROCESSING_SERVICE_ACTION.getMessage(SERVICE_ACTION_LOG));
            throw e;
        }

    }

}
