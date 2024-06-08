import java.util.ArrayList;
import java.util.Date;

public class Peticion implements PeticionTDA{
    private String idPeticion;
    private Paciente paciente;
    private String obraSocial;
    private Date fechaCarga;
    private Date fechaCalculadaEntrega;

    @Override
    public String agregarPeticion(Paciente paciente, String obraSocial, Date fechaCarga, Date fechaCalculadaEntrega) {
        return null;
    }

    @Override
    public boolean modificarPeticion(String idPeticion, Paciente paciente, String obraSocial, Date fechaCarga, Date fechaCalculadaEntrega) {
        return false;
    }

    @Override
    public void eliminarPeticion(String idPeticion) {

    }

    @Override
    public ArrayList<Peticion> listarPeticionesCriticas() {
        return null;
    }
}
