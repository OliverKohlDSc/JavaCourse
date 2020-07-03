package gmbh.conteco;

import java.io.Reader;

class CharacterProcessor {
    public static void process(Reader reader, Handler<Character> handler) {
        try(Reader r = reader) {
            int ch = r.read();
            while (ch != -1) {
                handler.handle((char) ch);
                ch = r.read();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}