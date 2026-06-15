/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package versionbeta;
import javax.swing.JOptionPane;

/**
 *
 * @author Limon
 */
public class VentanaMisPedidosB extends javax.swing.JInternalFrame {

    /**
     * Creates new form VentanaMisPedidos
     */
    private int idUsuarioActual;
    public VentanaMisPedidosB() {
        initComponents();
    }
    public VentanaMisPedidosB(int idUsuario) {
    this.idUsuarioActual = idUsuario;
        initComponents();
        tblCompras.setCellSelectionEnabled(false);
        tblCompras.setRowSelectionAllowed(true);
        tblCompras.setColumnSelectionAllowed(false);
        cargarMisPedidos();
    }
    public void cargarMisPedidos() {
    String[] columnas = {"No. de Pedido", "Fecha de Compra", "Precio", "Cantidad de productos", "Estado"};
    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0) {
    @Override
    public boolean isCellEditable(int row, int col) { return false; }
         };
        tblCompras.setModel(modelo);

        ConexionMySQL mysql = new ConexionMySQL();
        java.sql.Connection con = mysql.Conectar();

        if (con == null) return;

        String sql = "SELECT Id_Pedido, Hora_inicio, Total, Cantidad_Productos, Estado FROM pedidos WHERE Id_Usuario = ? ORDER BY Hora_inicio DESC";

        try {
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, this.idUsuarioActual); 
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = "Pedido #" + rs.getInt("Id_Pedido");
                fila[1] = rs.getString("Hora_inicio");
                fila[2] = "$" + String.format("%.2f", rs.getDouble("Total"));
                fila[3] = rs.getInt("Cantidad_Productos") + " arts.";
                
                String estado = rs.getString("Estado");
                fila[4] = estado != null ? estado : "En Proceso";

                modelo.addRow(fila);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error al cargar mis pedidos: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPedidos = new javax.swing.JButton();
        btnCarrito = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        lblPedidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompras = new javax.swing.JTable();
        btnDetallesPedido = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPedidos.setBackground(new java.awt.Color(204, 204, 204));
        btnPedidos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPedidos.setText("Mis Pedidos");
        getContentPane().add(btnPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(553, 59, 156, 32));

        btnCarrito.setBackground(new java.awt.Color(204, 204, 204));
        btnCarrito.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCarrito.setText("Carrito");
        btnCarrito.addActionListener(this::btnCarritoActionPerformed);
        getContentPane().add(btnCarrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 59, 148, 32));

        btnProductos.setBackground(new java.awt.Color(204, 204, 204));
        btnProductos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnProductos.setText("Productos");
        getContentPane().add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 59, 148, 32));

        lblPedidos.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblPedidos.setText("Mis pedidos");
        getContentPane().add(lblPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 126, 41));

        tblCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. de Pedido", "Fecha de Compra", "Precio ", "Cantidad de productos", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCompras.setCellSelectionEnabled(true);
        tblCompras.setShowHorizontalLines(true);
        tblCompras.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblCompras);
        tblCompras.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 149, 740, 370));

        btnDetallesPedido.setBackground(new java.awt.Color(0, 0, 255));
        btnDetallesPedido.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDetallesPedido.setForeground(new java.awt.Color(255, 255, 255));
        btnDetallesPedido.setText("Detalles del Pedido");
        btnDetallesPedido.addActionListener(this::btnDetallesPedidoActionPerformed);
        getContentPane().add(btnDetallesPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 227, -1));

        btnCerrarSesion1.setBackground(new java.awt.Color(255, 0, 0));
        btnCerrarSesion1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCerrarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion1.setText("Cerrar Sesion");
        btnCerrarSesion1.addActionListener(this::btnCerrarSesion1ActionPerformed);
        getContentPane().add(btnCerrarSesion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 530, 227, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarritoActionPerformed
        javax.swing.JDesktopPane desktop = this.getDesktopPane();
        if (desktop != null) {
            // Instanciamos el carrito (usando el nombre exacto de tu archivo 'VenatanaCarrito')
            VenatanaCarrito carrito = new VenatanaCarrito(idUsuarioActual);
            desktop.add(carrito);

            // Clonamos el tamaño y posición exactos
            carrito.setSize(this.getSize());
            carrito.setLocation(this.getLocation());

            carrito.setVisible(true);
            try {
                carrito.setSelected(true);
            } catch (Exception e) {}

            this.dispose(); // Ocultamos la tienda
        }
    }//GEN-LAST:event_btnCarritoActionPerformed

    private void btnDetallesPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesPedidoActionPerformed
    int filaSeleccionada = tblCompras.getSelectedRow();
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor, selecciona un pedido de la tabla primero.");
            return;
        }
        String textoCelda = tblCompras.getValueAt(filaSeleccionada, 0).toString();
        int idPedido = Integer.parseInt(textoCelda.replace("Pedido #", "").trim());
        javax.swing.JDesktopPane desktop = this.getDesktopPane();
        if (desktop != null) {
            VentanaListaProductos ticket = new VentanaListaProductos(idPedido);
            desktop.add(ticket);
            int x = (desktop.getWidth() - ticket.getWidth()) / 2;
            int y = (desktop.getHeight() - ticket.getHeight()) / 2;
            ticket.setLocation(x, y);
            ticket.toFront();
            ticket.setVisible(true);
        }    
    }//GEN-LAST:event_btnDetallesPedidoActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
    int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
    javax.swing.JDesktopPane desktop = this.getDesktopPane();
    if (desktop != null) {
        // Buscamos el JFrame principal (MyDesktopB) que sostiene todo
        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
        MyDesktopB principal = (win instanceof MyDesktopB) ? (MyDesktopB) win : null;

        // Instanciamos el login pasándole su contenedor principal
        VentanaLoginB login = new VentanaLoginB(principal);
        desktop.add(login);
        login.setVisible(true);
        login.setSize(400, 485);
        login.setLocation((desktop.getWidth() - login.getWidth()) / 2, (desktop.getHeight() - login.getHeight()) / 2);
    }
    this.dispose(); // Destruye la ventana actual de pedidos
    }
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCarrito;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnDetallesPedido;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPedidos;
    private javax.swing.JTable tblCompras;
    // End of variables declaration//GEN-END:variables
}
