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

import rva.services.Korisnik_uslugeService;
import rva.models.Korisnik_usluge;

@CrossOrigin
@RestController
public class Korisnik_uslugeController {

    @Autowired
    private Korisnik_uslugeService service;

    @GetMapping("/korisnik_usluge")
    public ResponseEntity<List<Korisnik_usluge>> getAllKorisnikUsluge() {
        List<Korisnik_usluge> korisnici = service.getAllKorisnikUsluges();
        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @GetMapping("/korisnik_usluge/{id}")
    public ResponseEntity<?> getKorisnikUslugeById(@PathVariable long id){
        if(service.existsById(id)){
            return ResponseEntity.ok(service.getKorisnikUslugeById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Korisnik_usluge with this ID does not exist");
        }
    }

    @GetMapping("/korisnik_usluge/prezime/{prezime}")
    public ResponseEntity<?> getKorisnikUslugeByPrezime(@PathVariable String prezime) {
        if(!service.getKorisnikUslugeByPrezime(prezime).isEmpty()) {
            return ResponseEntity.ok(service.getKorisnikUslugeByPrezime(prezime));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Korisnik_usluge with requested prezime (" + prezime + ") does not exist!");
        }
    }

    @GetMapping("korisnik_usluge/korisnikUslugeSearch/{search}")
    public ResponseEntity<?> getKorisnikUslugeBySearch(@PathVariable("search") String search){
        List<Korisnik_usluge> korisnici = service.getKorisnikUslugeBySearch(search);
        if(korisnici.isEmpty())
            return new ResponseEntity<>(
                      "No similar korisnik_usluge found", 
                      HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK)
                .body(korisnici);
    }

    @PostMapping("/korisnik_usluge")
    public ResponseEntity<?> createKorisnikUsluge(@RequestBody Korisnik_usluge korisnik_usluge){
        Korisnik_usluge savedKorisnikUsluge;
        if(!service.existsById(korisnik_usluge.getKorisnikUslugeID())) {
            savedKorisnikUsluge = service.save(korisnik_usluge);
        } else {
            List<Korisnik_usluge> lista = service.getAllKorisnikUsluges();
            long najvecaVrednost = 1;

            for (int i = 0; i < lista.size(); i++) {
                if (najvecaVrednost <= lista.get(i).getKorisnikUslugeID()) {
                    najvecaVrednost = lista.get(i).getKorisnikUslugeID();
                }

                if (i == lista.size() - 1) {
                    najvecaVrednost++;
                }
            }

            korisnik_usluge.setKorisnikUslugeID(najvecaVrednost);
            savedKorisnikUsluge = service.save(korisnik_usluge);
        }

        URI uri = URI.create("/korisnik_usluge/" + savedKorisnikUsluge.getKorisnikUslugeID());
        return ResponseEntity.created(uri).body(savedKorisnikUsluge);
    }

    @PutMapping("/korisnik_usluge/{id}")
    public ResponseEntity<?> updateKorisnikUsluge(@RequestBody Korisnik_usluge korisnik_usluge, @PathVariable long id){
        if(service.existsById(id)) {
            korisnik_usluge.setKorisnikUslugeID(id);
            Korisnik_usluge updateKorisnikUsluge = service.save(korisnik_usluge);
            return ResponseEntity.ok(updateKorisnikUsluge);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resource with requested id: " + id + " has not been found");
        }
    }

    @DeleteMapping("/korisnik_usluge/{id}")
    public ResponseEntity<?> deleteKorisnikUsluge(@PathVariable long id) {
        if(service.existsById(id)) {
            service.deleteKorisnikUslugeById(id);
            return ResponseEntity.ok("Resource with requested id: " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Resource with requested id: " + id + " has not been found");
        }
    }
}
