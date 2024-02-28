package tn.esprit.tpfoyer.service;

import jakarta.transaction.Transactional;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

public interface iFoyerServices {
    Foyer ajouterFoyer(Foyer f);
    void supprimerFoyer(long idFoyer);
    Foyer getFoyer(long idFoyer);
   /* List getAll(long idFoyer);*/
    List<Foyer> getAllFoyer();
    Foyer updateFoyer(Foyer f);

    @Transactional
    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, Long idUniversite) ;

    Foyer getfoyerbyid(Long idFoyer);

}
