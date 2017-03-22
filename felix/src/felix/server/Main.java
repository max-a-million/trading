package felix.server;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import primus.Config;

public class Main {

	private static File LockFile;
	private static Main.CmdsApi Cmds;
	
	private static void applyOptions(String[] args) {
		
	}
	
	private static void applyNewOptions(String[] args) {
		try {
			Cmds.addOptions(args);
		} catch (RemoteException e) {
		}
	}
	
	private static void createCmdManager() {
		try {
			LocateRegistry.createRegistry(Integer.parseInt(Config.AlgoServerCmdsPort));
			Cmds = new Main.AlgoServerCommans();
			Naming.rebind(Config.AlgoServerCmdsUri, Cmds);
		} catch (IOException e) {
		}
	}
	
	private static void bindToCmdManager() {
		System.out.println("additional options");
		try {
			Cmds = (Main.CmdsApi)Naming.lookup(Config.AlgoServerCmdsUri);
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
		}
	}
	
	private static void unbindFromCmdManager() {
		try {
			Naming.unbind(Config.AlgoServerCmdsUri);
			UnicastRemoteObject.unexportObject(Cmds, true);
		} catch (IOException | NotBoundException e) {
		}
	}
	
	private static void prepareLaunch() {
		try {
			LockFile = new File(Config.AlgoServerLockFileName);
			LockFile.createNewFile();
		} catch (IOException e) {
		}
	}
	
	private static boolean isRelaunch() {
		LockFile = new File(Config.AlgoServerLockFileName);
		return LockFile.exists();
	}
	
	private static void finishLaunch() {
		LockFile.delete();
	}
	
	public static void main(String[] args) {
		
		if (isRelaunch()) {
			bindToCmdManager();
			applyNewOptions(args);
			return;
		} 
		
		prepareLaunch();
		createCmdManager();
		applyOptions(args);
		
		TradingBrain tradingBrain = new TradingBrain();
		tradingBrain.prepare();
		tradingBrain.start();
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		while (!input.equals("exit")) {
			input = reader.nextLine();
		}
		reader.close();
		
		tradingBrain.exit();
		tradingBrain.interrupt();
		Thread.yield();
		
		finishLaunch();
		unbindFromCmdManager();
	}

	public static class AlgoServerCommans extends UnicastRemoteObject implements CmdsApi {

		protected AlgoServerCommans() throws RemoteException {
		}
		
		public void addOptions(String[] args) throws RemoteException {
			for (String arg : args) System.out.println(arg);
		}
	}
	
	public static interface CmdsApi extends Remote {
		public void addOptions(String[] args) throws RemoteException;
	}
}
