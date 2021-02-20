package id206059156_id313533341.controller;

import java.util.ArrayList;

import id206059156_id313533341.listeners.TournamentListenable;
import id206059156_id313533341.listeners.ViewListenable;
import id206059156_id313533341.model.Game;
import id206059156_id313533341.model.Participant;
import id206059156_id313533341.model.Tournament;
import id206059156_id313533341.view.TournamentViewable;

public class Controller implements TournamentListenable, ViewListenable {
	private Tournament tournament;
	private TournamentViewable view;

	public Controller(Tournament tournament, TournamentViewable view) {
		this.tournament = tournament;
		this.view = view;

		tournament.registerListners(this);
		view.registerListener(this);

	}

	@Override
	public void viewAddedParticipant(String name) {
		tournament.addParticipant(new Participant(name));
	}

	@Override
	public void addedParticipants(Participant participant) {
		view.showAddedParticipant(participant);
	}

	public String viewAskForchosenGameType() throws Exception {
		return view.getGameType();
	}

	@Override
	public boolean viewStratGame(String gameType) {
		try {
			tournament.startTournament(gameType);
			return true;
		} catch (Exception e) {
			view.showMsg(e.toString());
			return false;
		}
	}

	@Override
	public int getNumOfScoresPerGame() {
		return tournament.getNumOfScores();
	}

	@Override
	public void viewSetScores(int index, int scoreOne, int scoreTwo) throws Exception {
		for (int i = 0; i < 2; i++) {
			tournament.getCurrentStage().getAllGames().get(index).managingGame(scoreOne, scoreTwo);
		}
	}

	@Override
	public void viewManageGame(int gameIndex, int scoreOne, int scoreTwo) throws Exception {
		tournament.getCurrentStage().getAllGames().get(gameIndex).managingGame(scoreOne, scoreTwo);
	}

	@Override
	public void viewMangeTie(int activeStage, int index, int scorePlayerOne, int scorePlayerTwo) {
		tournament.getAllStages().get(activeStage).getAllGames().get(index).managingGame(scorePlayerOne,
				scorePlayerTwo);
	}

	@Override
	public boolean viewAskIsTie(int activeStage, int index) throws Exception {
		return tournament.isTie(activeStage, index);
	}

	@Override
	public Participant viewAskForWinner(int gameIndex) throws Exception {
		return tournament.getCurrentStage().getWinner(gameIndex);
	}

	@Override
	public int viewAskForGameIndex(int currentActiveStage) throws Exception {
		return tournament.getAllStages().get(currentActiveStage).getCurrentGameIndex();
	}

	@Override
	public Participant setWinnerNextStage() throws Exception {
		return tournament.getCurrentStage().getWinnerNow();
	}

	@Override
	public void startGame(ArrayList<Participant> players, int size) {
		view.showStartGame(players, size);
	}

	@Override
	public Game viewAskForCurrentGame() throws Exception {
		return tournament.getCurrentStage().getNextGame();
	}

	@Override
	public void viewAskCurrentWinner(int i, int activeStage) throws Exception {
		tournament.getCurrentWinner(i, activeStage);
	}

	@Override
	public void updateCurrentWinner(Participant winnerNow) throws Exception {
		view.showCurrentWinner(winnerNow);
	}

	@Override
	public int getCurrentStageIndex() throws Exception {
		return tournament.getCurrentStage().getCurrentStageIndex();
	}

	@Override
	public int getAllStagesSize() {
		return tournament.getAllStages().size();
	}

	@Override
	public boolean viewAskIsWinner(int activeStage, int activeGame) throws Exception {
		if (tournament.getAllStages().get(activeStage).getAllGames().get(activeGame).getWinner() == null)
			return false;
		return true;
	}

	@Override
	public String viewAskForGameType() {
		return tournament.getAllStages().get(0).getAllGames().get(0).getClass().getSimpleName();
	}

	@Override
	public int getTieRules() {
		return tournament.getTieRules();
	}

	@Override
	public void updateGameIsFull(String msg) {
		view.showMsg(msg);

	}

	@Override
	public void updatePlayerIsHere(String msg) {
		view.showMsg(msg);

	}

}
