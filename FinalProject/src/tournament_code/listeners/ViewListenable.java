package tournament_code.listeners;

import tournament_code.model.Game;
import tournament_code.model.Participant;

public interface ViewListenable {
	
	void viewAddedParticipant(String name);
	String viewAskForchosenGameType() throws Exception;
	boolean viewStratGame(String gameType);
	int getNumOfScoresPerGame();
	void viewSetScores(int index,int scoreOne, int scoreTwo) throws Exception;
	void viewManageGame(int gameIndex, int scoreOne, int scoreTwo) throws Exception;
	int viewAskForGameIndex(int cuurentActiveStage) throws Exception;
	Participant setWinnerNextStage() throws Exception;
	Participant viewAskForWinner(int index) throws Exception;
	Game viewAskForCurrentGame() throws Exception;
	void viewAskCurrentWinner(int gameIndex, int activeStage) throws Exception;
	int getCurrentStageIndex() throws Exception;
	int getAllStagesSize();
	boolean viewAskIsWinner(int activeStage, int activeGame) throws Exception;
	String viewAskForGameType();
	boolean viewAskIsTie(int activeStage, int index) throws Exception;
	void viewMangeTie(int activeStage, int index, int scorePlayerOne, int scorePlayerTwo);
	int getTieRules();

}
