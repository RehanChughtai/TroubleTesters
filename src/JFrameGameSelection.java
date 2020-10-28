
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class JFrameGameSelection extends javax.swing.JFrame {

    /**
     * Creates new form JFrameGameSelection
     */
    public JFrameGameSelection() 
    {
        initComponents();
        
        // set the background image on the form
        try
        {
            Image img = ImageIO.read(new File("Background.jpg"));
            BackgroundPanel backPanel = new BackgroundPanel(img, BackgroundPanel.SCALED, 1.0f, 0.5f);
            getContentPane().add(backPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, this.getWidth(), this.getHeight()));
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Login Screen", JOptionPane.WARNING_MESSAGE);
        }
        
        // center the form on the screen
        this.setLocationRelativeTo(null);

        // make the form non-resizeable
        setResizable(false);
        
        // set cmdLogin as the default button
        JRootPane rootPane = SwingUtilities.getRootPane(cmdPlay); 
        rootPane.setDefaultButton(cmdPlay); 
        
        {
            // center-align the spinner    
            JComponent editor = jSpinnerNumberOfQuestions.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) 
            {
                JSpinner.DefaultEditor spinnerEditor  = (JSpinner.DefaultEditor)editor;
                spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
            }
        }

        {
            JComponent editor = jSpinnerNumberOfSeconds.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) 
            {
                JSpinner.DefaultEditor spinnerEditor  = (JSpinner.DefaultEditor)editor;
                spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
            }
        }
        
        ButtonGroup groupGameType = new ButtonGroup();
        groupGameType.add(jRadioButtonClassic);
        groupGameType.add(jRadioButtonArcade);
                
        jSpinnerNumberOfQuestions.setValue(10);
        jSpinnerNumberOfSeconds.setValue(120);
        
        UserScore score = JFrameLogin.UserScores.GetUserScore(JFrameLogin.CurrentUsername);
        
        jLabelUsername.setText(JFrameLogin.CurrentUsername);
        jLabelTotalScore.setText(Integer.toString(score.getScoreTotal()));
        jLabelClassicScore.setText(Integer.toString(score.getClassicScore()));
        jLabelArcadeScore.setText(Integer.toString(score.getArcadeScore()));
        jLabelLastGame.setText(score.getLastGameTimestamp());
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
        jLabelUsername = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelClassicScore = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelArcadeScore = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelTotalScore = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelLastGame = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cmdPlay = new javax.swing.JButton();
        jRadioButtonClassic = new javax.swing.JRadioButton();
        jRadioButtonArcade = new javax.swing.JRadioButton();
        jPanelArcade = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinnerNumberOfSeconds = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanelClassic = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSpinnerNumberOfQuestions = new javax.swing.JSpinner();
        jButtonMainMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game Selection");
        setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsername.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelUsername.setText("mike");
        jPanel1.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 150, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226900986025763.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 20, 90, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226901052338148.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 170, -1));

        jLabelClassicScore.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelClassicScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelClassicScore.setText("0");
        jPanel1.add(jLabelClassicScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 90, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226901092190333.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 170, 40));

        jLabelArcadeScore.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelArcadeScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelArcadeScore.setText("0");
        jPanel1.add(jLabelArcadeScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 90, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226901133037808.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 160, 40));

        jLabelTotalScore.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabelTotalScore.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalScore.setText("0");
        jPanel1.add(jLabelTotalScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 90, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226901159205060.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 130, 40));

        jLabelLastGame.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelLastGame.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelLastGame.setText("2017-01-12 22:00");
        jPanel1.add(jLabelLastGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 130, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Background.jpg"))); // NOI18N
        jLabel12.setText("jLabel12");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 320));

        cmdPlay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        cmdPlay.setForeground(new java.awt.Color(0, 102, 255));
        cmdPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/button_play-game (1).png"))); // NOI18N
        cmdPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPlayActionPerformed(evt);
            }
        });
        getContentPane().add(cmdPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 160, 40));

        jRadioButtonClassic.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButtonClassic.setSelected(true);
        jRadioButtonClassic.setText("Play Classic Game");
        jRadioButtonClassic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonClassicActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonClassic, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 170, -1));

        jRadioButtonArcade.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButtonArcade.setText("Play Arcade Game");
        jRadioButtonArcade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonArcadeActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonArcade, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 180, -1));

        jPanelArcade.setBackground(new java.awt.Color(255, 204, 255));
        jPanelArcade.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("How many questions do you want to play?");
        jPanelArcade.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 240, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226902829525933.png"))); // NOI18N
        jPanelArcade.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 60, 20));

        jSpinnerNumberOfSeconds.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jSpinnerNumberOfSeconds.setModel(new javax.swing.SpinnerNumberModel(120, 10, 3600, 1));
        jSpinnerNumberOfSeconds.setEnabled(false);
        jSpinnerNumberOfSeconds.setValue(10);
        jPanelArcade.add(jSpinnerNumberOfSeconds, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226902612223867 (1).png"))); // NOI18N
        jPanelArcade.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        getContentPane().add(jPanelArcade, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 270, 90));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226899405870884.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 290, 40));

        jPanelClassic.setBackground(new java.awt.Color(255, 204, 255));
        jPanelClassic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("How many questions do you want to play?");
        jPanelClassic.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 240, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226902435199494.png"))); // NOI18N
        jPanelClassic.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 30));

        jSpinnerNumberOfQuestions.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jSpinnerNumberOfQuestions.setModel(new javax.swing.SpinnerNumberModel(10, 1, 100, 1));
        jSpinnerNumberOfQuestions.setValue(10);
        jPanelClassic.add(jSpinnerNumberOfQuestions, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 40));

        getContentPane().add(jPanelClassic, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 270, 90));

        jButtonMainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/button_return-to-main-menu.png"))); // NOI18N
        jButtonMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMainMenuActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMainMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 260, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPlayActionPerformed

        this.setVisible(false);

        JFrameGameBoard frm = new JFrameGameBoard(); //the values of the last game
        
        boolean classicSelected = jRadioButtonClassic.isSelected();
        
        if (classicSelected)
        {
            frm.setGameType(Enums.EGameType.Classic);
            int numberOfQuestions = (Integer)jSpinnerNumberOfQuestions.getValue();
            frm.setNumberOfQuestions(numberOfQuestions);
        }
        else
        {
            frm.setGameType(Enums.EGameType.Arcade);
            int numberOfSeconds = (Integer)jSpinnerNumberOfSeconds.getValue();
            frm.setNumberOfSeconds(numberOfSeconds);
        }
        
        frm.setVisible(true);
            
        this.dispose();
    }//GEN-LAST:event_cmdPlayActionPerformed

    private void jRadioButtonClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonClassicActionPerformed

            boolean classicSelected = jRadioButtonClassic.isSelected();
            jSpinnerNumberOfQuestions.setEnabled(classicSelected);
            jSpinnerNumberOfSeconds.setEnabled(!classicSelected);
    }//GEN-LAST:event_jRadioButtonClassicActionPerformed

    private void jRadioButtonArcadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonArcadeActionPerformed

            boolean classicSelected = jRadioButtonClassic.isSelected();
            jSpinnerNumberOfQuestions.setEnabled(classicSelected);
            jSpinnerNumberOfSeconds.setEnabled(!classicSelected);
    }//GEN-LAST:event_jRadioButtonArcadeActionPerformed

    private void jButtonMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMainMenuActionPerformed
        // TODO add your handling code here:
        JFrameMainMenu mm = new JFrameMainMenu();
        mm.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonMainMenuActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameGameSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGameSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGameSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGameSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new JFrameGameSelection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdPlay;
    private javax.swing.JButton jButtonMainMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelArcadeScore;
    private javax.swing.JLabel jLabelClassicScore;
    private javax.swing.JLabel jLabelLastGame;
    private javax.swing.JLabel jLabelTotalScore;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelArcade;
    private javax.swing.JPanel jPanelClassic;
    private javax.swing.JRadioButton jRadioButtonArcade;
    private javax.swing.JRadioButton jRadioButtonClassic;
    private javax.swing.JSpinner jSpinnerNumberOfQuestions;
    private javax.swing.JSpinner jSpinnerNumberOfSeconds;
    // End of variables declaration//GEN-END:variables
}