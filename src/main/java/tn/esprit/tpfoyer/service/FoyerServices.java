package tn.esprit.tpfoyer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.iBlocRepository;
import tn.esprit.tpfoyer.repository.iFoyerRepository;
import tn.esprit.tpfoyer.repository.iUniversiteRepository;

import java.util.List;
@RequiredArgsConstructor
@Service
public class FoyerServices implements iFoyerServices {

   //@Autowired
    final iFoyerRepository foyerRepository;
    final iBlocRepository  blocRepository;
    final iUniversiteRepository universiteRepository;
  /*  public FoyerServices(iFoyerRepository foyerRepository) {
        this.foyerRepository = foyerRepository;
    }*/

    @Override
    public Foyer ajouterFoyer(Foyer f) {

        return foyerRepository.save(f);
    }

    @Override
    public void supprimerFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);

    }

    @Override
    public Foyer getFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public List<Foyer> getAllFoyer() {
        return (List<Foyer>) foyerRepository.findAll();
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f) ;
    }
    @Transactional
    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        if (universite != null) {
            foyerRepository.save(foyer);
            universite.setFoyer(foyer);
            universiteRepository.save(universite);
            return foyer;
        } else {

            return null;
        }
    }

    @Override
    public Foyer getfoyerbyid(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }


}
