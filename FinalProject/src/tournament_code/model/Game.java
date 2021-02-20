package tournament_code.model;

import java.util.ArrayList;

public abstract class Game {
	public static int gameCounter = 0;
	protected int index;
	protected Participant playerOne;
	protected Participant playerTwo;
	protected Participant winner;
	protected ArrayList<Integer> totalResults;
	protected boolean isOver = false;

	public Game(Participant playerOne, Participant playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;

	}

	public boolean isWinning() {

		int scoreOne = totalResults.get(0);
		int scoreTwo = totalResults.get(1);

		if (scoreOne == scoreTwo) {
			return false;
		}

		if (scoreOne < scoreTwo) {
			winner = this.playerTwo;
		} else
			winner = this.playerOne;
		return true;
	}

	public int getScore(int team) {
		return totalResults.get(team);
	}

	public int getIndex() {
		return index;
	}

	public Participant getWinner() throws Exception {
		if (isOver) {
			return winner;
		} else {
			throw new Exception("Game is not over");
		}
	}

	public Participant getPlayerOne() {
		return playerOne;
	}

	public Participant getPlayerTwo() {
		return playerTwo;
	}

	public void addResults(int resultPlayerOne, int resultPlayerTwo) {

		totalResults.set(0, totalResults.get(0) + resultPlayerOne);
		totalResults.set(1, totalResults.get(1) + resultPlayerTwo);

	}

	public void setIndex(int i) {
		this.index = i;
	}

	public abstract void managingGame(int i, int j);

	public abstract int numOfScores();

	public abstract boolean isDone();

	public abstract boolean isTie();

	public abstract int getTieRules();

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Game)) {
			return false;
		} else {
			Game g = (Game) other;
			return (g.getPlayerOne().equals(playerOne) && g.getPlayerTwo().equals(playerTwo));
		}
	}

	@Override
	public String toString() {
		return "Player one: " + playerOne.toString() + " Player two: " + playerTwo.toString() + "\n"
				+ totalResults.toString();
	}
}
