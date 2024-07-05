
package Controllers;

import Modelos.Configuracion;
import Modelos.UsuarioDao;
import Views.PanelAdmin;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;


public class ConfigControllers implements MouseListener{

    private PanelAdmin views;
    private Configuracion cof;
    private UsuarioDao cDao;

    public ConfigControllers(Configuracion cof, UsuarioDao cDao, PanelAdmin views) {
        this.cof = cof;
        this.cDao = cDao;
        this.views = views;
        this.views.JLabelCat.addMouseListener(this);
        this.views.JLabelClientes.addMouseListener(this);
        this.views.JLabelConfig.addMouseListener(this);
        this.views.JLabelMedidas.addMouseListener(this);
        this.views.JLabelNuevaVenta.addMouseListener(this);
        this.views.JLabelNuevaCompra.addMouseListener(this);
        this.views.JLabelProveedor.addMouseListener(this);
        this.views.JLabelUsers.addMouseListener(this);
        this.views.JLabelCaja.addMouseListener(this);
        this.views.JLabelProductos.addMouseListener(this);
        cof = cDao.getConfig();
        views.txtIdEmpresa.setText(""+cof.getId());
        views.txtRucEmpresa.setText(cof.getRuc());
        views.txtTelefonoEmpresa.setText(cof.getTelefono());
        views.txtNombreEmpresa.setText(cof.getNombre());
        views.txtDireccionEmpresa.setText(cof.getDireccion());
        views.txtMensaje.setText(cof.getMensaje());
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.JLabelConfig) {
            views.jTabbedPane1.setSelectedIndex(9);
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
        if(e.getSource() == views.JLabelCat){
            views.JPanelCategorias.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelClientes){
                        views.JPanelClientes.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelConfig){
                        views.JPanelConfig.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelMedidas){
                        views.JPanelMedidas.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelNuevaCompra){
                        views.JPanelNuevaCompra.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelNuevaVenta){
                        views.JPanelNuevaVenta.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelProveedor){
                        views.JPanelProveedor.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelUsers){
                        views.JPanelUsers.setBackground(new Color(32, 178, 170));
        }else if(e.getSource() == views.JLabelCaja){
                        views.JPanelCaja.setBackground(new Color(32, 178, 170));
        }else{
             views.JPanelProductos.setBackground(new Color(32, 178, 170));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() == views.JLabelCat){
            views.JPanelCategorias.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelClientes){
                        views.JPanelClientes.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelConfig){
                        views.JPanelConfig.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelMedidas){
                        views.JPanelMedidas.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelNuevaCompra){
                        views.JPanelNuevaCompra.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelNuevaVenta){
                        views.JPanelNuevaVenta.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelProveedor){
                        views.JPanelProveedor.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelUsers){
                        views.JPanelUsers.setBackground(new Color(0,0,0));
        }else if(e.getSource() == views.JLabelCaja){
                        views.JPanelCaja.setBackground(new Color(0,0,0));
        }else{
             views.JPanelProductos.setBackground(new Color(0,0,0));
        }
    }
    
}
