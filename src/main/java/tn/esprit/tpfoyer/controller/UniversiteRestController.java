package tn.esprit.tpfoyer.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.iUniversiteServices;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("universite")
@CrossOrigin(origins = "*")
public class UniversiteRestController {
    private final iUniversiteServices universiteServices;


    @GetMapping("/alluniversite")
    public List<Universite> getAllUniversite() {
        return universiteServices.getAllUniversite();
    }

   /* @PutMapping("/affecterfoyer/{idFoyer}/{nom}")
    public Universite affecterUni (@PathVariable Long idFoyer,@PathVariable String nom);
    return univServices.*/




    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteServices.ajouterUniversite(u);
    }

    @PutMapping("/update/{idUniversite}")
    public Universite updateUniversite(@PathVariable Long idUniversite, @RequestBody Universite updatedUniversite) {
        Universite existingUniversite = universiteServices.getUniversite(idUniversite);

        if (existingUniversite != null) {
            // Mettez à jour les attributs de l'université existante avec les nouvelles valeurs
            existingUniversite.setNomUniversite(updatedUniversite.getNomUniversite());
            existingUniversite.setAdresse(updatedUniversite.getAdresse());

            // Enregistrez l'université mise à jour
            return universiteServices.updateUniversite(existingUniversite);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{idUniversite}")
    public void deleteUniversite(@PathVariable Long idUniversite) {
        Universite existingUniversite = universiteServices.getUniversite(idUniversite);

        if (existingUniversite != null) {
            // Supprimez l'université en utilisant le service
            universiteServices.supprimerUniversite(idUniversite);
        } else {
            log.warn("N'existe pas");
        }
    }

    @PutMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable Long idFoyer, @PathVariable String nomUniversite) {
        return universiteServices.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable Long idUniversite) {
        return universiteServices.desaffecterFoyerAUniversite(idUniversite);
    }
}




