module gmbh.conteco {
	provides java.lang.System.LoggerFinder
		with gmbh.conteco.ConsoleLogFinder;
	
	exports gmbh.conteco;
}