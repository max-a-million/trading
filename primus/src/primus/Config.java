package primus;

public class Config {
	
	public static String AlgoServerLockFileName = "algo-server.lock";
	public static String AlgoServerCmdsUri = "rmi://localhost:7200/algo-server-cmds";
	public static String AlgoServerCmdsPort = "7200";
	
	public static class FinamTC {
		public static final String RemoteCommandPath 	= "rmi://localhost:7283/datp-finam-tc-cmd";
		public static final String RemoteAlgoDataPath 	= "rmi://localhost:7284/datp-finam-tc-data-algo";
		public static final String RemoteProxyDataPath 	= "rmi://localhost:7285/datp-finam-tc-data-proxy";
	
		public static final String RemoteCommandPort 	= "7283";
		public static final String RemoteAlgoDataPort 	= "7284";
		public static final String RemoteProxyDataPort 	= "7285";
		
		public static String UserName;
		public static String UserPassword;
	}
	
	public static class FinamHistory {
		public static final String RemoteCommandPath 	= "rmi://localhost:7293/datp-finam-his-cmd";
		public static final String RemoteAlgoDataPath 	= "rmi://localhost:7294/datp-finam-his-data-algo";
		public static final String RemoteProxyDataPath 	= "rmi://localhost:7295/datp-finam-his-data-proxy";
	
		public static final String RemoteCommandPort 	= "7293";
		public static final String RemoteAlgoDataPort 	= "7294";
		public static final String RemoteProxyDataPort 	= "7295";
	}
}
