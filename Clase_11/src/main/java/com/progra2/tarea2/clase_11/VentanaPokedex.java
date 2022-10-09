package com.progra2.tarea2.clase_11;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class VentanaPokedex extends javax.swing.JFrame {
    
    //defino mis variables de instancia
    BufferedImage buffer1 = null;
    Image imagen1 = null;
    int contador = 0 ;

    
    //variables para db
    Statement  estado; // lleva el estado de la conexion.
    ResultSet resultadoConsulta; //variable que contiene el resultado de la consulta
    Connection conexion;
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) imagenPokemon.getGraphics();
        g2.drawImage(buffer1,0,0,imagenPokemon.getWidth(),imagenPokemon.getHeight(),null);
    }

    /**
     * Creates new form VentanaPokedex
     */
    public VentanaPokedex() {
        initComponents();
        try {
            imagen1 = ImageIO.read(new File("C:\\Users\\ASUS\\Downloads\\Telegram Desktop\\datos\\imagenes\\black-white.png"));
        } catch (Exception e) {
            System.out.println("Hubo error en "+e.getMessage());
        }
        
        buffer1 = (BufferedImage) imagenPokemon.createImage(imagenPokemon.getWidth(),imagenPokemon.getHeight());
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String JDBC_URL = "jdbc:mysql://localhost:3306/pokedex?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
            conexion = DriverManager.getConnection(JDBC_URL,"root","donald225");
            estado = conexion.createStatement();
        } catch (Exception e) {
            System.out.println("Error en:"+e.getMessage());
        }
    }
    
    private void dibujaElPokemonQueEstaEnLaPosicion(int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer1.getGraphics();
        g2.setColor(Color.black);
        g2.fillRect(0, 0, //pinta el fondo del jpanel negro
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight()); 
        g2.drawImage(imagen1,
                0,  //posicion X inicial dentro del jpanel 
                0,  // posicion Y inicial dentro del jpanel
                imagenPokemon.getWidth(), //ancho del jpanel
                imagenPokemon.getHeight(), //alto del jpanel
                columna*96, //posicion inicial X dentro de la imagen de todos los pokemon
                fila*96, //posicion inicial Y dentro de la imagen de todos los pokemon
                columna*96 + 96, //posicion final X
                fila*96 + 96, //posicion final Y
                null  //si no lo pones no va
                );
        repaint();
    }
    
    private void datosdelPokemon(int id){
        
        dibujaElPokemonQueEstaEnLaPosicion(id);
        
        try{
            resultadoConsulta = estado.executeQuery("select id, name, species, habitat from pokemon where id = "+(id + 1));
            if(resultadoConsulta.next()){
                nombrePokemon.setText(resultadoConsulta.getString(1));
                label_Id.setText(resultadoConsulta.getString(2));
                Label_Especie.setText(resultadoConsulta.getString(3));
                Label_Habitat.setText(resultadoConsulta.getString(4));
            }else{
                limpiar2();
            }
        }catch(SQLException ex){
            
        }   
    }
    
    private void derecha(){
        
        int id = Integer.parseInt(TextField_Id.getText());
        id = id + 1;
        TextField_Id.setText(id+"");
        
       try{
            resultadoConsulta = estado.executeQuery("select id, name, species, habitat from pokemon where id = "+id);
            if(resultadoConsulta.next()){
                nombrePokemon.setText(resultadoConsulta.getString(1));
                label_Id.setText(resultadoConsulta.getString(2));
                Label_Especie.setText(resultadoConsulta.getString(3));
                Label_Habitat.setText(resultadoConsulta.getString(4));                
            }
            else{
                limpiar2();
            }
            id --;
            dibujaElPokemonQueEstaEnLaPosicion(id);
        } catch (SQLException ex) {
        }
        
        
    }
    
    private void izquierda(){ 
       try{
           int id = Integer.parseInt(TextField_Id.getText());
           id = id - 1;
           TextField_Id.setText(id+"");
                    
            resultadoConsulta = estado.executeQuery("select id, name, species, habitat from pokemon where id = "+id);
            if(resultadoConsulta.next()){
                nombrePokemon.setText(resultadoConsulta.getString(1));
                label_Id.setText(resultadoConsulta.getString(2));
                Label_Especie.setText(resultadoConsulta.getString(3));
                Label_Habitat.setText(resultadoConsulta.getString(4));
            }
            else{
                limpiar2();
            }
            
         id--;
        dibujaElPokemonQueEstaEnLaPosicion(id);
        } catch (SQLException ex) {
            
        }         
    }
    
    private void limpiar(){
        nombrePokemon.setText("Nombre");
        label_Id.setText("Id");
        Label_Especie.setText("Especie");
        Label_Habitat.setText("Habitat");
        TextField_Id.setText("");
        imagenPokemon.repaint();
    }
    
    private void limpiar2(){
        nombrePokemon.setText("Este pokemon no figura en la pokedex");
        label_Id.setText("Id");
        Label_Especie.setText("Especie");
        Label_Habitat.setText("Habitat");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagenPokemon = new javax.swing.JPanel();
        izq = new javax.swing.JButton();
        der = new javax.swing.JButton();
        nombrePokemon = new javax.swing.JLabel();
        Button_Buscar = new javax.swing.JButton();
        label_Id = new javax.swing.JLabel();
        Label_Especie = new javax.swing.JLabel();
        Label_Habitat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TextField_Id = new javax.swing.JTextField();
        Button_Limpiar = new javax.swing.JButton();
        Button_Agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout imagenPokemonLayout = new javax.swing.GroupLayout(imagenPokemon);
        imagenPokemon.setLayout(imagenPokemonLayout);
        imagenPokemonLayout.setHorizontalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );
        imagenPokemonLayout.setVerticalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        izq.setText("<= IZQ");
        izq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqActionPerformed(evt);
            }
        });

        der.setText("DER =>");
        der.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derActionPerformed(evt);
            }
        });

        nombrePokemon.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        nombrePokemon.setText("Nombre");

        Button_Buscar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        Button_Buscar.setText("Buscar");
        Button_Buscar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        Button_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_BuscarActionPerformed(evt);
            }
        });

        label_Id.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label_Id.setText("id");

        Label_Especie.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        Label_Especie.setText("Especie");

        Label_Habitat.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        Label_Habitat.setText("Habitat");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingrese el id del pokemon a buscar");

        TextField_Id.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        Button_Limpiar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        Button_Limpiar.setText("Limpiar");
        Button_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_LimpiarActionPerformed(evt);
            }
        });

        Button_Agregar.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        Button_Agregar.setText("Agregar");
        Button_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagenPokemon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(nombrePokemon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_Id)
                                        .addGap(268, 268, 268)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(179, 179, 179))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(Label_Especie)
                                                .addGap(367, 367, 367))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(Button_Buscar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                        .addComponent(Button_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Label_Habitat)
                                        .addGap(339, 339, 339)
                                        .addComponent(TextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(izq, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(der, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Button_Agregar)
                        .addGap(365, 365, 365))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(imagenPokemon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(der, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izq, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(nombrePokemon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label_Id)
                        .addGap(15, 15, 15)
                        .addComponent(Label_Especie)
                        .addGap(14, 14, 14)
                        .addComponent(Label_Habitat)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextField_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Button_Limpiar)
                            .addComponent(Button_Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(Button_Agregar)
                        .addGap(81, 81, 81))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void izqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqActionPerformed
        try{
            izquierda();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No ha ingresado ningun id");
        }
    }//GEN-LAST:event_izqActionPerformed

    private void derActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derActionPerformed
       
        try{
            derecha();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No ha ingresado ningun id");
        }
    }//GEN-LAST:event_derActionPerformed

    private void Button_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_BuscarActionPerformed
        // TODO add your handling code here:
        try{
            int id = Integer.parseInt(TextField_Id.getText());
            if(id > 507){
                JOptionPane.showMessageDialog(null,"Solo hay 507 pokemons en esta base de datos");
            }else{
                datosdelPokemon(id - 1);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No ha ingresado ningun id");
        }
    }//GEN-LAST:event_Button_BuscarActionPerformed

    private void Button_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_LimpiarActionPerformed
        // TODO add your handling code here:
            limpiar();
    }//GEN-LAST:event_Button_LimpiarActionPerformed

    private void Button_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AgregarActionPerformed
        // TODO add your handling code here:
        AgregarPokemon abrir = new AgregarPokemon();
        abrir.setVisible(true);
    }//GEN-LAST:event_Button_AgregarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_Agregar;
    private javax.swing.JButton Button_Buscar;
    private javax.swing.JButton Button_Limpiar;
    private javax.swing.JLabel Label_Especie;
    private javax.swing.JLabel Label_Habitat;
    private javax.swing.JTextField TextField_Id;
    private javax.swing.JButton der;
    private javax.swing.JPanel imagenPokemon;
    private javax.swing.JButton izq;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label_Id;
    private javax.swing.JLabel nombrePokemon;
    // End of variables declaration//GEN-END:variables
}
