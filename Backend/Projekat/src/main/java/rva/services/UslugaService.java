package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Usluga;
import rva.repositories.UslugaRepository;

@Service
public class UslugaService {
    @Autowired
    private UslugaRepository repo;

    public List<Usluga> getAllUslugas() {
        return repo.findAll();
    }

    public Optional<Usluga> getUslugaById(long id) {
        return repo.findById(id);
    }

    public Optional<List<Usluga>> getUslugaByNaziv(String naziv) {
        Optional<List<Usluga>> usluge = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
        return usluge;
    }

    public List<Usluga> getUslugaBySearch(String search) {
        String searchEntry = search.toLowerCase();
        return repo.getBySearch(searchEntry);
    }

    public Usluga save(Usluga usluga) {
        return repo.save(usluga);
    }

    public boolean existsById(long id) {
        return repo.existsById(id);
    }

    public void deleteUslugaById(long id) {
        repo.deleteById(id);
    }
}
