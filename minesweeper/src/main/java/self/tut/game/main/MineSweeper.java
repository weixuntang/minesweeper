package self.tut.game.main;

import self.tut.game.utilities.MineFieldControls;

public class MineSweeper {
	
	/**
	 * App Logic
	 * 
	 * Objective : To find cell that do not carry bombs.
	 * 
	 * It will contains two set of arrays : -
	 * 
	 * First array to contains number and bomb.
	 * Second array to contains data to display on the screen.
	 * 
	 * Both arrays will be generated randomly by the system.
	 * 
	 * At each turn players will be prompted to input x, y value. If there
	 * is a bomb in the location. The game will end otherwise. It will reveal the
	 * neighbouring cell. 
	 * 
	 * 
	 * Class Objective : -
	 * To contain the rules and method that will dictate the game.
	 */
	MineFieldControls mfc = new MineFieldControls();
	
	private int [][] fieldVisible = new int[10][10];
	private int [][] fieldHidden = new int [10][10];

	public static void main(String[] args) {
		
		MineSweeper ms = new MineSweeper();
		
		ms.startGame();
	}

	
	/**
	 * Method that initialize the game.
	 */
	public void startGame() {

		boolean endGame = false;

//		Greet
		System.out.println("=== Welcome To Mine Sweeper ===");
//		Setup Hidden field
		mfc.fieldSetup(fieldHidden);

//		Run the game until player win or lose
		while (!endGame) {
//			Display Field
			mfc.displayMineField(fieldVisible, fieldHidden);
//			User Move
			endGame = mfc.userMove(fieldVisible, fieldHidden);
//			Check if win.
			if (mfc.chkWin(fieldVisible, fieldHidden)) {
//				display hidden.
				System.out.println("\n================You WON!!!================");
			}
		}
	}

}
	

	


