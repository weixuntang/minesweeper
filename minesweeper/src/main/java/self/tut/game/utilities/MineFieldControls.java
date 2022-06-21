package self.tut.game.utilities;

import java.util.Scanner;

public class MineFieldControls {
	
	Scanner sc = new Scanner(System.in);

	/**
	 * Method to retrieve user input
	 * 
	 * @return user input in string.
	 */
	private String getUserInputInt(String input) {
			System.out.printf(input);

			return sc.nextLine().replaceAll("\\s+","");
	}

	
	/**
	 * Method to set up the field for mine sweeper (visible)
	 */
	public void displayMineField(int[][] fieldVisible, int[][] fieldHidden) {

		System.out.print("\t ");
		for (int i = 0; i < 10; i++) {
			System.out.print(" " + i + "  ");
		}
		System.out.print("\n");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "\t| ");
			for (int j = 0; j < 10; j++) {
				if (fieldVisible[i][j] == 0) {
					System.out.print("?");
				} else if (fieldVisible[i][j] == 50) {
					System.out.print(" ");
				} else {
					System.out.print(fieldVisible[i][j]);
				}
				System.out.print(" | ");
			}
			System.out.print("\n");
		}
		
		buildHidden(fieldHidden);
	}

	
	/**
	 * Method to build hidden
	 * 
	 * @param fieldHidden
	 */
	public void buildHidden(int[][] fieldHidden) {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int cnt = 0;
				if (100 != fieldHidden[i][j]) {
					if (0 != i) {
						if (100 == fieldHidden[i - 1][j])
							cnt++;
						if (0 != j && 100 == fieldHidden[i - 1][j - 1]) {
							cnt++;
						}

					}
					if (9 != i) {
						if (100 == fieldHidden[i + 1][j])
							cnt++;
						if (9 != j && 100 == fieldHidden[i + 1][j + 1]) {
							cnt++;
						}
					}
					if (0 != j) {
						if (100 == fieldHidden[i][j - 1])
							cnt++;
						if (9 != i && 100 == fieldHidden[i + 1][j - 1]) {
							cnt++;
						}
					}
					if (9 != j) {
						if (100 == fieldHidden[i][j + 1])
							cnt++;
						if (0 != i && 100 == fieldHidden[i - 1][j + 1]) {
							cnt++;
						}
					}

					fieldHidden[i][j] = cnt;
				}
			}
		}
	}
	
	

	/**
	 * This method plants bomb randomly place bomb (a value of 100) in random x and y axis.
	 * 
	 * @param fieldHidden
	 * @return
	 */
	public int[][] fieldSetup(int[][] fieldHidden) {

		for (int count = 0; count < 10; count++) {

			int x = ValueUtils.randomNumberGenerator(10);

			int y = ValueUtils.randomNumberGenerator(10);

			fieldHidden[x][y] = 100;
		}
		
		return fieldHidden;
	}
	
	
	/**
	 * 
	 * 
	 * @return
	 */
	public boolean userMove(int[][] fieldVisible, int[][] fieldHidden) {
//		Input Row & Col.
		String[] xAndY = getUserInputInt("Enter X & Y Number : ").split(",");
		int x = Integer.parseInt(xAndY[0]);
		int y = Integer.parseInt(xAndY[1]);

//		Check for invalid inputs
		if (!ValueUtils.checkForInvalidValues(x, y, fieldVisible)) {

			if (100 == fieldHidden[x][y]) {
				displayHidden(fieldHidden);
				System.out.print("Oops! You stepped on a mine!\n============GAME OVER============");
				return true;
			} else if (0 == fieldHidden[x][y]) {
				fixVisible(x, y, fieldVisible, fieldHidden);
			} else {
				fixNeighbours(x, y, fieldVisible, fieldHidden);
			}
			return false;
		} else {
			return true;
		}

	}


	private void displayHidden(int[][] fieldHidden) {
		System.out.print("\t ");
		for (int i = 0; i < 10; i++) {
			System.out.print(" " + i + "  ");
		}
		System.out.print("\n");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "\t| ");
			for (int j = 0; j < 10; j++) {
				if (fieldHidden[i][j] == 0) {
					System.out.print(" ");
				} else if (fieldHidden[i][j] == 100) {
					System.out.print("X");
				} else {
					System.out.print(fieldHidden[i][j]);
				}
				System.out.print(" | ");
			}
			System.out.print("\n");
		}
	}


	private void fixNeighbours(int x, int y, int[][] fieldVisible, int[][] fieldHidden) {

		int randNum = ValueUtils.randomNumberBy4NoSize();
		
		fieldVisible[x][y] = fieldHidden[x][y];

		if (0 == randNum) {
			if (0 != x && 100 != fieldHidden[x - 1][y]) {
					fieldVisible[x - 1][y] = fieldHidden[x - 1][y];
					if (0 == fieldVisible[x - 1][y])
						fieldVisible[x - 1][y] = 50;
			}
			if (0 != y && 100 != fieldHidden[x][y - 1]) {

				fieldVisible[x][y - 1] = fieldHidden[x][y - 1];
				if (0 == fieldVisible[x][y - 1])
					fieldVisible[x][y - 1] = 50;
			}
			
			if (0 != y && 100 != fieldHidden[x - 1][y - 1]) {
				fieldVisible[x - 1][y - 1] = fieldHidden[x - 1][y - 1];
				if (0 == fieldVisible[x - 1][y - 1])
					fieldVisible[x - 1][y - 1] = 50;
			}
		} else if (randNum == 1) {
			if (0 != x && 100 != fieldHidden[x - 1][y]) {
					fieldVisible[x - 1][y] = fieldHidden[x - 1][y];
					if (0 == fieldVisible[x - 1][y])
						fieldVisible[x - 1][y] = 50;
			}
			if (9 != y && 100 != fieldHidden[x][y + 1]) {
					fieldVisible[x][y + 1] = fieldHidden[x][y + 1];
					if (0 == fieldVisible[x][y + 1])
						fieldVisible[x][y + 1] = 50;
			}
			if (9 != y && x > 0 && 100 != fieldHidden[x - 1][y + 1]) {
					fieldVisible[x - 1][y + 1] = fieldHidden[x - 1][y + 1];
					if (fieldVisible[x - 1][y + 1] == 0)
						fieldVisible[x - 1][y + 1] = 50;
			}
		} else if (2 == randNum) {
			if (x != 9 && 100 != fieldHidden[x + 1][y]) {	
					fieldVisible[x + 1][y] = fieldHidden[x + 1][y];
					if (0 == fieldVisible[x + 1][y])
						fieldVisible[x + 1][y] = 50;
			}
			if (y != 9 && 100 != fieldHidden[x][y + 1]) {
					fieldVisible[x][y + 1] = fieldHidden[x][y + 1];
					if (0 == fieldVisible[x][y + 1])
						fieldVisible[x][y + 1] = 50;
			}
			if (x != 9 && y != 9 && 100 != fieldHidden[x + 1][y + 1]) {
					fieldVisible[x + 1][y + 1] = fieldHidden[x + 1][y + 1];
					if (0 == fieldVisible[x + 1][y + 1])
						fieldVisible[x + 1][y + 1] = 50;
			}
		} else {
			if (9 != x && 100 != fieldHidden[x + 1][y]) {
					fieldVisible[x + 1][y] = fieldHidden[x + 1][y];
					if (0 == fieldVisible[x + 1][y])
						fieldVisible[x + 1][y] = 50;
			}
			if (0 != y && 100 != fieldHidden[x][y - 1]) {	
					fieldVisible[x][y - 1] = fieldHidden[x][y - 1];
					if (0 == fieldVisible[x][y - 1])
						fieldVisible[x][y - 1] = 50;
			}
			if (9 != x && 0 != y && 100 != fieldHidden[x + 1][y - 1]) {
				fieldVisible[x + 1][y - 1] = fieldHidden[x + 1][y - 1];
				if (0 == fieldVisible[x + 1][y - 1])
					fieldVisible[x + 1][y - 1] = 50;
			}
		}

	}

	private void fixVisible(int x, int y, int[][] fieldVisible, int[][] fieldHidden) {
		fieldVisible[x][y] = 50;
		if (0 != x) {
			fieldVisible[x - 1][y] = fieldHidden[x - 1][y];
			if (0 == fieldVisible[x - 1][y])
				fieldVisible[x - 1][y] = 50;
			if (0 != y) {
				fieldVisible[x - 1][y - 1] = fieldHidden[x - 1][y - 1];
				if (0 == fieldVisible[x - 1][y - 1])
					fieldVisible[x - 1][y - 1] = 50;
			}
		}
		if (9 != x) {
			fieldVisible[x + 1][y] = fieldHidden[x + 1][y];
			if (0 == fieldVisible[x + 1][y])
				fieldVisible[x + 1][y] = 50;
			if (9 != y) {
				fieldVisible[x + 1][y + 1] = fieldHidden[x + 1][y + 1];
				if (0 == fieldVisible[x + 1][y + 1])
					fieldVisible[x + 1][y + 1] = 50;
			}
		}
		if (0 != y) {
			fieldVisible[x][y - 1] = fieldHidden[x][y - 1];
			if (0 == fieldVisible[x][y - 1])
				fieldVisible[x][y - 1] = 50;
			if (9 != x) {
				fieldVisible[x + 1][y - 1] = fieldHidden[x + 1][y - 1];
				if (0 == fieldVisible[x + 1][y - 1])
					fieldVisible[x + 1][y - 1] = 50;
			}
		}
		if (9 != y) {
			fieldVisible[x][y + 1] = fieldHidden[x][y + 1];
			if (0 == fieldVisible[x][y + 1])
				fieldVisible[x][y + 1] = 50;
			if (0 != x) {
				fieldVisible[x - 1][y + 1] = fieldHidden[x - 1][y + 1];
				if (0 == fieldVisible[x - 1][y + 1])
					fieldVisible[x - 1][y + 1] = 50;
			}
		}

	}

	/**
	 * Method to check if user have evaded all the mine on the play field.
	 * 
	 * @param fieldVisible
	 * @param fieldHidden
	 * @return
	 */
	public boolean chkWin(int[][] fieldVisible, int[][] fieldHidden) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (0 == fieldVisible[i][j] && 100 != fieldHidden[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
}
