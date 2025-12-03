/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Modelo.Pais;
import Controlador.Conn;
import DAO.PaisDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author aacev
 */
public class VistaPais extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaPais.class.getName());

    private DefaultTableModel tabla;
    private PaisDAO paisDAO;
    private List<Pais> paises;

    public VistaPais() {
        initComponents();
        inicializarComponentes();
        cargarDatosDesdeBD();
    }
    
    private void inicializarComponentes() {
        tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        setLocationRelativeTo(null);
        
        // Inicializar DAO
        try {
            this.paisDAO = new PaisDAO();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error inicializando PaisDAO: " + e.getMessage());
        }
    }
    
    private void cargarDatosDesdeBD() {
        try {
            if (paisDAO != null) {
                this.paises = paisDAO.obtenerTodosPaises();
                actualizarTabla();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error cargando datos: " + e.getMessage());
        }
    }

    private void actualizarTabla() {
        if (paises == null) return;
        
        tabla.setRowCount(0); 

        for (Pais pais : paises) {
            Object[] fila = {
                pais.getCodPais(),
                pais.getNombre(),
                pais.getContinente(),
                pais.getPoblacion()
            };
            tabla.addRow(fila);
        }
    }
    
    private Pais obtenerPaisPorFila(int fila) {
        if (fila >= 0 && fila < paises.size()) {
            return paises.get(fila);
        }
        return null;
    }
    
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtContinente.setText("");
        txtPoblacion.setText("");
    }
    
    private boolean validarCamposRequeridos() {
        return !txtCodigo.getText().trim().isEmpty() &&
               !txtNombre.getText().trim().isEmpty() &&
               !txtContinente.getText().trim().isEmpty() &&
               !txtPoblacion.getText().trim().isEmpty();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblContinente = new javax.swing.JLabel();
        lblPoblacion = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtContinente = new javax.swing.JTextField();
        txtPoblacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bttnComparar = new javax.swing.JButton();
        bttnAgregar = new javax.swing.JButton();
        bttnModificar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        bttnAtras = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(720, 400));

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(430, 288));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Continente", "Poblacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        jLayeredPane2.setPreferredSize(new java.awt.Dimension(250, 288));

        lblTitulo.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registro poblacional internacional");

        lblCodigo.setText("Codigo");

        lblNombre.setText("Nombre");

        lblContinente.setText("Continente");

        lblPoblacion.setText("Poblacion");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtContinente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContinenteKeyTyped(evt);
            }
        });

        txtPoblacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPoblacionActionPerformed(evt);
            }
        });
        txtPoblacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPoblacionKeyTyped(evt);
            }
        });

        jLayeredPane2.setLayer(lblTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lblCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lblNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lblContinente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(lblPoblacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtNombre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtContinente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtPoblacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContinente, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(lblPoblacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtContinente)
                    .addComponent(txtPoblacion)
                    .addComponent(txtCodigo)))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContinente)
                    .addComponent(txtContinente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPoblacion)
                    .addComponent(txtPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bttnComparar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        bttnComparar.setText("Comparar");
        bttnComparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCompararActionPerformed(evt);
            }
        });

        bttnAgregar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        bttnAgregar.setText("Agregar");
        bttnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAgregarActionPerformed(evt);
            }
        });

        bttnModificar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        bttnModificar.setText("Modificar");
        bttnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bttnComparar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(bttnModificar)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttnComparar)
                    .addComponent(bttnAgregar)
                    .addComponent(bttnModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bttnAtras.setText("Atras");
        bttnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bttnAtras)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bttnAtras)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void bttnCompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCompararActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un pais de la tabla", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Pais paisSeleccionado = obtenerPaisPorFila(fila);
        if (paisSeleccionado != null) {
            txtCodigo.setText(paisSeleccionado.getCodPais());
            txtNombre.setText(paisSeleccionado.getNombre());
            txtContinente.setText(paisSeleccionado.getContinente());
            txtPoblacion.setText(String.valueOf(paisSeleccionado.getPoblacion()));
        }
    }//GEN-LAST:event_bttnCompararActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        if(txtCodigo.getText().length() > 2 || !Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void bttnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAgregarActionPerformed
        if (!validarCamposRequeridos()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String codigo = txtCodigo.getText().trim().toUpperCase();
        String nombre = txtNombre.getText().trim();
        String continente = txtContinente.getText().trim();
        String poblacionStr = txtPoblacion.getText().trim();
        
        try {
            Pais paisExistente = paisDAO.obtenerPaisPorCodigo(codigo);
            if (paisExistente != null) {
                JOptionPane.showMessageDialog(this, "El código del país ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int poblacion = Integer.parseInt(poblacionStr);
            
            Pais nuevoPais = new Pais(
                nombre, 
                continente, 
                "N/A",
                0.0f, 
                0, 
                poblacion, 
                0.0f, 
                0.0f, 
                "N/A",
                "N/A",
                codigo
                );
            
            
            int idGenerado = paisDAO.insertarPais(nuevoPais);
            
            if (idGenerado > 0) {
                JOptionPane.showMessageDialog(this, "País " + nombre + " agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosDesdeBD();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el país", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La población debe ser un número válido", "Error de dato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error agregando país: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnAgregarActionPerformed

    private void txtPoblacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPoblacionActionPerformed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_txtPoblacionActionPerformed

    private void bttnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnModificarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un país para modificar", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validarCamposRequeridos()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Pais paisOriginal = obtenerPaisPorFila(fila);
        if (paisOriginal == null) {
            JOptionPane.showMessageDialog(this, "País no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String codigo = txtCodigo.getText().trim().toUpperCase();
        String nombre = txtNombre.getText().trim();
        String continente = txtContinente.getText().trim();
        String poblacionStr = txtPoblacion.getText().trim();
        
        try {
            int poblacion = Integer.parseInt(poblacionStr);
            
            // Actualizar el país existente
            paisOriginal.setCodPais(codigo);
            paisOriginal.setNombre(nombre);
            paisOriginal.setContinente(continente);
            paisOriginal.setPoblacion(poblacion);
            
            // Actualizar en la base de datos
            boolean exito = paisDAO.actualizarPais(paisOriginal);
            
            if (exito) {
                JOptionPane.showMessageDialog(this, "País modificado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosDesdeBD(); // Recargar datos desde BD
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar el país", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La población debe ser un número válido", "Error de dato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error modificando país: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bttnModificarActionPerformed

    private void bttnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_bttnAtrasActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
       if(txtNombre.getText().length() > 50 || !Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtContinenteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContinenteKeyTyped
        if(txtContinente.getText().length() > 50 || !Character.isLetter(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtContinenteKeyTyped

    private void txtPoblacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPoblacionKeyTyped
       if(txtPoblacion.getText().length() > 15 || !Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtPoblacionKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        if (fila != -1) {
            Pais paisSeleccionado = obtenerPaisPorFila(fila);
            if (paisSeleccionado != null) {
                txtCodigo.setText(paisSeleccionado.getCodPais());
                txtNombre.setText(paisSeleccionado.getNombre());
                txtContinente.setText(paisSeleccionado.getContinente());
                txtPoblacion.setText(String.valueOf(paisSeleccionado.getPoblacion()));
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new VistaPais().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnAgregar;
    private javax.swing.JButton bttnAtras;
    private javax.swing.JButton bttnComparar;
    private javax.swing.JButton bttnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblContinente;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPoblacion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContinente;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPoblacion;
    // End of variables declaration//GEN-END:variables
}
