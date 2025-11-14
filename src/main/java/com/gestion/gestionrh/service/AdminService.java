package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import java.util.List;

public interface AdminService {
    List<Admin> getAll();
    Admin getById(Integer id);
    void save(Admin obj);
    void delete(Integer id);
}