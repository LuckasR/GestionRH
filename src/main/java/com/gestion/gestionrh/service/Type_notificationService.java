package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Type_notificationService {
    List<Type_notification> getAll();
    Type_notification getById(Integer id);
    void save(Type_notification obj);
    void delete(Integer id);
}