package algo.analyst;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		AnalystBrain analyst = new AnalystBrain();
		analyst.start();
		
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();
		while (!input.equals("exit")) {
			input = reader.nextLine();
		}
		reader.close();
		
		analyst.interrupt();
		Thread.yield();
	}

}
