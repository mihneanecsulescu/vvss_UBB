package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class BibliotecaCtrlTest {
    private BibliotecaCtrl ctrl;

    @Before
    public void init() {
        this.ctrl = new BibliotecaCtrl(new CartiRepoMock());
    }

    @Test
    public void testAdaugaCarte_ValidECP() throws Exception {
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

    @Test(expected = Exception.class)
    public void testAdaugaCarte_InvalidECP_titluInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("123");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test()
    public void testAdaugaCarte_ValidBVA() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("M");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        int size = ctrl.getCarti().size();
        ctrl.adaugaCarte(carte);
        assertEquals(ctrl.getCarti().size(), size + 1);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_InvalidBVA_anInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("M");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(0);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidECP_titluVid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(0);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidECP_autorInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("M");
        carte.setReferenti(Arrays.asList("1", "2"));
        carte.setAnAparitie(0);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidECP_anInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("M");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(-1);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidBVA_autorInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("Titlu");
        carte.setReferenti(Collections.singletonList(""));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidBVA_anInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("Titlu");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2019);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Arrays.asList("keyword unu", "keyword doi"));

        ctrl.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void testAdaugaCarte_invalidBVA_cuvantCheieInvalid() throws Exception {
        Carte carte = new Carte();
        carte.setTitlu("Titlu");
        carte.setReferenti(Arrays.asList("Autor unu", "Autor doi"));
        carte.setAnAparitie(2010);
        carte.setEditura("Editura");
        carte.setCuvinteCheie(Collections.singletonList(""));

        ctrl.adaugaCarte(carte);
    }
}
