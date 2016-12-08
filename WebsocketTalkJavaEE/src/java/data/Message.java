/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 * 入力欄の値
 * @author bodaiji
 */
public class Message implements EntryArea {
    public final String name;
    public final String message;
    
    public Message(String name, String message) {
        this.name    = name;
        this.message = message;
    }
}
