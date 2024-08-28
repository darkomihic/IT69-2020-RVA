package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Banka;
import rva.repositories.BankaRepository;

@Service
public class BankaService {
	@Autowired
	private BankaRepository repo;

	public List<Banka> getAllBankas() {
		return repo.findAll();
	}

	public Optional<Banka> getBankaById(long id) {
		return repo.findById(id);
	}

	public Optional<List<Banka>> getBankaByNaziv(String naziv) {
		Optional<List<Banka>> banke = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return banke;
	}

	public List<Banka> getBankaBySearch(String search) {
		String searchEntry = search.toLowerCase();
		return repo.getBySearch(searchEntry);
	}

	public Banka save(Banka banka) {
		return repo.save(banka);
	}

	public boolean existsById(long id) {
		if (repo.existsById(id)) {
			return true;
		} else
			return false;
	}

	public void deleteBankaById(long id) {
		repo.deleteById(id);
	}
}
