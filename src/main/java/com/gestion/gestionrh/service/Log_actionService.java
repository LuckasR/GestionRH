package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface Log_actionService {
    List<Log_action> getAll();
    Log_action getById(Integer id);
    void save(Log_action obj);
    void delete(Integer id);
}