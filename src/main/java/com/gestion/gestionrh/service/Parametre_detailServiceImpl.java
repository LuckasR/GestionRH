package com.gestion.gestionrh.service;

import com.gestion.gestionrh.model.Parametre_detail;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gestion.gestionrh.repository.Parametre_detailRepository;
import java.util.List;


@Service
public class Parametre_detailServiceImpl implements Parametre_detailService {
    
    @Autowired
    private Parametre_detailRepository repo;

    public List<Parametre_detail> getAll(){
        return repo.findAll();
    }

    public Parametre_detail getById(Integer id){
        return repo.findById(id).orElse(null);
    }

    public void save(Parametre_detail obj){
        repo.save(obj);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }







}
