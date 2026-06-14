/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package intento;

import java.awt.Color;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 * @author ErickJimz
 */
public class PanelVentas extends javax.swing.JFrame {

    private int idUsuarioActual;
    private java.sql.Connection cn;
    private java.sql.PreparedStatement ps;
    private java.sql.ResultSet rs;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PanelVentas.class.getName());

    public PanelVentas() {
        initComponents();
        this.setLocationRelativeTo(null);
        marcarBotonActual();
    }

    public PanelVentas(int idUsuario) {
        initComponents();
        this.idUsuarioActual = idUsuario;
        this.setLocationRelativeTo(null);

        ConexionMySQL conexion = new ConexionMySQL();
        cn = conexion.Conectar();

        if (cn == null) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
            return;
        }

        spnCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        marcarBotonActual();
        cargarResumen();
        cargarTablaVentas();
    }

    private class ProductoItem {
        int idProducto;
        String nombre;
        double precio;

        public ProductoItem(int idProducto, String nombre, double precio) {
            this.idProducto = idProducto;
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    private void botonActivo(JButton boton) {
        boton.setBackground(new Color(36, 107, 255));
        boton.setForeground(Color.WHITE);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
    }

    private void botonInactivo(JButton boton) {
        boton.setBackground(new Color(204, 204, 204));
        boton.setForeground(Color.BLACK);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
    }

    private void marcarBotonActual() {
        botonActivo(btnRegistroProductos);
        botonInactivo(btnHistorialVentas);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        // NetBeans sobreescribirá este método automáticamente usando el archivo .form
    }
    // </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String nombreProd = txtProducto.getText().trim();
        if (nombreProd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, escribe el nombre del producto.");
            return;
        }

        double precioProd = 0;
        try {
            precioProd = Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un precio numérico válido.");
            return;
        }

        int cantidad = Integer.parseInt(spnCantidad.getValue().toString());
        double subtotal = precioProd * cantidad;

        try {
            cn.setAutoCommit(false);
            int idProducto = 0;
            String sqlInsProd = "INSERT INTO productos(Id_Usuario, Nombre, Precio, Total, Fecha, Cantidad) VALUES (?, ?, ?, ?, NOW(), ?)";
            try (PreparedStatement psInsProd = cn.prepareStatement(sqlInsProd, Statement.RETURN_GENERATED_KEYS)) {
                psInsProd.setInt(1, idUsuarioActual); 
                psInsProd.setString(2, nombreProd);
                psInsProd.setDouble(3, precioProd);
                psInsProd.setDouble(4, subtotal);    
                psInsProd.setInt(5, cantidad);       
                psInsProd.executeUpdate();
                try (ResultSet rsKeys = psInsProd.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idProducto = rsKeys.getInt(1);
                    }
                }
            }
            String sqlVenta = "INSERT INTO ventas(Id_Usuario, Fecha, Total, Metodo_pago) VALUES (?, NOW(), ?, ?)";
            ps = cn.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idUsuarioActual);
            ps.setDouble(2, subtotal);
            ps.setString(3, "Efectivo");
            ps.executeUpdate();

            ResultSet claves = ps.getGeneratedKeys();
            int idVenta = 0;
            if (claves.next()) {
                idVenta = claves.getInt(1);
            }
            String sqlDetalle = "INSERT INTO detalle_ventas(Id_Venta, Id_Producto, Cantidad, Subtotal) VALUES (?, ?, ?, ?)";
            ps = cn.prepareStatement(sqlDetalle);
            ps.setInt(1, idVenta);
            ps.setInt(2, idProducto); 
            ps.setInt(3, cantidad);
            ps.setDouble(4, subtotal);
            ps.executeUpdate();
            String sqlPedido = "INSERT INTO pedidos(Id_Venta, Estado, Hora_inicio, Hora_entrega) VALUES (?, ?, NOW(), NULL)";
            ps = cn.prepareStatement(sqlPedido);
            ps.setInt(1, idVenta);
            ps.setString(2, "Pendiente");
            ps.executeUpdate();

            cn.commit();
            JOptionPane.showMessageDialog(this, "¡Venta registrada con éxito!");
            txtProducto.setText("");
            txtPrecio.setText("");
            spnCantidad.setValue(1);

            cargarResumen();
            cargarTablaVentas();

        } catch (Exception e) {
            try { if (cn != null) cn.rollback(); } catch (Exception ex) {}
            JOptionPane.showMessageDialog(this, "Error en la transacción: " + e.getMessage());
        } finally {
            try { if (cn != null) cn.setAutoCommit(true); } catch (Exception e) {}
        }
    }                                          

    private void btnRegistroProductosActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        marcarBotonActual();
    }                                                    

    private void btnHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        VentanaHistorialVentas ventana = new VentanaHistorialVentas();
        ventana.setVisible(true);
        this.dispose();
    }                                                  

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {                                                
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            VentanaLogin login = new VentanaLogin();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            this.dispose();
        } 
    }                                               

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void btnNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {                                              
        txtProducto.setText(""); 
        spnCantidad.setValue(1);
        txtPrecio.setText("");
    }                                             

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void cargarResumen() {
        try {
            String sqlVentas = "SELECT IFNULL(SUM(Total), 0) AS total_ventas FROM ventas WHERE Id_Usuario = ?";
            ps = cn.prepareStatement(sqlVentas);
            ps.setInt(1, idUsuarioActual);
            rs = ps.executeQuery();
            if (rs.next()) {
                jLabel3.setText("$" + String.format("%.2f", rs.getDouble("total_ventas")));
            }

            String sqlProductos = "SELECT IFNULL(SUM(dv.Cantidad), 0) AS productos FROM detalle_ventas dv " +
                                  "INNER JOIN ventas v ON dv.Id_Venta = v.Id_Venta WHERE v.Id_Usuario = ?";
            ps = cn.prepareStatement(sqlProductos);
            ps.setInt(1, idUsuarioActual);
            rs = ps.executeQuery();
            if (rs.next()) {
                jLabel5.setText(String.valueOf(rs.getInt("productos")));
            }

            String sqlTransacciones = "SELECT COUNT(*) AS transacciones FROM ventas WHERE Id_Usuario = ?";
            ps = cn.prepareStatement(sqlTransacciones);
            ps.setInt(1, idUsuarioActual);
            rs = ps.executeQuery();
            if (rs.next()) {
                jLabel7.setText(String.valueOf(rs.getInt("transacciones")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar resumen: " + e.getMessage());
        }
    }

    private void cargarTablaVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        modelo.addColumn("Fecha");
        modelo.addColumn("Acciones");
        tblVentas.setModel(modelo);

        try {
            String sql = "SELECT Nombre AS producto, Cantidad AS cantidad, Precio AS precio, Total AS subtotal, Fecha AS fecha FROM productos WHERE Id_Usuario = ? ORDER BY Fecha DESC";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuarioActual);
            rs = ps.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("producto");
                fila[1] = rs.getInt("cantidad");
                fila[2] = "$" + String.format("%.2f", rs.getDouble("precio"));
                fila[3] = "$" + String.format("%.2f", rs.getDouble("subtotal"));
                fila[4] = rs.getString("fecha");
                fila[5] = "Registrado";
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla de ventas: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new PanelVentas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:initComponents
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHistorialVentas;
    private javax.swing.JButton btnNuevaVenta;
    private javax.swing.JButton btnRegistroProductos;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblProductosVendidos;
    private javax.swing.JLabel lblTransacciones;
    private javax.swing.JLabel lblVentasTotales;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:initComponents
}