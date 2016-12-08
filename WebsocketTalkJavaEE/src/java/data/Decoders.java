/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author bodaiji
 */
public abstract class Decoders {
    //初期化・破棄の際は何もしません。
    private static abstract class BaseTextDecoder<T extends EntryArea> implements Decoder.Text<T> {
        @Override
        public void init(EndpointConfig config) {}
        @Override
        public void destroy() {}
    }
    
    public static class MessageDecoder extends BaseTextDecoder<Message> {
        @Override
        public Message decode(String s) throws DecodeException {
            try(JsonReader reader = Json.createReader(new StringReader(s))) {
                JsonObject obj = reader.readObject();
                return new Message(
                obj.getString("name"),
                obj.getString("message"));
            }
        }
        @Override
        public boolean willDecode(String s) {
            try(JsonReader reader = Json.createReader(new StringReader(s))) {
                JsonObject obj = reader.readObject();
                return obj.containsKey("message") && obj.containsKey("name");
            } catch(JsonParsingException e) {
                return false;
            }
        }
    }
}
