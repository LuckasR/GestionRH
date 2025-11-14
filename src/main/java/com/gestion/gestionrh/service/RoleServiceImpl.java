package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.*;
import com.gestion.gestionrh.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repo;

    public List<Role> getAll() { return repo.findAll(); }
    public Role getById(Integer id) { return repo.findById(id).orElse(null); }
    public void save(Role obj) { repo.save(obj); }
    public void delete(Integer id) { repo.deleteById(id); }
}