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
public class JBossConfigManager {

    private static HashMap<String, JBossConfig> jbossConfigMap = new HashMap<String, JBossConfig>();
    private static JBossConfigManager instance;
    private static JBossConfig jbossConfig;
    private static final String FILE_PATH = "jboss.conf.xml";

    private JBossConfigManager() {
        File configFile = new File(FILE_PATH);
        if (!configFile.exists()) {
            buildDomXml();
        }
    }

    public static void init() {
        instance = JBossConfigManager.getInstance();
    }

    // 多執行緒時，當物件需要被建立時才使用synchronized保證Singleton一定是單一的 ，增加程式效能
    public static JBossConfigManager getInstance() {
        if (instance == null) {
            synchronized (JBossConfigManager.class) {
                if (instance == null) {
                    instance = new JBossConfigManager();
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
        List<Element> jbossList = doc.selectNodes("//config/JBOSS");
        if (jbossList != null) {
            for (Element jbossEl : jbossList) {
                JBossConfig config = new JBossConfig();
                String home = jbossEl.selectSingleNode("JBOSS_HOME").getText().trim();
                String port = jbossEl.selectSingleNode("PORT").getText().trim();
                String company_name = jbossEl.selectSingleNode("COMPANY_NAME").getText().trim();
                config.setJBOSS_HOME(home);
                config.setPORT(port);
                config.setCOMPANY_NAME(company_name);
                jbossConfigMap.put(port, config);
            };
        }
    }

    private Document buildDocument() {
        Document document = DocumentHelper.createDocument();
        Element configEl = document.addElement("config");
        for (JBossConfig value : jbossConfigMap.values()) {
            Element jbossEl = configEl.addElement("JBOSS");
            jbossEl.addElement("JBOSS_HOME").setText(value.getJBOSS_HOME());
            jbossEl.addElement("PORT").setText(value.getPORT());
            jbossEl.addElement("COMPANY_NAME").setText(value.getCOMPANY_NAME());
        };
        return document;
    }

    public Boolean isExsitPort(String port) {
        if (jbossConfigMap.containsKey(port)) {
            return true;
        } else {
            return false;
        }
    }

    public void updateJBossConfig(JBossConfig config, String action) throws Exception {
        String port = config.getPORT();
        if (action.equals("add")) {
            if (!isExsitPort(port)) {
                addJBossConfig(config);
            } else {
                replaceJBossConfig(config);
            }
        } else if (action.equals("delete")) {
            removeJBossConfig(port);
        }

        buildDomXml();
    }

    public void addJBossConfig(JBossConfig config) throws Exception {
        String port = config.getPORT();
        if (!jbossConfigMap.containsKey(port)) {
            jbossConfigMap.put(port, config);
        } else {
            throw new Exception("已存在的Port號");
        }
    }

    public void removeJBossConfig(String port) throws Exception {
        if (jbossConfigMap.containsKey(port)) {
            jbossConfigMap.remove(port);
        } else {
            throw new Exception("不存在的port號");
        }
    }

    public void replaceJBossConfig(JBossConfig config) throws Exception {
        String port = config.getPORT();
        if (jbossConfigMap.containsKey(port)) {
            jbossConfigMap.put(port, config);
        } else {
            throw new Exception("不存在的公司別");
        }
    }

    public static HashMap<String, JBossConfig> getJBossConfigMap() {
        return jbossConfigMap;
    }

    public static JBossConfig getJBossConfigMap(String port) throws Exception {
        if (jbossConfigMap.containsKey(port)) {
            return jbossConfigMap.get(port);
        } else {
            throw new Exception("不存在的公司別");
        }
    }

    public Integer getJBossConfigCount() {
        return jbossConfigMap.size();
    }

    public static JBossConfig getJbossConfig() {
        return jbossConfig;
    }

    public static void setJbossConfig(JBossConfig jbossConfig) {
        JBossConfigManager.jbossConfig = jbossConfig;
    }
}
