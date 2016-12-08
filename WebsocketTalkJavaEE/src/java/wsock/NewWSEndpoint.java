/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsock;

import data.Decoders;
import data.Encoders;
import data.EntryArea;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author bodaiji
 */
@ServerEndpoint(value="/endpoint",
                decoders = Decoders.MessageDecoder.class,
                encoders = Encoders.MessageEncoder.class)
public class NewWSEndpoint {

    static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    
    /**
     * 「送信」ボタン押下時に各ユーザーにメッセージを送信します。
     * @param message
     * @param sess 
     */
    @OnMessage
    public void onMessage(EntryArea message, Session sess) {
         for(Session other : sess.getOpenSessions()) {
            other.getAsyncRemote().sendObject(message);
        }
    }
    
    /**
     * 接続時にセッションを取得します。
     * @param sess 
     */
    @OnOpen
    public void open(Session sess){
        sessions.add(sess);
    }
    
    /**
     * クローズ時にセッションを破棄します。
     * @param sess 
     */
    @OnClose
    public void close(Session sess){
        sessions.remove(sess);
    }
}