package ev.beans;

import ev.dao.PersonaDao;
import ev.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PersonaBean {
    
    private Persona persona = new Persona();
    private List<Persona> lstPersonas;
    //variable para decidir si aparece el boton registrar o modificar
    private String accion;
    
    //get y set para ver la entidad persona en el index
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    //get y set para ver la entidad lstPersona en el index
    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    //gets y sets para Accion
    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
    //ve si la peticion q esta enviando el cliente es de tipo postback, 
    //asi controlamos la accion event en el persona.xhtml no se maneje siempre
    private boolean isPostBack(){
        boolean respuesta;
        respuesta = FacesContext.getCurrentInstance().isPostback();
        return respuesta;
    }
    
    //evalua si la 'accion' fue modificar o registrar
    public void operacion() throws Exception{
        switch(accion){
            case "Registrar":
                this.registrar();
                this.limpiar();
                break;
            case "Modificar":
                this.modificar();
                this.limpiar();
                break;
        }
    }
    
    //limpia las variable persona con los valores por defecto
    //asi los dialogos apareceran limpios
    public void limpiar(){
        this.persona.setCodigo(0);
        this.persona.setNombre("");
        this.persona.setSexo("");
    }
    
    //ingresar una persona a la base de datos
    //no es llamado desde el index asi q puede ser privado
    private void registrar() throws Exception{
        PersonaDao dao;
        
        try{
            //obtenemos las funciones necesarias desde el archivo PersonaDao
            //Las funciones en Dao son las que se comunican con la base de datos
            dao = new PersonaDao();
            dao.registrar(persona);
            //listar nuevamente , mostrar personas en la pag
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
    
    //Update persona
    private void modificar() throws Exception{
        PersonaDao dao;
        
        try{
            //obtenemos las funciones necesarias desde el archivo PersonaDao
            //Las funciones en Dao son las que se comunican con la base de datos
            dao = new PersonaDao();
            //modificamos la base de  datos
            dao.modificar(persona);
            //luego de modificarlo, actualizamos la lista de personas
            this.listar("V");
            
        }catch(Exception e){
            throw e;
        }
    }
    
    //obtener lista de personas desde la base de datos
    public void listar(String repite) throws Exception{
        PersonaDao dao;      
        try{
            if(repite.equals("F")){
                //si el postback es falso carga la pag web
                //si es verdadero entonces la resp. es la misma pag web
                if(isPostBack() == false){
                    //obtenemos las funciones necesarias desde el archivo PersonaDao
                    dao = new PersonaDao();
                    lstPersonas = dao.listar();
                }
            }else{
                //obtenemos las funciones necesarias desde el archivo PersonaDao
                dao = new PersonaDao();
                lstPersonas = dao.listar();
            }
        }catch(Exception e){
            throw e;
        }
    }
    
    //buscar en la bd un elemento persona a partir de su id
    public void leerId(Persona p) throws Exception{
        PersonaDao dao;
        Persona temporal;//variable temporal
        
        try{
            //obtenemos las funciones necesarias desde el archivo PersonaDao
            dao = new PersonaDao();
            //obtenemos el elemeto persona 
            temporal = dao.leerId(p);
            //si el elemento temporal no es nulo, lo cargamos en nuestro elemento fijo
            if(temporal != null){
                this.persona = temporal;
                this.accion = "Modificar";
            }
            
        }catch(Exception e){
            throw e;
        }
    }
    
    //eliminar
    public void eliminar(Persona per) throws Exception{
        PersonaDao dao;
        try{
            //obtenemos las funciones necesarias desde el archivo PersonaDao
            dao = new PersonaDao();
            dao.eliminar(per);
            //luego de modificarlo, actualizamos la lista de personas
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
}
