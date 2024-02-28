package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.iBlocServices;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("bloc")
@Slf4j
@CrossOrigin(origins="*")
public class BlocRestController {
    private final iBlocServices blocServices;

    @GetMapping("/allbloc")
    public List<Bloc> getAllBloc() {
        return blocServices.getAllBloc();
    }

    @PostMapping("/add")
    public Bloc addBloc(@RequestBody Bloc b) {
        return blocServices.ajouterBloc(b);
    }

    @PutMapping("/update/{idBloc}")
    public Bloc updateBloc(@PathVariable Long idBloc, @RequestBody Bloc updatedBloc) {
        Bloc existingBloc = blocServices.getBloc(idBloc);

        if (existingBloc != null) {
            // Mettez à jour les attributs du bloc existant avec les nouvelles valeurs
            existingBloc.setNomBloc(updatedBloc.getNomBloc());
            existingBloc.setCapaciteBloc(updatedBloc.getCapaciteBloc());

            // Enregistrez le bloc mis à jour
            return blocServices.modifierBloc(existingBloc);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{idBloc}")
    public void deleteBloc(@PathVariable Long idBloc) {
        Bloc existingBloc = blocServices.getBloc(idBloc);

        if (existingBloc != null) {
            // Supprimez le bloc en utilisant le service
            blocServices.supprimerBloc(idBloc);
        } else {
            log.warn("N'existe pas");
        }
    }
    @PutMapping("/affecterChambres/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> idChambre, @PathVariable Long idBloc) {
        return blocServices.affecterChambresABloc(idChambre, idBloc);
    }
    @PutMapping("/affecterBlocFoyer/{idBloc}/{idFoyer}")
    public Bloc affecterBlocAFoyer(@PathVariable Long idBloc, @PathVariable Long idFoyer) {
        return blocServices.affecterBlocAFoyer(idBloc, idFoyer);
    }
}
