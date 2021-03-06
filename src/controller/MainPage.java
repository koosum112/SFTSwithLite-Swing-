/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ItemEvent;
import java.io.File;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import model.CmdProxy;
import model.CompanyConfig;
import model.CompanyConfigManager;
import model.JBossConfig;
import model.JBossConfigManager;
import model.SwitchProcessor;

/**
 *
 * @author user
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        initSystem();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        waitDialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jBossListPopupMenu = new javax.swing.JPopupMenu();
        switchItem = new javax.swing.JMenuItem();
        openLog = new javax.swing.JMenuItem();
        mainPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane5 = new javax.swing.JSplitPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        progressBar = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        patchPath = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        browseBtn = new javax.swing.JButton();
        switchBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        companyCombo = new javax.swing.JComboBox();
        ftpBtn = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        settingBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jBossList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        info_companyName = new javax.swing.JLabel();
        info_sftsys = new javax.swing.JLabel();
        info_address = new javax.swing.JLabel();

        waitDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        waitDialog.setTitle("提示");
        waitDialog.setAlwaysOnTop(true);
        waitDialog.setBounds(new java.awt.Rectangle(500, 500, 500, 200));
        waitDialog.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        waitDialog.setLocation(new java.awt.Point(800, 400));
        waitDialog.setResizable(false);

        jLabel4.setFont(new java.awt.Font("微軟正黑體", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("切換中，請稍後.......");
        jLabel4.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout waitDialogLayout = new javax.swing.GroupLayout(waitDialog.getContentPane());
        waitDialog.getContentPane().setLayout(waitDialogLayout);
        waitDialogLayout.setHorizontalGroup(
            waitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        waitDialogLayout.setVerticalGroup(
            waitDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        switchItem.setText("啟動目前的JBOSS");
        switchItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchItemActionPerformed(evt);
            }
        });
        jBossListPopupMenu.add(switchItem);

        openLog.setText("開啟Log路徑");
        openLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLogActionPerformed(evt);
            }
        });
        jBossListPopupMenu.add(openLog);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SFTSwitchLite");
        setIconImage(new ImageIcon("sourceFile\\nintendo-switch.png").getImage());
        setLocation(new java.awt.Point(350, 200));
        setMinimumSize(new java.awt.Dimension(1024, 480));
        setPreferredSize(new java.awt.Dimension(1068, 550));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setPreferredSize(new java.awt.Dimension(1024, 480));

        jSplitPane1.setDividerLocation(240);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(1000, 456));

        jSplitPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jSplitPane2.setDividerLocation(130);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(680, 456));

        jSplitPane5.setDividerLocation(325);
        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane5.setPreferredSize(new java.awt.Dimension(680, 320));

        jTabbedPane2.setPreferredSize(new java.awt.Dimension(680, 295));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(680, 250));

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        outputTextArea.setLineWrap(true);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        jTabbedPane2.addTab("輸出結果", jScrollPane2);

        jSplitPane5.setTopComponent(jTabbedPane2);

        progressBar.setPreferredSize(new java.awt.Dimension(680, 20));
        progressBar.setStringPainted(true);
        jSplitPane5.setRightComponent(progressBar);

        jSplitPane2.setBottomComponent(jSplitPane5);

        jPanel2.setPreferredSize(new java.awt.Dimension(680, 136));

        jLabel1.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("公司別：");

        jLabel2.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Patch路徑：");

        patchPath.setFont(new java.awt.Font("微軟正黑體", 0, 14)); // NOI18N
        patchPath.setMaximumSize(new java.awt.Dimension(320, 2147483647));

        addBtn.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        addBtn.setText("新增");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        browseBtn.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        browseBtn.setText("瀏覽");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        switchBtn.setFont(new java.awt.Font("微軟正黑體", 0, 24)); // NOI18N
        switchBtn.setText("切換");
        switchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                switchBtnActionPerformed(evt);
            }
        });

        delBtn.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        delBtn.setText("刪除");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        companyCombo.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        companyCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                companyComboItemStateChanged(evt);
            }
        });

        ftpBtn.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        ftpBtn.setText("FTP");
        ftpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(companyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delBtn))
                    .addComponent(patchPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(switchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(companyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(delBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patchPath, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(browseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(ftpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(switchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(jPanel2);

        jSplitPane1.setRightComponent(jSplitPane2);

        jSplitPane3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jSplitPane3.setDividerLocation(250);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setName(""); // NOI18N
        jSplitPane3.setPreferredSize(new java.awt.Dimension(320, 456));

        settingBtn.setFont(new java.awt.Font("微軟正黑體", 0, 15)); // NOI18N
        settingBtn.setText("設定");
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        jLabel3.setText("JBoss清單:");

        jBossList.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        jBossList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jBossList.setComponentPopupMenu(jBossListPopupMenu);
        jBossList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBossListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBossListMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jBossList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jSplitPane3.setLeftComponent(jPanel1);

        jLabel5.setText("公司名稱:");

        jLabel6.setFont(new java.awt.Font("微軟正黑體", 0, 18)); // NOI18N
        jLabel6.setText(" 公司別資訊");

        jLabel8.setText("SFTSYS:");

        jLabel9.setText("資料庫IP:");

        info_companyName.setText("N/A");

        info_sftsys.setText("N/A");

        info_address.setText("N/A");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(0, 0, 0))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(info_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(info_companyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(info_sftsys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_companyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_sftsys)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info_address)
                .addContainerGap())
        );

        jSplitPane3.setRightComponent(jPanel4);

        jSplitPane1.setLeftComponent(jSplitPane3);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //切換
    private void switchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchBtnActionPerformed
        swtichFN();
    }//GEN-LAST:event_switchBtnActionPerformed
//預覽patch包路徑
    private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed

        try {
            String msg = isSelectedCompanyCheck();
            if (msg.isEmpty()) {
                File file = null;
                File sourceFile = getDirectoryFile(patchPath.getText()); //介面路徑
                if (sourceFile == null || !sourceFile.exists()) {
                    sourceFile = getDirectoryFile("");  // 使用者預設路徑
                }
                if (sourceFile == null || !sourceFile.exists()) {
                    sourceFile = FileSystemView.getFileSystemView().getHomeDirectory(); // 最後才取桌面路径
                }

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("請選擇patch包");
                fileChooser.setFileFilter(new FileNameExtensionFilter("patch.exe", "exe"));
                fileChooser.setCurrentDirectory(sourceFile);

                if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    if (file != null) {
                        CompanyConfig config = CompanyConfigManager.getCompanyConfig();
                        config.setPatchPath(file.getPath());
                        CompanyConfigManager.getInstance().updateCompanyConfig(config, "add"); //暫存最近選的patch路徑
                        patchPath.setText(file.getPath());
                    }
                }
            } else {
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_browseBtnActionPerformed
    //取得路徑，不存在則取的空字串
    private File getDirectoryFile(String path) {
        File directoryFile = null;
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                directoryFile = file;
            } else if (file.isFile()) {
                directoryFile = new File(path.substring(0, path.lastIndexOf('\\')));
            }
        }
        return directoryFile;
    }

    //新增公司別
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        new AddCompanyPage().setVisible(true);
    }//GEN-LAST:event_addBtnActionPerformed
    //刪除公司別
    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed

        try {
            String msg = isSelectedCompanyCheck();
            if (msg.isEmpty()) {
                CompanyConfig config = CompanyConfigManager.getCompanyConfig();
                CompanyConfigManager.getInstance().updateCompanyConfig(config, "delete");
                loadComboBoxData();
            } else {
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_delBtnActionPerformed

//JBOSS設定
    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingBtnActionPerformed
        new AddJBossPage().setVisible(true);
    }//GEN-LAST:event_settingBtnActionPerformed
    //選擇公司別
    private void companyComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_companyComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                CompanyConfig c_config = CompanyConfigManager.getCompanyConfigMap(String.valueOf(companyCombo.getItemAt(companyCombo.getSelectedIndex())));
                CompanyConfigManager.setCompanyConfig(c_config);
                patchPath.setText(c_config.getPatchPath());
                info_companyName.setText(c_config.getCompanyName());
                info_sftsys.setText(c_config.getSFTSYS());
                info_address.setText(c_config.getAddress());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_companyComboItemStateChanged
    //選擇JBOSS
    private void jBossListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBossListMousePressed
        if (JBossConfigManager.getJbossConfig() == null) {
            try {
                JBossConfig config = JBossConfigManager.getJBossConfigMap(String.valueOf(jBossList.getSelectedValue()));
                JBossConfigManager.setJbossConfig(config);
                loadComboBoxData();
                companyCombo.setSelectedItem(config.getCOMPANY_NAME());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (!JBossConfigManager.getJbossConfig().getPORT().equals(jBossList.getSelectedValue())) {
                if (JOptionPane.showConfirmDialog(new JFrame(), "JBOSS環境是否切換為" + jBossList.getSelectedValue() + "?") == JOptionPane.OK_OPTION) {
                    try {
                        JBossConfig config = JBossConfigManager.getJBossConfigMap(String.valueOf(jBossList.getSelectedValue()));
                        JBossConfigManager.setJbossConfig(config);
                        loadComboBoxData();
                        companyCombo.setSelectedItem(config.getCOMPANY_NAME());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    jBossList.setSelectedValue(JBossConfigManager.getJbossConfig().getPORT(), rootPaneCheckingEnabled);
                }
            }
        }
    }//GEN-LAST:event_jBossListMousePressed

    private void ftpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftpBtnActionPerformed
        try {
            String msg = isSelectedCompanyCheck();
            if (msg.isEmpty()) {
                new FtpFileChooserPage();
            } else {
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ftpBtnActionPerformed

    private void switchItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_switchItemActionPerformed
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doRunbat();
                }
            }).start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_switchItemActionPerformed

    private void jBossListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBossListMouseReleased
        if (evt.isPopupTrigger()) {
            jBossListPopupMenu.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jBossListMouseReleased

    private void openLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLogActionPerformed
        openLog();
    }//GEN-LAST:event_openLogActionPerformed
    //系統初始化
    private void initSystem() {
        try {
            CompanyConfigManager.init();
            JBossConfigManager.init();
            initJBossList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initJBossList() throws Exception {
        loadJBossList();
    }

    public static void loadComboBoxData() throws Exception {
        companyCombo.removeAllItems();
        HashMap<String, CompanyConfig> companyList = CompanyConfigManager.getCompanyConfigMap();
        DefaultListModel dlm = new DefaultListModel();
        for (String key : companyList.keySet()) {
            companyCombo.addItem(key);
        }
    }

    public static void loadJBossList() throws Exception {
        HashMap<String, JBossConfig> jbossList = JBossConfigManager.getJBossConfigMap();
        DefaultListModel dlm = new DefaultListModel();
        for (String port : jbossList.keySet()) {
            dlm.addElement(port);
        }
        jBossList.setModel(dlm);
    }

    private void swtichFN() {
        try {
            String msg = beforeSwitchCheck();
            if (msg.isEmpty()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SwitchProcessor sp = new SwitchProcessor();
                        try {
                            waitDialog.setVisible(true);
                            setBtnEnabled(false);
                            String result = sp.doSwitch(CompanyConfigManager.getCompanyConfig());
                            waitDialog.dispose();
                            setBtnEnabled(true);
                            if (result.equals("")) {
                                JBossConfig config = JBossConfigManager.getJbossConfig();
                                config.setCOMPANY_NAME(String.valueOf(companyCombo.getItemAt(companyCombo.getSelectedIndex())));
                                JBossConfigManager.getInstance().updateJBossConfig(config, "add"); //暫存最近選的公司別

                                JOptionPane.showMessageDialog(new JFrame(), "切換完成", "成功", JOptionPane.INFORMATION_MESSAGE);
                                if (JOptionPane.showConfirmDialog(new JFrame(), "是否要啟動SFT環境?") == JOptionPane.OK_OPTION) {
                                    doRunbat();
                                }
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "切換失敗：" + result, "錯誤", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }).start();
            } else {
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    void runbatFN(java.awt.event.ActionEvent evt) {
        doRunbat();
    }

    private void doRunbat() {
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        String title = companyCombo.getItemAt(companyCombo.getSelectedIndex()) + "_" + jBossList.getSelectedValue();
        try {
            CmdProxy.callCmd("cmd.exe /c start \"" + title + "\" " + jboss_home + "\\bin\\run.bat");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openLog() {
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        try {
            File root = new File(jboss_home);
            if (root.exists()) {
                CmdProxy.callCmd("Explorer.exe " + root.getParent() + "\\SFT\\WebContent\\log");
            } else {
                throw new Exception("查無JBoss路徑。");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setBtnEnabled(boolean b) {
        addBtn.setEnabled(b);
        switchBtn.setEnabled(b);
        browseBtn.setEnabled(b);
        settingBtn.setEnabled(b);
        delBtn.setEnabled(b);
        companyCombo.setEnabled(b);
        jBossList.setEnabled(b);
        ftpBtn.setEnabled(b);
    }

    private String isSelectedCompanyCheck() {
        String msg = "";
        if (jBossList.getSelectedIndex() == -1) {
            msg = "尚未切換 JBoss 的 Port 號!";
        } else if (companyCombo.getSelectedIndex() == -1) {
            msg = "尚未選擇公司別!";
        }
        return msg;
    }

    private String beforeSwitchCheck() {
        String msg = "";
        if (jBossList.getSelectedIndex() == -1) {
            msg = "尚未切換 JBoss 的 Port 號!";
        } else if (companyCombo.getSelectedIndex() == -1) {
            msg = "尚未選擇公司別!";
        } else if (patchPath.getText().isEmpty() || patchPath.getText().equals("")) {
            msg = "尚未選擇 patch 路徑!";
        }
        return msg;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton browseBtn;
    public static javax.swing.JComboBox companyCombo;
    private javax.swing.JButton delBtn;
    private javax.swing.JButton ftpBtn;
    private javax.swing.JLabel info_address;
    private javax.swing.JLabel info_companyName;
    private javax.swing.JLabel info_sftsys;
    public static javax.swing.JList jBossList;
    private javax.swing.JPopupMenu jBossListPopupMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem openLog;
    public static javax.swing.JTextArea outputTextArea;
    public static javax.swing.JTextField patchPath;
    public static javax.swing.JProgressBar progressBar;
    private javax.swing.JButton settingBtn;
    public static javax.swing.JButton switchBtn;
    private javax.swing.JMenuItem switchItem;
    private javax.swing.JDialog waitDialog;
    // End of variables declaration//GEN-END:variables
}
