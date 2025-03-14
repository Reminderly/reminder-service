package br.com.reminderly.reminder.enums;

import java.text.MessageFormat;

public enum LogMessage {

    SERVICE_PROCESS_START("Starting process for {0} request."),
    SERVICE_PROCESS_FINISH("Finishing process for {0} request."),
    SAVING_REMINDER_IN_DATABASE("Saving updated reminder in database."),
    RETRIEVING_REMINDER_FROM_DATABASE("Retrieving reminder from database."),
    DELETING_REMINDER_FROM_DATABASE("Deleting reminder from database."),
    REMINDER_NOT_FOUND_BY_ID("Reminder not found for id: {0}"),
    UPDATING_REMINDER_ENTITY("Updating reminder entity."),
    ERROR_PROCESSING_SERVICE_ACTION("Error processing service action: {0}"),
    PUBLISHING_MESSAGE_IN_QUEUE("Publishing message in queue."),
    REQUESTING_REMINDER_SCHEDULE("Requesting reminder schedule"),
    ERROR_ON_REQUEST_CLIENT("Error on request client: {0} - Status code: {1} - Response body: {2} ");


    private final String message;

    LogMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return MessageFormat.format(message, args);
    }

}


