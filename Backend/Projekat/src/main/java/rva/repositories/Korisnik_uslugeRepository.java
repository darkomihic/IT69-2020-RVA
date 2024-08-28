package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Korisnik_usluge;

public interface Korisnik_uslugeRepository extends JpaRepository<Korisnik_usluge, Long> {

	public abstract List<Korisnik_usluge> findByPrezimeContainingIgnoreCase(String prezime);
	
	@Query(value = "select * from korisnik_usluge where lower(prezime) like %:search%", nativeQuery = true)
	List<Korisnik_usluge> getBySearch(@Param("search") String search);
}
