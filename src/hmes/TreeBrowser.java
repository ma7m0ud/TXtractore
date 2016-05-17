/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmes;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author user1
 */
public class TreeBrowser extends javax.swing.JPanel {
         File fileRoot ;

    private DefaultMutableTreeNode root;

    private DefaultTreeModel treeModel;

    /**
     * Creates new form Browse
     */
    String fileAp;
    public TreeBrowser(String path) {
        fileRoot= new File(path);;
        fileAp=path;
        initComponents();
runNode();
tree.expandRow(0);
//System.out.println(tree.getPathForRow(0));
    }
public DefaultTreeModel getmod(){
  //      System.out.println(System.getProperty("user.home")+"\\Dropbox");
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);

       
return treeModel;
}
File fil;
public void runNode(){

        tree.setShowsRootHandles(true);
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
           String url=e.getPath().toString();
           url=url.substring(1, url.length()-1);
           
           url=url.replaceAll(", ", "\\\\");
               
                if(fileAp.contains("Dropbox")){
                fil=new File(System.getProperty("user.home")+"\\"+url);}else if(fileAp.contains("Google Drive")){
                fil=new File(System.getProperty("user.home")+"\\"+url);
                }else{
                fil=new File(System.getProperty("user.home")+"\\Downloads\\"+url);
                }
               }
            
        });
         TreeChildNodes ccn = 
                new TreeChildNodes(fileRoot, root);
new Thread(ccn).start();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(605, 472));

        tree.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tree.setModel(getmod());
        jScrollPane1.setViewportView(tree);

        jButton1.setBackground(new java.awt.Color(61, 90, 254));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Open");
        jButton1.setPreferredSize(new java.awt.Dimension(61, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(61, 90, 254));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Extract");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(fil!=null){
        if(!fil.isDirectory()){
                Desktop dt = Desktop.getDesktop();
                
                try {
                    
                    dt.open(fil);
                    
                } catch (IOException ex) {
                  
                }
            }}        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     
        JFileChooser cho=new JFileChooser();
         cho.setDialogTitle("Extract To");
         cho.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         cho.showDialog(this, "EXTRACT");
        long startTime = System.nanoTime();
         try {
         if(fil.isDirectory()){
         FileUtils.copyDirectoryToDirectory(fil, cho.getSelectedFile());
         }else{    
             FileUtils.copyFileToDirectory(fil, cho.getSelectedFile());
         }} catch (IOException ex) {
             Logger.getLogger(TableBrowser.class.getName()).log(Level.SEVERE, null, ex);
         }     // TODO add your handling code here:
         
long endTime = System.nanoTime();

long duration = (endTime - startTime); 
System.out.println("Extraction Time in milliseconds = "+duration/1000000);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables
}
