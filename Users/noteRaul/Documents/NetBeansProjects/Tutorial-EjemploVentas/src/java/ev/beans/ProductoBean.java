package ev.beans;

import ev.dao.ProductoDao;
import ev.model.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ProductoBean {
    
    private Producto producto = new Producto();
    private List<Producto> lstProductos;
    //variable para decidir si aparece el boton registrar o modificar
    private String accion;
    
    //get y set para ver la entidad producto en el index
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //get y set para ver la entidad lstProducto en el index
    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
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
    
    //limpia las variable producto con los valores por defecto
    //asi los dialogos apareceran limpios
    public void limpiar(){
        this.producto.setCodigo(0);
        this.producto.setNombre("");
        this.producto.setPrecio(0.0);
    }
    
    //ingresar una producto a la base de datos
    //no es llamado desde el index asi q puede ser privado
    private void registrar() throws Exception{
        ProductoDao dao;
        
        try{
            //obtenemos las funciones necesarias desde el archivo ProductoDao
            //Las funciones en Dao son las que se comunican con la base de datos
            dao = new ProductoDao();
            dao.registrarProducto(producto);
            //listar nuevamente , mostrar productos en la pag
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
    
    //Update producto
    private void modificar() throws Exception{
        ProductoDao dao;
        
        try{
            //obtenemos las funciones necesarias desde el archivo ProductoDao
            //Las funciones en Dao son las que se comunican con la base de datos
            dao = new ProductoDao();
            //modificamos la base de  datos
            dao.modificarProducto(producto);
            //luego de modificarlo, actualizamos la lista de productos
            this.listar("V");
            
        }catch(Exception e){
            throw e;
        }
    }
    
    //obtener lista de productos desde la base de datos
    public void listar(String valor) throws Exception{
        ProductoDao dao;
        
        try{
            if(valor.equals("F")){                
                if(isPostBack() == false){
                    //obtenemos las funciones necesarias desde el archivo ProductoDao
                    dao = new ProductoDao();
                    lstProductos = dao.listarProducto();
                }
            }else{
                dao = new ProductoDao();
                lstProductos = dao.listarProducto();
            }
            
        }catch(Exception e){
            throw e;
        }
    }
    
    //buscar en la bd un elemento producto a partir de su id
    public void leerId(Producto p) throws Exception{
        ProductoDao dao;
        Producto temporal;//variable temporal
        
        try{
            //obtenemos las funciones necesarias desde el archivo ProductoDao
            dao = new ProductoDao();
            //obtenemos el elemeto producto 
            temporal = dao.leerIdPrducto(p);
            //si el elemento temporal no es nulo, lo cargamos en nuestro elemento fijo
            if(temporal != null){
                this.producto = temporal;
                this.accion = "Modificar";
            }
            
        }catch(Exception e){
            throw e;
        }
    }
    
    //eliminar
    public void eliminar(Producto per) throws Exception{
        ProductoDao dao;
        try{
            //obtenemos las funciones necesarias desde el archivo ProductoDao
            dao = new ProductoDao();
            dao.eliminarProducto(per);
            //luego de modificarlo, actualizamos la lista de productos
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
}
