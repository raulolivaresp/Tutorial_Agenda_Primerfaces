package tpa.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tpa.ejb.UsuarioFacadeLocal;
import tpa.model.Persona;
import tpa.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable{
 
    private Usuario usuario;
    private Persona persona;
    
    //Le ponemos EJB para q jsf lo identifica y ahorrarnos el "= new"
    //ya que las instancias no se inicializan
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    
    //despues de iniciar el programa se crean las variables usuario y persona
    @PostConstruct
    public void init(){
        usuario = new Usuario();
        persona = new Persona();
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    //metodo para registrar un nuevo usaurio
    public void registrar(){
        try{
            this.usuario.setCodigo(persona);
            usuarioEJB.create(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new
         FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario registrado con exito","Insercion concretada"));
        }catch(Exception e){
            //mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new 
         FacesMessage(FacesMessage.SEVERITY_FATAL,"Eror al crear usuario","No se logro registrar al usuario con exito"));
        }
    }
    
}
