
package Modelos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;


public class ProductosDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;


    public boolean registrar(Productos pro){
        String sql = "INSERT INTO productos (codigo, descripcion, precio_compra, precio_venta, proveedor_id, categoria_id, medida_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio_compra());
            ps.setDouble(4, pro.getPrecio_venta());
            ps.setInt(5, pro.getProveedor_id());
            ps.setInt(6, pro.getCategoria_id());
            ps.setInt(7, pro.getMedida_id());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public List ListaProductos(String valor){
        List<Productos> ListaPro = new ArrayList();
        String sql = "SELECT * FROM productos ORDER BY estado ASC";
        String buscar = "SELECT * FROM productos WHERE codigo LIKE '%"+valor+"%' OR descripcion LIKE '%"+valor+"%'";
        try {
            con = cn.getConexion();
            if (valor.equalsIgnoreCase("")) {
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
            }else{
                ps = con.prepareStatement(buscar);
                rs = ps.executeQuery();
            }
            while(rs.next()){
                Productos pro = new Productos();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setEstado(rs.getString("estado"));
                ListaPro.add(pro);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaPro;
    }
    
    public boolean modificar(Productos pro){
        String sql = "UPDATE productos SET codigo = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, proveedor_id = ?, categoria_id = ?, medida_id = ? WHERE id = ? ";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getDescripcion());
            ps.setDouble(3, pro.getPrecio_compra());
            ps.setDouble(4, pro.getPrecio_venta());
            ps.setInt(5, pro.getProveedor_id());
            ps.setInt(6, pro.getCategoria_id());
            ps.setInt(7, pro.getMedida_id());
            ps.setInt(8, pro.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public boolean accion(String estado, int id){
        String sql = "UPDATE productos SET estado = ? WHERE id = ?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            ps.setInt(2, id);
            ps.execute();
            return true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }
    
    public Productos buscarPro(int id){
        String sql = "SELECT p.*, pr.id, pr.proveedor, m.id, m.medida, c.id, c.categoria\n" +
"FROM productos p\n" +
"INNER JOIN proveedor pr ON p.proveedor_id = pr.id\n" +
"INNER JOIN medidas m ON p.medida_id = m.id\n" +
"INNER JOIN categorias c ON p.categoria_id = c.id WHERE p.id = ?";
        Productos pro = new Productos();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setCodigo(rs.getString("codigo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setProveedor_id(rs.getInt("proveedor_id"));
                pro.setMedida_id(rs.getInt("medida_id"));
                pro.setCategoria_id(rs.getInt("categoria_id"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setMedida(rs.getString("medida"));
                pro.setCat(rs.getString("categoria"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }
    
    public Productos buscarCodigo(String codigo){
        String sql = "SELECT * FROM productos WHERE codigo =? AND estado = 'Activo'";
        Productos pro = new Productos();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio_compra"));
                pro.setPrecio_venta(rs.getDouble("precio_venta"));
                pro.setCantidad(rs.getInt("cantidad"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }
    
    public Productos buscarId(int id){
        String sql = "SELECT * FROM productos WHERE codigo =?";
        Productos pro = new Productos();
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro.setCantidad(rs.getInt("cantidad"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return pro;
    }
   
    public boolean registrarCompra(int id, String total){
        String sql = "INSERT INTO compras (proveedor_id, total) VALUES (?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean registrarCompraDetalle(int compra_id, int id ,double precio, int cant, double sub_total){
        String sql = "INSERT INTO detalle_compra (compra_id, producto_id, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra_id);
            ps.setInt(2, id);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean registrarVentaDetalle(int venta_id, int producto_id ,double precio, int cant, double sub_total){
        String sql = "INSERT INTO detalle_ventas (venta_id, producto_id, precio, cantidad, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta_id);
            ps.setInt(2, producto_id);
            ps.setDouble(3, precio);
            ps.setInt(4, cant);
            ps.setDouble(5, sub_total);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public boolean ActualizarStock(int cant, int id){
        String sql = "UPDATE productos SET cantidad = cantidad + ? WHERE id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, id);
            ps.execute();
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public int getUltimoId (String tabla){
        String sql = "SELECT MAX(id) AS id FROM "+tabla;
        int id = 0;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
               id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    public List ListaDetalle(int compra_id){
        List<Productos> ListaPro = new ArrayList();
        String sql = "SELECT c.*, d.*, p.id, p.descripcion\n" +
"FROM compras c\n" +
"INNER JOIN detalle_compra d ON d.compra_id = c.id\n" +
"INNER JOIN productos p ON p.id = d.producto_id\n" +
"WHERE c.id = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra_id);
            rs = ps.executeQuery();
            while(rs.next()){
                Productos pro = new Productos();
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio_compra(rs.getDouble("precio"));
                pro.setPrecio_venta(rs.getDouble("subtotal"));
                ListaPro.add(pro);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ListaPro;
    }
    
    public void generarReporte(int compra_id){
        double totalGeneral = 0.00;
        String fecha = "";
        String nomProveedor = "";
        String dirProveedor = "";
        String telProveedor = "";
        String mensaje = "";
        try {
            Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "Compra.pdf");
            
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            PdfPTable empresa = new PdfPTable(4);
            empresa.setWidthPercentage(100);
            float [] tamanioEncabezado = new float[]{15f, 15f, 40f, 30f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            Image img = Image.getInstance(getClass().getResource("/Img/Riga.png"));
            empresa.addCell(img);
            empresa.addCell("");
            
            String sql = "SELECT * FROM configuracion ";
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()){
                    mensaje = rs.getString("mensaje");
                    empresa.addCell("Ruc: "+rs.getString("ruc")+ "\nNombre: "+ rs.getString("nombre")
                    +"\nTelefono: "+rs.getString("telefono")+ "\nDirección: "+ rs.getString("direccion"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            String consultaProveedor = "SELECT p.proveedor, p.telefono, p.direccion, c.fecha, c.total FROM compras c \n" +
"INNER JOIN proveedor p ON c.proveedor_id = p.id WHERE c.id = "+ compra_id;
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProveedor);
                rs = ps.executeQuery();
                if (rs.next()){
                    //Datos del proveedor
                    nomProveedor = rs.getString("proveedor");
                    telProveedor = rs.getString("telefono");
                    dirProveedor = rs.getString("direccion");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
             empresa.addCell("N° Compra: "+compra_id+ "\nVendedor: "+ "Alex"
                    +"\nFecha: "+ fecha);
            
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            
            Paragraph titProveedor = new Paragraph();
            titProveedor.add("Datos del Proveedor");
            titProveedor.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProveedor);
            doc.add(Chunk.NEWLINE);
            
            //Mostrar Datos del Proveedor
            PdfPTable proveedor = new PdfPTable(3);
            proveedor.setWidthPercentage(100);
            float [] tamanioProveedor = new float[]{40, 20f, 40f};
            proveedor.setWidths(tamanioProveedor);
            proveedor.setHorizontalAlignment(Element.ALIGN_LEFT);
            proveedor.getDefaultCell().setBorder(0);
            PdfPCell nomPr = new PdfPCell(new Phrase("Nombre",negrita));
            PdfPCell telPr = new PdfPCell(new Phrase("Telefono",negrita));
            PdfPCell diPr = new PdfPCell(new Phrase("Dirección",negrita));
            nomPr.setBorder(0);
            telPr.setBorder(0);
            diPr.setBorder(0);
            nomPr.setBackgroundColor(BaseColor.DARK_GRAY);
            telPr.setBackgroundColor(BaseColor.DARK_GRAY);
            diPr.setBackgroundColor(BaseColor.DARK_GRAY);
            
            proveedor.addCell(nomPr);
            proveedor.addCell(telPr);
            proveedor.addCell(diPr);
            
            //proveedor
            proveedor.addCell(nomProveedor);
            proveedor.addCell(telProveedor);
            proveedor.addCell(dirProveedor);
            
            doc.add(proveedor);
            doc.add(Chunk.NEWLINE);
            
            Paragraph titProducto = new Paragraph();
            titProducto.add("Detalles de la compra");
            titProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProducto);
            doc.add(Chunk.NEWLINE);
            
            //Mostrar Datos del Proveedor
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float [] tamanioProducto = new float[]{50, 10f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            producto.getDefaultCell().setBorder(0);
            PdfPCell desPro = new PdfPCell(new Phrase("Descripción",negrita));
            PdfPCell cantPro = new PdfPCell(new Phrase("Cant",negrita));
            PdfPCell precioPro = new PdfPCell(new Phrase("Precio",negrita));
            PdfPCell subtotalPro = new PdfPCell(new Phrase("SubTotal",negrita));
            
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precioPro.setBorder(0);
            subtotalPro.setBorder(0);
            
            desPro.setBackgroundColor(BaseColor.DARK_GRAY);
            cantPro.setBackgroundColor(BaseColor.DARK_GRAY);
            precioPro.setBackgroundColor(BaseColor.DARK_GRAY);
            subtotalPro.setBackgroundColor(BaseColor.DARK_GRAY);
            
            producto.addCell(desPro);
            producto.addCell(cantPro);
            producto.addCell(precioPro);
            producto.addCell(subtotalPro);
            
            String consultaProductos = "SELECT d.precio, d.cantidad, d.subtotal, p.descripcion FROM compras c\n" +
"INNER JOIN detalle_compra d ON c.id = d.compra_id\n" +
"INNER JOIN productos p ON d.producto_id = p.id\n" +
"WHERE c.id ="+ compra_id;
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProductos);
                rs = ps.executeQuery();
                while (rs.next()){
                    //Datos del proveedor
                    producto.addCell(rs.getString("descripcion"));
                    producto.addCell(rs.getString("cantidad"));
                    producto.addCell(rs.getString("precio"));
                    producto.addCell(rs.getString("subtotal"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            
            Paragraph total = new Paragraph();
            total.add("Total a pagar:  "+totalGeneral);
            total.setAlignment(Element.ALIGN_RIGHT);
            doc.add(total);
            doc.add(Chunk.NEWLINE);
            
            Paragraph agradecimiento = new Paragraph();
            agradecimiento.add(mensaje);
            agradecimiento.setAlignment(Element.ALIGN_CENTER);
            doc.add(agradecimiento);
            doc.add(Chunk.NEWLINE);
            
            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }
    
    public boolean registrarVenta(int id, String total, int usuario_id){
        String sql = "INSERT INTO ventas (cliente_id, total, usuario_id) VALUES (?, ?, ?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, total);
            ps.setInt(3, usuario_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public void generarReporteVenta(int venta_id){
        double totalGeneral = 0.00;
        String fecha = "";
        String nomCliente = "";
        String dirCliente = "";
        String telCliente = "";
        String mensaje = "";
        try {
            Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            String url = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            FileOutputStream archivo;
            File salida = new File(url + File.separator + "venta.pdf");
            
            archivo = new FileOutputStream(salida);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            PdfPTable empresa = new PdfPTable(4);
            empresa.setWidthPercentage(100);
            float [] tamanioEncabezado = new float[]{15f, 15f, 40f, 30f};
            empresa.setWidths(tamanioEncabezado);
            empresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            empresa.getDefaultCell().setBorder(0);
            Image img = Image.getInstance(getClass().getResource("/Img/Riga.png"));
            empresa.addCell(img);
            empresa.addCell("");
            
            String sql = "SELECT * FROM configuracion ";
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()){
                    mensaje = rs.getString("mensaje");
                    empresa.addCell("Ruc: "+rs.getString("ruc")+ "\nNombre: "+ rs.getString("nombre")
                    +"\nTelefono: "+rs.getString("telefono")+ "\nDirección: "+ rs.getString("direccion"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            String consultaCliente = "SELECT c.nombre, c.telefono, c.direccion, v.total, v.fecha FROM ventas v \n" +
"INNER JOIN clientes c ON v.cliente_id = c.id WHERE v.id = "+ venta_id;
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(consultaCliente);
                rs = ps.executeQuery();
                if (rs.next()){
                    //Datos del proveedor
                    nomCliente = rs.getString("nombre");
                    telCliente = rs.getString("telefono");
                    dirCliente = rs.getString("direccion");
                    totalGeneral = rs.getDouble("total");
                    fecha = rs.getString("fecha");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
             empresa.addCell("N° Venta: "+venta_id+ "\nVendedor: "+ "Alex"
                    +"\nFecha: "+ fecha);
            
            doc.add(empresa);
            doc.add(Chunk.NEWLINE);
            
            Paragraph titCliente = new Paragraph();
            titCliente.add("Datos del Cliente");
            titCliente.setAlignment(Element.ALIGN_CENTER);
            doc.add(titCliente);
            doc.add(Chunk.NEWLINE);
            
            //Mostrar Datos del Proveedor
            PdfPTable cliente = new PdfPTable(3);
            cliente.setWidthPercentage(100);
            float [] tamanioCliente = new float[]{40, 20f, 40f};
            cliente.setWidths(tamanioCliente);
            cliente.setHorizontalAlignment(Element.ALIGN_LEFT);
            cliente.getDefaultCell().setBorder(0);
            PdfPCell nomCli = new PdfPCell(new Phrase("Nombre",negrita));
            PdfPCell telCli = new PdfPCell(new Phrase("Telefono",negrita));
            PdfPCell dirCli = new PdfPCell(new Phrase("Dirección",negrita));
            nomCli.setBorder(0);
            telCli.setBorder(0);
            dirCli.setBorder(0);
            nomCli.setBackgroundColor(BaseColor.DARK_GRAY);
            telCli.setBackgroundColor(BaseColor.DARK_GRAY);
            dirCli.setBackgroundColor(BaseColor.DARK_GRAY);
            
            cliente.addCell(nomCli);
            cliente.addCell(telCli);
            cliente.addCell(dirCli);
            
            //proveedor
            cliente.addCell(nomCliente);
            cliente.addCell(telCliente);
            cliente.addCell(dirCliente);
            
            doc.add(cliente);
            doc.add(Chunk.NEWLINE);
            
            Paragraph titProducto = new Paragraph();
            titProducto.add("Detalles de la venta");
            titProducto.setAlignment(Element.ALIGN_CENTER);
            doc.add(titProducto);
            doc.add(Chunk.NEWLINE);
            
            //Mostrar Datos del Proveedor
            PdfPTable producto = new PdfPTable(4);
            producto.setWidthPercentage(100);
            float [] tamanioProducto = new float[]{50, 10f, 20f, 20f};
            producto.setWidths(tamanioProducto);
            producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            producto.getDefaultCell().setBorder(0);
            PdfPCell desPro = new PdfPCell(new Phrase("Descripción",negrita));
            PdfPCell cantPro = new PdfPCell(new Phrase("Cant",negrita));
            PdfPCell precioPro = new PdfPCell(new Phrase("Precio",negrita));
            PdfPCell subtotalPro = new PdfPCell(new Phrase("SubTotal",negrita));
            
            desPro.setBorder(0);
            cantPro.setBorder(0);
            precioPro.setBorder(0);
            subtotalPro.setBorder(0);
            
            desPro.setBackgroundColor(BaseColor.DARK_GRAY);
            cantPro.setBackgroundColor(BaseColor.DARK_GRAY);
            precioPro.setBackgroundColor(BaseColor.DARK_GRAY);
            subtotalPro.setBackgroundColor(BaseColor.DARK_GRAY);
            
            producto.addCell(desPro);
            producto.addCell(cantPro);
            producto.addCell(precioPro);
            producto.addCell(subtotalPro);
            
            String consultaProductos = "SELECT d.precio, d.cantidad, d.subtotal, p.descripcion FROM ventas v INNER JOIN detalle_ventas d ON v.id = d.venta_id INNER JOIN productos p ON d.producto_id = p.id WHERE v.id = "+ venta_id;
            try{
                con = cn.getConexion();
                ps = con.prepareStatement(consultaProductos);
                rs = ps.executeQuery();
                while (rs.next()){
                    //Datos del proveedor
                    producto.addCell(rs.getString("descripcion"));
                    producto.addCell(rs.getString("cantidad"));
                    producto.addCell(rs.getString("precio"));
                    producto.addCell(rs.getString("subtotal"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
            
            doc.add(producto);
            doc.add(Chunk.NEWLINE);
            
            Paragraph total = new Paragraph();
            total.add("Total a pagar:  "+totalGeneral);
            total.setAlignment(Element.ALIGN_RIGHT);
            doc.add(total);
            doc.add(Chunk.NEWLINE);
            
            Paragraph agradecimiento = new Paragraph();
            agradecimiento.add(mensaje);
            agradecimiento.setAlignment(Element.ALIGN_CENTER);
            doc.add(agradecimiento);
            doc.add(Chunk.NEWLINE);
            
            doc.close();
            archivo.close();
            
            Desktop.getDesktop().open(salida);
        } catch (DocumentException | HeadlessException | IOException e) {
        }
    }
}
