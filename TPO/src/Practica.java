import java.util.ArrayList;

public class Practica implements PracticaTDA{
    private int codigo;
    private String nombre;
    private String grupo;
    private ArrayList<String> valoresCriticos;
    private ArrayList<String> valoresReservados;
    private int cantidadHoras;
    private boolean estaHabilitada;

    @Override
    public int agregarPractica(String nombre, String grupo, ArrayList<String> valoresCriticos, ArrayList<String> valoresReservados, int cantidadHoras, boolean estaHabilitada) {
        return 0;
    }

    @Override
    public void deshabilitarPractica(int codigo) {

    }

    @Override
    public boolean modificarPractica(int codigo, String nombre, String grupo, ArrayList<String> valoresCriticos, ArrayList<String> valoresReservados, int cantidadHoras, boolean estaHabilitada) {
        return false;
    }

    @Override
    public void eliminarPractica(int codigo) {

    }

    @Override
    public ArrayList<String> obtenerValoresCriticos(int codigo) {
        return null;
    }
}
