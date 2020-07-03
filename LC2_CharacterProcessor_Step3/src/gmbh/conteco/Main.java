package gmbh.conteco;

import static java.lang.System.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        Reader reader = new StringReader("Hello ");
        List<Character> chars = new ArrayList<>();
        CharacterProcessor.process(reader, ch -> chars.add(ch));
        for (Character ch : chars)
            out.println(ch);

        // This will produce an error
        // Variable used in a lambda expressions should be final or effectively final
        // int n = 0;
        // CharacterProcessor.process(reader, ch -> n++); // NOK!!!
        // out.println(n);

        // What can we do to circumvent this behaviour?
        // We've the possibility to use a final object
        // Here comes our Box<T> class into play
        reader = new StringReader("Hello");
        Box<Integer> n = new Box<>(0);
        CharacterProcessor.process(reader, ch -> n.value++);
        out.println(n);
    }
}