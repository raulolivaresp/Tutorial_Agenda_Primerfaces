package ev.dao;

import ev.model.DetalleVenta;
import ev.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


public class VentaDao extends Dao{
    
    //registrar una venta en la base de datos
    public void registrar(Venta ven, List<DetalleVenta> listaDet) throws Exception{
        try{
            this.Conectar(); //conectar con la mysql
            
            this.getCn().setAutoCommit(false); 
            //preparar SQL query
            PreparedStatement st = this.getCn().prepareStatement("INSERT into Venta (fecha, codPersona, monto) values(?,?,?)");
            //agregar variables nombre y sexo
            st.setDate(1, ven.getFecha());
            st.setInt(2, ven.getPersona().getCodigo() );
            st.setDouble(3, ven.getMonto() );
            //ejecutar
            st.executeUpdate();
            st.close();
           
            PreparedStatement st2 = this.getCn().prepareStatement("SELECT LAST_INSERT_ID() FROM venta limit 1");
            ResultSet rs;//resultado del sql query
            int codigoVenta=0; //guardamos aqui el ultima venta realizada
            rs = st2.executeQuery();
            while(rs.next()){
                codigoVenta = rs.getInt(1);
            }
            
            for(DetalleVenta det: listaDet){
                // ahora det sera el elemento con que interactuaeremos cada iteracion
                PreparedStatement st3 = this.getCn().prepareStatement("INSERT into Detalleventa (codVenta, codProducto, cantidad) values(?,?,?)");
                //agregar variables 
                st3.setInt(1, codigoVenta );
                st3.setInt(2, det.getProducto().getCodigo() );
                st3.setInt(3, det.getCantidad() );
                //ejecutar
                st3.executeUpdate();
                st3.close();
                
                //ejecutamos el bloque
                this.getCn().commit();
            }

                    
        }catch(Exception e){
            //si hay un error ejecutamoe el rollback y desasemos lo hecho
            this.getCn().rollback();
            throw e;
            
        }finally{
            this.Cerrar();
        }
    }
}
