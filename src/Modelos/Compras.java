
package Modelos;

public class Compras {
    private int id;
    private int proveedor_id;
    private String nombreProveedor;
    private double total;
    private String fecha;
    private int provedor_id;

    public Compras() {
    }

    public Compras(int id, int proveedor_id, String nombreProveedor, double total, String fecha, int provedor_id) {
        this.id = id;
        this.proveedor_id = proveedor_id;
        this.nombreProveedor = nombreProveedor;
        this.total = total;
        this.fecha = fecha;
        this.provedor_id = provedor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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

    public int getProvedor_id() {
        return provedor_id;
    }

    public void setProvedor_id(int provedor_id) {
        this.provedor_id = provedor_id;
    }

   
    
    
}
