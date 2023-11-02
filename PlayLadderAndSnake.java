//-----------------------------------------
// Written by: Julia Trinh
//-----------------------------------------

import java.util.Scanner;

/**
 * The PlayLadderAndSnake class is where the user can play the game.
 * @author julia
 *
 */
public class PlayLadderAndSnake {

	/**
	 * Main method
	 * @param args for main method
	 */
	public static void main(String[] args) {
		
		// Identifying my scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Welcome message
		System.out.println("************************************************");
		System.out.println("*                                              *");
		System.out.println("*   Welcome to Julia's Ladder and Snake Game   *");
		System.out.println("*                                              *");
		System.out.println("************************************************");
		
		System.out.print("\nEnter the number of players you want to play with: ");
		int numPlayers = keyboard.nextInt();
		
		// Create an LadderandSnake object
		LadderAndSnake game = new LadderAndSnake(numPlayers);
		
		// Prompt the player to enter the players' names and determine who goes first
		System.out.print("\nEnter the names of the two players, separated by a space (Ex: Jane Joe): ");
		String name1 = keyboard.next();
		String name2 = keyboard.next();
		game.orderOfPlayingTurns(name1, name2);

		String play = keyboard.nextLine();
		
		// Play the game while nobody is at cell 100 yet
		while (game.getBoard()[9][9]==null && play != null) {
			
			game.play();
			if (game.getBoard()[9][9]==null) {
				System.out.print("Game not over; Press enter to flip again");
				play = keyboard.nextLine();
			}
			System.out.println();
		}
		
		System.out.print("Congratulations ");
		if (game.getPositionPlayer1x() == 9 && game.getPositionPlayer1y() == 9)
			System.out.print(game.getNameP1());
		else if (game.getPositionPlayer2x() == 9 && game.getPositionPlayer2y() == 9)
			System.out.print(game.getNameP2());
		System.out.println("; you have won the game!");

	}

}
