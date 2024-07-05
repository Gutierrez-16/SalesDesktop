
package Controllers;

import Modelos.Combo;
import Modelos.Medidas;
import Modelos.MedidasDao;
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


public class MedidasControllers implements ActionListener, MouseListener, KeyListener{
    private Medidas med;
    private MedidasDao medDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public MedidasControllers(Medidas med, MedidasDao medDao, PanelAdmin views) {
        this.med = med;
        this.medDao = medDao;
        this.views = views;
        this.views.btnRegistrarMed.addActionListener(this);
        this.views.btnModificarMed.addActionListener(this);
        this.views.btnNuevoMed.addActionListener(this);
        this.views.TableMed.addMouseListener(this);
        this.views.JMenuEliminarMed.addActionListener(this);
        this.views.JMenuReingresarMed.addActionListener(this);
        this.views.txtBuscarMed.addKeyListener(this);
        this.views.JLabelMedidas.addMouseListener(this);
        listarMedidas();
        llenarMed();
        AutoCompleteDecorator.decorate(views.cbxMedidaPro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarMed) {
            if (views.txtNombreMed.getText().equals("") 
                    || views.txtNonbreCortoMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNonbreCortoMed.getText());
                if (medDao.registrar(med)) {
                    limpiarTable();
                    listarMedidas();
                    llenarMed();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida registrado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar");
                }
            }
        }else if (e.getSource() == views.btnModificarMed) {
            if (views.txtIdMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                if (views.txtNombreMed.getText().equals("") 
                    || views.txtNonbreCortoMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNonbreCortoMed.getText());
                med.setId(Integer.parseInt(views.txtIdMed.getText()));
                if (medDao.modificar(med)) {
                    limpiarTable();
                    listarMedidas();
                    llenarMed();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida modificado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            }
            }
        }else if (e.getSource() == views.JMenuEliminarMed) {
            if (views.txtIdMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }else{
                int id = Integer.parseInt(views.txtIdMed.getText());
                if(medDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarMedidas();
                    llenarMed();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar Medida");
                }
            }
        }else if (e.getSource() == views.JMenuReingresarMed) {
            if (views.txtIdMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }else{
                int id = Integer.parseInt(views.txtIdMed.getText());
                if(medDao.accion("Activo", id)){
                    limpiarTable();
                    listarMedidas();
                    llenarMed();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Medida Reingresado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar Medida");
                }
            }
        }else{
            limpiar();
        }
    }
    public void listarMedidas(){
        Tables color = new Tables();
        views.TableMed.setDefaultRenderer(views.TableMed.getColumnClass(0), color);
        List<Medidas> lista = medDao.ListaMeidas(views.txtBuscarMed.getText());
        modelo = (DefaultTableModel) views.TableMed.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i<lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getNombre_corto();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableMed.setModel(modelo);
        JTableHeader header = views.TableMed.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.black);
        header.setForeground(Color.white);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableMed){
            int fila = views.TableMed.rowAtPoint(e.getPoint());
            views.txtIdMed.setText(views.TableMed.getValueAt(fila, 0).toString());
            views.txtNombreMed.setText(views.TableMed.getValueAt(fila, 1).toString());
            views.txtNonbreCortoMed.setText(views.TableMed.getValueAt(fila, 2).toString());
        }else if (e.getSource() == views.JLabelMedidas) {
            views.jTabbedPane1.setSelectedIndex(5);
            limpiarTable();
            listarMedidas();
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
        if (e.getSource() == views.txtBuscarMed) {
            limpiarTable();
            listarMedidas();
        }
    }
    public void limpiarTable(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    private void limpiar(){
        views.txtNombreMed.setText("");
        views.txtNonbreCortoMed.setText("");
        views.txtIdMed.setText("");
    }
    private void llenarMed(){
        List<Medidas> lista = medDao.ListaMeidas(views.txtBuscarMed.getText());
        views.cbxMedidaPro.removeAllItems();
        for (int i = 0; i<lista.size(); i++){
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbxMedidaPro.addItem(new Combo(id, nombre));
        }
    }
    
}
