
package Modelos;


public class Productos {
    private int id;
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precio_compra;
    private double precio_venta;
    private int proveedor_id;
    private int categoria_id;
    private int medida_id;
    private String estado;
    private String proveedor;
    private String medida;
    private String cat;

    public Productos() {
    }

    public Productos(int id, String codigo, String descripcion, int cantidad, double precio_compra, double precio_venta, int proveedor_id, int categoria_id, int medida_id, String estado, String proveedor, String medida, String cat) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.proveedor_id = proveedor_id;
        this.categoria_id = categoria_id;
        this.medida_id = medida_id;
        this.estado = estado;
        this.proveedor = proveedor;
        this.medida = medida;
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getMedida_id() {
        return medida_id;
    }

    public void setMedida_id(int medida_id) {
        this.medida_id = medida_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    
    
}
