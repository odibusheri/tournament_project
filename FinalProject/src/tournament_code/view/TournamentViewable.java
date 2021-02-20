package tournament_code.view;

import java.util.ArrayList;

import tournament_code.listeners.ViewListenable;
import tournament_code.model.Participant;

public interface TournamentViewable {
	void registerListener(ViewListenable l);
	void showAddedParticipant(Participant pariticpant);
	String getGameType() throws Exception;
	void showMsg(String msg);
	void setGameStageIndex(int index);
	void showStartGame(ArrayList<Participant> players, int size);
	void showCurrentWinner(Participant winnerNow) throws Exception;
}
