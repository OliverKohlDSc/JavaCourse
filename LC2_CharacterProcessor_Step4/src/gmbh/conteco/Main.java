package gmbh.conteco;

import java.io.Reader;
import java.io.StringReader;

public class Main {
    // Ignore whitespace
    public static void main(String[] args) {
        Reader reader = new StringReader("H   ello");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader, ch -> {
            if (!Character.isWhitespace(ch))
                n.value++;
        });
        System.out.println(n);
    }
}