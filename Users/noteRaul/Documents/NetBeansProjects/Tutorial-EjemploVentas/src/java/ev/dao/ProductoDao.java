package ev.dao;

import ev.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductoDao extends Dao {

    //registrar una proona en la base de datos
    public void registrarProducto(Producto p) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT into Producto (nombre, precio) values(?,?)");
            //agregar variables nombre y sexo
            st.setString(1, p.getNombre());
            st.setDouble(2, p.getPrecio());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;
            
        }finally{
            this.Cerrar();
        }
        
    }
    
    //obtener lista de proonas que esten en la base de datos
    public List<Producto> listarProducto() throws Exception{
        List<Producto> lista; //lista de proonas
        ResultSet rs; //obtiene los valores de la base de datos
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio FROM Producto");
            //ejecutamos el query
            rs = st.executeQuery();
            //inicializamos la lista como una arrayList
            lista = new ArrayList();
            //buscamos los valores, mientras alla un valor en el ResultSet
            while(rs.next()){
               Producto pro = new Producto();
               //obtenemos el valor del codigo desde la columna "codigo" 
               pro.setCodigo(rs.getInt("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setPrecio(rs.getDouble("precio"));
               //agregamos a la lista
               lista.add(pro);         
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            //cerrar conexion
            this.Cerrar();
        }
        return lista;
    }
    
    //obtener el elemento producto a partir de un id dado
    public Producto leerIdPrducto(Producto p) throws Exception{
        Producto pro;
        ResultSet rs;
            
        try{
            //conectar con la base de datos
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, precio FROM Producto WHERE codigo = ?");
            //agregamos el valor del codigo q necesitamos
            st.setInt(1, p.getCodigo());
            //ejecutar y guardamos respuesta en el ResultSet
            rs = st.executeQuery();
            //inicializamos Producto
            pro = new Producto();
            //buscamos los valores en el rs
            while(rs.next()){
               //obtenemos el valor del codigo desde la columna "codigo" 
               pro.setCodigo(rs.getInt("codigo"));
               pro.setNombre(rs.getString("nombre"));
               pro.setPrecio(rs.getDouble("precio"));
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            //terminamos la conexion
            this.Cerrar();
        }
        return pro;
    }
    
    //modificar proona antes creada
    public void modificarProducto(Producto pro) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE Producto SET nombre = ?, precio = ? WHERE codigo = ? ");
            //agregar variables nombre y sexo
            st.setString(1, pro.getNombre());
            st.setDouble(2, pro.getPrecio());
            st.setInt(3, pro.getCodigo());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;     
        }finally{
            this.Cerrar();
        }
    }
    
    //eliminar proona antes creada
    public void eliminarProducto(Producto pro) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM Producto WHERE codigo = ? ");
            //agregamos el codigo al query
            st.setInt(1, pro.getCodigo());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;
        }finally{
            this.Cerrar();
        }
    }
    
}
