package gmbh.conteco;

import java.io.Reader;
import java.util.function.Predicate;

// We are using the functional interface Predicate
public class CharacterProcessor {
    public static void process(Reader reader,
                               Predicate<Character> predicate, Handler<Character> handler) {
        try(Reader r = reader) {
            int ch = r.read();
            while (ch != -1) {
                if (predicate.test((char)ch))
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