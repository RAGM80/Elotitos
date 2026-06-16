package versionbeta;
import versionbeta.ConexionMySQL;
import versionbeta.VentanaLoginB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author rafhi
 */

public class CrearCuentaB extends javax.swing.JInternalFrame {
  boolean passVisible = false; 
   private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CrearCuentaB.class.getName());

    /**
     * Creates new form CrearCuentaB
     */
    private MyDesktopB jefe;
    public CrearCuentaB() {
    this.jefe = null;
        initComponents();
        inicializarPlaceholders();
    }
    public CrearCuentaB(MyDesktopB principal) {
        this.jefe = principal;
        initComponents();
        inicializarPlaceholders();
    }
    private void inicializarPlaceholders() {
        txtNombre.setText("Nombre");
        txtNombre.setForeground(new java.awt.Color(153, 153, 153));

        txtApellido.setText("Apellidos");
        txtApellido.setForeground(new java.awt.Color(153, 153, 153));

        txtUsuario.setText("Usuario");
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));

        txtPregunta.setText("Ingresa una pregunta");
        txtPregunta.setForeground(new java.awt.Color(153, 153, 153));

        txtRespuesta.setText("Ingresa la respuesta a tu pregunta");
        txtRespuesta.setForeground(new java.awt.Color(153, 153, 153));

        cmbRol.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1 && value == null) {
                    setText("Rol"); 
                    setForeground(new java.awt.Color(153, 153, 153)); 
                } else {
                    setForeground(new java.awt.Color(0, 0, 0)); 
                }
                return this;
            }
            
        });
        cmbRol.setSelectedIndex(-1);

        pwdContrasena.setText("Contraseña");
        pwdContrasena.setForeground(new java.awt.Color(153, 153, 153));
        pwdContrasena.setEchoChar((char) 0);

        pwdConfirmarContrasena.setText("Confirmar contraseña");
        pwdConfirmarContrasena.setForeground(new java.awt.Color(153, 153, 153));
        pwdConfirmarContrasena.setEchoChar((char) 0);

        pwdContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(pwdContrasena.getPassword());

                if (pass.equals("Contraseña")) {
                    pwdContrasena.setText("");
                    pwdContrasena.setForeground(java.awt.Color.BLACK);
                    pwdContrasena.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(pwdContrasena.getPassword()).trim();

                if (pass.isEmpty()) {
                    pwdContrasena.setText("Contraseña");
                    pwdContrasena.setForeground(new java.awt.Color(153, 153, 153));
                    pwdContrasena.setEchoChar((char) 0);
                }
            }
        });

        pwdConfirmarContrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                String pass = new String(pwdConfirmarContrasena.getPassword());

                if (pass.equals("Confirmar contraseña")) {
                    pwdConfirmarContrasena.setText("");
                    pwdConfirmarContrasena.setForeground(java.awt.Color.BLACK);
                    pwdConfirmarContrasena.setEchoChar('•');
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                String pass = new String(pwdConfirmarContrasena.getPassword()).trim();

                if (pass.isEmpty()) {
                    pwdConfirmarContrasena.setText("Confirmar contraseña");
                    pwdConfirmarContrasena.setForeground(new java.awt.Color(153, 153, 153));
                    pwdConfirmarContrasena.setEchoChar((char) 0);
                }
            }
        });

        javax.swing.SwingUtilities.invokeLater(() -> {
            pnlFondo.requestFocusInWindow();
        });
        
    }
