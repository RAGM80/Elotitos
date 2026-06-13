/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package intento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rafhi
 */
public class VentanaHistorialVentas extends javax.swing.JFrame {
  private Connection cn;
private PreparedStatement ps;
private ResultSet rs;

private int idPedidoSeleccionado = -1;  
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaHistorialVentas.class.getName());

    /**
     * Creates new form VentanaHistorialVentas
     */
    public VentanaHistorialVentas() {
        initComponents();
         this.setLocationRelativeTo(null);

    ConexionMySQL conexion = new ConexionMySQL();
    cn = conexion.Conectar();

    if (cn == null) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar a la base de datos."
        );
        return;
    }

    scrollPedidos.setHorizontalScrollBarPolicy(
            javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPedidos.setVerticalScrollBarPolicy(
            javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
    );

    pnlPedidos.setLayout(
            new javax.swing.BoxLayout(
                    pnlPedidos,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

    cargarResumen();
    cargarPedidos();
    
    }
private void cargarResumen() {

    try {
        String sqlVentas =
                "SELECT IFNULL(SUM(Total), 0) AS total_ventas " +
                "FROM ventas";

        ps = cn.prepareStatement(sqlVentas);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblTotalVentas.setText("$" + String.format("%.2f", rs.getDouble("total_ventas")));
        }

        String sqlProductos =
                "SELECT COUNT(*) AS productos_vendidos " +
                "FROM detalle_ventas";

        ps = cn.prepareStatement(sqlProductos);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblProductosVendidos.setText(String.valueOf(rs.getInt("productos_vendidos")));
        }

        String sqlTransacciones =
                "SELECT COUNT(*) AS transacciones " +
                "FROM ventas";

        ps = cn.prepareStatement(sqlTransacciones);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblTransacciones.setText(String.valueOf(rs.getInt("transacciones")));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar resumen: " + e.getMessage()
        );
    }
}
private void cargarPedidos() {

    pnlPedidos.removeAll();

    try {
        String sql =
                "SELECT " +
                "p.Id_Pedido, " +
                "p.Estado, " +
                "p.Hora_inicio, " +
                "p.Hora_entrega, " +
                "v.Fecha, " +
                "v.Total, " +
                "u.Nombre, " +
                "u.Apellido, " +
                "u.Usuario " +
                "FROM pedidos p " +
                "INNER JOIN ventas v ON p.Id_Venta = v.Id_Venta " +
                "INNER JOIN usuarios u ON v.Id_Usuario = u.Id_Usuario " +
                "ORDER BY p.Id_Pedido DESC";

        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();

        boolean hayPedidos = false;

        while (rs.next()) {
            hayPedidos = true;

            int idPedido = rs.getInt("Id_Pedido");
            String fecha = rs.getString("Fecha");
            String estado = rs.getString("Estado");
            double total = rs.getDouble("Total");

            String cliente =
                    rs.getString("Nombre") + " " +
                    rs.getString("Apellido");

            JPanel tarjeta = crearTarjetaPedido(
                    idPedido,
                    fecha,
                    cliente,
                    estado,
                    total
            );

            pnlPedidos.add(tarjeta);
            pnlPedidos.add(Box.createVerticalStrut(15));
        }

        if (!hayPedidos) {
            javax.swing.JLabel lblVacio =
                    new javax.swing.JLabel("No hay pedidos registrados.");

            lblVacio.setFont(
                    new Font("Times New Roman", Font.BOLD, 18)
            );

            pnlPedidos.add(lblVacio);
        }

        pnlPedidos.revalidate();
        pnlPedidos.repaint();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar pedidos: " + e.getMessage()
        );
    }
}
private JPanel crearTarjetaPedido(
        int idPedido,
        String fecha,
        String cliente,
        String estado,
        double total) {

    JPanel tarjeta = new JPanel();
    tarjeta.setBackground(new Color(217, 217, 217));
    tarjeta.setPreferredSize(new Dimension(540, 120));
    tarjeta.setMaximumSize(new Dimension(540, 120));
    tarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    tarjeta.setBorder(
            BorderFactory.createLineBorder(
                    new Color(180, 180, 180)
            )
    );

    javax.swing.JLabel lblPedido =
            new javax.swing.JLabel("Pedido #" + String.format("%03d", idPedido));

    lblPedido.setFont(new Font("Times New Roman", Font.BOLD, 20));

    tarjeta.add(
            lblPedido,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 200, 30)
    );

    javax.swing.JLabel lblFecha =
            new javax.swing.JLabel(fecha);

    lblFecha.setFont(new Font("Times New Roman", Font.PLAIN, 15));

    tarjeta.add(
            lblFecha,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 220, 25)
    );

    javax.swing.JLabel lblCliente =
            new javax.swing.JLabel("Cliente: " + cliente);

    lblCliente.setFont(new Font("Times New Roman", Font.PLAIN, 15));

    tarjeta.add(
            lblCliente,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 300, 25)
    );

    javax.swing.JLabel lblEstado =
            new javax.swing.JLabel(estado);

    lblEstado.setFont(new Font("Times New Roman", Font.BOLD, 15));

    if (estado.equalsIgnoreCase("PENDIENTE")) {
        lblEstado.setForeground(new Color(120, 100, 0));
    } else if (estado.equalsIgnoreCase("ACEPTADO")
            || estado.equalsIgnoreCase("En Camino")) {
        lblEstado.setForeground(new Color(0, 120, 0));
    } else if (estado.equalsIgnoreCase("RECHAZADO")) {
        lblEstado.setForeground(Color.RED);
    } else {
        lblEstado.setForeground(Color.DARK_GRAY);
    }

    tarjeta.add(
            lblEstado,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 130, 25)
    );

    javax.swing.JLabel lblTotal =
            new javax.swing.JLabel("$" + String.format("%.2f", total));

    lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));

    tarjeta.add(
            lblTotal,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 75, 120, 25)
    );

    tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {

            idPedidoSeleccionado = idPedido;

            tarjeta.setBackground(new Color(190, 210, 255));

            JOptionPane.showMessageDialog(
                    VentanaHistorialVentas.this,
                    "Pedido seleccionado: #" + idPedido
            );
        }
    });

    return tarjeta;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlVentasTotales = new javax.swing.JPanel();
        lblVentasTitulo = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JLabel();
        pnlProductosVendidos = new javax.swing.JPanel();
        lblProductosTitulo = new javax.swing.JLabel();
        lblProductosVendidos = new javax.swing.JLabel();
        lblIconoProductos = new javax.swing.JLabel();
        pnlTransacciones = new javax.swing.JPanel();
        lblTransaccionesTitulo = new javax.swing.JLabel();
        lblTransacciones = new javax.swing.JLabel();
        lblIconoTransacciones = new javax.swing.JLabel();
        btnRegistroProductos = new javax.swing.JButton();
        btnHistorialVentas = new javax.swing.JButton();
        btnAceptarPedido = new javax.swing.JButton();
        btnRechazarPedido = new javax.swing.JButton();
        scrollPedidos = new javax.swing.JScrollPane();
        pnlPedidos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitulo.setText("Historial de Ventas");

        lblVentasTitulo.setText("Ventas Totales");

        lblTotalVentas.setText("175");

        javax.swing.GroupLayout pnlVentasTotalesLayout = new javax.swing.GroupLayout(pnlVentasTotales);
        pnlVentasTotales.setLayout(pnlVentasTotalesLayout);
        pnlVentasTotalesLayout.setHorizontalGroup(
            pnlVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasTotalesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlVentasTotalesLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblTotalVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblVentasTitulo))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlVentasTotalesLayout.setVerticalGroup(
            pnlVentasTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVentasTotalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVentasTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblProductosTitulo.setText("Productos vendidos");

        lblProductosVendidos.setText("15");

        lblIconoProductos.setText("📦");

        javax.swing.GroupLayout pnlProductosVendidosLayout = new javax.swing.GroupLayout(pnlProductosVendidos);
        pnlProductosVendidos.setLayout(pnlProductosVendidosLayout);
        pnlProductosVendidosLayout.setHorizontalGroup(
            pnlProductosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductosVendidosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProductosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProductosVendidosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblProductosVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIconoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblProductosTitulo))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlProductosVendidosLayout.setVerticalGroup(
            pnlProductosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductosVendidosLayout.createSequentialGroup()
                .addComponent(lblProductosTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlProductosVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductosVendidos)
                    .addComponent(lblIconoProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblTransaccionesTitulo.setText("Transacciones");

        lblTransacciones.setText("2");

        lblIconoTransacciones.setText("💸");

        javax.swing.GroupLayout pnlTransaccionesLayout = new javax.swing.GroupLayout(pnlTransacciones);
        pnlTransacciones.setLayout(pnlTransaccionesLayout);
        pnlTransaccionesLayout.setHorizontalGroup(
            pnlTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTransaccionesTitulo)
                    .addGroup(pnlTransaccionesLayout.createSequentialGroup()
                        .addComponent(lblTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIconoTransacciones)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnlTransaccionesLayout.setVerticalGroup(
            pnlTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTransaccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaccionesTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTransaccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTransacciones)
                    .addComponent(lblIconoTransacciones))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRegistroProductos.setText("Registro de Productos");
        btnRegistroProductos.addActionListener(this::btnRegistroProductosActionPerformed);

        btnHistorialVentas.setText("Historial de ventas");
        btnHistorialVentas.addActionListener(this::btnHistorialVentasActionPerformed);

        btnAceptarPedido.setText("Aceptar pedido");
        btnAceptarPedido.addActionListener(this::btnAceptarPedidoActionPerformed);

        btnRechazarPedido.setText("Rechazar pedido");
        btnRechazarPedido.addActionListener(this::btnRechazarPedidoActionPerformed);

        pnlPedidos.setLayout(new javax.swing.BoxLayout(pnlPedidos, javax.swing.BoxLayout.LINE_AXIS));
        scrollPedidos.setViewportView(pnlPedidos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(btnAceptarPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRechazarPedido)
                .addGap(140, 140, 140))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnRegistroProductos)
                                .addGap(90, 90, 90)
                                .addComponent(btnHistorialVentas))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pnlVentasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(pnlProductosVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTransacciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlVentasTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlProductosVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistroProductos)
                    .addComponent(btnHistorialVentas))
                .addGap(18, 18, 18)
                .addComponent(scrollPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptarPedido)
                    .addComponent(btnRechazarPedido))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarPedidoActionPerformed
