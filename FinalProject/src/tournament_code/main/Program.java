package tournament_code.main;

import tournament_code.controller.Controller;
import tournament_code.model.Participant;
import tournament_code.model.Tournament;
import tournament_code.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Tournament tournament = new Tournament();

		MainView tournamentView = new MainView(primaryStage);

		Controller controller = new Controller(tournament, tournamentView);

	}

}
