package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.iFoyerServices;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("foyer")
@CrossOrigin(origins="*")
public class FoyerRestController {
    private final iFoyerServices foyerServices;

    @GetMapping("/allfoyer")
    public List<Foyer> getAllFoyer(){
        return foyerServices.getAllFoyer();
    }

    @PostMapping("/add")
    public Foyer addFoyer(@RequestBody Foyer f){
        return foyerServices.ajouterFoyer(f);
    }

    @PutMapping("/update/{idFoyer}")
    public Foyer updateFoyer(@PathVariable Long idFoyer, @RequestBody Foyer updatedFoyer) {
        Foyer existingFoyer = foyerServices.getFoyer(idFoyer);

        if (existingFoyer != null) {

            existingFoyer.setNomFoyer(updatedFoyer.getNomFoyer());
            existingFoyer.setCapaciteFoyer(updatedFoyer.getCapaciteFoyer());
            existingFoyer.setDescriptionFoyer(updatedFoyer.getDescriptionFoyer());
            existingFoyer.setTelephoneFoyer(updatedFoyer.getTelephoneFoyer());
            existingFoyer.setTypeF(updatedFoyer.getTypeF());


            return foyerServices.updateFoyer(existingFoyer);
        } else {
            return null;
        }
    }


    @DeleteMapping("/delete/{idFoyer}")
    public void deleteFoyer(@PathVariable Long idFoyer) {
        Foyer existingFoyer = foyerServices.getFoyer(idFoyer);

        if (existingFoyer != null) {
            foyerServices.supprimerFoyer(idFoyer);
        } else {
          log.warn("N'existe pas");
        }
    }

    @PostMapping("/add/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @PathVariable Long idUniversite) {
        return foyerServices.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }

    @GetMapping("/getfoyer/{id}")
    public Foyer getfoyerbyid(@PathVariable Long id) {
        return foyerServices.getfoyerbyid(id);
    }
}


