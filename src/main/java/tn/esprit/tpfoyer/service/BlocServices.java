package tn.esprit.tpfoyer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.iBlocRepository;
import tn.esprit.tpfoyer.repository.iChambreRepository;
import tn.esprit.tpfoyer.repository.iFoyerRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlocServices implements iBlocServices{

    private final iBlocRepository blocRepository;
    private final iChambreRepository chambreRepository;
    private final iFoyerRepository foyerRepository;
    @Override
    public Bloc ajouterBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void supprimerBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);

    }

    @Override
    public Bloc getBloc(Long idBloc) {
        return blocRepository.findById(idBloc).orElseGet(null);
    }

    @Override
    public Bloc modifierBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public List<Bloc> getAllBloc() {
        return (List<Bloc>)blocRepository.findAll() ;
    }

    @Transactional
    @Override
    public Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        for(Long id:idChambre){
            Chambre chambre = chambreRepository.findById(id).orElse(null);
            assert chambre != null;
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }

        return blocRepository.save(bloc);
    }
    @Transactional
    @Override
    public Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);

        assert bloc != null;
        bloc.setFoyer(foyer);
        blocRepository.save(bloc);

        return bloc;
    }


}
