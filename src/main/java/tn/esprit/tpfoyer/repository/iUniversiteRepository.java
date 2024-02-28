package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entity.Universite;

public interface iUniversiteRepository extends CrudRepository <Universite, Long>
{
    @Query("SELECT u FROM Universite u WHERE u.nomUniversite = :nom")
    Universite findUniversiteByNom(@Param("nom") String nom);

    Universite findUniversitesByNomUniversite(String nom);

}
