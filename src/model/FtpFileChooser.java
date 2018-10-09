/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.FtpFileChooserPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author KooSum
 */
public class FtpFileChooser extends FTPClient {

    private static FtpFileChooser instance;
    private String server = "10.40.140.172";
    private String acount = "dci"; //dci
    private String password = "70614749"; //70614749
    private static final String ROOT_PATH = "/SFT_code/patch/";
    private DefaultTreeModel treeModel;

    public static FtpFileChooser getInstance() throws Exception {
        if (instance == null) {
            synchronized (FtpFileChooser.class) {
                if (instance == null) {
                    instance = new FtpFileChooser();
                }
            }
        }
        return instance;
    }

    private void checkConnetion() throws Exception {
        int reply = getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnectFTP();
            JOptionPane.showMessageDialog(new JFrame(), "checkConnetion=" + reply + " : " + FTPReply.isPositiveCompletion(reply));
        }
    }

    //初始化 樹狀模型
    public void initTreeModel() throws Exception {
        DefaultMutableTreeNode root = initRootNode();
        treeModel = new DefaultTreeModel(root);
        setTreeModel(treeModel);
    }

    //初始化 樹根
    public DefaultMutableTreeNode initRootNode() throws Exception {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("FTP根目錄");
        buildTreeNode(root);
        return root;
    }

    //建立 樹節點
    public void buildTreeNode(DefaultMutableTreeNode parent) throws Exception {
        if (parent.getChildCount() == 1 && ((DefaultMutableTreeNode) parent.getChildAt(0)).getUserObject().equals("Pendding...")) {
            parent.removeAllChildren();
        }
        if (parent.getChildCount() == 0) {
            String[] directoryNames = listDirectoryNames(getTreePathString(parent));
            for (String directoryName : directoryNames) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(directoryName);
                DefaultMutableTreeNode emptyDir = new DefaultMutableTreeNode("Pendding...");
                child.add(emptyDir);
                parent.add(child);
            }
        }
    }

    //取得節點的完整路徑
    public static String getTreePathString(DefaultMutableTreeNode node) {
        String remoteDir = "";
        try {
            for (int index = 1; index < node.getPath().length; index++) {
                if (!node.getPath()[index].toString().equals("Pendding...")) {
                    remoteDir = remoteDir + node.getPath()[index].toString() + "/";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ROOT_PATH + remoteDir;
    }

    public String[] listDirectoryNames(String path) throws Exception {
        FTPFile[] directories = null;
        String[] dirctoryNames = null;
        try {
            directories = listDirectories(path);
            System.out.println(getReplyString());
            SFTSwitchLogger.doFTPLog(getReplyString());
            checkConnetion();
            dirctoryNames = new String[directories.length];
            for (int i = 0; i < directories.length; i++) {
                dirctoryNames[i] = directories[i].getName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            SFTSwitchLogger.doFTPLog(ex.getMessage());
            JOptionPane.showMessageDialog(new JFrame(), "listDirectoryNames error: " + ex.getMessage());
            throw new Exception("查看資料夾清單失敗:" + ex.getMessage());
        }
        return dirctoryNames;
    }

    public String[] listDirectoryNames() throws Exception {
        FTPFile[] directories = null;
        String[] dirctoryNames = null;
        try {
            directories = listDirectories();
            SFTSwitchLogger.doFTPLog(getReplyString());
            dirctoryNames = new String[directories.length];
            for (int i = 0; i < directories.length; i++) {
                dirctoryNames[i] = directories[i].getName();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("查看資料夾清單失敗:" + ex.getMessage());
        }
        return dirctoryNames;
    }

    public String[] listFileNames() throws Exception {
        String[] fileNames = null;
        try {
            fileNames = listNames();
            SFTSwitchLogger.doFTPLog(getReplyString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("查看檔案清單失敗:" + ex.getMessage());
        }
        return fileNames;
    }

    public String[] listFileNames(String path) throws Exception {
        String[] fileNames = null;
        try {
            fileNames = listNames(path);
            SFTSwitchLogger.doFTPLog(getReplyString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("查看檔案清單失敗:" + ex.getMessage());
        }
        return fileNames;
    }

    public FTPFile[] listFTPFiles(String path) throws Exception {
        FTPFile[] fileNames = null;
        try {
            fileNames = listFiles(path);
            SFTSwitchLogger.doFTPLog(getReplyString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("查看檔案清單失敗:" + ex.getMessage());
        }
        return fileNames;
    }

    private FtpFileChooser() throws Exception {
        setAutodetectUTF8(true);
    }

    public void initInstance() throws Exception {
        try {
            setBufferSize(1024);
            setFileType(BINARY_FILE_TYPE);
            enterLocalPassiveMode(); // 被動模式，要有這個才不會被卡巴擋住
            initTreeModel();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("FtpFileChooser初始化失敗:" + ex.getMessage());
        }
    }

    // 連線至FTP
    public void connetFTP() throws Exception {
        try {
            connect(getServer(), 21);
            SFTSwitchLogger.doFTPLog(getReplyString());
            System.out.print(getReplyString());
        } catch (IOException ex) {
            ex.printStackTrace();
            try {
                disconnectFTP();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("連線失敗:" + ex.getMessage() + " \n" + e.getMessage());
            }
            throw new Exception("連線失敗:" + ex.getMessage());
        }
    }

    // 登入FTP
    public void loginFTP() throws Exception {
        try {
            if (getAcount().isEmpty() || getPassword().isEmpty()) {
                throw new Exception("請輸入帳號密碼");
            } else if (!login(getAcount(), getPassword())) {
                throw new Exception("登入失敗:" + getReplyString());
            }
            SFTSwitchLogger.doFTPLog(getReplyString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    //登出FTP
    public void logoutFTP() throws Exception {
        try {
            if (!logout()) {
                System.out.print(getReplyString());
                throw new Exception("登出失敗");
            } else {
                System.out.print(getReplyString());
            }
            SFTSwitchLogger.doFTPLog(getReplyString());
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                disconnectFTP();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception(ex.getMessage() + " \n" + e.getMessage());
            }
            throw new Exception(ex.getMessage());
        }
    }

//    // 下載
//    public boolean downloadFTPFile(String src, String dist) throws Exception {
//        FileOutputStream fos = null;
//        boolean isSuccess = false;
//        try {
//            fos = new FileOutputStream(dist);
//            isSuccess = retrieveFile(src, fos);
//            System.out.print(getReplyString());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("FTP用戶端出錯！", e);
//        } finally {
//            IOUtils.closeQuietly(fos);
//            return isSuccess;
//        }
//    }
    // 下載
    public boolean downloadFTPFile(String src, String dist) throws Exception {

        boolean isSuccess = false;
        InputStream initialStream = null;
        OutputStream outStream = null;
        try {
            System.out.print("initialStream...");
            FTPFile[] target = listFTPFiles(src);
            long targetSize = target[0].getSize();
            initialStream = retrieveFileStream(src);
            System.out.print(getReplyString());
            System.out.println("get!");
            File targetFile = new File(dist);
            outStream = new FileOutputStream(targetFile);
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            long total = 0;
            while ((bytesRead = initialStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
                total += bytesRead;
                FtpFileChooserPage.progressBar.setValue(Math.round(((float) total / (float) targetSize) * 100));
            }
            isSuccess = true;
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP用戶端出錯！", e);
        } finally {
            IOUtils.closeQuietly(initialStream);
            IOUtils.closeQuietly(outStream);
            return isSuccess;
        }
    }

    //斷開連線
    public void disconnectFTP() throws Exception {
        try {
            disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("關閉FTP連接發生異常！", ex);
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public void setTreeModel(DefaultTreeModel treeModel) {
        this.treeModel = treeModel;
    }
}
