import java.util.ArrayList;
import java.util.Date;

public interface PeticionTDA {
    //returns id
    public String agregarPeticion(Paciente paciente, String obraSocial, Date fechaCarga, Date fechaCalculadaEntrega);
    public boolean modificarPeticion(String idPeticion,Paciente paciente, String obraSocial, Date fechaCarga, Date fechaCalculadaEntrega);
    public void eliminarPeticion(String idPeticion);
    public ArrayList<Peticion> listarPeticionesCriticas();
}
