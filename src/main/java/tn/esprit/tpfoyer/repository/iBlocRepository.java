package tn.esprit.tpfoyer.repository;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.tpfoyer.entity.Bloc;

public interface iBlocRepository extends CrudRepository<Bloc, Long> {
    Bloc getBlocByNomBloc(String nomBlog);
}
