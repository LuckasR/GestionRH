package com.gestion.gestionrh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.gestionrh.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {}