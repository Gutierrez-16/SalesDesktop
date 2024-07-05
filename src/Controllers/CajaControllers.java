package Controllers;

import Modelos.AperturaCierre;
import Modelos.Cajas;
import Modelos.CajasDao;
import Modelos.Combo;
import Modelos.Tables;
import Views.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CajaControllers implements ActionListener, MouseListener, KeyListener {

    private Cajas cj;
    private CajasDao cjDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public CajaControllers(Cajas cj, CajasDao cjDao, PanelAdmin views) {
        this.cj = cj;
        this.cjDao = cjDao;
        this.views = views;
        this.views.btnRegistrarCaja.addActionListener(this);
        this.views.btnModificarCaja.addActionListener(this);
        this.views.btnCajas.addActionListener(this);
        this.views.btnNuevoCaja.addActionListener(this);
        this.views.TableCaja.addMouseListener(this);
        this.views.JMenuEliminarCaja.addActionListener(this);
        this.views.JMenuReingresarCaja.addActionListener(this);
        this.views.txtBuscarCaja.addKeyListener(this);
        this.views.JLabelCaja.addMouseListener(this);
        
        this.views.btnRegistrarApertura.addActionListener(this);
        this.views.btnModificarApertura.addActionListener(this);
        this.views.btnNuevoApertura.addActionListener(this);
        
        this.views.TableApertura.addKeyListener(this);
        
        listarAperturas();
        llenarCajas();
        AutoCompleteDecorator.decorate(views.cbxCajaUser);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCaja) {
            registrarCaja();
        } else if (e.getSource() == views.btnModificarCaja) {
            modificarCaja();
        } else if (e.getSource() == views.JMenuEliminarCaja) {
            eliminarCaja();
        } else if (e.getSource() == views.JMenuReingresarCaja) {
            reingresarCaja();
        } else if (e.getSource() == views.btnNuevoCaja) {
            limpiar();
        } else if (e.getSource() == views.btnCajas) {
            views.jTabbedPane1.setSelectedIndex(11);
            limpiarTable();
            listarCajas();
        } else if (e.getSource() == views.btnRegistrarApertura) {
            abrirCaja();
        } else if (e.getSource() == views.btnModificarApertura) {
            cerrarCaja();
        } else if (e.getSource() == views.btnNuevoApertura) {
            limpiarApertura();
        }
    }

    public void listarCajas() {
        Tables color = new Tables();
        views.TableCaja.setDefaultRenderer(views.TableCaja.getColumnClass(0), color);
        List<Cajas> lista = cjDao.ListaCajas(views.txtBuscarCaja.getText());
        modelo = (DefaultTableModel) views.TableCaja.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableCaja.setModel(modelo);
        JTableHeader header = views.TableCaja.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.black);
        header.setForeground(Color.white);
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.TableCaja) {
            int fila = views.TableCaja.rowAtPoint(e.getPoint());
            views.txtIdCaja.setText(views.TableCaja.getValueAt(fila, 0).toString());
            views.txtNombreCaja.setText(views.TableCaja.getValueAt(fila, 1).toString());
        } else if (e.getSource() == views.JLabelCaja) {
            views.jTabbedPane1.setSelectedIndex(11);
            limpiarTable();
            listarCajas();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarCaja) {
            limpiarTable();
            listarCajas();
        }
    }

    private void limpiar() {
        views.txtNombreCaja.setText("");
        views.txtIdCaja.setText("");
    }

    private void llenarCajas() {
        List<Cajas> lista = cjDao.ListaCajas(views.txtBuscarCaja.getText());
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbxCajaUser.addItem(new Combo(id, nombre));
        }
    }

    private void registrarCaja() {
        if (views.txtNombreCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        } else {
            cj.setNombre(views.txtNombreCaja.getText());
            if (cjDao.registrar(cj)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja registrado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar");
            }
        }
    }

    private void modificarCaja() {
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (views.txtNombreCaja.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            } else {
                cj.setNombre(views.txtNombreCaja.getText());
                cj.setId(Integer.parseInt(views.txtIdCaja.getText()));
                if (cjDao.modificar(cj)) {
                    limpiarTable();
                    listarCajas();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Caja modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            }
        }
    }

    private void eliminarCaja() {
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int id = Integer.parseInt(views.txtIdCaja.getText());
            if (cjDao.accion("Inactivo", id)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja eliminado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar Caja");
            }
        }
    }

    private void reingresarCaja() {
        if (views.txtIdCaja.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecciona una fila");
        } else {
            int id = Integer.parseInt(views.txtIdCaja.getText());
            if (cjDao.accion("Activo", id)) {
                limpiarTable();
                listarCajas();
                limpiar();
                JOptionPane.showMessageDialog(null, "Caja Reingresado");
            } else {
                JOptionPane.showMessageDialog(null, "Error al reingresar Caja");
            }
        }
    }

    private void abrirCaja() {
        if (views.txtMontoInicial.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese monto Inicial");
        } else {
            double monto = Double.parseDouble(views.txtMontoInicial.getText());
            int usuario_id = Integer.parseInt(views.txtIdUsuario.getText());
            String resultado = cjDao.abrirCaja(monto, usuario_id);
            if ("existe".equalsIgnoreCase(resultado)) {
                JOptionPane.showMessageDialog(null, "La Caja ya esta abierta");
                limpiarTable();
                listarAperturas();
            } else if ("registrado".equalsIgnoreCase(resultado)) {
                limpiarTable();
                listarAperturas();
                limpiarApertura();
                JOptionPane.showMessageDialog(null, "Caja abierta");
            }
        }
    }

    private void cerrarCaja() {
        int pregunta = JOptionPane.showConfirmDialog(null, "Esta seguro de cerrar la caja");
        if (pregunta == 0) {
            double montoFinal = cjDao.montoFinal(Integer.parseInt(views.txtIdUsuario.getText()));
            System.out.println(montoFinal);
            int totalVentas = cjDao.totalVentas(Integer.parseInt(views.txtIdUsuario.getText()));
            System.out.println(totalVentas);
            AperturaCierre apert = new AperturaCierre();
            apert.setFecha_cierre("2023-11-20");
            
            apert.setMonto_final(montoFinal);
            apert.setTotal_ventas(totalVentas);
            
            apert.setUsuario_id(Integer.parseInt(views.txtIdUsuario.getText()));
            if (cjDao.cerrarCaja(apert)) {
                JOptionPane.showMessageDialog(null, "Caja cerrada");
            }else{
                JOptionPane.showMessageDialog(null, "Error al cerrar la caja");
            }
        }
    }
    
    private void limpiarApertura(){
        views.txtMontoInicial.setText("");
        views.txtBuscarApertura.setText("");
    }
    
    public void listarAperturas() {
//        Tables color = new Tables();
//        views.TableApertura.setDefaultRenderer(views.TableApertura.getColumnClass(0), color);
        List<AperturaCierre> lista = cjDao.ListaAperturas(views.txtBuscarApertura.getText());
        modelo = (DefaultTableModel) views.TableApertura.getModel();
        Object[] ob = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getFecha_apertura();
            ob[1] = lista.get(i).getFecha_cierre();
            ob[2] = lista.get(i).getMonto_inicial();
            ob[3] = lista.get(i).getMonto_final();
            ob[4] = lista.get(i).getTotal_ventas();
            ob[5] = lista.get(i).getNombreUsuario();
            modelo.addRow(ob);
        }
        views.TableApertura.setModel(modelo);
        JTableHeader header = views.TableApertura.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.black);
        header.setForeground(Color.white);
    }
}
