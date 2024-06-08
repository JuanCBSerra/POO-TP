public interface PacienteTDA {
    public void agregarPaciente(int dni,String nombre,String domicilio,String email,String sexo,int edad);

    public void eliminarPaciente(int dni);

    public void modificarPaciente(int dni,String nombre,String domicilio,String email,String sexo,int edad);

}
