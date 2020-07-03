package com.company;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Main {

    public static void main(String[] args) {
	    ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	    ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

	    String meinName = "Oliver";
	    Integer result = null;

	    try {
	        nashorn.eval("print('" + meinName + "')");
	        result = (Integer)nashorn.eval("10 + 2");
        } catch (ScriptException ex) {
	        ex.printStackTrace();
        }

	    System.out.println(result.toString());
    }
}
