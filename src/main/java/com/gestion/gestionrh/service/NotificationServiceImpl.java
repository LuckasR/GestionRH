package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository repo;

    public List<Notification> getAll() { return repo.findAll(); }
    public Notification getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Notification obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}