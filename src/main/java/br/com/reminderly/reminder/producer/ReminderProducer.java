package br.com.reminderly.reminder.producer;

import br.com.reminderly.reminder.entity.ReminderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReminderProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.scheduler.name}")
    private String routingKey;

    public void publishReminderMessage(ReminderEntity reminderEntity) {

        ReminderMessage reminderMessage = new ReminderMessage(reminderEntity.getId(), reminderEntity.getMessage(),
                reminderEntity.getTitle(), reminderEntity.getSendingTo(), reminderEntity.getNotificationType().toString(), reminderEntity.getReminderTime());

        rabbitTemplate.convertAndSend("", routingKey, reminderMessage);
    }


}

record ReminderMessage(UUID reminderId, String message, String reminderTitle, String sendingTo,
                       String notificationType, Instant reminderTime) {
}

