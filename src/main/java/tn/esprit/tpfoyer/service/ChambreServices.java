package tn.esprit.tpfoyer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.iBlocRepository;
import tn.esprit.tpfoyer.repository.iChambreRepository;
import tn.esprit.tpfoyer.repository.imageRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChambreServices implements iChambreServices {

    private final iChambreRepository chambreRepository;
    private final iBlocRepository blocRepository;
    private final imageRepository imageRepository ;
    @Override
    public Chambre ajouterChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void supprimerChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);

    }

    @Override
    public Chambre modifierChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre getChambre(Long idChambre) {
        return chambreRepository.findById(idChambre).orElseGet(null);
    }

    @Override
    public List<Chambre> getAllChambre() {
        return (List<Chambre>)chambreRepository.findAll();
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeC(idBloc,typeC);
    }
    @Transactional
    @Override
    public Chambre affecterBlocAChambre(long idChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        if (bloc != null && chambre != null) {
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return chambre;
    }
    @Transactional
    @Override
    public Chambre ajouterChambreEtAffecterBloc(Chambre chambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        if (bloc != null) {

            Chambre addedChambre = chambreRepository.save(chambre);

            // Affecter la chambre au bloc
            addedChambre.setBloc(bloc);
            chambreRepository.save(addedChambre);

            return addedChambre;
        }

        return null;
    }
    public Chambre saveChambreWithImage(Chambre chambre, MultipartFile image) throws IOException {
//
//
// Sauvegarder l'image dans la base de données
        chambre.setImagebyte(image.getBytes());
        return chambreRepository.save(chambre);
    }

    public byte[] getImageById(Long id) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(id);
        if (optionalChambre.isPresent()) {
            return optionalChambre.get().getImagebyte();
        }
        return new byte[0]; // Retourne une image vide si la chambre n'est pas trouvée
    }
    @Override
    public List<Chambre> getChambresParNomBloc(String nomBloc) {
        Bloc b = blocRepository.getBlocByNomBloc(nomBloc);
        return chambreRepository.findByBloc(b) ;
    }



}