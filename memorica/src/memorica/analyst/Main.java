package memorica.analyst;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


/*
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

}*/
