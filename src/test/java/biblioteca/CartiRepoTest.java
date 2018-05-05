package biblioteca;

import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class CartiRepoTest {

    @Spy
    private CartiRepoMock repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cautaCarte_emptyCarti() {
        doReturn(new ArrayList()).when(repo).getCarti();

        List<Carte> carti = repo.cautaCarte("autor");
        assertEquals(carti.size(), 0);
    }

    @Test
    public void cautaCarte_autorInexistent() {
        Carte carte = new Carte();
        carte.setReferenti(Collections.singletonList("autor"));

        doReturn(Collections.singletonList(carte)).when(repo).getCarti();

        List<Carte> carti = repo.cautaCarte("aaa");
        assertEquals(carti.size(), 0);
    }

    @Test
    public void cautaCarte_autorGasit() {
        Carte carte = new Carte();
        carte.setReferenti(Collections.singletonList("autor"));

        doReturn(Collections.singletonList(carte)).when(repo).getCarti();

        List<Carte> carti = repo.cautaCarte("autor");
        assertEquals(carti.size(), 1);
    }

    @Test
    public void getCartiOrdonateDinAnul_anValid() {
        List<Carte> carti = repo.getCartiOrdonateDinAnul(1973);
        assertEquals(carti.size(), 2);
        assertEquals(carti.get(0).getTitlu(), "Poezii");
    }

    @Test
    public void getCartiOrdonateDinAnul_anInvalid() {
        List<Carte> carti = repo.getCartiOrdonateDinAnul(-1);
        assertEquals(carti.size(), 0);
    }
}
