/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileOutputStream;
import java.io.IOException;
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
    private String acount = "dci";
    private String password = "70614749";
    private static final String ROOT_PATH = "/SFT_code/patch/";
    private DefaultTreeModel treeModel;

    public static FtpFileChooser getInstance() throws Exception {
        if (instance == null) {
            synchronized (FtpFileChooser.class) {
                if (instance == null) {
                    instance = new FtpFileChooser();
                    if (!instance.isConnected() || !instance.isAvailable()) {
                        throw new Exception("FTP似乎斷線了...");
                    }
                }
            }
        } else {
            if (!instance.isConnected() || !instance.isAvailable()) {
                instance = new FtpFileChooser();
            }
            if (!instance.isConnected() || !instance.isAvailable()) {
                throw new Exception("FTP罷工...再試個幾次，連不上就自己手動開環境吧 ㄎㄎ。");
            }
        }
        return instance;
    }

    private void checkConnetion() throws Exception {
        int reply = getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnectFTP();
            System.err.println("FTP server refused connection.");
        }
    }

    //初始化 樹狀模型
    public DefaultTreeModel initTreeModel() throws Exception {
        DefaultMutableTreeNode root = initRootNode();
        treeModel = new DefaultTreeModel(root);
        return treeModel;
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
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(new String(directoryName.getBytes("iso-8859-1"), "utf-8"));
                DefaultMutableTreeNode emptyDir = new DefaultMutableTreeNode("Pendding...");
                child.add(emptyDir);
                parent.add(child);
            }
        }
    }

    //取得節點的完整路徑
    public static String getTreePathString(DefaultMutableTreeNode node) throws Exception {
        String remoteDir = "";
        for (int index = 1; index < node.getPath().length; index++) {
            if (!node.getPath()[index].toString().equals("Pendding...")) {
                remoteDir = remoteDir + node.getPath()[index].toString() + "/";
            }
        }
        return ROOT_PATH + remoteDir;
    }

    public String[] listDirectoryNames(String path) throws Exception {
        FTPFile[] directories = null;
        String[] dirctoryNames = null;
        try {
            directories = listDirectories(path);
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

    public String[] listDirectoryNames() throws Exception {
        FTPFile[] directories = null;
        String[] dirctoryNames = null;
        try {
            directories = listDirectories();
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
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("查看檔案清單失敗:" + ex.getMessage());
        }
        return fileNames;
    }

    private FtpFileChooser() throws Exception {
        try {
            connetFTP();
            loginFTP();
            setBufferSize(1024);
            setFileType(BINARY_FILE_TYPE);
            setTreeModel(initTreeModel());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("FtpFileChooser初始化失敗:" + ex.getMessage());
        }
    }

    // 連線至FTP
    private void connetFTP() throws Exception {
        try {
            connect(getServer());
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
    private void loginFTP() throws Exception {
        try {
            if (!login(getAcount(), getPassword())) {
                System.out.print(getReplyString());
                throw new Exception("登入失敗");
            } else {
                System.out.print(getReplyString());
            }
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

    //登出FTP
    public void logoutFTP() throws Exception {
        try {
            if (!logout()) {
                System.out.print(getReplyString());
                throw new Exception("登出失敗");
            } else {
                System.out.print(getReplyString());
            }
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

    // 下載
    public boolean downloadFTPFile(String src, String dist) throws Exception {
        FileOutputStream fos = null;
        boolean isSuccess = false;
        try {
            fos = new FileOutputStream(dist);
//            System.out.println("從FTP路徑:" + src + "下載至" + dist);
            isSuccess = retrieveFile(src, fos);
            System.out.print(getReplyString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP用戶端出錯！", e);
        } finally {
            IOUtils.closeQuietly(fos);
            return isSuccess;
        }
    }

    //斷開連線
    public void disconnectFTP() throws Exception {
        try {
            disconnect();
            System.out.print(getReplyString());
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