private void abrirLogin() {

    VentanaLoginB login = new VentanaLoginB(this.jefe);

    if (this.jefe != null) {

        this.jefe.jDesktopPane1.add(login);
        login.setSize(400, 485);
        login.setVisible(true);

        int x = (this.jefe.jDesktopPane1.getWidth() - login.getWidth()) / 2;
        int y = (this.jefe.jDesktopPane1.getHeight() - login.getHeight()) / 2;

        login.setLocation(x, y);

        this.jefe.jDesktopPane1.revalidate();
        this.jefe.jDesktopPane1.repaint();

    } else {
        login.setVisible(true);
    }

    this.dispose();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        cmbRol = new javax.swing.JComboBox<>();
        pwdContrasena = new javax.swing.JPasswordField();
        pwdConfirmarContrasena = new javax.swing.JPasswordField();
        btnGuardar = new javax.swing.JButton();
        lblVerContrasena = new javax.swing.JLabel();
        txtPregunta = new javax.swing.JTextField();
        lblAyudaPregunta = new javax.swing.JLabel();
        txtRespuesta = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTitulo.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblTitulo.setText("Crear Cuenta");

        txtNombre.setBackground(new java.awt.Color(204, 204, 204));
        txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });

        txtApellido.setBackground(new java.awt.Color(204, 204, 204));
        txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtApellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidoFocusLost(evt);
            }
        });
        txtApellido.addActionListener(this::txtApellidoActionPerformed);

        txtUsuario.setBackground(new java.awt.Color(204, 204, 204));
        txtUsuario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });

        cmbRol.setBackground(new java.awt.Color(204, 204, 204));
        cmbRol.setForeground(new java.awt.Color(153, 153, 153));
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor", "Cliente" }));
        cmbRol.setToolTipText("");
        cmbRol.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmbRol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbRolFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmbRolFocusLost(evt);
            }
        });
        cmbRol.addActionListener(this::cmbRolActionPerformed);

        pwdContrasena.setBackground(new java.awt.Color(204, 204, 204));
        pwdContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        pwdConfirmarContrasena.setBackground(new java.awt.Color(204, 204, 204));
        pwdConfirmarContrasena.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pwdConfirmarContrasena.addActionListener(this::pwdConfirmarContrasenaActionPerformed);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        lblVerContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intento/pngwing.com.png"))); // NOI18N
        lblVerContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVerContrasenaMouseClicked(evt);
            }
        });

        txtPregunta.setBackground(new java.awt.Color(204, 204, 204));
        txtPregunta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPreguntaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPreguntaFocusLost(evt);
            }
        });
        txtPregunta.addActionListener(this::txtPreguntaActionPerformed);

        lblAyudaPregunta.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        lblAyudaPregunta.setForeground(new java.awt.Color(102, 102, 0));
        lblAyudaPregunta.setText("En caso de que olvides tu contraseña se mostrara esta pregunta para poder recuperarla");

        txtRespuesta.setBackground(new java.awt.Color(204, 204, 204));
        txtRespuesta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtRespuesta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRespuestaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRespuestaFocusLost(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(204, 204, 204));
        btnRegresar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(pwdContrasena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVerContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtApellido)
                            .addComponent(txtUsuario)
                            .addComponent(cmbRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pwdConfirmarContrasena)
                            .addComponent(txtPregunta)
                            .addComponent(lblAyudaPregunta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRespuesta))
                        .addContainerGap(34, Short.MAX_VALUE))))
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pwdContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVerContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdConfirmarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAyudaPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void insertarDatos() {
        String nombreReal = this.txtNombre.getText().trim();
    String apellidoReal = this.txtApellido.getText().trim();
    String user = this.txtUsuario.getText().trim();
    String pass = new String(this.pwdContrasena.getPassword()).trim();
    String confirmPass = new String(this.pwdConfirmarContrasena.getPassword()).trim();
    if (pass.equals("Contraseña")) {
    pass = "";
}

if (confirmPass.equals("Confirmar contraseña")) {
    confirmPass = "";
}
    String pregunta = this.txtPregunta.getText().trim();
    String respuesta = this.txtRespuesta.getText().trim();

    if (nombreReal.isEmpty() || nombreReal.equalsIgnoreCase("Nombre")) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa tu nombre.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        txtNombre.requestFocus();
        return;
    }

    if (apellidoReal.isEmpty() || apellidoReal.equalsIgnoreCase("Apellidos")) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa tus apellidos.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        txtApellido.requestFocus();
        return;
    }

    if (this.cmbRol.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(
                this,
                "Selecciona un rol.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        cmbRol.requestFocus();
        return;
    }

    String rol = this.cmbRol.getSelectedItem().toString();

    if (user.isEmpty() || user.equalsIgnoreCase("Usuario")) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa un usuario.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        txtUsuario.requestFocus();
        return;
    }

    if (user.contains(" ")) {
        JOptionPane.showMessageDialog(
                this,
                "El usuario no debe contener espacios.",
                "Usuario inválido",
                JOptionPane.WARNING_MESSAGE
        );
        txtUsuario.requestFocus();
        return;
    }

    if (pass.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa una contraseña.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        pwdContrasena.requestFocus();
        return;
    }

    if (pass.length() < 4) {
        JOptionPane.showMessageDialog(
                this,
                "La contraseña debe tener al menos 4 caracteres.",
                "Contraseña débil",
                JOptionPane.WARNING_MESSAGE
        );
        pwdContrasena.requestFocus();
        return;
    }

    if (confirmPass.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Confirma tu contraseña.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        pwdConfirmarContrasena.requestFocus();
        return;
    }

    if (!pass.equals(confirmPass)) {
        JOptionPane.showMessageDialog(
                this,
                "Las contraseñas no coinciden.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        pwdConfirmarContrasena.requestFocus();
        return;
    }

    if (pregunta.isEmpty() || pregunta.equalsIgnoreCase("Ingresa una pregunta")) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa una pregunta de seguridad.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        txtPregunta.requestFocus();
        return;
    }

    if (respuesta.isEmpty() || respuesta.equalsIgnoreCase("Ingresa la respuesta a tu pregunta")) {
        JOptionPane.showMessageDialog(
                this,
                "Ingresa la respuesta de seguridad.",
                "Dato faltante",
                JOptionPane.WARNING_MESSAGE
        );
        txtRespuesta.requestFocus();
        return;
    }

    ConexionMySQL cmysql = new ConexionMySQL();
    Connection conec = cmysql.Conectar();

    if (conec == null) {
        JOptionPane.showMessageDialog(
                this,
                "No se pudo conectar a la base de datos.",
                "Error de conexión",
                JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    try {
        String sqlExiste =
                "SELECT COUNT(*) AS total " +
                "FROM usuarios " +
                "WHERE Usuario = ?";

        PreparedStatement psExiste = conec.prepareStatement(sqlExiste);
        psExiste.setString(1, user);

        ResultSet rsExiste = psExiste.executeQuery();

        if (rsExiste.next() && rsExiste.getInt("total") > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ese usuario ya existe. Elige otro.",
                    "Usuario repetido",
                    JOptionPane.WARNING_MESSAGE
            );
            txtUsuario.requestFocus();
            return;
        }

        String senSQL =
                "INSERT INTO usuarios " +
                "(Usuario, Contrasena, Rol, Estado, Pregunta_Seguridad, Respuesta_Seguridad, Nombre, Apellido) " +
                "VALUES (?, ?, ?, 'Activo', ?, ?, ?, ?)";

        PreparedStatement ps = conec.prepareStatement(senSQL);

        ps.setString(1, user);
        ps.setString(2, pass);
        ps.setString(3, rol);
        ps.setString(4, pregunta);
        ps.setString(5, respuesta);
        ps.setString(6, nombreReal);
        ps.setString(7, apellidoReal);

        int n = ps.executeUpdate();

        if (n > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta creada correctamente."
            );

            abrirLogin();
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                "Error al insertar: " + ex.getMessage(),
                "Error SQL",
                JOptionPane.ERROR_MESSAGE
        );
    } finally {
        try {
            if (conec != null) {
                conec.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }
    }
    private void pwdConfirmarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdConfirmarContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwdConfirmarContrasenaActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed

    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        if (txtNombre.getText().equals("Nombre")) {
            txtNombre.setText("");
            txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        }
    
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        if (txtNombre.getText().isEmpty()) {
            txtNombre.setForeground(new java.awt.Color(153, 153, 153));
            txtNombre.setText("Nombre");
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtApellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoFocusGained
       if (txtApellido.getText().equals("Apellidos")) {
            txtApellido.setText("");
            txtApellido.setForeground(new java.awt.Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtApellidoFocusGained

    private void txtApellidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidoFocusLost
        if (txtApellido.getText().isEmpty()) {
            txtApellido.setForeground(new java.awt.Color(153, 153, 153));
            txtApellido.setText("Apellidos");
        }       
    }//GEN-LAST:event_txtApellidoFocusLost

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        if (txtUsuario.getText().equals("Usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(new java.awt.Color(0, 0, 0));
        }
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
            txtUsuario.setText("Usuario");
        }
    }//GEN-LAST:event_txtUsuarioFocusLost
 
    private void lblVerContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVerContrasenaMouseClicked
        if (passVisible) {
            pwdContrasena.setEchoChar('•'); 
            passVisible = false;
        } else {
            pwdContrasena.setEchoChar((char) 0); 
            passVisible = true;
        }

    }//GEN-LAST:event_lblVerContrasenaMouseClicked

    private void cmbRolFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbRolFocusGained
    
   
    }//GEN-LAST:event_cmbRolFocusGained

    private void cmbRolFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbRolFocusLost
    
   
    }//GEN-LAST:event_cmbRolFocusLost

    private void txtPreguntaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPreguntaFocusGained
    if (txtPregunta.getText().equals("Ingresa una pregunta")) {
            txtPregunta.setText("");
            txtPregunta.setForeground(new java.awt.Color(0, 0, 0));
        }

    }//GEN-LAST:event_txtPreguntaFocusGained

    private void txtPreguntaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPreguntaFocusLost
    if (txtPregunta.getText().isEmpty()) {
            txtPregunta.setForeground(new java.awt.Color(153, 153, 153));
            txtPregunta.setText("Ingresa una pregunta");
        }
  
    }//GEN-LAST:event_txtPreguntaFocusLost

    private void txtRespuestaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRespuestaFocusGained
    if (txtRespuesta.getText().equals("Ingresa la respuesta a tu pregunta")) {
            txtRespuesta.setText("");
            txtRespuesta.setForeground(new java.awt.Color(0, 0, 0));
        }

    }//GEN-LAST:event_txtRespuestaFocusGained

    private void txtRespuestaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRespuestaFocusLost
    if (txtRespuesta.getText().isEmpty()) {
            txtRespuesta.setForeground(new java.awt.Color(153, 153, 153));
            txtRespuesta.setText("Ingresa la respuesta a tu pregunta");
        }

    }//GEN-LAST:event_txtRespuestaFocusLost

    private void txtPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPreguntaActionPerformed

    }//GEN-LAST:event_txtPreguntaActionPerformed

    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
   

    }//GEN-LAST:event_cmbRolActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    insertarDatos();
   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
   abrirLogin();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
    java.awt.EventQueue.invokeLater(() -> new CrearCuentaB(null).setVisible(true));
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel lblAyudaPregunta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVerContrasena;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPasswordField pwdConfirmarContrasena;
    private javax.swing.JPasswordField pwdContrasena;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPregunta;
    private javax.swing.JTextField txtRespuesta;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
