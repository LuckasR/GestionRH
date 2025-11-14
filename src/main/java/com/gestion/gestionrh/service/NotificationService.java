package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface NotificationService {
    List<Notification> getAll();
    Notification getById(Integer id);
    void save(Notification obj);
    void delete(Integer id);
}