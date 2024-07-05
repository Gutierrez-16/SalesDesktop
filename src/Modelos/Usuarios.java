
package Modelos;


public class Usuarios {
    private int id;
    private String usuario;
    private String nombre;
    private String clave;
    private int caja;
    private String nombre_Caja;
    private String rol;
    private String estado;
    
    public Usuarios () {
        
    }

    public Usuarios(int id, String usuario, String nombre, String clave, int caja, String nombre_Caja, String rol, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.clave = clave;
        this.caja = caja;
        this.nombre_Caja = nombre_Caja;
        this.rol = rol;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getCaja() {
        return caja;
    }

    public void setCaja(int caja) {
        this.caja = caja;
    }

    public String getNombre_Caja() {
        return nombre_Caja;
    }

    public void setNombre_Caja(String nombre_Caja) {
        this.nombre_Caja = nombre_Caja;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
