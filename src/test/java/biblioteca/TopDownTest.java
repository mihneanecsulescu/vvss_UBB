package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TopDownTest {
    private CartiRepoMock repo;
    private BibliotecaCtrl ctrl;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init() {
        this.repo = new CartiRepoMock();
        this.ctrl = new BibliotecaCtrl(repo);
    }

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
    public void integrationSecondRequest() throws Exception {
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
    }

    @Test
    public void integrationThirdRequest() throws Exception {
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
