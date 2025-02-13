package br.com.reminderly.reminder.exception.handler;

import br.com.reminderly.reminder.exception.ReminderNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, Object> errors = new HashMap<>();
        List<String> errorMessages = new ArrayList<>();

        ex.getConstraintViolations().forEach(violation ->
                errorMessages.add(violation.getMessage())
        );

        errors.put("errors", errorMessages);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(value = ReminderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleReminderNotFoundException(ReminderNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}
