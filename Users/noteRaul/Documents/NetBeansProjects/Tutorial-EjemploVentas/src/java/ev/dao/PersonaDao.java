package ev.dao;

import ev.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PersonaDao extends Dao {

    //registrar una persona en la base de datos
    public void registrar(Persona per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT into Persona (nombre, sexo) values(?,?)");
            //agregar variables nombre y sexo
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;
            
        }finally{
            this.Cerrar();
        }
    }
    
    //obtener lista de personas que esten en la base de datos
    public List<Persona> listar() throws Exception{
        List<Persona> lista; //lista de personas
        ResultSet rs; //obtiene los valores de la base de datos
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM Persona");
            //ejecutamos el query
            rs = st.executeQuery();
            //inicializamos la lista como una arrayList
            lista = new ArrayList();
            //buscamos los valores, mientras alla un valor en el ResultSet
            while(rs.next()){
               Persona per = new Persona();
               //obtenemos el valor del codigo desde la columna "codigo" 
               per.setCodigo(rs.getInt("codigo"));
               per.setNombre(rs.getString("nombre"));
               per.setSexo(rs.getString("sexo"));
               //agregamos a la lista
               lista.add(per);         
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            //cerrar conexion
            this.Cerrar();
        }
        return lista;
    }
    
    //obtener el elemento persona a partir de un id dado
    public Persona leerId(Persona p) throws Exception{
        Persona pers;
        ResultSet rs;
            
        try{
            //conectar con la base de datos
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("SELECT codigo, nombre, sexo FROM Persona WHERE codigo = ?");
            //agregamos el valor del codigo q necesitamos
            st.setInt(1, p.getCodigo());
            //ejecutar y guardamos respuesta en el ResultSet
            rs = st.executeQuery();
            //inicializamos pers
            pers = new Persona();
            //buscamos los valores en el rs
            while(rs.next()){
               //obtenemos el valor del codigo desde la columna "codigo" 
               pers.setCodigo(rs.getInt("codigo"));
               pers.setNombre(rs.getString("nombre"));
               pers.setSexo(rs.getString("sexo"));
            }
            
        }catch(Exception e){
            throw e;
        }finally{
            //terminamos la conexion
            this.Cerrar();
        }
        return pers;
    }
    
    //modificar persona antes creada
    public void modificar(Persona per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE Persona SET nombre = ?, sexo = ? WHERE codigo = ? ");
            //agregar variables nombre y sexo
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.setInt(3, per.getCodigo());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;     
        }finally{
            this.Cerrar();
        }
    }
    
    //eliminar persona antes creada
    public void eliminar(Persona per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM Persona WHERE codigo = ? ");
            //agregamos el codigo al query
            st.setInt(1, per.getCodigo());
            //ejecutar
            st.executeUpdate();
                    
        }catch(Exception e){
            throw e;
        }finally{
            this.Cerrar();
        }
    }
    
}
