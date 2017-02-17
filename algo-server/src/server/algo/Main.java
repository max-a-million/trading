package server.algo;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.Tlhelp32.MODULEENTRY32W;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Kernel32 win32 = Kernel32.INSTANCE;
		
		PROCESSENTRY32 pe32 = new PROCESSENTRY32();
		HANDLE proc = win32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
		win32.Process32First(proc, pe32);
		do {
			String exeFile = Native.toString(pe32.szExeFile);
			//System.out.println(Native.toString(pe32.szExeFile));
			if (exeFile.startsWith("algo-server")) {
				System.out.println(Native.toString(pe32.szExeFile));
				return;
				/*MODULEENTRY32W mo32 = new MODULEENTRY32W();
				HANDLE mod = win32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE, pe32.th32ProcessID);
				win32.Module32FirstW(mod, mo32);
				do {
					System.out.println(Native.toString(mo32.szModule));
				} while (win32.Module32NextW(mod, mo32));*/
			}
			
		} while(win32.Process32Next(proc, pe32));
		
		while (true) {
			System.out.println("waiting... " + pe32.th32ProcessID);
			Thread.sleep(1500);
		}

	}

}
