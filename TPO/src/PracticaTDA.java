import java.util.ArrayList;

public interface PracticaTDA {
    public int agregarPractica(String nombre, String grupo, ArrayList<String> valoresCriticos,ArrayList<String> valoresReservados, int cantidadHoras, boolean estaHabilitada);
    public void deshabilitarPractica(int codigo);
    public boolean modificarPractica(int codigo,String nombre, String grupo, ArrayList<String> valoresCriticos,ArrayList<String> valoresReservados, int cantidadHoras, boolean estaHabilitada);
    public void eliminarPractica(int codigo);

    public ArrayList<String> obtenerValoresCriticos(int codigo);

}
