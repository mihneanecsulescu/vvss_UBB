package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class BigBangTest {
    private CartiRepoMock repo;
    private BibliotecaCtrl ctrl;

    @Before
    public void init() {
        this.repo = new CartiRepoMock();
        this.ctrl = new BibliotecaCtrl(repo);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void unitTestFirstRequest() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("Titlu");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        int size = ctrl.getCarti().size();
        ctrl.adaugaCarte(carte);
        assertEquals(ctrl.getCarti().size(), size + 1);
    }

    @Test
    public void unitTestSecondRequest() {
        Carte carte = new Carte();
        carte.setReferenti(Collections.singletonList("autor"));

//        doReturn(Collections.singletonList(carte)).when(repo).getCarti();

        List<Carte> carti = repo.cautaCarte("Eminescu");
        assertEquals(carti.size(), 1);
    }

    @Test
    public void unitTestThirdRequest() {
        List<Carte> carti = repo.getCartiOrdonateDinAnul(1973);
        assertEquals(carti.size(), 2);
        assertEquals(carti.get(0).getTitlu(), "Poezii");
    }

    @Test
    public void integrationTest() throws Exception {
        //cerinta i
        Carte carte = new Carte();
        carte.setTitlu("M");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));
        ctrl.adaugaCarte(carte);
        //cerinta ii
        assertEquals(repo.cautaCarte("Autor unu").size(), 1);
        //cerinta iii
        assertEquals(repo.getCartiOrdonateDinAnul(2010).size(), 1);
    }
}
