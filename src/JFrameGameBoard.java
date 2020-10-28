
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
public class JFrameGameBoard extends javax.swing.JFrame 
{
    private static final int ARCADE_TIMEOUT_SECONDS = 10;
    private int _arcadeTimeSeconds = ARCADE_TIMEOUT_SECONDS;
    private Timer _timerGlobal = null;
    
    private Date _startTime = null;
    private Enums.EGameType _gameType = Enums.EGameType.Unknown;
    private int _numberOfQuestions = 0;
    private int _numberOfSeconds = 0;
    
    private int _currentQuestionIndex = 0;
    private int _currentScorePass = 0;
    private int _currentScoreFail = 0;
    private List<String> _userQuestions = null;
    private UserScore _currentUserScore = null;
   
    
    public int getNumberOfQuestions() 
    {
        return _numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) 
    {
        _numberOfQuestions = numberOfQuestions;
        
        _showCurrentQuestionIndex(-1);
    }

    public int getNumberOfSeconds() 
    {
        return _numberOfSeconds;
    }

    public void setNumberOfSeconds(int numberOfSeconds) 
    {
        _numberOfSeconds = numberOfSeconds;
        
        if (_gameType == Enums.EGameType.Arcade)
        {
            _arcadeTimeSeconds = _numberOfSeconds;
        }
        
        _showCurrentQuestionIndex(-1);
    }
    
    public Enums.EGameType getGameType() 
    {
        return _gameType;
    }

    public void setGameType(Enums.EGameType gameType) 
    {
        this._gameType = gameType;
        
        switch(_gameType)
        {
            case Classic:
                jLabelHeader.setText("Classic");
                break;
            case Arcade:
                jLabelHeader.setText("Arcade");
                break;
            default:
                jLabelHeader.setText("Unsupported Game Type");
                break;
        }
          
    }
    
    /**
     * Creates new form JFrameGameBoard
     */
    public JFrameGameBoard()
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

        jButtonCheck.setEnabled(false);
        
        // handle the return key in the answer text field
        jTextAnswer.addActionListener
        (
            new ActionListener() 
            {
                @Override public void actionPerformed(ActionEvent e) 
                {
                    jButtonCheckActionPerformed(e);
                }
            }
        );
        
        _currentUserScore = JFrameLogin.UserScores.GetUserScore(JFrameLogin.CurrentUsername);
        
