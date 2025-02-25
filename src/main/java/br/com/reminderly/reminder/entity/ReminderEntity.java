package br.com.reminderly.reminder.entity;

import br.com.reminderly.reminder.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reminder")
public class ReminderEntity {
    @GeneratedValue
    @Id
    private UUID id;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Instant reminderTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;
    @Column(nullable = false)
    private String sendingTo;
    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}
