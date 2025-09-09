package controlador;

public class Mascotas {
    private int id;
    private String nombre;
    private String tipo;
    private String raza;
    private String sexo; // 'macho' o 'hembra'
    private double precioBase;
    private double precioVenta;
    private String estado; // 'disponible' o 'vendido'
    private int idCliente;

    // Constructor vacío
    public Mascotas() {}

    // Constructor sin id (útil para crear nuevas mascotas)
    public Mascotas(String nombre, String tipo, String raza, String sexo, double precioBase, double precioVenta, String estado, int idCliente) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.sexo = sexo;
        this.precioBase = precioBase;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    // Constructor con id (útil para editar o eliminar una mascota existente)
    public Mascotas(int id, String nombre, String tipo, String raza, String sexo, double precioBase, double precioVenta, String estado, int idCliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.sexo = sexo;
        this.precioBase = precioBase;
        this.precioVenta = precioVenta;
        this.estado = estado;
        this.idCliente = idCliente;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    // Método toString para imprimir los datos
    @Override
    public String toString() {
        return "Mascotas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", raza='" + raza + '\'' +
                ", sexo='" + sexo + '\'' +
                ", precioBase=" + precioBase +
                ", precioVenta=" + precioVenta +
                ", estado='" + estado + '\'' +
                ", idCliente=" + idCliente +
                '}';
    }
}
