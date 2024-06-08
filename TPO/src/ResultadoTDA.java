import java.util.ArrayList;

public interface ResultadoTDA {
    public void agregarResultado(ArrayList<String> valores);
    public void modificarResultado(int idResultado,ArrayList<String> valores);

    public void eliminarResultado(int idResultado);

    public void obtenerValor(int idResultado,String elemento);
}
