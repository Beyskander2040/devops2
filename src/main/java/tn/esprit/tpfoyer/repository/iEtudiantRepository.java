package tn.esprit.tpfoyer.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.time.LocalDate;
import java.util.List;

public interface iEtudiantRepository extends CrudRepository<Etudiant, Long> {
    Etudiant findEtudiantByCin(Long cin);

}
