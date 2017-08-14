package tpa.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tpa.ejb.CategoriaFacadeLocal;
import tpa.model.Categoria;

@Named
@ViewScoped
public class CategoriaController implements Serializable {
    
    //Le ponemos EJB para q jsf lo identifica y ahorrarnos el "= new"
    //ya que las instancias no se inicializan
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    private Categoria categoria; //para guardar los objetos

    //gets y sets del objeto
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }    
    
    //inicializamos el objeto
    @PostConstruct
    public void init(){
        categoria = new Categoria();
    }

    //registrar nueva categoria
    public void registrar(){
        try{
            //creamos una nueva categoria
            categoriaEJB.create(categoria);
        }catch(Exception e){
            //msj fallo
        }
        
    }
    
}
