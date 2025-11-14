package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getById(Integer id);
    void save(Role obj);
    void delete(Integer id);
}