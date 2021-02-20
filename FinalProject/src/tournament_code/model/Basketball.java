package id206059156_id313533341.model;

import java.util.ArrayList;

public class Basketball extends Game {
	private final int MIN_QUARTERS = 4;
	private final int TIE_RULE = 1;
	private int counter = 0;

	public Basketball(Participant playerOne, Participant playerTwo) {
		super(playerOne, playerTwo);

		this.totalResults = new ArrayList<Integer>(MIN_QUARTERS);
		totalResults.add(0);
		totalResults.add(0);
		isOver = false;

	}

	@Override
	public void managingGame(int resultPlayerOne, int resultPlayerTwo) {

		if (counter < MIN_QUARTERS) {
			addResults(resultPlayerOne, resultPlayerTwo);
		}
		counter++;

		if (counter > MIN_QUARTERS && !isOver) {
			addResults(resultPlayerOne, resultPlayerTwo);

		}
		if (counter >= MIN_QUARTERS)
			isOver = isDone();
	}

	@Override
	public boolean isTie() {

		if ((counter >= MIN_QUARTERS) && !isWinning()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isDone() {
		if (counter < MIN_QUARTERS) {
			return false;
		}
		return isWinning();
	}

	@Override
	public int numOfScores() {
		return MIN_QUARTERS;
	}

	@Override
	public int getTieRules() {

		return TIE_RULE;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Basketball)) {
			return false;
		} else {
			return super.equals(other);
		}
	}
}
