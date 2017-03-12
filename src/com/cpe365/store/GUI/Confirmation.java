/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe365.store.GUI;

import com.cpe365.store.Data.Item;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cptony
 */
public class Confirmation extends javax.swing.JFrame {

    String customerName;
    String customerAddress;
    String customerCcn;
    List<Item> items;
    /**
     * Creates new form Confirmation
     */
    public Confirmation(String customerName, String customerAddress, String customerCcn, List<Item> items) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCcn = customerCcn;
        this.items = items;
        initComponents();
        setLocationRelativeTo(null);
        
        DefaultTableModel model = (DefaultTableModel) itemsTable.getModel();
        for(Item item : items)
            model.addRow(new Object[]{item.getId(), item.getName(), item.getStock(), item.getPrice(), item.getPrice()*item.getStock()});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLable = new javax.swing.JLabel();
        nameLable = new javax.swing.JLabel();
        addressLable = new javax.swing.JLabel();
        ccnLable = new javax.swing.JLabel();
        itemsLable = new javax.swing.JLabel();
        itemsPane = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        ConfirmationButton = new javax.swing.JButton();
        creditcardField = new javax.swing.JLabel();
        addressField = new javax.swing.JLabel();
        nameField = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLable.setText("Confirmation");

        nameLable.setText("Name:");

        addressLable.setText("Address:");

        ccnLable.setText("Creditcard Number:");

        itemsLable.setText("Items:");

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Quantity", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        itemsTable.setName(""); // NOI18N
        itemsPane.setViewportView(itemsTable);

        ConfirmationButton.setText("Confirm Order");
        ConfirmationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmationButtonActionPerformed(evt);
            }
        });

        creditcardField.setText(customerCcn);

        addressField.setText(customerAddress);

        nameField.setText(customerName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(titleLable))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemsPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(itemsLable)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ccnLable)
                                        .addComponent(addressLable, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nameLable, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addressField)
                                        .addComponent(creditcardField)
                                        .addComponent(nameField))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(ConfirmationButton)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLable)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLable)
                    .addComponent(nameField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLable)
                    .addComponent(addressField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccnLable)
                    .addComponent(creditcardField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
<<<<<<< HEAD
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jButton1)
=======
                .addComponent(itemsLable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(ConfirmationButton)
>>>>>>> 752936b17955aee339a8fd55fa5f72aeea9f2159
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmationButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmationButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Confirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                List<Item> itemsList = new ArrayList<Item>();
                
                for(int i = 0 ; i < 10 ; ++i)
                    itemsList.add(new Item(i, "item"+Integer.toString(i), 22.0, "lol price","lol manufacturer", 5, false));
                
                new Confirmation("TESTNAME", "TEST ADDRESS", "TEST CCN", itemsList).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmationButton;
    private javax.swing.JLabel addressField;
    private javax.swing.JLabel addressLable;
    private javax.swing.JLabel ccnLable;
    private javax.swing.JLabel creditcardField;
    private javax.swing.JLabel itemsLable;
    private javax.swing.JScrollPane itemsPane;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel nameField;
    private javax.swing.JLabel nameLable;
    private javax.swing.JLabel titleLable;
    // End of variables declaration//GEN-END:variables
}
