/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author bodaiji
 */
public abstract class Encoders {
    private static abstract class BaseTextEncoder<T> implements Encoder.Text<T> {
        @Override
        public void destroy() {}
        @Override
        public void init(EndpointConfig config){}
        
        protected String toMessageJson(String name, String message) {
            StringWriter w = new StringWriter();
            JsonGenerator gen = Json.createGenerator(w);
            gen.writeStartObject()
                    .write("name", name)
                    .write("message", message)
                    .writeEnd().close();
            return w.toString();
        }
    }
    
    public static class MessageEncoder extends BaseTextEncoder<Message> {
        @Override
        public String encode(Message message) throws EncodeException {
            return toMessageJson(message.name, message.message);
        }
    }
}
