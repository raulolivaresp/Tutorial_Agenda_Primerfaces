package tpa.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tpa.model.Categoria;

/*
Invoca a la unidad de persistencia.
implementa el entitimanager para manejar las identidades  del abstractFacade
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //constructor q envia el tipo de dato q vamos a utilizar en el abstractFacade
    public CategoriaFacade() {
        super(Categoria.class);
    }
    
}
