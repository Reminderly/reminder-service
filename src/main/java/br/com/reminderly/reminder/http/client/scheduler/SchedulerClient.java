package br.com.reminderly.reminder.http.client.scheduler;

import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.enums.LogMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class SchedulerClient {

    private final SchedulerConfig schedulerConfig;
    private final WebClient webClient;

    private static final String SERVICE_ACTION_LOG = "Scheduler Client";

    public SchedulerClient(WebClient.Builder webClientBuilder, SchedulerConfig schedulerConfig) {
        this.schedulerConfig = schedulerConfig;
        this.webClient = webClientBuilder.baseUrl(this.schedulerConfig.getBaseUrl()).build();

    }

    public ReminderEntity scheduleReminder(ReminderEntity reminderEntity) {
        ReminderEntity reminder = null;

        reminder = webClient.post()
                .uri(schedulerConfig.getCreateScheduleUri())
                .bodyValue(reminderEntity)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.createException().flatMap(error ->
                                Mono.error(new RuntimeException(LogMessage.ERROR_ON_REQUEST_CLIENT.getMessage(SERVICE_ACTION_LOG, response.statusCode(), error.getResponseBodyAsString())))
                        )
                )
                .bodyToMono(ReminderEntity.class)
                .block();

        return reminder;
    }

}
