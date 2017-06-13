package examples;

/**
 *
 * 2 Dim Array Assessment
 *
 * Complete the following program.
 *
 */

public class Golf {

	// Each row stores rounds of golf for a particular player

	public static void main(String args[]) {
		Golf masters = new Golf();
		System.out.println("MASTERS1");
		masters.displayColumns();
		System.out.println();
		System.out.println("Player 2's Score is: " + masters.getScore(2));
		System.out.println("Player " + masters.findWinner() + " is the winner");
		System.out.println("The average score for all of the rounds is : " + masters.averageRound());
		System.out.println();
		System.out.println("MASTERS2");

		int[][] scores2 = { { 73, 72, 71, 70 }, { 67, 76, 67, 74 }, { 72, 70, 74, 70 }, { 72, 72, 72, 72 },
				{ 71, 67, 70, 78 }, { 68, 70, 72, 77 } };

		Golf masters2 = new Golf(scores2);
		masters2.displayColumns();
		System.out.println();
		System.out.println("Player 3's Score is: " + masters2.getScore(3));
		System.out.println("Player " + masters2.findWinner() + " is the winner");
		System.out.println("The average score for all of the rounds is : " + masters2.averageRound());

	}

	public int[][] scores;

	/**
	 *
	 * Constructor for objects of class Golf
	 *
	 */

	public Golf() {
		int[][] myScores = { { 67, 73, 79 }, { 71, 70, 68 }, { 78, 76, 75 } };
		scores = myScores;
	}

	/**
	 *
	 * Constructor for objects of class Golf
	 *
	 */

	public Golf(int[][] myScores) {
		scores = myScores;
	}

	/**
	 *
	 * returns the average round of all of the golfers
	 */

	double averageRound() {
		double sum = 0;
		for (int[] x : scores) {
			for (int y : x) {
				sum += y;
			}
		}
		return sum / (scores.length * scores.length);
	}

	/**
	 *
	 * displays the scores in columnar form. Golfer # is the header for each
	 * column
	 *
	 */

	void displayColumns() {
		System.out.println("SCORES");
		System.out.println("0\t1\t2\t3\t4\t5"); // \t will space to the next tab
		for (int r = 0; r < scores.length; r++) {
			for (int c = 0; c < scores[0].length; c++) {
				System.out.print(scores[r][c] + "\t");
			}
			System.out.println();
		}
		System.out.println("\n-\t-\t-\t-\t-\t-");

	}

	/**
	 *
	 * returns the index # of the winner. That is the golfer with the lowest sum
	 * of the
	 *
	 * rounds
	 *
	 */
	int findWinner() {
		int index = 0;
		for (int i = 0; i < scores.length; i++) {
			if (getScore(i) < getScore(index)) {
				index = i;
			}
		}
		return index;
	}

	/**
	 *
	 * returns the total score of a particular player (0 - n)
	 *
	 */

	int getScore(int player) {
		int score = 0;
		for (int i : scores[player]) {
			score += i;
		}
		return score;
	}

}
