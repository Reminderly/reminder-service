package br.com.reminderly.reminder.controller;

import br.com.reminderly.reminder.dto.ReminderRequest;
import br.com.reminderly.reminder.dto.ReminderResponse;
import br.com.reminderly.reminder.entity.ReminderEntity;
import br.com.reminderly.reminder.enums.NotificationType;
import br.com.reminderly.reminder.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReminderControllerTest {

    @Mock
    private CreateReminderService createReminderService;
    @Mock
    private GetAllRemindersService getAllRemindersService;
    @Mock
    private GetReminderService getReminderService;
    @Mock
    private UpdateReminderService updateReminderService;
    @Mock
    private DeleteReminderService deleteReminderService;

    @InjectMocks
    private ReminderController reminderController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private ReminderRequest reminderRequest;
    private ReminderResponse reminderResponse;
    private static final String BASE_URI = "/v1/reminder";
    private static final String REMINDER_ID_URI_PARAM = "/{reminderId}";
    private static final String FAKE_REMINDER_MESSAGE = "Test message";
    private static final UUID FAKE_REMINDER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private static final String FAKE_REMINDER_SEND_TO = "test@email.com";
    private static final String FAKE_REMINDER_TITLE = "Test Title";

    @BeforeEach
    void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(reminderController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        reminderRequest = ReminderRequest.builder().reminderTime(Instant.now()).message(FAKE_REMINDER_MESSAGE).notificationType(NotificationType.EMAIL)
                .title(FAKE_REMINDER_SEND_TO).sendingTo(FAKE_REMINDER_TITLE).build();

        reminderResponse = ReminderResponse.builder().reminderId(FAKE_REMINDER_ID).reminderTime(Instant.now())
                .message(FAKE_REMINDER_MESSAGE).notificationType(NotificationType.WHATSAPP).build();

    }

    @Test
    void shouldReturn201WhenCreatingReminderSuccessfully() throws Exception {

        when(createReminderService.execute(any())).thenReturn(reminderResponse);

        MvcResult mvcResult = mockMvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reminderRequest)))
                .andExpect(status().isCreated()).andReturn();

        ReminderResponse reminderResponseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReminderResponse.class);
        assertThat(reminderResponseResult).usingRecursiveComparison().isEqualTo(reminderResponse);

    }

    @Test
    void shouldReturn200GettingReminderSuccessfully() throws Exception {

        when(getReminderService.execute(any())).thenReturn(reminderResponse);

        MvcResult mvcResult = mockMvc.perform(get(BASE_URI + REMINDER_ID_URI_PARAM, FAKE_REMINDER_ID)).andExpect(status().isOk()).andReturn();

        ReminderResponse reminderResponseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReminderResponse.class);
        assertThat(reminderResponseResult).usingRecursiveComparison().isEqualTo(reminderResponse);

    }

    @Test
    void shouldReturn200GettingAllReminderSuccessfully() throws Exception {

        List<ReminderEntity> reminders = new ArrayList<>();

        reminders.add(new ReminderEntity());
        reminders.add(new ReminderEntity());

        Pageable pageable = PageRequest.of(0, 2);
        Page<ReminderEntity> remindersPage = new PageImpl<>(reminders, pageable, reminders.size());

        when(getAllRemindersService.execute(any())).thenReturn(remindersPage);

        mockMvc.perform(get(BASE_URI)).andExpect(status().isOk()).andReturn();

    }

    @Test
    void shouldReturn200UpdatingReminderSuccessfully() throws Exception {

        when(updateReminderService.execute(any(), any())).thenReturn(reminderResponse);

        MvcResult mvcResult = mockMvc.perform(patch(BASE_URI + REMINDER_ID_URI_PARAM, FAKE_REMINDER_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reminderRequest)))
                .andExpect(status().isOk()).andReturn();

        ReminderResponse reminderResponseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReminderResponse.class);
        assertThat(reminderResponseResult).usingRecursiveComparison().isEqualTo(reminderResponse);

    }

    @Test
    void shouldReturn200DeletingReminderSuccessfully() throws Exception {

        when(deleteReminderService.execute(any())).thenReturn(reminderResponse);

        MvcResult mvcResult = mockMvc.perform(delete(BASE_URI + REMINDER_ID_URI_PARAM, FAKE_REMINDER_ID))
                .andExpect(status().isOk()).andReturn();

        ReminderResponse reminderResponseResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ReminderResponse.class);
        assertThat(reminderResponseResult).usingRecursiveComparison().isEqualTo(reminderResponse);

    }


}
