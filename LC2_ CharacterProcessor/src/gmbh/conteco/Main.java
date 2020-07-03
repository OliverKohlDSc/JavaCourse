package gmbh.conteco;

import static java.lang.System.*;

import java.io.Reader;
import java.io.StringReader;

public class Main{

    public static void main(String[] args) {
        Reader reader = new StringReader("Hello");
        CharacterProcessor.process(reader, new Handler<Character>() {
            @Override
            public void handle(Character ch) {
                out.println(ch);
            }
        });
    }
}
