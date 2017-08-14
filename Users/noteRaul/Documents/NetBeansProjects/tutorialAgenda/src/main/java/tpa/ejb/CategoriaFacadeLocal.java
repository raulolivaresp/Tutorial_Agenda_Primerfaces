package tpa.ejb;

import java.util.List;
import javax.ejb.Local;
import tpa.model.Categoria;

/*
Interfaz para ingresar a los metodos del abstracFacade y al categoriFacade 
utilizando la identidad Categoria
 */
@Local
public interface CategoriaFacadeLocal {

    void create(Categoria categoria);

    void edit(Categoria categoria);

    void remove(Categoria categoria);

    Categoria find(Object id);

    List<Categoria> findAll();

    List<Categoria> findRange(int[] range);

    int count();
    
}