if (idPedidoSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona primero un pedido.");
        return;
    }

    try {
        String sql =
                "UPDATE pedidos " +
                "SET Estado = 'ACEPTADO' " +
                "WHERE Id_Pedido = ?";

        ps = cn.prepareStatement(sql);
        ps.setInt(1, idPedidoSeleccionado);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pedido aceptado correctamente."
            );

            idPedidoSeleccionado = -1;
            cargarResumen();
            cargarPedidos();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al aceptar pedido: " + e.getMessage()
        );
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarPedidoActionPerformed

    private void btnRechazarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarPedidoActionPerformed
 if (idPedidoSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona primero un pedido.");
        return;
    }

    int opcion = JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que deseas rechazar este pedido?",
            "Confirmar rechazo",
            JOptionPane.YES_NO_OPTION
    );

    if (opcion != JOptionPane.YES_OPTION) {
        return;
    }

    try {
        String sql =
                "UPDATE pedidos " +
                "SET Estado = 'RECHAZADO' " +
                "WHERE Id_Pedido = ?";

        ps = cn.prepareStatement(sql);
        ps.setInt(1, idPedidoSeleccionado);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pedido rechazado correctamente."
            );

            idPedidoSeleccionado = -1;
            cargarResumen();
            cargarPedidos();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al rechazar pedido: " + e.getMessage()
        );
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRechazarPedidoActionPerformed

    private void btnRegistroProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroProductosActionPerformed
    PanelVentas ventana = new PanelVentas();
ventana.setVisible(true);
ventana.setLocationRelativeTo(null);
this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistroProductosActionPerformed

    private void btnHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialVentasActionPerformed
    cargarResumen();
cargarPedidos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaHistorialVentas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptarPedido;
    private javax.swing.JButton btnHistorialVentas;
    private javax.swing.JButton btnRechazarPedido;
    private javax.swing.JButton btnRegistroProductos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIconoProductos;
    private javax.swing.JLabel lblIconoTransacciones;
    private javax.swing.JLabel lblProductosTitulo;
    private javax.swing.JLabel lblProductosVendidos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JLabel lblTransacciones;
    private javax.swing.JLabel lblTransaccionesTitulo;
    private javax.swing.JLabel lblVentasTitulo;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JPanel pnlProductosVendidos;
    private javax.swing.JPanel pnlTransacciones;
    private javax.swing.JPanel pnlVentasTotales;
    private javax.swing.JScrollPane scrollPedidos;
    // End of variables declaration//GEN-END:variables
}
