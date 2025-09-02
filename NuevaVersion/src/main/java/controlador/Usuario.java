package controlador;

public class Usuario {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String clave;

    // Constructor vacío (útil para frameworks y setters)
    public Usuario() {}

    // Constructor sin id (para crear un nuevo usuario)
    public Usuario(String cedula, String nombre, String apellido, String clave) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
    }

    // Constructor con id (para cuando lo lees de la BD o quieres eliminarlo)
    public Usuario(int id, String cedula, String nombre, String apellido, String clave) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    // Método para imprimir datos de forma legible
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", clave='" + clave + '\'' +
                '}';
    }
}