        _showCurrentTotalScore();
        
    }        

    private void _showCurrentQuestionIndex(int index)
    {
        String label;
        if (_gameType == Enums.EGameType.Classic)
        {
            if (index == -1)
            {
               label = String.format("%1$d/%2$d", 0, _numberOfQuestions);
            }
            else
            {
               label = String.format("%1$d/%2$d", _currentQuestionIndex+1, _numberOfQuestions);
            }
        }
        else
        {
            if (index == -1)
            {
               label = String.format("%1$d", 0);
            }
            else
            {
               label = String.format("%1$d", _currentQuestionIndex+1);
            }
        }
        
        jLabelQuestion.setText(label);
    }

    private void _showCurrentScorePass()
    {
        String label = String.format("%1$d", _currentScorePass);
        jLabelScorePass.setText(label);
    }

    private void _showCurrentScoreFail()
    {
        String label = String.format("%1$d", _currentScoreFail);
        jLabelScoreFail.setText(label);
    }

    private void _showCurrentTotalScore()
    {
        String label = String.format("%1$d", _currentUserScore.getScoreTotal());
        jLabelTotalScore.setText(label);
    }
    
    private void _startTimerGlobal()
    {
        _startTime = new Date();

        _timerGlobal = new Timer();
        _timerGlobal.scheduleAtFixedRate
        (new TimerTask() 
            {
                @Override
                public void run() 
                {
                    SwingUtilities.invokeLater
                    (new Runnable() 
                        {
                            @Override public void run() 
                            {
                                String clockTime;
                                int seconds = 0;
                                long clockSeconds = 0;
                                long clockMinutes = 0; 
                                long clockHours = 0; 
                                
                                if (_gameType == Enums.EGameType.Classic)
                                {
                                    Date now = new Date();
                                    long timespan = now.getTime() - _startTime.getTime();
                                    clockSeconds = TimeUnit.MILLISECONDS.toSeconds(timespan);
                                    clockMinutes = TimeUnit.MILLISECONDS.toMinutes(timespan); 
                                    clockHours = TimeUnit.MILLISECONDS.toHours(timespan);

                                    clockTime = String.format("%1$02d:%2$02d:%3$02d", clockHours, clockMinutes, clockSeconds);
                                    jLabelClock.setText(clockTime);
                                }
                                else
                                {
                                    seconds = _arcadeTimeSeconds--;
                                    Calendar calendar = Calendar.getInstance(); 
                                    calendar.set(2000, 1, 1, 0, 0, (int)seconds);
                                    Date dt = calendar.getTime();

                                    clockSeconds = dt.getSeconds();
                                    clockMinutes = dt.getMinutes(); 
                                    clockHours = dt.getHours();
                                    
                                    clockTime = String.format("%1$02d:%2$02d:%3$02d", clockHours, clockMinutes, clockSeconds);
                                    jLabelClock.setText(clockTime);
                                    
                                    if (seconds == 0)
                                    {
                                        try
                                        {
                                            _showGameOver();
                                        }
                                        catch(Exception ex){}
                                    }
                                }
                                
                            }
                        }
                    );
                }
            }, 
            0, 
            1*1000
        );        
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelHeader = new javax.swing.JLabel();
        jLabelClock = new javax.swing.JLabel();
        jLabelQuestion = new javax.swing.JLabel();
        jLabelTotalScore = new javax.swing.JLabel();
        jLabelScoreFail = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextAnswer = new javax.swing.JTextField();
        jButtonCheck = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextQuestion = new javax.swing.JTextField();
        jButtonClose = new javax.swing.JButton();
        jButtonStart = new javax.swing.JButton();
        jLabelScorePass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game Board");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHeader.setBackground(new java.awt.Color(102, 102, 102));
        jLabelHeader.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabelHeader.setForeground(new java.awt.Color(204, 204, 204));
        jLabelHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHeader.setText("Classic");
        jLabelHeader.setToolTipText("");
        jLabelHeader.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelHeader.setOpaque(true);
        getContentPane().add(jLabelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 46));

        jLabelClock.setBackground(new java.awt.Color(0, 0, 0));
        jLabelClock.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelClock.setForeground(new java.awt.Color(255, 255, 102));
        jLabelClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClock.setText("00:00:00");
        jLabelClock.setToolTipText("Countdown Clock");
        jLabelClock.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelClock.setOpaque(true);
        getContentPane().add(jLabelClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 156, 46));

        jLabelQuestion.setBackground(new java.awt.Color(0, 0, 0));
        jLabelQuestion.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelQuestion.setForeground(new java.awt.Color(51, 153, 255));
        jLabelQuestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQuestion.setText("1/10");
        jLabelQuestion.setToolTipText("Current Question Number");
        jLabelQuestion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelQuestion.setOpaque(true);
        getContentPane().add(jLabelQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 46));

        jLabelTotalScore.setBackground(new java.awt.Color(0, 0, 0));
        jLabelTotalScore.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabelTotalScore.setForeground(new java.awt.Color(51, 255, 153));
        jLabelTotalScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotalScore.setText("0");
        jLabelTotalScore.setToolTipText("Player's Current Score Total");
        jLabelTotalScore.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelTotalScore.setOpaque(true);
        getContentPane().add(jLabelTotalScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 130, 40));

        jLabelScoreFail.setBackground(new java.awt.Color(0, 0, 0));
        jLabelScoreFail.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelScoreFail.setForeground(new java.awt.Color(255, 0, 51));
        jLabelScoreFail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScoreFail.setText("0");
        jLabelScoreFail.setToolTipText("Number of Wrong Answers (Fail)");
        jLabelScoreFail.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelScoreFail.setOpaque(true);
        getContentPane().add(jLabelScoreFail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 110, 46));

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Answer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jTextAnswer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextAnswerActionPerformed(evt);
            }
        });

        jButtonCheck.setBackground(new java.awt.Color(255, 204, 102));
        jButtonCheck.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jButtonCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/check.png"))); // NOI18N
        jButtonCheck.setText("Check");
        jButtonCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(113, 113, 113))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 520, 100));

        jPanel2.setBackground(new java.awt.Color(255, 153, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Question", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18))); // NOI18N

        jTextQuestion.setEditable(false);
        jTextQuestion.setBackground(new java.awt.Color(255, 255, 51));
        jTextQuestion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextQuestionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextQuestion, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 520, 100));

        jButtonClose.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/button_return (1).png"))); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 90, 40));

        jButtonStart.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/button_start (1).png"))); // NOI18N
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 90, 40));

        jLabelScorePass.setBackground(new java.awt.Color(0, 0, 0));
        jLabelScorePass.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelScorePass.setForeground(new java.awt.Color(51, 255, 153));
        jLabelScorePass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScorePass.setText("0");
        jLabelScorePass.setToolTipText("Number of Correct Answers (Pass)");
        jLabelScorePass.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jLabelScorePass.setOpaque(true);
        getContentPane().add(jLabelScorePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 100, 46));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cooltext226899103029279 (1).png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed

        _resetGame();
        
        _loadUserQuestions();
        
        _showNextQuestion();
        
        jButtonStart.setEnabled(false);
        
        jButtonCheck.setEnabled(true);
        
        _startTimerGlobal();
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed

