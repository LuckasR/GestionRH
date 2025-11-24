package com.gestion.gestionrh.service;
import com.gestion.gestionrh.model.Taux;
import java.util.List;
public interface TauxService {
    List<Taux> getAll();
    Taux getById(Integer id);
    void save(Taux obj);
    Taux saveAndGet(Taux obj);
    void delete(Integer id);
}
