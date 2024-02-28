package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.iEtudiantServices;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("etudiant")
public class EtudiantRestController {
    private final iEtudiantServices etudiantServices;

    @GetMapping("/alletudiant")
    public List<Etudiant> getAllEtudiant() {
        return etudiantServices.getAllEtudiant();
    }

    @GetMapping("/etudiantById/{idEtudiant}")
    public Etudiant getEtudiant(@PathVariable Long idEtudiant) {
        return etudiantServices.getEtudiant(idEtudiant);
    }

    @PostMapping("/add")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantServices.ajouterEtudiant(e);
    }

    @PutMapping("/update")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantServices.modifierEtudiant(etudiant);
    }


    @DeleteMapping("/delete/{idEtudiant}")
    public void deleteEtudiant(@PathVariable Long idEtudiant) {
        Etudiant existingEtudiant = etudiantServices.getEtudiant(idEtudiant);

        if (existingEtudiant != null) {
            // Supprimez l'étudiant en utilisant le service
            etudiantServices.supprimerEtudiant(idEtudiant);
        } else {
            log.warn("N'existe pas");
        }
    }
}