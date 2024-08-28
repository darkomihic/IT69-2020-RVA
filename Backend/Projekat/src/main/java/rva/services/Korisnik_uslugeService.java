package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.repositories.Korisnik_uslugeRepository;
import rva.models.Korisnik_usluge;

@Service
public class Korisnik_uslugeService {
    @Autowired
    private Korisnik_uslugeRepository repo;
    
    public List<Korisnik_usluge> getAllKorisnikUsluges() {
        return repo.findAll();
    }
    
    public Optional<Korisnik_usluge> getKorisnikUslugeById(long id){
        return repo.findById(id);
    }
    
    public Optional<List<Korisnik_usluge>> getKorisnikUslugeByPrezime(String prezime) {
        Optional<List<Korisnik_usluge>> korisnici = Optional.of(repo.findByPrezimeContainingIgnoreCase(prezime));
        return korisnici;
    }
    
    public List<Korisnik_usluge> getKorisnikUslugeBySearch(String search) {
        String searchEntry = search.toLowerCase();
        return repo.getBySearch(searchEntry);
    }
    
    public Korisnik_usluge save(Korisnik_usluge korisnik_usluge) {
        return repo.save(korisnik_usluge);
    }
    
    public boolean existsById(long id) {
        if(repo.existsById(id)) {
            return true;
        } else return false;
    }
    
    public void deleteKorisnikUslugeById(long id) {
        repo.deleteById(id);
    }
}
