package gmbh.conteco;
import java.lang.System.Logger;

public class ConsoleLogFinder extends System.LoggerFinder {

	@Override
	public Logger getLogger(String name, Module module) {
		return new ConsoleLogger();
	}
}
