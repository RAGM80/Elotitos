/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package versionbeta;

/**
 *
 * @author Limon
 */
public class VentanaListaProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form VentanaListaProductos
     */
    private int idPedidoActual;
    public VentanaListaProductos() {
        initComponents();
    }

    // 🌟 NUEVO CONSTRUCTOR: Atrapa el folio cuando abren la ventana
    public VentanaListaProductos(int idPedido) {
        this.idPedidoActual = idPedido;
        initComponents();
        
        // Ponemos el número en tu etiqueta
        lblFolio.setText("Folio: #" + idPedidoActual);
        
        // Llenamos la tablita
        cargarDetalles();
    }
    public void cargarDetalles() {
        javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0); 
        ConexionMySQL mysql = new ConexionMySQL();
        java.sql.Connection con = mysql.Conectar();
        if (con == null) return;
        String sql = "SELECT Producto, Cantidad, Precio FROM detalle_pedidos WHERE Id_Pedido = ?";
        try {
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, this.idPedidoActual); 
            java.sql.ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getString("Producto");
                fila[1] = rs.getInt("Cantidad");
                fila[2] = "$" + String.format("%.2f", rs.getDouble("Precio")); 
                modelo.addRow(fila);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error al cargar detalles: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFolio = new javax.swing.JLabel();
        lblDetalles1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFolio.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblFolio.setText("Folio:");
        getContentPane().add(lblFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 120, 40));

        lblDetalles1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDetalles1.setText("Detalles del pedido");
        getContentPane().add(lblDetalles1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 120, 41));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Producto", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 340, 180));

        btnCerrar.setBackground(new java.awt.Color(255, 51, 0));
        btnCerrar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
    this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDetalles1;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
