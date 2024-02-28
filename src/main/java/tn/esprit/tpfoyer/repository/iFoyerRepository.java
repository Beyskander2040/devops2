package tn.esprit.tpfoyer.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface iFoyerRepository extends CrudRepository <Foyer, Long> {

@Query("select f from  Foyer f join f.blocs b join b.chambres ch where ch.typeC =:type")
List<Foyer> getFoyerByTypeChambre(@Param("type") TypeChambre typeC);

}
