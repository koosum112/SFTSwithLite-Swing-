/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.Element;
import org.apache.commons.io.FileUtils;
import controller.MainPage;

/**
 *
 * @author user
 */
public class SwitchProcessor {

    public SwitchProcessor() {

    }

    public String doSwitch(CompanyConfig config) {
        String msg = "";
        try {
            SFTSwitchLogger.doLog(">>>> 開始切換...");
            MainPage.progressBar.setValue(0);
            cleanTmpFile();
            MainPage.progressBar.setValue(5);
            unZip(config.getPatchPath());
            MainPage.progressBar.setValue(50);
            delWAR_JAR();
            MainPage.progressBar.setValue(60);
            replaceWAR_JAR();
            MainPage.progressBar.setValue(80);
            cleanTmpWork();
            MainPage.progressBar.setValue(85);
            replaceDatabaseConfig(config);
            replaceMssql(config);
            MainPage.progressBar.setValue(100);
            SFTSwitchLogger.doLog(">>>> 開始切換...OK!");
        } catch (Exception ex) {
            SFTSwitchLogger.doLog(ex.getMessage());
            msg = ex.getMessage();
        }
        return msg;
    }

    private void unZip(String filePath) throws Exception {
        SFTSwitchLogger.doLog("開始解壓縮...");
//        CmdProxy.callCmd("plugins\\winRAR\\UnRAR.exe x " + filePath + " tmpPatch\\");
        CmdProxy.callCmd("plugins\\winRAR\\UnRAR.exe x " + filePath + " tmpPatch\\");
        SFTSwitchLogger.doLog("開始解壓縮...OK!");
    }

    private void delWAR_JAR() throws Exception {
        SFTSwitchLogger.doLog("刪除WAR檔&JAR檔...");
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        File deployFolder = new File(jboss_home + "\\server\\default\\deploy");
        File[] list = deployFolder.listFiles();
        for (File file : list) {
            String fileName = file.getName();

            if (file.isFile()) {
                if (fileName.lastIndexOf(".war") == fileName.length() - 4 || file.getName().indexOf(".jar") == fileName.length() - 4) {
                    file.delete();
                    System.out.println("DEL " + file.getName());
                }
            }
        }
        SFTSwitchLogger.doLog("刪除WAR檔&JAR檔...OK!");
    }

    private void replaceWAR_JAR() throws Exception {
        SFTSwitchLogger.doLog("開始覆蓋WAR檔&JAR檔...");
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        File srcDeployFolder = new File("tmpPatch\\SFT_Patch\\server\\default");
        File targetDeployFolder = new File(jboss_home + "\\server\\default");
        FileUtils.copyDirectory(srcDeployFolder, targetDeployFolder);
        SFTSwitchLogger.doLog("開始覆蓋WAR檔&JAR檔...OK!");
    }

    private void cleanTmpWork() throws Exception {
        SFTSwitchLogger.doLog("開始刪除Tmp & Work資料夾...");
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        File tmp = new File(jboss_home + "\\server\\default\\tmp");
        if (tmp.exists()) {
            FileUtils.deleteDirectory(tmp);
        }
        File work = new File(jboss_home + "\\server\\default\\work");
        if (work.exists()) {
            FileUtils.deleteDirectory(work);
        }
        SFTSwitchLogger.doLog("開始刪除Tmp & Work資料夾...OK!");
    }

    private void cleanTmpFile() throws Exception {
        SFTSwitchLogger.doLog("清除patch暫存檔...");
        File tmp = new File("tmpPatch");
        if (tmp.exists()) {
            FileUtils.deleteDirectory(tmp);
        }
        SFTSwitchLogger.doLog("清除patch暫存檔...OK!");
    }

    private void replaceDatabaseConfig(CompanyConfig config) throws Exception {
        SFTSwitchLogger.doLog("修改database.conf.xml...");
        System.out.println("replaceProperties...Start");
        String sourcePath = "sourceFile\\database.conf.xml";
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        String distPath = jboss_home + "\\..\\SFT\\database.conf.xml";

        XmlManager xmlManager = new XmlManager();
        xmlManager.loadXml(sourcePath);
        Document mssqlDoc = xmlManager.getDocument();
        Element rootEl = mssqlDoc.getRootElement();
        doReplaceConfig(rootEl, config);
        xmlManager.setDocument(mssqlDoc);
        xmlManager.saveXml(distPath);
        SFTSwitchLogger.doLog("修改database.conf.xml...OK!");
    }

    private void replaceMssql(CompanyConfig config) throws Exception {
        SFTSwitchLogger.doLog("修改mssql.xml...");
        String sourcePath = "sourceFile\\mssql-ds.xml";
        String jboss_home = JBossConfigManager.getJbossConfig().getJBOSS_HOME();
        String distPath = jboss_home + "\\server\\default\\deploy\\mssql-ds.xml";

        XmlManager xmlManager = new XmlManager();
        xmlManager.loadXml(sourcePath);
        Document mssqlDoc = xmlManager.getDocument();
        Element rootEl = mssqlDoc.getRootElement();
        doReplaceConfig(rootEl, config);
        xmlManager.setDocument(mssqlDoc);
        xmlManager.saveXml(distPath);
        SFTSwitchLogger.doLog("修改mssql.xml...OK!");
    }

    private void doReplaceConfig(Element element, CompanyConfig config) throws Exception {
        Iterator<Element> it = element.elementIterator();
        if (it.hasNext()) {
            do {
                Element el = it.next();
                doReplaceConfig(el, config);
            } while (it.hasNext());
        } else {
            updateElementText(element, config);
        }
    }

    private void updateElementText(Element element, CompanyConfig config) throws Exception {
        String newText = element.getText();
        newText = newText.replace("{address}", config.getAddress());
        newText = newText.replace("{account}", config.getAccount());
        newText = newText.replace("{password}", config.getPassword());
        newText = newText.replace("{SFTSYS}", config.getSFTSYS());
        element.setText(newText);
    }
}
