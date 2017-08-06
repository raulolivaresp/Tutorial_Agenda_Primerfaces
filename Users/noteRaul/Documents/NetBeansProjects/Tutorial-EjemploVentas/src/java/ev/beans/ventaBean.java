package ev.beans;

import ev.dao.VentaDao;
import ev.model.DetalleVenta;
import ev.model.Producto;
import ev.model.Venta;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ventaBean {
    
    private Venta venta = new Venta();
    private Producto producto = new Producto();
    private int cantidad;
    private List<DetalleVenta> lista = new ArrayList();
    
    //gets y sets de Venta
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }
    
    public void agregarVenta(){
        DetalleVenta det = new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);
        
        this.lista.add(det);
    }
    
    public void registrarVenta() throws Exception{
        VentaDao dao;     
        double monto = 0;
        try{
            //recorremos la lista agregando el precio de cada producto
            for(DetalleVenta det : lista){
              monto += det.getProducto().getPrecio();
            }
            dao = new VentaDao();
            venta.setMonto(monto);
            //registramos la venta y el detalle de esta
            dao.registrar(venta, lista);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LISTO","TODO BN"));
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "FALLO DE REGISTRO"));
        }finally{
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        
    }
    
}
