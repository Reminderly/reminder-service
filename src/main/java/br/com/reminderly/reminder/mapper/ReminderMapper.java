package br.com.reminderly.reminder.mapper;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import org.springframework.beans.BeanUtils;

public class ReminderMapper {

    private ReminderMapper(){};

    public static ReminderResponse toResponse(ReminderEntity entity) {
        ReminderResponse response = new ReminderResponse();
        BeanUtils.copyProperties(entity, response);
        response.setReminderId(entity.getId());
        return response;
    }

    public static ReminderEntity toEntity(ReminderRequest request) {
        ReminderEntity entity = new ReminderEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }
}
