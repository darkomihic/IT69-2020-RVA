package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.repositories.FilijalaRepository;
import rva.models.Filijala;

@Service
public class FilijalaService {
	@Autowired
	private FilijalaRepository repo;
	
	public List<Filijala> getAllFilijalas() {
		return repo.findAll();
	}
	
	public Optional<Filijala> getFilijalaById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Filijala>> getFilijalaByAdresa(String adresa) {
		Optional<List<Filijala>> filijale = Optional.of(repo.findByAdresaContainingIgnoreCase(adresa));
		return filijale;
	}
	
	public List<Filijala> getFilijalaBySearch(String search) {
		String searchEntry = search.toLowerCase();
		return repo.getBySearch(searchEntry);
	}
	
	public Filijala save(Filijala filijala) {
		return repo.save(filijala);
	}
	
	public boolean existsById(long id) {
		if(repo.existsById(id)) {
			return true;
		} else return false;
	}
	
	public void deleteFilijalaById(long id) {
		repo.deleteById(id);
	}
	
}
