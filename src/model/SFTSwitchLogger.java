/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MainPage;

/**
 *
 * @author user
 */
public class SFTSwitchLogger{
    public static void doLog(String msg) {
       MainPage.outputTextArea.append(msg + "\n");
       MainPage.outputTextArea.setCaretPosition(MainPage.outputTextArea.getDocument().getLength());
    }
}
