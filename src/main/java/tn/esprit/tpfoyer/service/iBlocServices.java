package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;

public interface iBlocServices {
    Bloc ajouterBloc (Bloc b);
    void supprimerBloc (Long idBloc);
    Bloc getBloc (Long idBloc);
    Bloc modifierBloc(Bloc b);
    List<Bloc> getAllBloc ();
    Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc);
    Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer) ;

}
