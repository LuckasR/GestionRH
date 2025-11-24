package com.gestion.gestionrh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.gestionrh.model.Taux;
import com.gestion.gestionrh.repository.TauxRepository;
import java.util.List;

@Service
public class TauxServiceImpl implements TauxService {
    
    @Autowired
    private TauxRepository repo;

    @Override
    public List<Taux> getAll() {
        return repo.findAll();
    }

    @Override
    public Taux getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void save(Taux obj) {
        repo.save(obj);
    }

    @Override
    public Taux saveAndGet(Taux obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
