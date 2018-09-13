/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import net.sf.json.JSONObject;

/**
 *
 * @author user
 */
public class JBossConfig {

    private String JBOSS_HOME = "";
    private String PORT = "";
    private String COMPANY_NAME = "";

    public JBossConfig(String JBOSS_HOME, String PORT, String COMPANY_NAME) {
        this.JBOSS_HOME = JBOSS_HOME;
        this.PORT = PORT;
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public JBossConfig(JSONObject json) {
        this.JBOSS_HOME = json.getString("JBOSS_HOME");
        this.PORT = json.getString("PORT");
        this.COMPANY_NAME = json.getString("COMPANY_NAME");
    }

    public JBossConfig() {

    }

    public String getJBOSS_HOME() {
        return JBOSS_HOME;
    }

    public void setJBOSS_HOME(String JBOSS_HOME) {
        this.JBOSS_HOME = JBOSS_HOME;
    }

    public void setPORT(String PORT) {
        this.PORT = PORT;
    }

    public String getPORT() {
        return PORT;
    }
    
    public void setCOMPANY_NAME(String COMPANY_NAME){
        this.COMPANY_NAME = COMPANY_NAME;
    }
    
    public String getCOMPANY_NAME(){
        return COMPANY_NAME;
    }
}