//        this.setVisible(false);
//        this.dispose();
//        
//        System.exit(0);
        JFrameGameSelection gs = new JFrameGameSelection();
        gs.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckActionPerformed
        
        _checkAnswer();
        
        if (_currentQuestionIndex < _userQuestions.size()-1)
        {
            _currentQuestionIndex++;

            _showNextQuestion();
            
        }
        else
        {
            try
            {
                _showGameOver();
            }
            catch(Exception ex){}
        }
        
    }//GEN-LAST:event_jButtonCheckActionPerformed

    private void jTextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextQuestionActionPerformed

    private void jTextAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextAnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAnswerActionPerformed
    
    private void _loadUserQuestions()
    {
        if (_gameType == Enums.EGameType.Classic)
        {
            _userQuestions = JFrameLogin.UserQuestions.GetRandomQuestions(_numberOfQuestions);
        }
        else
        {
            _userQuestions = JFrameLogin.UserQuestions.GetRandomQuestions(0);
        }
        
    }
    
    private void _showNextQuestion()
    {
        String question = _userQuestions.get(_currentQuestionIndex);
        
        jTextQuestion.setText(question);
        jTextAnswer.setText("");
        
        _showCurrentQuestionIndex(_currentQuestionIndex);
    }
    
    private void _checkAnswer()
    {
        String question = _userQuestions.get(_currentQuestionIndex);
        String answer = JFrameLogin.UserQuestions.GetAnswer(question);
        
        String userAnswer = jTextAnswer.getText();
        
        if (answer.equalsIgnoreCase(userAnswer))
        {
            _currentScorePass++;
            _showCurrentScorePass();
            
            if (_gameType == Enums.EGameType.Classic)
            {
                int classiScore = _currentUserScore.getClassicScore();
                _currentUserScore.setClassicScore(classiScore+1);
            }
            else
            {
                int arcadeScore = _currentUserScore.getArcadeScore();
                _currentUserScore.setArcadeScore(arcadeScore+1);
            }
        }
        else
        {
            _currentScoreFail++;
            _showCurrentScoreFail();
            
        }
        
        JFrameLogin.UserScores.Save();
        
        _showCurrentTotalScore();
    }
    
    private void _showGameOver() throws InterruptedException
    {
        _timerGlobal.cancel();

        if (!JFrameLogin.MediaPlayerMuted)
        {    
            try 
            {
                JFrameLogin.StopBackgroundSound();
                JFrameLogin.PlayGameOverSound();

                TimeUnit.SECONDS.sleep(5);

                JFrameLogin.StopGameOverSound();
                JFrameLogin.StartBackgroundSound();
            } 
            catch(Exception ex) 
            {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Login Screen", JOptionPane.WARNING_MESSAGE);
            }
        }
       
        JOptionPane.showMessageDialog(null, "Game Over", jLabelHeader.getText(), JOptionPane.INFORMATION_MESSAGE);
        
        jButtonStart.setEnabled(true);
        
        jButtonCheck.setEnabled(false);
    }
    
    private void _resetGame()
    {
        if (null != _timerGlobal)
        {
            _timerGlobal.cancel();
        }
        
        String clockTime = String.format("%1$02d:%2$02d:%3$02d", 0, 0, 0);
        jLabelClock.setText(clockTime);
        
        _currentQuestionIndex = 0;
        _currentScorePass = 0;
        _currentScoreFail = 0;
                
        _showCurrentQuestionIndex(-1);
        _showCurrentScorePass();
        _showCurrentScoreFail();
        
        
    }
    
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
            java.util.logging.Logger.getLogger(JFrameGameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameGameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameGameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameGameBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameGameBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCheck;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelClock;
    private javax.swing.JLabel jLabelHeader;
    private javax.swing.JLabel jLabelQuestion;
    private javax.swing.JLabel jLabelScoreFail;
    private javax.swing.JLabel jLabelScorePass;
    private javax.swing.JLabel jLabelTotalScore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextAnswer;
    private javax.swing.JTextField jTextQuestion;
    // End of variables declaration//GEN-END:variables
}
