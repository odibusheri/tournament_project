package tournament_code.model;

import java.util.ArrayList;
import java.util.Vector;

public class Tennis extends Game {

	private final int MAX_SETS = 5;
	private final int MIN_SETS = 3;
	private int counter = 0;
	private Vector<Integer> resultOne;
	private Vector<Integer> resultTwo;

	public Tennis(Participant playerOne, Participant playerTwo) {
		super(playerOne, playerTwo);

		this.totalResults = new ArrayList<Integer>(MIN_SETS);
		totalResults.add(0);
		totalResults.add(0);
		isOver = false;
		resultOne = new Vector<Integer>(MIN_SETS);
		resultTwo = new Vector<Integer>(MIN_SETS);

	}

	@Override
	public void addResults(int result, int result2) {
		resultOne.add(result);
		resultTwo.add(result2);
		if (result > result2) {
			totalResults.set(0, totalResults.get(0) + 1);
		} else
			totalResults.set(1, totalResults.get(1) + 1);
	}

	@Override
	public void managingGame(int resultPlayerOne, int resultPlayerTwo) {

		if (counter < MIN_SETS) {
			{
				addResults(resultPlayerOne, resultPlayerTwo);
				counter++;

			}

			// checking if there is a winner at first round
			if (counter == MIN_SETS) {
				if (getScore(0) == 3 || getScore(1) == 3) {
					if (getScore(0) == 3) {
						winner = this.playerOne;
					} else
						winner = this.playerTwo;

					isOver = true;
				}
			}
		} else
		// continue for 2 sets
		if (!isOver && counter < MAX_SETS) {

			addResults(resultPlayerOne, resultPlayerTwo);
			counter++;

		}

		if (counter == MAX_SETS) {
			if (getScore(0) > getScore(1)) {
				winner = this.playerOne;
			} else
				winner = this.playerTwo;

			isOver = true;
		}

	}

	@Override
	public boolean isDone() {

		if (counter < MIN_SETS) {
			return false;
		}

		// checking if there is a winner at first round
		if (counter == MIN_SETS) {
			if (getScore(0) == 3 || getScore(1) == 3) {
				isOver = true;
				return true;
			}
		}

		if (counter == MAX_SETS) {
			isOver = true;
			return true;
		}

		return false;

	}

	@Override
	public boolean isTie() {

		if ((counter == MIN_SETS) && !(getScore(0) == 3 || getScore(1) == 3)) {
			return true;
		}

		return false;
	}

	@Override
	public int numOfScores() {
		return MIN_SETS;
	}

	@Override
	public int getTieRules() {

		return MAX_SETS - MIN_SETS;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tennis)) {
			return false;
		} else {
			return super.equals(other);
		}
	}

}
