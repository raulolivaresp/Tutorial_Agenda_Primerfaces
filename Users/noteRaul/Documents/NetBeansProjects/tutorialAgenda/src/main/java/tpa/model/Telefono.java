package tpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="telefono")
public class Telefono implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int codigo;
    
    //muchos telefonos - 1 persona asi q referenciamos la columna con la entidad persona
    @ManyToOne
    @JoinColumn(name = "codigoPersona", nullable = false)
    private Persona persona;
    
    @Column(name = "numero")   
    private String numero;

    //gets y sets de la clase
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    //sobrescribimos hashCode y equals para poder luego comparar distintos elementos
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefono other = (Telefono) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
 
    //toString nos permitira dar un alias o apodo a un objeto
    @Override
    public String toString() {
        return "Telefono{" + "codigo=" + codigo + '}';
    }
    
}
