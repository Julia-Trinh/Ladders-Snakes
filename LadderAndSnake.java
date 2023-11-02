//-----------------------------------------
// Written by: Julia Trinh
//-----------------------------------------

import java.lang.Math;

/**
 * The LadderAndSnake class implements methods to play the game of ladder and snake
 * @author julia
 */
public class LadderAndSnake {

	/**
	 * Attributes
	 */
	private String[][] board = new String[10][10];  // [y coordinate][x coordinate]
	private int numPlayers;
	private String namePlayer1;
	private String namePlayer2;
	private int positionPlayer1x;
	private int positionPlayer1y;
	private int positionPlayer2x;
	private int positionPlayer2y;
	
	private int tempNumP1;  // Temporary number used while checking if player encounters a ladder or a snake
	private int tempNumP2;
	private int positionP1xBefore, positionP1yBefore, positionP1xAfter, positionP1yAfter;
	private int positionP2xBefore, positionP2yBefore, positionP2xAfter, positionP2yAfter;
	
	/**
	 * Default constructor to initialize the board
	 */
	public LadderAndSnake() {
		
		this.numPlayers = 2;
		this.namePlayer1 = "Player 1";
		this.namePlayer1 = "Player 2";
		this.positionPlayer1x = -1;   // -1 to indicate that they are outside the board
		this.positionPlayer1y = 0;
		this.positionPlayer2x = -1;
		this.positionPlayer2y = 0;
		
		// Set all squares on the board to null
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				board[i][j] = null;
			}
		}
		
		System.out.println("Player 1 will go first, then Player 2.");
	}
	
	/**
	 * Takes the number of players and initialize the board
	 * @param numP an integer value
	 */
	public LadderAndSnake(int numP) {
		
		setNumPlayers(numP);
		this.positionPlayer1x = -1;
		this.positionPlayer1y = 0;
		this.positionPlayer2x = -1;
		this.positionPlayer2y = 0;
		
		// Set all squares on the board to null
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				board[i][j] = null;
			}
		}	
	}
	
	/** 
	 * Flips the Dice (random value between 1 and 6)
	 * @return an integer between 1 and 6
	 */
	public int flipDice() {
		return (int) (Math.random()*(6-1+1)+1);
	}
	
	/**
	 * Gets the x position of player 1
	 * @return an integer indicating the x position of player 1
	 */
	public int getPositionPlayer1x() {
		return positionPlayer1x;
	}
	
	/**
	 * Gets the y position of player 1
	 * @return an integer indicating the y position of player 1
	 */
	public int getPositionPlayer1y() {
		return positionPlayer1y;
	}
	
	/**
	 * Gets the x position of player 2
	 * @return an integer indicating the x position of player 2
	 */
	public int getPositionPlayer2x() {
		return positionPlayer2x;
	}
	
	/**
	 * Gets the y position of player 2
	 * @return an integer indicating the y position of player 2
	 */
	public int getPositionPlayer2y() {
		return positionPlayer2y;
	}
	
	/**
	 * Gets the name of player 1
	 * @return a String indicating the name of player 1
	 */
	public String getNameP1() {
		return namePlayer1;
	}
	
	/**
	 * Gets the name of player 2
	 * @return a String indicating the name of player 2
	 */
	public String getNameP2() {
		return namePlayer2;
	}
	
	/**
	 * Gets the board
	 * @return an array of Strings that represents the board
	 */
	public String[][] getBoard() {
		return board;
	}
	
	/**
	 * Decides who starts first by flipping the dice. Player with the highest number becomes player 1.
	 * Flip the dice again if both players get the same value.
	 * @param name1 a String of the first name
	 * @param name2 a String of the second name
	 */
	public void orderOfPlayingTurns(String name1, String name2) {
		
		int diceValueOfName1, diceValueOfName2;
		int numAttempts = 0;
		
		System.out.println("\nNow deciding which player will start playing;");
		do{
			diceValueOfName1 = flipDice();
			diceValueOfName2 = flipDice();
			System.out.println(name1 + " got a dice value of " + diceValueOfName1);
			System.out.println(name2 + " got a dice value of " + diceValueOfName2);
			
			if (diceValueOfName1 == diceValueOfName2)
				System.out.println("A tie was achieved between " + name1 + " and " + name2 + ". Attempting to break the tie.");
			
			numAttempts++;
			
		} while(diceValueOfName1 == diceValueOfName2);
		
		// Setting the players. Player 1 is the player that goes first.
		if (diceValueOfName1 > diceValueOfName2) {
			this.namePlayer1 = name1;
			this.namePlayer2 = name2;
		}
		
		else {
			this.namePlayer1 = name2;
			this.namePlayer2 = name1;
		}
		
		System.out.print("Reached final decision on order of playing: " + this.namePlayer1 + " then " + this.namePlayer2 + ". ");
		System.out.println("It took " + numAttempts + " attempt(s) before a decision could be made.\n");
	}
	
	/**
	 * Sets the number of players. (can only be played by 2 people)
	 * @param numP an integer representing the number of players
	 */
	public void setNumPlayers(int numP) {
		
		if (numP > 2) {
			System.out.println("Initialization was attempted for " + numP + " member of players; however, this is only expected "
					+ "for an extended version of the game. Value will be set to 2.");
			this.numPlayers = 2;
		}
		
		else if (numP < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		}
		
		else if (numP == 2)
			this.numPlayers = numP;
	}
	
	/**
	 * Plays the game and displays the necessary information and actions of the game.
	 */
	public void play() {
		
		int initialPositionP1x = positionPlayer1x;
		int initialPositionP1y = positionPlayer1y;
		int initialPositionP2x = positionPlayer2x;
		int initialPositionP2y = positionPlayer2y;
		
		// For player 1-------------------------------------------------------------------------------
		int diceValueP1 = flipDice();
		
		// if it goes past 100
		if (positionPlayer1y == 9 && positionPlayer1x + diceValueP1 > 9) {
			positionPlayer1x = positionPlayer1x + diceValueP1 - (positionPlayer1x + diceValueP1 - 9)*2;
		}
		else if (positionPlayer1x + diceValueP1 > 9) {
			positionPlayer1x = positionPlayer1x + diceValueP1 - 10;
			positionPlayer1y += 1;;
		}
		else {
			positionPlayer1x += diceValueP1;
		}
		
		// Set the positions of before going up a ladder or down a snake (if present)
		positionP1xBefore = positionPlayer1x;
		positionP1yBefore = positionPlayer1y;
		
		// Check if players encounter the bottom of a ladder or a snake head
		// Return a number that combines the position in y and the position in x
		// Example, if positiony=2 and positionx=4, will return 24
		tempNumP1 = Integer.valueOf(ifLadderOrSnake(positionPlayer1x, positionPlayer1y));
		positionPlayer1x = tempNumP1 % 10;
		positionPlayer1y = tempNumP1 / 10;
		
		// Set the positions of after going up a ladder or down a snake (if present)
		positionP1xAfter = positionPlayer1x;
		positionP1yAfter = positionPlayer1y;
		
		// Display the actions of the round
		displayAction(namePlayer1, diceValueP1, positionP1xBefore, positionP1yBefore, positionP1xAfter, positionP1yAfter);
		
		// Check if player 1 goes to the same cell as player 2, if yes player 2 goes out of the board
		// Change players' position on the board
		if (board[positionPlayer1y][positionPlayer1x] == namePlayer2) {
			if (initialPositionP1x != -1) {
				board[initialPositionP1y][initialPositionP1x] = null;
			}
			positionPlayer2x = -1;
			positionPlayer2y = 0;
			board[positionPlayer1y][positionPlayer1x] = namePlayer1;
			System.out.println(namePlayer1 + " landed on the same cell as " + namePlayer2 + "; " + namePlayer2 + "'s position is now at 0.");
		}
		else {
			if (initialPositionP1x != -1) {
				board[initialPositionP1y][initialPositionP1x] = null;
			}
			board[positionPlayer1y][positionPlayer1x] = namePlayer1;
		}
		
		// For player 2-------------------------------------------------------------------------------
		if (board[9][9] == null) {
			int diceValueP2 = flipDice();
			
			// if it goes past 100
			if (positionPlayer2y == 9 && positionPlayer2x + diceValueP2 > 9) {
				positionPlayer2x = positionPlayer2x + diceValueP2 - (positionPlayer2x + diceValueP2 - 9)*2;
			}
			else if (positionPlayer2x + diceValueP2 > 9) {
				positionPlayer2x = positionPlayer2x + diceValueP2 - 10;
				positionPlayer2y += 1;;
			}
			else {
				positionPlayer2x += diceValueP2;
			}
			
			// Set the positions of before going up a ladder or down a snake (if present)
			positionP2xBefore = positionPlayer2x;
			positionP2yBefore = positionPlayer2y;
			
			// Check if players encounter the bottom of a ladder or a snake head
			tempNumP2 = Integer.valueOf(ifLadderOrSnake(positionPlayer2x, positionPlayer2y));
			positionPlayer2x = tempNumP2 % 10;
			positionPlayer2y = tempNumP2 / 10;
			
			// Set the positions of after going up a ladder or down a snake (if present)
			positionP2xAfter = positionPlayer2x;
			positionP2yAfter = positionPlayer2y;
			
			// Display the actions of the round
			displayAction(namePlayer2, diceValueP2, positionP2xBefore, positionP2yBefore, positionP2xAfter, positionP2yAfter);
			
			// Check if player 2 goes to the same cell as player 1, if yes player 1 goes out of the board
			// Change players' position on the board
			if (board[positionPlayer2y][positionPlayer2x] == namePlayer1) {
				if (initialPositionP2x != -1) {
					board[initialPositionP2y][initialPositionP2x] = null;
				}
				positionPlayer1x = -1;
				positionPlayer1y = 0;
				board[positionPlayer2y][positionPlayer2x] = namePlayer2;
				System.out.println(namePlayer2 + " landed on the same cell as " + namePlayer1 + "; " + namePlayer1 + "'s position is now at 0.");
			}
			else {
				if (initialPositionP2x != -1) {
					board[initialPositionP2y][initialPositionP2x] = null;
				}
				board[positionPlayer2y][positionPlayer2x] = namePlayer2;
			}
		}
		
		System.out.println();
		displayBoard();
	}
	
	/**
	 * Changes player's position if the player encounters a ladder or a snake.
	 * @param positionx an integer representing the x position of the player
	 * @param positiony an integer representing the y position of the player
	 * @return a String containing the x and the y positions
	 */
	public String ifLadderOrSnake(int positionx, int positiony) {
		
		switch(positiony) {
		case 0:
			switch(positionx) {
			case 0:   // if in square 1, goes up to square 38
				positionx = 7;
				positiony = 3;
				break;
				
			case 3:   // if in square 4, goes up to square 14
				positionx = 3;
				positiony = 1;
				break;
				
			case 8:   // if in square 9, goes up to square 31
				positionx = 0;
				positiony = 3;
				break;
			}
			break;
		
		case 1:
			switch(positionx) {
			case 5:   // if in square 16, goes down to square 6
				positionx = 5;
				positiony = 0;
				break;
			}
			break;
			
		case 2:
			switch(positionx) {
			case 0:   // if in square 21, goes up to square 42
				positionx = 1;
				positiony = 4;
				break;
				
			case 7:   // if in square 28, goes up to square 84
				positionx = 3;
				positiony = 8;
				break;
			}
			break;
		
		case 3:
			switch(positionx) {
			case 5:   // if in square 36, goes up to square 44
				positionx = 3;
				positiony = 4;
				break;
			}
			break;
		
		case 4:
			switch(positionx) {
			case 7:   // if in square 48, goes down to square 30
				positionx = 9;
				positiony = 2;
				break;
			}
			break;
			
		case 5:
			switch(positionx) {
			case 0:   // if in square 51, goes up to square 67
				positionx = 6;
				positiony = 6;
				break;
			}
			break;
			
		case 6:
			switch(positionx) {
			case 3:   // if in square 64, goes down to square 60
				positionx = 9;
				positiony = 5;
				break;
			}
			break;
		
		case 7:
			switch(positionx) {
			case 0:   // if in square 71, goes up to square 91
				positionx = 0;
				positiony = 9;
				break;
			
			case 8:   // if in square 79, goes down to square 19
				positionx = 8;
				positiony = 1;
				break;
			
			case 9:   // if in square 80, goes up to square 100
				positionx = 9;
				positiony = 9;
				break;
			}
			break;
		
		case 9:
			switch(positionx) {
			case 2:   // if in square 93, goes down to square 68
				positionx = 7;
				positiony = 6;
				break;
			
			case 4:   // if in square 95, goes down to square 24
				positionx = 3;
				positiony = 2;
				break;
				
			case 6:   // if in square 97, goes down to square 76
				positionx = 5;
				positiony = 7;
				break;
				
			case 7:   // if in square 98, goes down to square 78
				positionx = 7;
				positiony = 7;
			}
			break;
		}
		return (String.valueOf(positiony) + String.valueOf(positionx));
	}
	
	/**
	 * Displays the cell number
	 * @param positionx an integer representing the x position of the player
	 * @param positiony an integer representing the y position of the player
	 */
	public void displayNum(int positionx, int positiony) {
		if (positionx == 9)
			System.out.print(positiony+1 + "0");
		else if (positiony == 0)
			System.out.print(positionx+1);
		else
			System.out.print(positiony + "" + (positionx+1));
	}
	
	/**
	 * Displays the actions of each round.
	 * @param name a String indicating the player's name
	 * @param dice an integer indicating the dice value of the player
	 * @param positionxBefore an integer indicating the x position before going up a ladder or going down a snake
	 * @param positionyBefore an integer indicating the y position before going up a ladder or going down a snake
	 * @param positionxAfter an integer indicating the x position after going up a ladder or going down a snake
	 * @param positionyAfter an integer indicating the y position after going up a ladder or going down a snake
	 */
	public void displayAction(String name, int dice, int positionxBefore, int positionyBefore, int positionxAfter, int positionyAfter) {
		
		System.out.print(name + " got a dice value of " + dice + "; ");
		
		// if player didn't encounter a ladder or a snake
		if ((positionxBefore == positionxAfter) && (positionyBefore == positionyAfter)) {
			System.out.print("now in square ");
			displayNum(positionxBefore, positionyBefore);
			System.out.println();
		}
		// if player went down a snake
		else if (positionyBefore > positionyAfter) {
			System.out.print("gone to square ");
			displayNum(positionxBefore, positionyBefore);
			System.out.print(" then down to square ");
			displayNum(positionxAfter, positionyAfter);
			System.out.println();
		}
		// if player went up a ladder
		else if (positionyBefore < positionyAfter) {
			System.out.print("gone to square ");
			displayNum(positionxBefore, positionyBefore);
			System.out.print(" then up to square ");
			displayNum(positionxAfter, positionyAfter);
			System.out.println();	
		}
	}
	
	/**
	 * Displays the board (cell numbers and players' name in the right cell)
	 */
	public void displayBoard() {
		
		int squareNum = 111;
		boolean increasing = false;
		boolean increasing2 = false;
		
		for (int i=9; i>=0; i--) {
			
			squareNum-=10;
			
			// Displaying the square numbers
			if (increasing == false) {
				
				squareNum-=1;
				for (int j=1; j<=10; j++) {
					System.out.print(squareNum + "\t");
					squareNum--;
				}
				increasing = true;
			}
			
			else {
				
				squareNum+=1;
				for (int j=1; j<=10; j++) {
					System.out.print(squareNum + "\t");
					squareNum++;
				}
				increasing = false;
			}
			
			// Displaying where the players are
			System.out.println();
			if (increasing2 == false) {
				
				for (int j=9; j>=0; j--) {
					if (board[i][j] == null)
						System.out.print("\t");
					else 
						System.out.print(board[i][j] + "\t");
				}
				increasing2 = true;
			}
			
			else {
				
				for (int j=0; j<=9; j++) {
					if (board[i][j] == null)
						System.out.print("\t");
					else 
						System.out.print(board[i][j] + "\t");
				}
				increasing2 = false;
			}
			System.out.println();
		}
	}
	
}
