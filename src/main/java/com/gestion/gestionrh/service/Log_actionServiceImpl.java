package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Log_actionServiceImpl implements Log_actionService {

    @Autowired
    private Log_actionRepository repo;

    public List<Log_action> getAll() { return repo.findAll(); }
    public Log_action getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Log_action obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}