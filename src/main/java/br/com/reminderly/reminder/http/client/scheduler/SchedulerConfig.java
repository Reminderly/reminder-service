package br.com.reminderly.reminder.http.client.scheduler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class SchedulerConfig {

    @Value("${api.scheduler.url.base}")
    private String baseUrl;

    @Value("${api.scheduler.uri.create.scheduler}")
    private String createScheduleUri;
}
