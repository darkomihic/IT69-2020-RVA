package rva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Banka;

public interface BankaRepository extends JpaRepository<Banka, Long> {

	public abstract List<Banka> findByNazivContainingIgnoreCase(String naziv);

	@Query(value = "select * from banka where lower(naziv) like %:search%", nativeQuery = true)
	List<Banka> getBySearch(@Param("search") String search);

}
