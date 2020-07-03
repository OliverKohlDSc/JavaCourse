package gmbh.conteco;

import java.lang.System.Logger.Level;

public class Main {
	private static System.Logger LOGGER = System.getLogger("JavaPlatformLoggingApi");
	
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "It just works!");
		LOGGER.log(Level.ERROR, "Ups ...");
		
		// ch.qos.logback
		// <configuration>
		//    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
		//       <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
		//			<pattern>
		//				%d{dd.MM.yyyy HH:mm:ss} [%thread] %-5level %logger{36} -- %msg%n
		//			</pattern>
		//       </encoder>
		//	  </appender>
		//    <root>
		//       <appender-ref ref="STDOUT />
		//    </root>
		// </configuration>
		// -Dlogback.configurationFile=mods/logback.xml
		
		
		// SL4J
	}
}
