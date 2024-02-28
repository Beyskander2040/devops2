package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

public interface iEtudiantServices {
    Etudiant ajouterEtudiant (Etudiant e);
    void supprimerEtudiant (Long idEtudiant);
    Etudiant modifierEtudiant(Etudiant e);
    Etudiant getEtudiant (Long idEtudiant);
    List<Etudiant> getAllEtudiant();
}
