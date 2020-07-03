package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // Note: Main.java uses uncked or unsafe operations.
    // Note: Recompile with -X1int:uncekced for details ....
    @SafeVarargs
    private void display(List<String>... products) {
        for (List<String> product : products) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
	    Main main = new Main();
	    List<String> list = new ArrayList<String>();
	    list.add("Laptop");
	    list.add("Tablet");
	    main.display(list);

	    // Getting an instance of runtime version
	    Runtime.Version version = Runtime.version();

        System.out.println("Current version is " + version);
        // deprecated: major minor, security, pre
        System.out.println("Build Number " + version.build());
    }
}
