package gmbh.conteco;

import static java.lang.System.*;

import java.io.Reader;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        Reader reader = new StringReader("Hello ");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader,
                ch -> ! Character.isWhitespace(ch),
                ch -> n.value++);
        out.println(n);
    }
}
