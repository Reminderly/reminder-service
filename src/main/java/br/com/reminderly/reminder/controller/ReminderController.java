package br.com.reminderly.reminder.controller;

import br.com.reminderly.reminder.annotation.ValidUUID;
import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.service.CreateRemindersService;
import br.com.reminderly.reminder.service.GetAllRemindersService;
import br.com.reminderly.reminder.service.GetReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Validated
@RestController
@RequiredArgsConstructor
public class ReminderController {

    private final CreateRemindersService createRemindersService;
    private final GetAllRemindersService getAllRemindersService;
    private final GetReminderService getReminderService;

    @PostMapping("/v1/reminder")
    public ResponseEntity<ReminderResponse> createReminder(@RequestBody ReminderRequest reminderRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(createRemindersService.execute(reminderRequest));
    }

    @GetMapping("/v1/reminder")
    public ResponseEntity<Object> getReminders(){

        return ResponseEntity.status(HttpStatus.CREATED).body(getAllRemindersService.execute());
    }

    @GetMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> getReminder(@PathVariable @ValidUUID String id){

        return ResponseEntity.status(HttpStatus.CREATED).body(getReminderService.execute(id));
    }

    @PatchMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> updateReminder(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/v1/reminder/{id}")
    public ResponseEntity<Object> deleteReminder(@PathVariable String id){

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
