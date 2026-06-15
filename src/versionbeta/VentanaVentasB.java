
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package versionbeta;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 * @author ErickJimz
 */
public class VentanaVentasB extends javax.swing.JInternalFrame {
    private int idUsuarioActual;
    private java.sql.Connection cn;
    private java.sql.PreparedStatement ps;
    private java.sql.ResultSet rs;

    public VentanaVentasB() {
        initComponents();
    }

    public VentanaVentasB(int idUsuario) {
        initComponents();
        this.idUsuarioActual = idUsuario;

        ConexionMySQL conexion = new ConexionMySQL();
        cn = conexion.Conectar();

        if (cn == null) {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
            return;
        }

        spnCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblVentasTotales = new javax.swing.JLabel();
        lblTotalVentas = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblProductosVendidos = new javax.swing.JLabel();
        lblProductosVendido = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblTransacciones = new javax.swing.JLabel();
        lblTransaccion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        btnHistorialVentas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        spnCantidad = new javax.swing.JSpinner();
        txtPrecio = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        btnRegistroProductos = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        lblVentasTotales.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lblVentasTotales.setText("ventas totales");

        lblTotalVentas.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTotalVentas.setText("$175.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTotalVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentasTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblVentasTotales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalVentas))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        lblProductosVendidos.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lblProductosVendidos.setText("productos vendidos");

        lblProductosVendido.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblProductosVendido.setText("$150");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProductosVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblProductosVendido, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblProductosVendidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblProductosVendido))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        lblTransacciones.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        lblTransacciones.setText("Transacciones");

        lblTransaccion.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblTransaccion.setText("2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTransacciones, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTransaccion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblTransacciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTransaccion))
        );

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Registro de productos");

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setText("Producto");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel14))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setText("Cantidad");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel18.setText("Precio");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        btnAgregar.setBackground(new java.awt.Color(51, 255, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Producto", "Cantidad ", "Precio", "Total ", "Fecha", "Acciones "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblVentas);

        btnHistorialVentas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHistorialVentas.setText("Historial de Ventas");
        btnHistorialVentas.addActionListener(this::btnHistorialVentasActionPerformed);

        btnCerrarSesion.setBackground(new java.awt.Color(255, 0, 51));
        btnCerrarSesion.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(this::btnCerrarSesionActionPerformed);

        txtPrecio.addActionListener(this::txtPrecioActionPerformed);

        txtProducto.addActionListener(this::txtProductoActionPerformed);

        btnRegistroProductos.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnRegistroProductos.setText("Registro de Productos");
        btnRegistroProductos.addActionListener(this::btnRegistroProductosActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistroProductos))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(btnHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion)
                .addGap(288, 288, 288))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnAgregar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHistorialVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistroProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(btnCerrarSesion)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
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
            JOptionPane.showMessageDialog(this, "¡Venta registrada con éxito por separado!");
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
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnHistorialVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialVentasActionPerformed
 
   javax.swing.JDesktopPane desktop = this.getDesktopPane();
    if (desktop != null) {
        VentanaHistorialVentasB historial = new VentanaHistorialVentasB(this.idUsuarioActual);
        desktop.add(historial);
        historial.setVisible(true);
        historial.setLocation((desktop.getWidth() - historial.getWidth()) / 2, 
                              (desktop.getHeight() - historial.getHeight()) / 2);
        this.dispose(); 
    }
    }//GEN-LAST:event_btnHistorialVentasActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == JOptionPane.YES_OPTION) {
        // 1. Obtener la referencia al escritorio
        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (win instanceof MyDesktopB) {
            MyDesktopB principal = (MyDesktopB) win;
            principal.mostrarLogin(); 
        }
        this.dispose();
    }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void btnRegistroProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroProductosActionPerformed
    cargarResumen();
    cargarTablaVentas();
    }//GEN-LAST:event_btnRegistroProductosActionPerformed
private void cargarResumen() {
if (cn == null) return;
    String sqlVentas = "SELECT IFNULL(SUM(sub.Total), 0) AS total_dinero FROM (SELECT DISTINCT p.Id_Pedido, p.Total FROM pedidos p INNER JOIN detalle_pedidos dp ON p.Id_Pedido = dp.Id_Pedido INNER JOIN productos pr ON dp.Producto = pr.Nombre WHERE pr.Id_Usuario = ? AND p.Estado = 'ACEPTADO') AS sub";
    String sqlProds = "SELECT IFNULL(SUM(dp.Cantidad), 0) AS total_articulos FROM detalle_pedidos dp INNER JOIN pedidos p ON dp.Id_Pedido = p.Id_Pedido INNER JOIN productos pr ON dp.Producto = pr.Nombre WHERE pr.Id_Usuario = ? AND p.Estado = 'ACEPTADO'";
    String sqlTrans = "SELECT COUNT(DISTINCT p.Id_Usuario) AS total_clientes FROM pedidos p INNER JOIN detalle_pedidos dp ON p.Id_Pedido = dp.Id_Pedido INNER JOIN productos pr ON dp.Producto = pr.Nombre WHERE pr.Id_Usuario = ? AND p.Estado = 'ACEPTADO'";
    try {
        ps = cn.prepareStatement(sqlVentas);
        ps.setInt(1, this.idUsuarioActual);
        rs = ps.executeQuery();
        if (rs.next()) {
            lblTotalVentas.setText(String.format("$ %.2f", rs.getDouble("total_dinero")));
        }
        ps = cn.prepareStatement(sqlProds);
        ps.setInt(1, this.idUsuarioActual);
        rs = ps.executeQuery();
        if (rs.next()) {
            lblProductosVendido.setText(rs.getInt("total_articulos") + "");
        }
        ps = cn.prepareStatement(sqlTrans);
        ps.setInt(1, this.idUsuarioActual);
        rs = ps.executeQuery();
        if (rs.next()) {
            lblTransaccion.setText(String.valueOf(rs.getInt("total_clientes")));
        }
    } catch (Exception e) {
        System.out.println("Error al cargar resumen de estadísticas: " + e.getMessage());
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
        String sql = "SELECT Nombre AS producto, Cantidad AS cantidad, Precio AS precio, Total AS subtotal, Fecha AS fecha " +
                     "FROM productos " +
                     "WHERE Id_Usuario = ? " +
                     "ORDER BY Fecha DESC";

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
        JOptionPane.showMessageDialog(
                this,
                "Error al cargar tabla de ventas: " + e.getMessage()
        );
    }
}
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnHistorialVentas;
    private javax.swing.JButton btnRegistroProductos;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JLabel lblProductosVendido;
    private javax.swing.JLabel lblProductosVendidos;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JLabel lblTransaccion;
    private javax.swing.JLabel lblTransacciones;
    private javax.swing.JLabel lblVentasTotales;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
