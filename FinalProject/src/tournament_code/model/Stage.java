package id206059156_id313533341.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Stage {
	public static int stageIndex = 0;
	private int currentStageIndex;
	private ArrayList<Game> allGames;
	private int counter = 0;

	public Stage(ArrayList<Participant> players, GameType gameType) {
		this.currentStageIndex += stageIndex++;
		int numberOfGames = players.size() / 2;
		allGames = new ArrayList<Game>(numberOfGames);
		for (int i = 0; i < numberOfGames; i++) {

			Game game = null;
			if (gameType == GameType.Soccer) {
				game = new Soccer(players.get(2 * i), players.get((2 * i) + 1));
				game.setIndex(i);
			}

			if (gameType == GameType.Basketball) {
				game = new Basketball(players.get(2 * i), players.get((2 * i) + 1));
				game.setIndex(i);

			}

			if (gameType == GameType.Tennis) {
				game = new Tennis(players.get(2 * i), players.get((2 * i) + 1));
				game.setIndex(i);

			}

			allGames.add(i, game);

		}

	}

	public ArrayList<Game> getAllGames() {
		return allGames;
	}

	public ArrayList<Participant> getAllWinners() throws Exception {
		ArrayList<Participant> allWinners = new ArrayList<Participant>(allGames.size());
		Iterator<Game> iterator = allGames.iterator();
		while (iterator.hasNext()) {
			allWinners.add(iterator.next().getWinner());
		}
		return allWinners;
	}

	public boolean isDone() {
		Iterator<Game> iterator = allGames.iterator();
		while (iterator.hasNext()) {
			if (!iterator.next().isDone())
				return false;
		}

		return true;
	}

	public int getCurrentGameIndex() {
		for (Game game : allGames) {
			if (!game.isOver)
				return game.getIndex();
		}
		return allGames.size();
	}

	public Game getNextGame() {
		for (Game game : allGames) {
			if (!game.isOver)
				return game;
		}
		return allGames.get(0);
	}

	public Participant getWinnerNow() throws Exception {
		Participant currentWinner = null;
		if (counter < allGames.size()) {
			if (allGames.get(counter).isOver) {
				currentWinner = allGames.get(counter).getWinner();
				counter++;
			}
		}
		return currentWinner;

	}

	public Participant getWinner(int gameIndex) throws Exception {
		return allGames.get(gameIndex).getWinner();
	}

	public int getCurrentStageIndex() {
		return currentStageIndex;
	}

	public int getTieRules() {
		return allGames.get(0).getTieRules();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Stage)) {
			return false;
		} else {
			Stage stage = (Stage) other;
			return stage.getAllGames().equals(getAllGames());
		}
	}

	public String toString() {
		StringBuffer buff = new StringBuffer("Stage number: " + currentStageIndex + "\n");
		for (int i = 0; i < allGames.size(); i++) {
			buff.append(allGames.get(i).toString() + "\n");
		}
		return buff.toString();
	}

}
