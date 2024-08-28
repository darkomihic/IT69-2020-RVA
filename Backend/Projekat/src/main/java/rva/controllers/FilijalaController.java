package rva.controllers;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.services.FilijalaService;
import rva.models.Filijala;

@CrossOrigin
@RestController
public class FilijalaController {
	

	@Autowired
	private FilijalaService service;

	@GetMapping("/filijala")
	public ResponseEntity<List<Filijala>> getAllFilijala() {
        
        // Your business logic here
		List<Filijala> filijale = service.getAllFilijalas();
		return new ResponseEntity<>(filijale, HttpStatus.OK);
	}
	
	@GetMapping("/filijala/{id}")
	public ResponseEntity<?> getFilijalaById(@PathVariable long id){
		if(service.existsById(id)){
			return ResponseEntity.ok(service.getFilijalaById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filijala with this ID does not exist");
		}
	}
	
	@GetMapping("/filijala/adresa/{adresa}")
	public ResponseEntity<?> getFilijalaByNaziv(@PathVariable String adresa) {
		
		if(!service.getFilijalaByAdresa(adresa).isEmpty()) {
			
			return ResponseEntity.ok(service.getFilijalaByAdresa(adresa));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Filijala with requested adresa ( " + adresa + " ) does not exist!");
			
		}
		
	}
	
	@GetMapping("filijala/filijalaSearch/{search}")
	public  ResponseEntity<?> getFilijalaBySearch(@PathVariable("search") String search){
		List<Filijala> filijale = service.getFilijalaBySearch(search);
		if(filijale.isEmpty())
			return new ResponseEntity<>(
			          "Ni jedna slicna filijala ne postoji", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(filijale);
	}
	
	@PostMapping("/filijala")
	public ResponseEntity<?> createFilijala(@RequestBody Filijala filijala){
		Filijala savedFilijala;
		if(!service.existsById(filijala.getFilijalaID())) {
			
			savedFilijala = service.save(filijala);
			
		} else {
			
			List<Filijala> lista = service.getAllFilijalas();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getFilijalaID()) {
					najvecaVrednost = lista.get(i).getFilijalaID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			filijala.setFilijalaID(najvecaVrednost);
			savedFilijala = service.save(filijala);
			
		}
		
		URI uri = URI.create("/filijala/" + savedFilijala.getFilijalaID());
		return ResponseEntity.created(uri).body(savedFilijala);
		
	}
	
	@PutMapping("/filijala/{id}")
	public ResponseEntity<?> updateFilijala(@RequestBody Filijala filijala, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			filijala.setFilijalaID(id);
			Filijala updateFilijala = service.save(filijala);
			
			return ResponseEntity.ok(updateFilijala);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/filijala/{id}")
	public ResponseEntity<?> deleteFilijala(@PathVariable long id) {
		
		if(service.existsById(id)) {
			
			service.deleteFilijalaById(id);
			
			return ResponseEntity.ok("Resource with requested id: " + id + " has been deleted");
			
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
}
