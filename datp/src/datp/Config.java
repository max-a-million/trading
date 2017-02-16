package datp;

public class Config {
	
	public static class Finam {
		public static final String RemoteCommandPath 	= "rmi://localhost:7283/datp-finam-cmd";
		public static final String RemoteAlgoDataPath 	= "rmi://localhost:7284/datp-finam-data-algo";
		public static final String RemoteProxyDataPath 	= "rmi://localhost:7285/datp-finam-data-proxy";
	
		public static final String RemoteCommandPort 	= "7283";
		public static final String RemoteAlgoDataPort 	= "7284";
		public static final String RemoteProxyDataPort 	= "7285";
		
		public static String UserName;
		public static String UserPassword;
	}
}
