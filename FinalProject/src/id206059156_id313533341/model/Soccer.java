package id206059156_id313533341.model;

import java.util.ArrayList;

public class Soccer extends Game {
	private final int MIN_HALVES = 2;
	private final int TIE_RULE = 1;
	private int counter = 0;

	public Soccer(Participant playerOne, Participant playerTwo) {

		super(playerOne, playerTwo);

		this.totalResults = new ArrayList<Integer>(MIN_HALVES);
		totalResults.add(0);
		totalResults.add(0);
		isOver = false;
	}

	@Override
	public void managingGame(int resultPlayerOne, int resultPlayerTwo) {
		if (counter < MIN_HALVES) {
			addResults(resultPlayerOne, resultPlayerTwo);
		}
		counter++;

		if (!isOver && counter > MIN_HALVES) {
			addResults(resultPlayerOne, resultPlayerTwo);

		}
		if (counter >= MIN_HALVES)
			isOver = isDone();

	}

	public boolean getIsOver() {
		return isOver;
	}

	@Override
	public boolean isTie() {

		if ((counter >= MIN_HALVES) && !isWinning()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isDone() {

		if (counter < MIN_HALVES) {
			return false;
		}
		return isWinning();
	}

	@Override
	public int numOfScores() {
		return MIN_HALVES;
	}

	@Override
	public int getTieRules() {

		return TIE_RULE;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Soccer)) {
			return false;
		} else {
			return super.equals(other);
		}
	}

}
