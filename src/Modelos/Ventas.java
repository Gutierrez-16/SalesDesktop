
package Modelos;


public class Ventas {
    private int id;
    private int cliente_id;
    private String nombreCliente;
    private double total;
    private String fecha;

    public Ventas() {
    }

    public Ventas(int id, int cliente_id, String nombreCliente, double total, String fecha) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.nombreCliente = nombreCliente;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}
