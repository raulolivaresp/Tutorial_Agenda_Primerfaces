package tpa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="nota")
public class Nota implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    
    @ManyToOne
    @JoinColumn(name = "codigoPersona", nullable = false)
    private Persona persona;
    
    @ManyToOne
    @JoinColumn(name = "codigoCategoria")
    private Categoria categoria;
    
    @Column(name = "encabezado")
    private String encabezado;
    
    @Column(name = "cuerpo")
    private String cuerpo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha")
    private Date fecha;
    
    @Column(name = "comentarioAdmin")
    private String comentario;
    
    @Column(name = "valoracion")
    private short valoracion;

    //muchos gets y sets de nota
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public short getValoracion() {
        return valoracion;
    }

    public void setValoracion(short valoracion) {
        this.valoracion = valoracion;
    }

    //sobrescribimos hashCode y equals para poder luego comparar distintos elementos
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.codigo;
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
        final Nota other = (Nota) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }
    
    //toString nos permitira dar un alias o apodo a un objeto
    @Override
    public String toString() {
        return "Nota{" + "codigo=" + codigo + '}';
    }
    
        
}
