package br.com.reminderly.reminder.mapper;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ReminderMapper {

    private static final Logger logger = LoggerFactory.getLogger(ReminderMapper.class);

    private ReminderMapper(){};

    public static ReminderResponse toResponse(ReminderEntity entity) {
        logger.debug("Converting Reminder Entity to Reminder Request.");
        ReminderResponse response = new ReminderResponse();
        BeanUtils.copyProperties(entity, response);
        response.setReminderId(entity.getId());
        return response;
    }

    public static ReminderEntity toEntity(ReminderRequest request) {
        logger.debug("Converting Reminder Request to Reminder Entity.");
        ReminderEntity entity = new ReminderEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
