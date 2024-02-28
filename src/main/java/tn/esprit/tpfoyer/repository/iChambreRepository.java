package tn.esprit.tpfoyer.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface iChambreRepository extends CrudRepository<Chambre,Long> {
    List<Chambre> findByBlocIdBlocAndTypeC(Long idBloc, TypeChambre typeC);

    List<Chambre> findByBloc(Bloc b);


}
