
package Controllers;

import Modelos.Categorias;
import Modelos.CategoriasDao;
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


public class CategoriasControllers implements ActionListener, MouseListener, KeyListener{
    private Categorias cat;
    private CategoriasDao catDao;
    private PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();

    public CategoriasControllers(Categorias cat, CategoriasDao catDao, PanelAdmin views) {
        this.cat = cat;
        this.catDao = catDao;
        this.views = views;
        this.views.btnRegistrarCat.addActionListener(this);
        this.views.btnModificarCat.addActionListener(this);
        this.views.btnNuevoCat.addActionListener(this);
        this.views.TableCat.addMouseListener(this);
        this.views.JMenuEliminarCat.addActionListener(this);
        this.views.JMenuReingresarCat.addActionListener(this);
        this.views.txtBuscarCat.addKeyListener(this);
        this.views.JLabelCat.addMouseListener(this);
        listarCategorias();
        llenarCat();
        AutoCompleteDecorator.decorate(views.cbxCatPro);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegistrarCat) {
            if (views.txtNombreCat.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                cat.setNombre(views.txtNombreCat.getText());
                if (catDao.registrar(cat)) {
                    limpiarTable();
                    listarCategorias();
                    llenarCat();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria registrado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar");
                }
            }
        }else if (e.getSource() == views.btnModificarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
            }else{
                if (views.txtNombreCat.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }else{
                cat.setNombre(views.txtNombreCat.getText());
                cat.setId(Integer.parseInt(views.txtIdCat.getText()));
                if (catDao.modificar(cat)) {
                    limpiarTable();
                    listarCategorias();
                    llenarCat();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria modificado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            }
            }
        }else if (e.getSource() == views.JMenuEliminarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }else{
                int id = Integer.parseInt(views.txtIdCat.getText());
                if(catDao.accion("Inactivo", id)){
                    limpiarTable();
                    listarCategorias();
                    llenarCat();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria eliminado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar Categoria");
                }
            }
        }else if (e.getSource() == views.JMenuReingresarCat) {
            if (views.txtIdCat.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }else{
                int id = Integer.parseInt(views.txtIdCat.getText());
                if(catDao.accion("Activo", id)){
                    limpiarTable();
                    listarCategorias();
                    llenarCat();
                    limpiar();
                    JOptionPane.showMessageDialog(null, "Categoria Reingresado");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al reingresar Categoria");
                }
            }
        }else{
            limpiar();
        }
    }
    public void listarCategorias(){
        Tables color = new Tables();
        views.TableCat.setDefaultRenderer(views.TableCat.getColumnClass(0), color);
        List<Categorias> lista = catDao.ListaCategorias(views.txtBuscarCat.getText());
        modelo = (DefaultTableModel) views.TableCat.getModel();
        Object[] ob = new Object[3];
        for (int i = 0; i<lista.size(); i++){
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.TableCat.setModel(modelo);
        JTableHeader header = views.TableCat.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.black);
        header.setForeground(Color.white);
    }
    
    public void limpiarTable(){
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == views.TableCat){
            int fila = views.TableCat.rowAtPoint(e.getPoint());
            views.txtIdCat.setText(views.TableCat.getValueAt(fila, 0).toString());
            views.txtNombreCat.setText(views.TableCat.getValueAt(fila, 1).toString());
        }else if (e.getSource() == views.JLabelCat) {
            views.jTabbedPane1.setSelectedIndex(4);
            limpiarTable();
            listarCategorias();
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
        if (e.getSource() == views.txtBuscarCat) {
                limpiarTable();
                listarCategorias();
        }
    }
    private void limpiar(){
        views.txtNombreCat.setText("");
        views.txtIdCat.setText("");
    }
    private void llenarCat(){
        List<Categorias> lista = catDao.ListaCategorias(views.txtBuscarCat.getText());
        views.cbxCatPro.removeAllItems();
        for (int i = 0; i<lista.size(); i++){
            int id = lista.get(i).getId();
            String nombre = lista.get(i).getNombre();
            views.cbxCatPro.addItem(new Combo(id, nombre));
        }
    }
}
