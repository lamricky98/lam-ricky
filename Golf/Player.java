
public class Player {

	String name;
	char[][] board;
	boolean[][] turned;

	public Player() { //the default constructor. It creates the dimensions of the arrays.
		board = new char[3][3];
		turned = new boolean[3][3];
	}

	public Player(String n) { //A parameterized constructor that sets a name.
		name = n;
		board = new char[3][3];
		turned = new boolean[3][3];
	}
	
	public Player(Player a) {
		name = a.getName();
		board = new char [3][3];
		turned = new boolean[3][3];
	}

	public String getName() {
		return name;
	}

	public void setName(String a) {
		name = a;
	}

	public char cardAt(int r, int c) {
		return board[r][c];
	}

	public boolean flip(int r, int c) { //This is a special method used whenever the player is given a choice after flipping a card.
		boolean flipped = false;
		if (turned[r][c] == false) {
			turned[r][c] = true;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (turned[row][col] == true)
						System.out.print(board[row][col] + "  ");
					else if (turned[row][col] == false)
						System.out.print("[]" + " ");
				}
				System.out.println();
				flipped = true;
			}
		}
		return flipped;
	}

	public void setTo(int r, int c, char card) { //A card is fed to be placed inside the given dimension in the board array.
		board[r][c] = card;
	}

	public boolean isTurned(int r, int c) {
		return turned[r][c];
	}

	public void turn(int r, int c) { //This is a method called to flip a card when the user has no choice after flipping it.
		if (turned[r][c] == false)
			turned[r][c] = true;
		else if (turned[r][c] == true)
			turned[r][c] = true;
	}

	public boolean allTurned() {
		boolean allTurned = true;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (turned[r][c] == false) {
					allTurned = false;
					return allTurned;
				}
			}
		}
		return allTurned;
	}

	public int calculatePts() { //This method looks through each card in the array then sums the points.
		int[][] scores = new int[3][3];
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				switch (board[r][c]) {
				case 'A':
					scores[r][c] = 1;
					break;
				case '2':
					scores[r][c] = 2;
					break;
				case '3':
					scores[r][c] = 3;
					break;
				case '4':
					scores[r][c] = 4;
					break;
				case '5':
					scores[r][c] = 5;
					break;
				case '6':
					scores[r][c] = 6;
					break;
				case '7':
					scores[r][c] = 7;
					break;
				case '8':
					scores[r][c] = 8;
					break;
				case '9':
					scores[r][c] = 9;
					break;
				case 'T':
					scores[r][c] = 10;
					break;
				case 'J':
					scores[r][c] = 10;
					break;
				case 'Q':
					scores[r][c] = 10;
					break;
				case 'K':
					scores[r][c] = 0;
					break;
				case '?':
					scores[r][c] = -5;
					break;
				}
			}
		}
		
		// However, when there is the same card in a row or in a column or diagonally, those cards score a total of zero.
		
		if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][1] == board[0][2]) {
			scores[0][0] = 0;
			scores[0][1] = 0;
			scores[0][2] = 0;
		}

		if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][1] == board[1][2]) {
			scores[1][0] = 0;
			scores[1][1] = 0;
			scores[1][2] = 0;
		}

		if (board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][1] == board[2][2]) {
			scores[2][0] = 0;
			scores[2][1] = 0;
			scores[2][2] = 0;
		}

		if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[1][0] == board[2][0]) {
			scores[0][0] = 0;
			scores[1][0] = 0;
			scores[2][0] = 0;
		}

		if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[1][1] == board[2][1]) {
			scores[0][1] = 0;
			scores[1][1] = 0;
			scores[2][1] = 0;
		}

		if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[1][2] == board[2][2]) {
			scores[0][2] = 0;
			scores[1][2] = 0;
			scores[2][2] = 0;
		}

		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[1][1] == board[2][2]) {
			scores[0][0] = 0;
			scores[1][1] = 0;
			scores[2][2] = 0;
		}

		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[1][1] == board[2][2]) {
			scores[0][2] = 0;
			scores[1][1] = 0;
			scores[2][0] = 0;
		}

		int totalScore = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				totalScore = totalScore + scores[r][c];
			}
		}
		return totalScore;
	}

	public void displayGrid() {  //This is the regular board displaying method while the game is going on.
		System.out.println("Player " + name + "'s board");
		System.out.println("---------------------------");
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (turned[r][c] == true)
					System.out.print(board[r][c] + "  ");
				else if (turned[r][c] == false)
					System.out.print("[]" + " ");
			}
			System.out.println();
		}
	}
	
	public void displayBoard() { //This method will display every card regardless of if it is lying face down or not.
		System.out.println("Player " + name + "'s board");
		System.out.println("---------------------------");
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
					System.out.print(board[r][c] + "  ");
			}
			System.out.println();
		}
	}

}
