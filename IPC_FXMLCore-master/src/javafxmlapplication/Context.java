/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import javafx.stage.Stage;
import model.Member;

/**
 *
 * @author Usuario
 */
public class Context {
    private static final Context instance = new Context();
    private Member m;
    
    private Context() {
    }
    
    public static Context getInstance() {
        return instance;
    }
    
    public Member getMember() {
        return m;
    }
    
    public void setMember(Member m) {
        this.m = m;
    }
}

