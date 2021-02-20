package id206059156_id313533341.listeners;

import java.util.ArrayList;

import id206059156_id313533341.model.Participant;

public interface TournamentListenable {
	
	void addedParticipants(Participant participant);
	void startGame(ArrayList<Participant> players, int size);
	void updateCurrentWinner(Participant winnerNow) throws Exception;
	void updatePlayerIsHere(String msg);
	void updateGameIsFull(String msg);


}
