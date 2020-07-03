package gmbh.conteco;

import java.io.Reader;

public class CharacterProcessor {
    public static void process(Reader reader,
                               Tester<Character> tester, Handler<Character> handler) {
        try(Reader r = reader) {
            int ch = r.read();
            while (ch != -1) {
                if (tester.test((char)ch))
                    handler.handle((char) ch);
                ch = r.read();
            }
        }
        catch (Exception e) { throw new RuntimeException(e);  }
    }
    public static void process(Reader reader, Handler<Character> handler) {
        process(reader, (ch) -> true, handler);
    }
}