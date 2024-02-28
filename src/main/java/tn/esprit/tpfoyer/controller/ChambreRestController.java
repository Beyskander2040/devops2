package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.iChambreRepository;
import tn.esprit.tpfoyer.repository.imageRepository;
import tn.esprit.tpfoyer.service.iChambreServices;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("chambre")
public class ChambreRestController {

    private final iChambreServices chambreServices;
    private final iChambreRepository chamberRepo;
    private final imageRepository imageRepositroy ;



    @GetMapping("/allchambre")
    public List<Chambre> getAllChambre() {
        return chambreServices.getAllChambre();
    }

    @PostMapping("/add")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreServices.ajouterChambre(c);
    }

    @PutMapping("/update/{idChambre}")
    public Chambre updateChambre(@PathVariable Long idChambre, @RequestBody Chambre updatedChambre) {
        Chambre existingChambre = chambreServices.getChambre(idChambre);

        if (existingChambre != null) {
            // Mettez à jour les attributs de la chambre existante avec les nouvelles valeurs
            existingChambre.setNumeroChambre(updatedChambre.getNumeroChambre());
            existingChambre.setTypeC(updatedChambre.getTypeC());
            existingChambre.setBloc(updatedChambre.getBloc());
            existingChambre.setImagebyte(updatedChambre.getImagebyte());


            // Enregistrez la chambre mise à jour
            return chambreServices.modifierChambre(existingChambre);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{idChambre}")
    public void deleteChambre(@PathVariable Long idChambre) {
        Chambre existingChambre = chambreServices.getChambre(idChambre);

        if (existingChambre != null) {
            // Supprimez la chambre en utilisant le service
            chambreServices.supprimerChambre(idChambre);
        } else {
            log.warn("N'existe pas");
        }
    }

    @GetMapping("/getChambresParBlocEtType/{idBloc}/{typeC}")
    public List<Chambre> getChambresParBlocEtType(@PathVariable Long idBloc, @PathVariable TypeChambre typeC) {
        return chambreServices.getChambresParBlocEtType(idBloc, typeC);
    }

    @PostMapping("affecterBlocAChambre/{idChambre}/{idBloc}")
    public Chambre affecterBlocAFoyer(@PathVariable Long idChambre, @PathVariable Long idBloc) {
        return chambreServices.affecterBlocAChambre(idBloc, idChambre);
    }



    @PostMapping("/ajouterChambreEtAffecterBloc/{idBloc}")
    public Chambre ajouterChambreEtAffecterBloc(@RequestBody Chambre chambre, @PathVariable long idBloc) {
        return chambreServices.ajouterChambreEtAffecterBloc(chambre,idBloc);


    }
    @GetMapping("getchambreById/{idChambre}")
    public Chambre getChambre(@PathVariable Long idChambre){
        return chambreServices.getChambre(idChambre);
    }


    @GetMapping("getChamberList/{nomBloc}")
    List<Chambre> getChambresParNomBloc(@PathVariable("nomBloc") String nomBloc){
        return chambreServices.getChambresParNomBloc(nomBloc);
    }









    @PostMapping("uploadImg/{idChamber}")
    public Chambre addImg(@RequestParam("file") MultipartFile file , @PathVariable("idChamber") long idChamber) {

        Chambre chamber = chamberRepo.findById(idChamber).get();
        System.out.println("OK");

        try {
            chamber.setImagebyte(file.getBytes());
            chamberRepo.save(chamber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chamber;
    }


//
//        @GetMapping("/{id}/image")
//        public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
//            byte[] imageBytes = chambreServices.getImageById(id);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);  // Assurez-vous de définir le bon type de contenu
//            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//        }

}