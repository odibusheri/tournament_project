package id206059156_id313533341.view;

import java.util.ArrayList;

import id206059156_id313533341.listeners.ViewListenable;
import id206059156_id313533341.model.Participant;

public interface TournamentViewable {
	void registerListener(ViewListenable l);
	void showAddedParticipant(Participant pariticpant);
	String getGameType() throws Exception;
	void showMsg(String msg);
	void setGameStageIndex(int index);
	void showStartGame(ArrayList<Participant> players, int size);
	void showCurrentWinner(Participant winnerNow) throws Exception;
}
