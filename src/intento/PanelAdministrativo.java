/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package intento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ErickJimz
 */
public class PanelAdministrativo extends javax.swing.JFrame {
private Connection cn;
private PreparedStatement ps;
private ResultSet rs;   
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PanelAdministrativo.class.getName());

    /**
     * Creates new form PanelAdministrativo
     */
    public PanelAdministrativo() {
        initComponents();
        this.setLocationRelativeTo(null);

    ConexionMySQL conexion = new ConexionMySQL();
    cn = conexion.Conectar();

    if (cn == null) {
        JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar a la base de datos."
        );
        return;
    }

    cargarResumenAdministrativo();
    cargarRendimientoVendedores();
    cargarVendedoresMes();
    cargarMejorDiaVentas();
    cargarProductoMasVendido();
    }
private void cargarResumenAdministrativo() {

    try {
        String sqlIngresos =
                "SELECT IFNULL(SUM(Total), 0) AS ingresos " +
                "FROM ventas";

        ps = cn.prepareStatement(sqlIngresos);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblIngresosTotales.setText(
                    "$" + String.format("%.2f", rs.getDouble("ingresos"))
            );
        }

        String sqlVentas =
                "SELECT COUNT(*) AS total_ventas " +
                "FROM ventas";

        ps = cn.prepareStatement(sqlVentas);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblTotalVentas.setText(
                    String.valueOf(rs.getInt("total_ventas"))
            );
        }

        String sqlVendedores =
                "SELECT COUNT(*) AS vendedores_activos " +
                "FROM usuarios " +
                "WHERE Rol = 'Vendedor' AND Estado = 'Activo'";

        ps = cn.prepareStatement(sqlVendedores);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblVendedoresActivos.setText(
                    String.valueOf(rs.getInt("vendedores_activos"))
            );
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar resumen: " + e.getMessage()
        );
    }
}
private void cargarRendimientoVendedores() {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("Vendedor");
    modelo.addColumn("Estado");
    modelo.addColumn("Ventas");
    modelo.addColumn("Productos");
    modelo.addColumn("Ingresos");

    tblRendimientoVendedores.setModel(modelo);

    try {
        String sql =
                "SELECT " +
                "u.Usuario AS vendedor, " +
                "u.Estado AS estado, " +
                "COUNT(v.Id_Venta) AS ventas, " +
                "IFNULL(SUM(dv.Cantidad), 0) AS productos, " +
                "IFNULL(SUM(v.Total), 0) AS ingresos " +
                "FROM usuarios u " +
                "LEFT JOIN ventas v ON u.Id_Usuario = v.Id_Usuario " +
                "LEFT JOIN detalle_ventas dv ON v.Id_Venta = dv.Id_Venta " +
                "WHERE u.Rol = 'Vendedor' " +
                "GROUP BY u.Id_Usuario, u.Usuario, u.Estado " +
                "ORDER BY ingresos DESC";

        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {

            Object[] fila = new Object[5];

            fila[0] = rs.getString("vendedor");
            fila[1] = rs.getString("estado");
            fila[2] = rs.getInt("ventas");
            fila[3] = rs.getInt("productos");
            fila[4] = "$" + String.format("%.2f", rs.getDouble("ingresos"));

            modelo.addRow(fila);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar rendimiento de vendedores: " + e.getMessage()
        );
    }
}
private void cargarVendedoresMes() {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("Vendedor");
    modelo.addColumn("Ingresos");

    tblVendedoresMes.setModel(modelo);

    try {
        String sql =
                "SELECT " +
                "u.Usuario AS vendedor, " +
                "IFNULL(SUM(v.Total), 0) AS ingresos " +
                "FROM usuarios u " +
                "INNER JOIN ventas v ON u.Id_Usuario = v.Id_Usuario " +
                "WHERE u.Rol = 'Vendedor' " +
                "GROUP BY u.Id_Usuario, u.Usuario " +
                "ORDER BY ingresos DESC " +
                "LIMIT 3";

        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {

            Object[] fila = new Object[2];

            fila[0] = rs.getString("vendedor");
            fila[1] = "$" + String.format("%.2f", rs.getDouble("ingresos"));

            modelo.addRow(fila);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar vendedores del mes: " + e.getMessage()
        );
    }
}
private void cargarMejorDiaVentas() {

    try {
        String sql =
                "SELECT " +
                "CASE DAYOFWEEK(Fecha) " +
                "WHEN 1 THEN 'Domingo' " +
                "WHEN 2 THEN 'Lunes' " +
                "WHEN 3 THEN 'Martes' " +
                "WHEN 4 THEN 'Miércoles' " +
                "WHEN 5 THEN 'Jueves' " +
                "WHEN 6 THEN 'Viernes' " +
                "WHEN 7 THEN 'Sábado' " +
                "END AS dia, " +
                "COUNT(*) AS total " +
                "FROM ventas " +
                "GROUP BY dia " +
                "ORDER BY total DESC " +
                "LIMIT 1";

        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblMejorDiaVentas.setText(rs.getString("dia"));
        } else {
            lblMejorDiaVentas.setText("Sin ventas");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar mejor día: " + e.getMessage()
        );
    }
}
private void cargarProductoMasVendido() {

    try {
        String sql =
                "SELECT " +
                "p.Nombre AS producto, " +
                "SUM(dv.Cantidad) AS total_vendido " +
                "FROM detalle_ventas dv " +
                "INNER JOIN productos p ON dv.Id_Producto = p.Id_Productos " +
                "GROUP BY p.Id_Productos, p.Nombre " +
                "ORDER BY total_vendido DESC " +
                "LIMIT 1";

        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();

        if (rs.next()) {
            lblProductoMasVendido.setText(rs.getString("producto"));
        } else {
            lblProductoMasVendido.setText("Sin ventas");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar producto más vendido: " + e.getMessage()
        );
    }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lblIngresosTotales = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblVendedoresActivos = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRendimientoVendedores = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVendedoresMes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProductoMasVendido = new javax.swing.JLabel();
        lblMejorDiaVentas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Panel administrativo");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Rendimientos de vendedores ");

        jLabel25.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Ingresos totales");

        lblIngresosTotales.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblIngresosTotales.setText("$17,500.00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIngresosTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIngresosTotales)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel31.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Total ventas");

        lblTotalVentas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTotalVentas.setText("150");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalVentas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalVentas)
                .addContainerGap())
        );

        lblVendedoresActivos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblVendedoresActivos.setText("2");

        jLabel34.setFont(new java.awt.Font("Lucida Console", 0, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Vendedores activos");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVendedoresActivos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVendedoresActivos)
                .addContainerGap())
        );

        btnCerrarSesion.setBackground(new java.awt.Color(255, 0, 0));
        btnCerrarSesion.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        tblRendimientoVendedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vendedor", "Estado", "Ventas", "Productos", "Ingresos"
            }
        ));
        jScrollPane1.setViewportView(tblRendimientoVendedores);

        tblVendedoresMes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Vendedor", "Ingresos"
            }
        ));
        jScrollPane2.setViewportView(tblVendedoresMes);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Vendedores del Mes");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Productos mas vendidos");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Mejor dia de ventas  ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProductoMasVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMejorDiaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(199, 199, 199))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoMasVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMejorDiaVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
    int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que deseas cerrar sesión?",
            "Cerrar sesión",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (opcion == javax.swing.JOptionPane.YES_OPTION) {

        VentanaLogin login = new VentanaLogin();
        login.setVisible(true);
        login.setLocationRelativeTo(null);

        this.dispose();
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new PanelAdministrativo().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIngresosTotales;
    private javax.swing.JLabel lblMejorDiaVentas;
    private javax.swing.JLabel lblProductoMasVendido;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JLabel lblVendedoresActivos;
    private javax.swing.JTable tblRendimientoVendedores;
    private javax.swing.JTable tblVendedoresMes;
    // End of variables declaration//GEN-END:variables
}
