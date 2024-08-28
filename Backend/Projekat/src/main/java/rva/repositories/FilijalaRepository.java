package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Filijala;

public interface FilijalaRepository extends JpaRepository<Filijala, Long> {
	
	public abstract List<Filijala> findByAdresaContainingIgnoreCase(String adresa);
	
	@Query(value = "select * from filijala where lower(adresa) like %:search%", nativeQuery = true)
	List<Filijala> getBySearch(@Param("search") String search);
}
