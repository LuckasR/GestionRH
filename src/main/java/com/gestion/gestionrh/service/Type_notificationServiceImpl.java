package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Type_notificationServiceImpl implements Type_notificationService {

    @Autowired
    private Type_notificationRepository repo;

    public List<Type_notification> getAll() { return repo.findAll(); }
    public Type_notification getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Type_notification obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}