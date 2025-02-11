package br.com.reminderly.reminder.controller;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.service.GetRemindersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class ReminderController {

    private final GetRemindersService getRemindersService;

    @PostMapping("/v1/reminder")
    public ResponseEntity<ReminderRequest> createReminder(@RequestBody ReminderRequest reminderRequest) throws URISyntaxException {

        return ResponseEntity.status(HttpStatus.CREATED).body(getRemindersService.execute(reminderRequest));
    }

    @GetMapping("/v1/reminder")
    public ResponseEntity<Object> getReminders() throws URISyntaxException {

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> getReminder(@PathVariable String id) throws URISyntaxException {

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> updateReminder(@PathVariable String id) throws URISyntaxException {

        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> deleteReminder(@PathVariable String id) throws URISyntaxException {

        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
