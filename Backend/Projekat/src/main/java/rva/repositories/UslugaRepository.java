package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Usluga;

public interface UslugaRepository extends JpaRepository<Usluga, Long> {
	
	public abstract List<Usluga> findByNazivContainingIgnoreCase(String naziv);
	
	@Query(value = "select * from usluga where lower(naziv) like %:search%", nativeQuery = true)
	List<Usluga> getBySearch(@Param("search") String search);
	
}
