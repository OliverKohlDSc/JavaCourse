package gmbh.conteco;

import static java.lang.System.*;

import java.io.Reader;
import java.io.StringReader;

public class Main{

    public static void main(String[] args) {
        Reader reader = new StringReader("Hello");
        CharacterProcessor.process(reader, ch -> out.println(ch));
    }
}