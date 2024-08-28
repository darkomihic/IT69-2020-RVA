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

import rva.services.UslugaService;
import rva.models.Usluga;

@CrossOrigin
@RestController
public class UslugaController {

    @Autowired
    private UslugaService service;

    @GetMapping("/usluga")
    public ResponseEntity<List<Usluga>> getAllUslugas() {
        List<Usluga> usluge = service.getAllUslugas();
        return new ResponseEntity<>(usluge, HttpStatus.OK);
    }

    @GetMapping("/usluga/{id}")
    public ResponseEntity<?> getUslugaById(@PathVariable long id) {
        if (service.existsById(id)) {
            return ResponseEntity.ok(service.getUslugaById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usluga with this ID does not exist");
        }
    }

    @GetMapping("/usluga/naziv/{naziv}")
    public ResponseEntity<?> getUslugaByNaziv(@PathVariable String naziv) {
        if (!service.getUslugaByNaziv(naziv).isEmpty()) {
            return ResponseEntity.ok(service.getUslugaByNaziv(naziv));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usluga with requested naziv (" + naziv + ") does not exist!");
        }
    }

    @GetMapping("/usluga/uslugaSearch/{search}")
    public ResponseEntity<?> getUslugaBySearch(@PathVariable("search") String search) {
        List<Usluga> usluge = service.getUslugaBySearch(search);
        if (usluge.isEmpty()) {
            return new ResponseEntity<>("No similar usluga exists", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usluge);
    }

    @PostMapping("/usluga")
    public ResponseEntity<?> createUsluga(@RequestBody Usluga usluga) {
        Usluga savedUsluga;
        if (!service.existsById(usluga.getUslugaID())) {
            savedUsluga = service.save(usluga);
        } else {
            List<Usluga> lista = service.getAllUslugas();
            long najvecaVrednost = 1;

            for (int i = 0; i < lista.size(); i++) {
                if (najvecaVrednost <= lista.get(i).getUslugaID()) {
                    najvecaVrednost = lista.get(i).getUslugaID();
                }

                if (i == lista.size() - 1) {
                    najvecaVrednost++;
                }
            }

            usluga.setUslugaID(najvecaVrednost);
            savedUsluga = service.save(usluga);
        }

        URI uri = URI.create("/usluga/" + savedUsluga.getUslugaID());
        return ResponseEntity.created(uri).body(savedUsluga);
    }

    @PutMapping("/usluga/{id}")
    public ResponseEntity<?> updateUsluga(@RequestBody Usluga usluga, @PathVariable long id) {
        if (service.existsById(id)) {
            usluga.setUslugaID(id);
            Usluga updatedUsluga = service.save(usluga);
            return ResponseEntity.ok(updatedUsluga);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resource with requested id: " + id + " has not been found");
        }
    }

    @DeleteMapping("/usluga/{id}")
    public ResponseEntity<?> deleteUsluga(@PathVariable long id) {
        if (service.existsById(id)) {
            service.deleteUslugaById(id);
            return ResponseEntity.ok("Resource with requested id: " + id + " has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resource with requested id: " + id + " has not been found");
        }
    }
}
