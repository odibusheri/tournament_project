package tournament_code.listeners;

import java.util.ArrayList;

import tournament_code.model.Participant;

public interface TournamentListenable {
	
	void addedParticipants(Participant participant);
	void startGame(ArrayList<Participant> players, int size);
	void updateCurrentWinner(Participant winnerNow) throws Exception;
	void updatePlayerIsHere(String msg);
	void updateGameIsFull(String msg);


}
