package br.com.reminderly.reminder.controller;

import br.com.reminderly.reminder.annotation.ValidUUID;
import br.com.reminderly.reminder.dto.ReminderListResponse;
import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reminder")
public class ReminderController {

    private final CreateReminderService createRemindersService;
    private final GetAllRemindersService getAllRemindersService;
    private final GetReminderService getReminderService;
    private final UpdateReminderService updateReminderService;
    private final DeleteReminderService deleteReminderService;

    @PostMapping()
    public ResponseEntity<ReminderResponse> createReminder(@RequestBody @Valid ReminderRequest reminderRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(createRemindersService.execute(reminderRequest));
    }

    @GetMapping()
    public ResponseEntity<ReminderListResponse> getReminders(){

        return ResponseEntity.status(HttpStatus.OK).body(getAllRemindersService.execute());
    }

    @GetMapping("{reminderId}")
    public ResponseEntity<ReminderResponse> getReminder(@PathVariable @ValidUUID String reminderId){

        return ResponseEntity.status(HttpStatus.OK).body(getReminderService.execute(reminderId));
    }

    @PatchMapping("{reminderId}")
    public ResponseEntity<ReminderResponse> updateReminder(@PathVariable @ValidUUID String reminderId, @RequestBody ReminderRequest reminderRequest){

        return ResponseEntity.status(HttpStatus.OK).body(updateReminderService.execute(reminderId, reminderRequest));
    }

    @DeleteMapping("{reminderId}")
    public ResponseEntity<ReminderResponse> deleteReminder(@PathVariable @ValidUUID String reminderId){

        return ResponseEntity.status(HttpStatus.OK).body(deleteReminderService.execute(reminderId));
    }
}
