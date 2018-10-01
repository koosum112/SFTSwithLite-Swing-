/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author user
 */
public class CompanyConfigManager {

    private static HashMap<String, CompanyConfig> companyConfigMap = new HashMap<String, CompanyConfig>();
    private static CompanyConfigManager instance;
    private static CompanyConfig companyConfig;
    private static final String FILE_PATH = "company.conf.xml";

    private CompanyConfigManager() {
        File configFile = new File(FILE_PATH);
        if (!configFile.exists()) {
            buildDomXml();
        }
    }

    public static void init() {
        instance = CompanyConfigManager.getInstance();
    }

    // 多執行緒時，當物件需要被建立時才使用synchronized保證Singleton一定是單一的 ，增加程式效能
    public static CompanyConfigManager getInstance() {
        if (instance == null) {
            synchronized (CompanyConfigManager.class) {
                if (instance == null) {
                    instance = new CompanyConfigManager();
                    loadDomXml();
                }
            }
        } else {
            loadDomXml();
        }
        return instance;
    }

    public static void loadDomXml() {
        XmlManager xmlManager = new XmlManager();
        xmlManager.loadXml(FILE_PATH);
        parse(xmlManager.getDocument());
    }

    private void buildDomXml() {
        XmlManager xmlManager = new XmlManager();
        xmlManager.setDocument(buildDocument());
        xmlManager.saveXml(FILE_PATH);
    }

    public static void parse(Document doc) {
        List<Element> companyList = doc.selectNodes("//config/company");
        if (companyList != null) {
            for (Element companyEl : companyList) {
                CompanyConfig config = new CompanyConfig();
                String key = (companyEl.selectSingleNode("companyName") != null) ? companyEl.selectSingleNode("companyName").getText().trim() : "";
                String address = (companyEl.selectSingleNode("address") != null) ? companyEl.selectSingleNode("address").getText().trim() : "";
                String account = (companyEl.selectSingleNode("account") != null) ? companyEl.selectSingleNode("account").getText().trim() : "";
                String password = (companyEl.selectSingleNode("password") != null) ? companyEl.selectSingleNode("password").getText().trim() : "";
                String SFTSYS = (companyEl.selectSingleNode("SFTSYS") != null) ? companyEl.selectSingleNode("SFTSYS").getText().trim() : "";
                String patchPath = (companyEl.selectSingleNode("patchPath") != null) ? companyEl.selectSingleNode("patchPath").getText().trim() : "";
                String ftpPath = (companyEl.selectSingleNode("ftpPath") != null) ? companyEl.selectSingleNode("ftpPath").getText().trim() : "";

                config.setCompanyName(key);
                config.setAddress(address);
                config.setAccount(account);
                config.setPassword(password);
                config.setSFTSYS(SFTSYS);
                config.setPatchPath(patchPath);
                config.setFtpPath(ftpPath);
                companyConfigMap.put(key, config);
            }
        }
    }

    private Document buildDocument() {
        Document document = DocumentHelper.createDocument();
        Element configEl = document.addElement("config");
        for (CompanyConfig value : companyConfigMap.values()) {
            Element companyEl = configEl.addElement("company");
            companyEl.addElement("companyName").setText(value.getCompanyName());
            companyEl.addElement("address").setText(value.getAddress());
            companyEl.addElement("account").setText(value.getAccount());
            companyEl.addElement("password").setText(value.getPassword());
            companyEl.addElement("SFTSYS").setText(value.getSFTSYS());
            companyEl.addElement("patchPath").setText(value.getPatchPath());
            companyEl.addElement("ftpPath").setText(value.getFtpPath());
        };
        return document;
    }

    public Boolean isExsitCompany(String companyName) {
        if (companyConfigMap.containsKey(companyName)) {
            return true;
        } else {
            return false;
        }
    }

    public void updateCompanyConfig(CompanyConfig config, String action) throws Exception {
        String companyName = config.getCompanyName();
        if (action.equals("add")) {
            if (!isExsitCompany(companyName)) {
                addCompanyConfig(config);
            } else {
                replaceCompanyConfig(config);
            }
        } else if (action.equals("delete")) {
            removeCompanyConfig(companyName);
        }

        buildDomXml();
    }

    public void addCompanyConfig(CompanyConfig config) throws Exception {
        String companyName = config.getCompanyName();
        if (!companyConfigMap.containsKey(companyName)) {
            companyConfigMap.put(companyName, config);
        } else {
            throw new Exception("已存在的公司別");
        }
    }

    public void removeCompanyConfig(String companyName) throws Exception {
        if (companyConfigMap.containsKey(companyName)) {
            companyConfigMap.remove(companyName);
        } else {
            throw new Exception("不存在的公司別");
        }
    }

    public void replaceCompanyConfig(CompanyConfig config) throws Exception {
        String companyName = config.getCompanyName();
        if (companyConfigMap.containsKey(companyName)) {
            companyConfigMap.put(companyName, config);
        } else {
            throw new Exception("不存在的公司別");
        }
    }

    public static HashMap<String, CompanyConfig> getCompanyConfigMap() {
        return companyConfigMap;
    }

    public static CompanyConfig getCompanyConfigMap(String companyName) throws Exception {
        if (companyConfigMap.containsKey(companyName)) {
            return companyConfigMap.get(companyName);
        } else {
            throw new Exception("不存在的公司別");
        }
    }

    public static CompanyConfig getCompanyConfig() {
        return companyConfig;
    }

    public static void setCompanyConfig(CompanyConfig companyConfig) {
        CompanyConfigManager.companyConfig = companyConfig;
    }

    public Integer getCompanyConfigCount() {
        return companyConfigMap.size();
    }
}
