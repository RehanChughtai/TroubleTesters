
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JTable;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class JFrameScoreBoard extends javax.swing.JFrame 
{
    private BackgroundPanel jPanelBack = null;
    private JTable jTable = null;
    private JPanel jPanelFind = null;
    private JLabel jLabelFind = null;
    private JTextField jTextFieldFind = null;
    private JButton jButtonFind = null;
    private JButton jButtonClose = null;
    


    /**
     * Creates new form JFrameScoreBoard
     */
    public JFrameScoreBoard() 
    {
        initComponents();
        
        // set the size of the form
        this.setBounds(0, 0, 550, 440);
        
        // set the background panel
        // <editor-fold defaultstate="collapsed" desc="Back Panel"> 

        try
        {
            Image img = ImageIO.read(new File("Background.jpg"));
            jPanelBack = new BackgroundPanel(img, BackgroundPanel.SCALED, 1.0f, 0.5f);
            getContentPane().add(jPanelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, this.getWidth(), this.getHeight()-25));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Login Screen", JOptionPane.WARNING_MESSAGE);
        }
        
        // </editor-fold>

        // set the Layout Manager of the back panel
        jPanelBack.setLayout(new BoxLayout(jPanelBack, BoxLayout.PAGE_AXIS));

        // <editor-fold defaultstate="collapsed" desc="Find Panel"> 
        // add the search panel
        jPanelFind = new javax.swing.JPanel();
        jPanelFind.setLayout(new FlowLayout(FlowLayout.LEADING));
        jPanelFind.setBounds(0,0,this.getWidth(),100);

        // add the search label to the search panel
        jLabelFind = new javax.swing.JLabel();
        jLabelFind.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabelFind.setText("Search for username:");
        jLabelFind.setPreferredSize(new Dimension(150, 50));
        jLabelFind.setHorizontalAlignment(JLabel.RIGHT);
        jPanelFind.add(jLabelFind);
        
        // add the search text box to the search panel
        jTextFieldFind = new javax.swing.JTextField();
        jTextFieldFind.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jTextFieldFind.setPreferredSize(new Dimension(310, 30));
        jPanelFind.add(jTextFieldFind);
        
        // handle the return key in the username text field
        jTextFieldFind.addActionListener
        (
            new ActionListener() 
            {
                @Override public void actionPerformed(ActionEvent e) 
                {
                    jButtonFindActionPerformed(e);
                }
            }
        );
        
        // add the search button to the search panel
        jButtonFind = new javax.swing.JButton();
        jButtonFind.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jButtonFind.setText("Find");
        jButtonFind.setPreferredSize(new Dimension(60, 30));

        jButtonFind.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jButtonFindActionPerformed(evt);
            }
        });
        jPanelFind.add(jButtonFind);
        
        jPanelBack.add(jPanelFind);
      
        // </editor-fold>

        // add some space between controls    
        jPanelBack.add(Box.createRigidArea(new Dimension(0,5)));
        
        // <editor-fold defaultstate="collapsed" desc="Scores Table"> 
        // add the table to the back panel
        JScrollPane jScrollPane = new javax.swing.JScrollPane();
        jScrollPane.setBorder(BorderFactory.createEmptyBorder(0,6,10,10));

        jTable = new javax.swing.JTable();
        jTable.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Position", "Username", "Total Score", "Classic Score", "Arcade Scrore", "Last Game"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
        
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(60);     //position
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);    //username
        jTable.getColumnModel().getColumn(2).setPreferredWidth(85);    //total score
        jTable.getColumnModel().getColumn(3).setPreferredWidth(100);    //classic score
        jTable.getColumnModel().getColumn(4).setPreferredWidth(100);    //arcade score
        jTable.getColumnModel().getColumn(5).setPreferredWidth(119);    //last game

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        jTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer highlightRenderer = new DefaultTableCellRenderer();
        highlightRenderer.setHorizontalAlignment(JLabel.CENTER);
        highlightRenderer.setBackground(Color.CYAN);

        jTable.getColumnModel().getColumn(2).setCellRenderer(highlightRenderer);
        
        jTable.setBounds(0, 0, this.getWidth(), this.getHeight() - 100);
        
        Color tableColor = new Color(255, 178, 102);
        jTable.setOpaque(true);
        jTable.setFillsViewportHeight(true);
        jTable.setBackground(tableColor);
        jTable.getTableHeader().setBackground(tableColor);
       
        jScrollPane.setViewportView(jTable);
        
        jPanelBack.add(jScrollPane);


        Map<Integer, String> positions = JFrameLogin.UserScores.getPositions();
        
        Object[] keys = positions.keySet().toArray();

        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setRowCount(keys.length);
        
        for(int i=0; i<jTable.getRowCount(); i++)
        {
            jTable.setRowHeight(i, 30);            
        }
        
        for(int i=keys.length-1; i>=0; i--)
        {
            int position = keys.length - i;
            int rowIndex = (keys.length-1) - i;
            String username = positions.get((Integer)keys[i]);
            UserScore score = JFrameLogin.UserScores.GetUserScore(username);
            
            // exclude users who have never scored anything
            if (0 == score.getScoreTotal()) continue;    
            
            jTable.setValueAt(position, rowIndex, 0);
            jTable.setValueAt(username, rowIndex, 1);
            jTable.setValueAt(score.getScoreTotal(), rowIndex, 2);
            jTable.setValueAt(score.getClassicScore(), rowIndex, 3);
            jTable.setValueAt(score.getArcadeScore(), rowIndex, 4);
            jTable.setValueAt(score.getLastGameTimestamp(), rowIndex, 5);
        }
       
        // </editor-fold> 

        // add some space between controls    
        jPanelBack.add(Box.createRigidArea(new Dimension(0,5)));
        
        // <editor-fold defaultstate="collapsed" desc="Close Button">         
        // add some space between controls    
        jPanelBack.add(Box.createRigidArea(new Dimension(0,5)));

        // add the close button to the back panel
        jButtonClose = new javax.swing.JButton();
        jButtonClose.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonClose.setText("Close");
        jButtonClose.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtonClose.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                jButtonCloseActionPerformed(evt);
            }
        });
        //backPanel.add(jButtonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 90, 40));
        jPanelBack.add(jButtonClose);

        // </editor-fold>

        // add some space between controls    
        jPanelBack.add(Box.createRigidArea(new Dimension(0,10)));
        
        // set the title in the tital bar
        this.setTitle("Leader Board");
        
        // center the form on the screen
        this.setLocationRelativeTo(null);

        // make the form non-resizeable
        this.setResizable(false);

        // set the default close button behaviour
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       
    }
    
    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) 
    {                                         
           this.dispose();
    }                                        

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) 
    {   
        jTable.clearSelection();
        
        String username = jTextFieldFind.getText().trim().toLowerCase();
        
        boolean found = false;
        
        for (int rowIndex = 0; rowIndex <jTable.getRowCount(); rowIndex++)
        {
            DefaultTableModel model = (DefaultTableModel)jTable.getModel();
            String cellUsername = model.getValueAt(rowIndex, 1).toString();
            if (cellUsername.toLowerCase().contains(username))
            {
                jTable.setRowSelectionInterval(rowIndex, rowIndex);
                found = true;
                break;
            }
        }
        
        if (!found)
        {
            JOptionPane.showMessageDialog(null, "User Not Found", "Leader Board", JOptionPane.INFORMATION_MESSAGE);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Leader Board");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFrameScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameScoreBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameScoreBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
