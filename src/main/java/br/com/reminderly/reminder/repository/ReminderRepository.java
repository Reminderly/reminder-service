package br.com.reminderly.reminder.repository;

import br.com.reminderly.reminder.entity.ReminderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReminderRepository extends JpaRepository<ReminderEntity, UUID> {

    Page<ReminderEntity> findAll(Pageable pageable);
}
