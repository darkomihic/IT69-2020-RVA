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

import rva.services.BankaService;
import rva.models.Banka;

@CrossOrigin
@RestController
public class BankaController {
	

	@Autowired
	private BankaService service;

	@GetMapping("/banka")
	public ResponseEntity<List<Banka>> getAllBanka() {
        
        // Your business logic here
		List<Banka> banks = service.getAllBankas();
		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
	
	@GetMapping("/banka/{id}")
	public ResponseEntity<?> getBankaById(@PathVariable long id){
		if(service.existsById(id)){
			return ResponseEntity.ok(service.getBankaById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banka with this ID does not exist");
		}
	}
	
	@GetMapping("/banka/naziv/{naziv}")
	public ResponseEntity<?> getBankaByNaziv(@PathVariable String naziv) {
		
		if(!service.getBankaByNaziv(naziv).isEmpty()) {
			
			return ResponseEntity.ok(service.getBankaByNaziv(naziv));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Banka with requested naziv ( " + naziv + " ) does not exist!");
			
		}
		
	}
	
	@GetMapping("banka/bankaSearch/{search}")
	public  ResponseEntity<?> getBankaBySearch(@PathVariable("search") String search){
		List<Banka> bolnice = service.getBankaBySearch(search);
		if(bolnice.isEmpty())
			return new ResponseEntity<>(
			          "Ni jedna slicna banka ne postoji", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(bolnice);
	}
	
	@PostMapping("/banka")
	public ResponseEntity<?> createBanka(@RequestBody Banka banka){
		Banka savedBanka;
		if(!service.existsById(banka.getBankaID())) {
			
			savedBanka = service.save(banka);
			
		} else {
			
			List<Banka> lista = service.getAllBankas();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getBankaID()) {
					najvecaVrednost = lista.get(i).getBankaID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			banka.setBankaID(najvecaVrednost);
			savedBanka = service.save(banka);
			
		}
		
		URI uri = URI.create("/banka/" + savedBanka.getBankaID());
		return ResponseEntity.created(uri).body(savedBanka);
		
	}
	
	@PutMapping("/banka/{id}")
	public ResponseEntity<?> updateBanka(@RequestBody Banka banka, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			banka.setBankaID(id);
			Banka updateBanka = service.save(banka);
			
			return ResponseEntity.ok(updateBanka);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/banka/{id}")
	public ResponseEntity<?> deleteBanka(@PathVariable long id) {
		
		if(service.existsById(id)) {
			
			service.deleteBankaById(id);
			
			return ResponseEntity.ok("Resource with requested id: " + id + " has been deleted");
			
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
}
