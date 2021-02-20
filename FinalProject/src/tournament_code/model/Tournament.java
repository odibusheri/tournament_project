package tournament_code.model;

import java.util.ArrayList;

import tournament_code.listeners.TournamentListenable;

public class Tournament {
	private ArrayList<TournamentListenable> allListeners;
	private final int MAX_PLAYERS = 8;
	private ArrayList<Participant> players;
	private GameType gameType;
	private ArrayList<Stage> allStages;

	public Tournament() {
		this.players = new ArrayList<Participant>(MAX_PLAYERS);
		this.allStages = new ArrayList<Stage>();
		allListeners = new ArrayList<TournamentListenable>();

	}

	public void registerListners(TournamentListenable l) {
		allListeners.add(l);
	}

	public int getMaxPlayers() {
		return MAX_PLAYERS;
	}

	public ArrayList<Participant> getParticipants() {
		return players;
	}

	public boolean isHere(Participant player) {
		return players.contains(player);

	}

	public boolean addParticipant(Participant player) {
		if (!(isHere(player)) && players.size() < MAX_PLAYERS) {
			players.add(player);
			fireAddedParticipant(player);
			return true;
		} else if (players.size() == MAX_PLAYERS) {
			fireGameIsFull();
		} else {
			firePlayerIsHere();
		}
		return false;
	}

	private void firePlayerIsHere() {
		for (TournamentListenable tournamentListenable : allListeners) {
			tournamentListenable.updatePlayerIsHere("This player already in the game");
		}

	}

	private void fireGameIsFull() {
		for (TournamentListenable tournamentListenable : allListeners) {
			tournamentListenable
					.updateGameIsFull("Game is full please press 'Start Championship' after you choose a game");
		}

	}

	private void fireAddedParticipant(Participant player) {
		for (TournamentListenable tournamentListenable : allListeners) {
			tournamentListenable.addedParticipants(player);
		}
	}

	public boolean startTournament(String gameType) throws Exception {
		this.gameType = GameType.valueOf(gameType);

		if (players.size() < getMaxPlayers()) {
			throw new Exception("YOU NEED TO ADD MORE PARTICIPANTS");
		} else {
			allStages.add(new Stage(players, this.gameType));
			fireStartGame(players, allStages.size());
			return true;
		}

	}

	private void fireStartGame(ArrayList<Participant> players, int size) {
		for (TournamentListenable tournamentListenable : allListeners) {
			tournamentListenable.startGame(players, size);
		}
	}

	public Stage getCurrentStage() throws Exception {

		Stage currentStage = allStages.get(allStages.size() - 1);

		if (currentStage.isDone()) {
			if (!(currentStage.getAllGames().size() == 1)) {
				ArrayList<Participant> currentPlayers = currentStage.getAllWinners();
				allStages.add(new Stage(currentPlayers, gameType));
				return allStages.get(allStages.size() - 1);
			}
		}

		return currentStage;

	}

	public Stage getLastStage() {
		Stage currentStage = allStages.get(allStages.size() - 1);

		if (currentStage.isDone()) {
			return currentStage;
		}

		if (allStages.size() == 1) {
			return null;
		}

		return allStages.get(allStages.size() - 2);

	}

	public boolean isDone() throws Exception {
		Stage currentStage = getCurrentStage();
		if (currentStage.isDone() && currentStage.getAllGames().size() == 1) {
			return true;
		}
		return false;
	}

	public int getNumOfScores() {
		return allStages.get(0).getAllGames().get(0).numOfScores();
	}

	public Participant getCurrentWinner(int i, int activeStage) throws Exception {
		Participant winner = allStages.get(activeStage).getWinner(i);
		fireCurrentWinner(winner);
		return winner;
	}

	private void fireCurrentWinner(Participant winnerNow) throws Exception {
		for (TournamentListenable tournamentListenable : allListeners) {
			tournamentListenable.updateCurrentWinner(winnerNow);
		}
	}

	public ArrayList<Stage> getAllStages() {
		return allStages;
	}

	public boolean isTie(int activeStage, int index) throws Exception {

		return getAllStages().get(activeStage).getAllGames().get(index).isTie();
	}

	public int getTieRules() {

		return allStages.get(0).getTieRules();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tournament)) {
			return false;
		} else {
			Tournament t = (Tournament) other;
			return (t.getAllStages().equals(allStages));
		}
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer("Game details:\n");
		for (int i = 0; i < allStages.size(); i++) {
			buff.append(allStages.get(i).toString() + "\n");
		}
		return buff.toString();
	}

}
