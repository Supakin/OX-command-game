import java.util.InputMismatchException;
import java.util.Scanner;

public class OXgame {
	static Scanner kb = new Scanner(System.in);
	static char[] table = { '-', '-', '-', '-', '-', '-', '-', '-', '-' };
	static char player = 'o';
	static int round = 0;
	static int row;
	static int col;
	static int index = 0;
	
	
	
	static void printTable() {
		for (int i = 0; i < table.length; i++) {
			System.out.print(table[i] + " ");
			if (i == 2 || i == 5 || i == 8) {
				System.out.println();
			}
		}
	}
	
	static void printWelcome() {
		System.out.println("Welcome to OX Game <3");
	}
	
	
	static void printTurn() {
		if (player == 'o')
			player = 'x';
		else
			player = 'o';
		
		round++;
		System.out.println("Turn "+ player);
	}
	
	static void inputPosition() {
		while (true) {
			try {
				System.out.println("Please input Row, Col : ");
				row = kb.nextInt();
				col = kb.nextInt();
				
				if ((row < 1 || row > 3) || (col < 1 || col > 3)) {
					System.out.println("Min value of Row and Col is 1 \n" + "Max value of Row and Col is 3");
					continue;
				}
				
				if (checkBlank(getRealPosition())) {
					System.out.println("Please change position!!");
					continue;
				}
					
				
				table[getRealPosition()] = player;
				break;
			} catch (InputMismatchException e) {
				System.out.println("Position Incorrect");
				if (kb.hasNext()) {
					kb.nextLine();
				}
			}
		}
	}
	
	static boolean checkBlank(int index) {
		if (table[index] == '-')
			return false;
		return true;
	}
	
	static int getRealPosition() {
		return (row - 1) * 3 + col - 1;
	}

	static boolean checkResult( int index, int diff) {
		if (table[index] == table[index + diff] && table[index + diff] == table[index + (diff * 2)])
			return true;
		return false;
	}
	
	static boolean checkResultSlant() {
		if (checkBlank(0) && checkResult(0, 4)) 
			return true;
		if (checkBlank(2) && checkResult(2, 2)) 
			return true;
		return false;
	}
	
	static boolean checkResultVertical() {
		if (checkBlank(0) && checkResult(0, 1)) 
			return true;
		if (checkBlank(3) && checkResult(3, 1)) 
			return true;
		if (checkBlank(6) && checkResult(6, 1)) 
			return true;
		return false;
	}
	
	static boolean checkResultHorizontal() {
		if (checkBlank(0) && checkResult(0, 3)) 
			return true;
		if (checkBlank(1) && checkResult(1, 3)) 
			return true;
		if (checkBlank(2) && checkResult(2, 3)) 
			return true;
		return false;
	}

	
	static boolean checkWin() {
		if (checkResultVertical())
			return true;
		if (checkResultHorizontal())
			return true;
		if (checkResultSlant())
			return true;
		return false;
		
	}
	
	static boolean checkDraw() {
		if (round == 9) {
			player = 'd';
			return true;
		}

		return false;
	}
	
	

	static void printWinner() {
		printTable();
		if (player == 'd')
			System.out.println("Draw!!");
		else
			System.out.println("player " + Character.toString(player) + " Win!!");
	}

	public static void main(String[] args) {
		printWelcome();
		while (true) {
			printTable();
			printTurn();
			inputPosition();
			
			if (checkWin())
				break;

			if (checkDraw())
				break;
		}
		printWinner();
	}

}
