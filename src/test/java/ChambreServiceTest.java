import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.iBlocRepository;
import tn.esprit.tpfoyer.repository.iChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServices;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChambreServiceTest {

    @Mock
    private iChambreRepository chambreRepository;
    @Mock
    private iBlocRepository blocRepository;

    @InjectMocks
    private ChambreServices chambreService;

    @Test
    public void testAjouterChambre() {
        // Créer un objet Chambre fictif pour le test
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SIMPLE);
        chambre.setDescription("Description de la chambre");
        chambre.setEtat(true);
        chambre.setCreatedAt(new Date());
        chambre.setUpdatedAt(new Date());

        // Configurer le comportement du mock IChambreRepository
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Appeler la méthode à tester
        Chambre chambreAjoutee = chambreService.ajouterChambre(chambre);

        // Vérifier que la méthode save() de chambreRepository a été appelée avec la chambre
        verify(chambreRepository).save(chambre);

        // Vérifier que la chambre ajoutée est celle retournée par la méthode
        assert chambreAjoutee != null;
        assert chambreAjoutee.getIdChambre().equals(1L);
        assert chambreAjoutee.getNumeroChambre().equals(101L);
        assert chambreAjoutee.getTypeC().equals(TypeChambre.SIMPLE);
        assert chambreAjoutee.getDescription().equals("Description de la chambre");
        assert chambreAjoutee.isEtat();
    }
    @Test
    public void testSupprimerChambre() {
        // Identifier un ID de chambre fictif à supprimer
        Long idChambre = 1L;

        // Appeler la méthode à tester
        chambreService.supprimerChambre(idChambre);

        // Vérifier que la méthode deleteById() de chambreRepository a été appelée avec l'ID de chambre spécifié
        verify(chambreRepository).deleteById(idChambre);
    }
    @Test
    public void testModifierChambre() {
        // Créer un objet Chambre fictif pour le test
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SIMPLE);
        // Autres attributs...

        // Définir le comportement attendu du mock chambreRepository lors de l'appel à save()
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Appeler la méthode à tester
        Chambre chambreModifiee = chambreService.modifierChambre(chambre);

        // Vérifier que la méthode save() de chambreRepository a été appelée avec l'objet Chambre spécifié
        verify(chambreRepository).save(chambre);

        // Vérifier que la chambre modifiée retournée par la méthode est la même que celle renvoyée par le mock
        assertEquals(chambre, chambreModifiee);
    }
    @Test
    public void testGetChambre() {
        Long idChambre = 1L;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(idChambre);
        when(chambreRepository.findById(idChambre)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.getChambre(idChambre);

        assertEquals(chambre, result);
    }
    @Test
    public void testGetAllChambre() {
        List<Chambre> chambreList = Arrays.asList(new Chambre(), new Chambre());
        when(chambreRepository.findAll()).thenReturn(chambreList);

        List<Chambre> result = chambreService.getAllChambre();

        assertEquals(chambreList, result);
    }
    @Test
    public void testGetChambresParBlocEtType() {
        Long idBloc = 1L;
        TypeChambre typeC = TypeChambre.SIMPLE;
        List<Chambre> chambreList = Arrays.asList(new Chambre(), new Chambre());
        when(chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC)).thenReturn(chambreList);

        List<Chambre> result = chambreService.getChambresParBlocEtType(idBloc, typeC);

        assertEquals(chambreList, result);
    }
    @Test
    public void testAjouterChambreEtAffecterBloc() {
        Long idBloc = 1L;
        Bloc bloc = new Bloc();
        bloc.setIdBloc(idBloc);
        Chambre chambre = new Chambre();

        when(blocRepository.findById(idBloc)).thenReturn(Optional.of(bloc));
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.ajouterChambreEtAffecterBloc(chambre, idBloc);

        assertEquals(bloc, result.getBloc());
        verify(chambreRepository, times(2)).save(chambre); // La méthode save est appelée deux fois, une fois pour sauvegarder la chambre et une fois pour mettre à jour la chambre avec le bloc.
    }

}
