package ev.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("sexoConverter")
public class SexoConverter implements Converter{

    //funcion para convertir de JSF, para este caso pasar el elemnto M -> Masculino
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //inicializamos variable sexo q devolveremos
        String sexo = "";
        
        if(value != null){
            //casteo el valor de value a un String
            sexo = (String)value;
            switch(sexo){
                case "M":
                    sexo = "Masculino";
                    break;
                case "F":
                    sexo = "Femenino";
                    break;    
            }
        }
        
        return sexo;
    }
    
    
}
