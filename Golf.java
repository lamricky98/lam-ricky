// -------------------------------------------------------
// Assignment 4
// Written by: Ricky Lam 40089502
// For COMP 248 Section W – Winter 2019
// --------------------------------------------------------

//This is the main, driver method that runs with the help of the two classes from before.
//The golf game alternates turns between two players.
//It uses a standard playing card deck with two jokers.
//The victory condition is scoring lower than your opponent.
//The complexity of the game hides behind switching cards from the board, the deck, and the discard pile!
//I have included a prompt that asks to display the rules to the players before the game starts.


import java.util.Scanner;

public class Golf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String player1, player2; //These strings store the name entered by each player.
		String rulesConfirm;
		int p1Row, p1Col, p2Row, p2Col;
		DeckAndDiscard pileDnD = new DeckAndDiscard();
		Scanner keyIn = new Scanner(System.in);

		//This displays a banner for when the program is first launched.
		System.out.println(
				"  _______       __  ___       _______ __              _______               __     _______                      ");
		System.out.println(
				" |   _   .-----|  .'  _|     |       |  |--.-----.   |   _   .---.-.----.--|  |   |   _   .---.-.--------.-----.");
		System.out.println(
				" |.  |___|  _  |  |   ___    |.|   | |     |  -__|   |.  1___|  _  |   _|  _  |   |.  |___|  _  |        |  -__|");
		System.out.println(
				" |.  |   |_____|__|__||  |   `-|.  |-|__|__|_____|   |.  |___|___._|__| |_____|   |.  |   |___._|__|__|__|_____|");
		System.out.println(
				" |:  1   |             |_|     |:  |                 |:  1   |                    |:  1   |                     ");
		System.out.println(
				" |::.. . |                     |::.|                 |::.. . |                    |::.. . |                     ");
		System.out.println(
				" `-------'                     `---'                 `-------'                    `-------'                     ");
		System.out.println("This is a simple card game where your luck and some strategy will help you win!");
		System.out.println("Try your best to keep your score as low as possible.");
		System.out.println("Let's roll!");
		System.out.println();
		do {
			System.out.print("Would you like to read the rules? (y/n) ");
			rulesConfirm = keyIn.next();
			if ( rulesConfirm.length() != 1 && !( rulesConfirm.equalsIgnoreCase("y") || rulesConfirm.equalsIgnoreCase("n") ) )
				System.out.println("\n  Sorry! You must enter y or n as an answer. Case is not sensitive.\n");
		} while ( rulesConfirm.length() != 1 && !( rulesConfirm.equalsIgnoreCase("y") || rulesConfirm.equalsIgnoreCase("n") ) );
		if (rulesConfirm.equalsIgnoreCase("y"))
			displayRules();
		
		
		System.out.println("\nEnter the name of the first player:");
		player1 = keyIn.next();
		do {
			System.out.println("Enter the name of the second player:");
			player2 = keyIn.next();
			if (player2.equals(player1)) {
				System.out.println("\n  Please choose a different name from player one.\n");
			}
		} while (player2.equals(player1));
		Player playerOne = new Player(player1);
		Player playerTwo = new Player(player2);

		// Setting the board for both players.
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				playerOne.setTo(r, c, pileDnD.pickACard());
			}
		}

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				playerTwo.setTo(r, c, pileDnD.pickACard());
			}
		}

		//At the start of the game, the players get to flip any two of their nine cards.
		System.out.println();
		System.out.println(player1 + ", time to decide which 2 cards you want to turn over.");
		do {
			do {
				System.out.print("Which card do you want to flip (row col)? ");
				p1Row = keyIn.nextInt();
				p1Col = keyIn.nextInt();
				if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
					System.out.println("\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
				}
			} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
			if (playerOne.isTurned(p1Row, p1Col) == false) {
				playerOne.turn(p1Row, p1Col);
				break;
			} else if (playerOne.isTurned(p1Row, p1Col) == true) {
				System.out.println("This card has already been flipped.");
			}
		} while (!(playerOne.isTurned(p1Row, p1Col)));
		do {
			do {
				System.out.print("Next card you would like to flip (row col)? ");
				p1Row = keyIn.nextInt();
				p1Col = keyIn.nextInt();
				if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
					System.out.println("\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
				}
			} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
			if (playerOne.isTurned(p1Row, p1Col) == false) {
				playerOne.turn(p1Row, p1Col);
				break;
			} else if (playerOne.isTurned(p1Row, p1Col) == true) {
				System.out.println("\n  Error. This card has already been flipped.\n");
			}
		} while ((playerOne.isTurned(p1Row, p1Col)));

		System.out.println("\n" + player2 + ", time to decide which 2 cards you want to turn over.");
		do {
			do {
				System.out.print("Which card do you want to flip (row col)? ");
				p2Row = keyIn.nextInt();
				p2Col = keyIn.nextInt();
				if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
					System.out.println("\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
				}
			} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
			if (playerTwo.isTurned(p2Row, p2Col) == false) {
				playerTwo.turn(p2Row, p2Col);
				break;
			} else if (playerTwo.isTurned(p2Row, p2Col) == true) {
				System.out.println("This card has already been flipped.");
			}
		} while (!(playerTwo.isTurned(p2Row, p2Col)));
		do {
			do {
				System.out.print("Next card you would like to flip (row col)? ");
				p2Row = keyIn.nextInt();
				p2Col = keyIn.nextInt();
				if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
					System.out.println("\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
				}
			} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
			if (playerTwo.isTurned(p2Row, p2Col) == false) {
				playerTwo.turn(p2Row, p2Col);
				break;
			} else if (playerTwo.isTurned(p2Row, p2Col) == true) {
				System.out.println("\n  Error. This card has already been flipped.\n");
			}
		} while ((playerTwo.isTurned(p2Row, p2Col)));

		System.out.println("\nLet the game begin!");
		//The game has started, and one card in the deck is moved to the discard pile.
		pileDnD.pickACard();
		pileDnD.discard();

		int drawOrToss, replaceOrFlip, replaceOrToss; //These variables are used for the flow through the choices by the players.
		char tempCard; //I use this variable to pass cards between the player class and the deck and discard class.

		//I have set up multiple data validation points. There's actually one for every time the user is prompted.
		while (playerOne.allTurned() == false || playerTwo.allTurned() == false) { //This while loop checks for the condition that every card is still unturned before going through another round of turns.
			System.out.println("\nThe discard pile shows " + pileDnD.getTopDiscardCard());
			System.out.println(player1 + "'s turn:");
			playerOne.displayGrid();
			do {
				System.out.print("Do you want the card on the discard pile(0) or a new card(1)? ");
				drawOrToss = keyIn.nextInt();
				if (drawOrToss > 1 || drawOrToss < 0)
					System.out.println("\n  Please only enter 0 or 1 as your input.\n");
			} while (drawOrToss > 1 || drawOrToss < 0);
			if (drawOrToss == 0) { //If player one wants the discard pile's card...
				do {
					System.out.print(player1 + ", do you want to replace a flipped card(0) or flip a new card(1)? ");
					replaceOrFlip = keyIn.nextInt();
					if (replaceOrFlip > 1 || replaceOrFlip < 0)
						System.out.println("\n  Please only enter 0 or 1 as your input.\n");
				} while (replaceOrFlip > 1 || replaceOrFlip < 0);
				if (replaceOrFlip == 0) {
					do {
						do {
							System.out.print("Which flipped card do you want to replace (row col)? ");
							p1Row = keyIn.nextInt();
							p1Col = keyIn.nextInt();
							if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
								System.out.println(
										"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
							}
						} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
						if (playerOne.isTurned(p1Row, p1Col) == false) {
							System.out.println(
									"\n This card is not yet flipped. Please choose a card you have already flipped.\n");
						} else { //This block of code will swap the card on the board with the one showing on the discard pile.
							tempCard = pileDnD.getTopDiscardCard();
							pileDnD.switchWithBoard(playerOne.cardAt(p1Row, p1Col));
							playerOne.setTo(p1Row, p1Col, tempCard);
						}
					} while (playerOne.isTurned(p1Row, p1Col) == false);
				} else if (replaceOrFlip == 1) {
					do {
						do {
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							p1Row = keyIn.nextInt();
							p1Col = keyIn.nextInt();
							if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
								System.out.println(
										"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
							}
						} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
						if (playerOne.isTurned(p1Row, p1Col) == true) {
							System.out
									.println("\n This card is already flipped. Please choose another card to flip.\n");
						}
						if (playerOne.isTurned(p1Row, p1Col) == false) {
							break;
						}
					} while (playerOne.isTurned(p1Row, p1Col) == true);
					//If the chosen coordinates are legal, the card is switched with the one on the discard pile.
					playerOne.turn(p1Row, p1Col);
					tempCard = pileDnD.getTopDiscardCard();
					pileDnD.switchWithBoard(playerOne.cardAt(p1Row, p1Col));
					playerOne.setTo(p1Row, p1Col, tempCard);

				}
			} else if (drawOrToss == 1) { //A card is drawn and shown to the player before they make decisions.
				System.out.println("The card you are playing is " + pileDnD.pickACard() + "\n");
				do {
					System.out.print("Replace a card(0) or toss it(1)? ");
					replaceOrToss = keyIn.nextInt();
					if (replaceOrToss > 1 || replaceOrToss < 0)
						System.out.println("\n  Please only enter 0 or 1 as your input.\n");
				} while (replaceOrToss > 1 || replaceOrToss < 0);

				if (replaceOrToss == 0) { //The user has chosen to replace a card...
					do {
						System.out
								.print(player1 + ", do you want to replace a flipped card(0) or flip a new card(1)? ");
						replaceOrFlip = keyIn.nextInt();
						if (replaceOrFlip > 1 || replaceOrFlip < 0)
							System.out.println("\n  Please only enter 0 or 1 as your input.\n");
					} while (replaceOrFlip > 1 || replaceOrFlip < 0);
					if (replaceOrFlip == 0) { //If they're replacing a flipped card, then we just need to know which one they want to switch.
						do {
							do {
								System.out.print("Which flipped card do you want to replace (row col)? ");
								p1Row = keyIn.nextInt();
								p1Col = keyIn.nextInt();
								if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
									System.out.println(
											"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
								}
							} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
							if (playerOne.isTurned(p1Row, p1Col) == false) {
								System.out.println(
										"\n This card is not yet flipped. Please choose a card you have already flipped.\n");
							} else { //After validating the indexes, the card in the board is discarded and said card on the board is replaced with the card drawn.
								tempCard = pileDnD.getTopCard();
								pileDnD.switchCard(playerOne.cardAt(p1Row, p1Col));
								pileDnD.discard();
								playerOne.setTo(p1Row, p1Col, tempCard);
							}
						} while (playerOne.isTurned(p1Row, p1Col) == false);
					} else if (replaceOrFlip == 1) { //But when they're flipping a card that was face down before...
						do {
							do {
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								p1Row = keyIn.nextInt();
								p1Col = keyIn.nextInt();
								if ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0)) {
									System.out.println(
											"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
								}
							} while ((p1Row > 2 || p1Row < 0) || (p1Col > 2 || p1Col < 0));
							if (playerOne.isTurned(p1Row, p1Col) == true) {
								System.out.println(
										"\n This card is already flipped. Please choose another card to flip.\n");
							}
							if (playerOne.isTurned(p1Row, p1Col) == false) {
								break;
							}
						} while (playerOne.isTurned(p1Row, p1Col) == true);
						playerOne.flip(p1Row, p1Col); //This method will display the board with the chosen card already flipped.
						//The player is then asked whether they want to replace the card they chose.
						do {
							System.out.print("\nReplace this card(0) or toss(1)? ");
							replaceOrToss = keyIn.nextInt();
							if (replaceOrToss > 1 || replaceOrToss < 0)
								System.out.println("\n  Please only enter 0 or 1 as your input.\n");
						} while (replaceOrToss > 1 || replaceOrToss < 0);
						if (replaceOrToss == 0) { //This block of code will replace the drawn card with one on the board.
							tempCard = pileDnD.getTopCard();
							pileDnD.switchCard(playerOne.cardAt(p1Row, p1Col));
							pileDnD.discard();
							playerOne.setTo(p1Row, p1Col, tempCard);
						} else if (replaceOrToss == 1) { //this method just adds the card drawn before into the discard pile.
							pileDnD.discard();
						}

					}
				} else if (replaceOrToss == 1) { //This second toss is for when the user wants to toss without considering flipping a card on the board...
					pileDnD.discard();
				}
			}

			if (playerOne.allTurned() == true) { //A checkpoint before the second player's turn to see if the game is over.
				System.out.println("\n" + player1 + " has turned all cards. The game is over.");
				break;
			}
			
			if (pileDnD.getDeckCards() == 0) { //another checkpoint.
				System.out.println("There are no more cards left in the deck. Game is over!");
				break;
			}

			//From here on the code is basically the same for the second player, only with the variables replaced.
			System.out.println("\nThe discard pile shows " + pileDnD.getTopDiscardCard());
			System.out.println(player2 + "'s turn:");
			playerTwo.displayGrid();
			do {
				System.out.print("Do you want the card on the discard pile(0) or a new card(1)? ");
				drawOrToss = keyIn.nextInt();
				if (drawOrToss > 1 || drawOrToss < 0)
					System.out.println("\n  Please only enter 0 or 1 as your input.\n");
			} while (drawOrToss > 1 || drawOrToss < 0);
			if (drawOrToss == 0) {
				do {
					System.out.print(player2 + ", do you want to replace a flipped card(0) or flip a new card(1)? ");
					replaceOrFlip = keyIn.nextInt();
					if (replaceOrFlip > 1 || replaceOrFlip < 0)
						System.out.println("\n  Please only enter 0 or 1 as your input.\n");
				} while (replaceOrFlip > 1 || replaceOrFlip < 0);
				if (replaceOrFlip == 0) {
					do {
						do {
							System.out.print("Which flipped card do you want to replace (row col)? ");
							p2Row = keyIn.nextInt();
							p2Col = keyIn.nextInt();
							if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
								System.out.println(
										"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
							}
						} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
						if (playerTwo.isTurned(p2Row, p2Col) == false) {
							System.out.println(
									"\n This card is not yet flipped. Please choose a card you have already flipped.\n");
						} else {
							tempCard = pileDnD.getTopDiscardCard();
							pileDnD.switchWithBoard(playerTwo.cardAt(p2Row, p2Col));
							playerTwo.setTo(p2Row, p2Col, tempCard);
						}
					} while (playerTwo.isTurned(p2Row, p2Col) == false);
				} else if (replaceOrFlip == 1) {
					do {
						do {
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							p2Row = keyIn.nextInt();
							p2Col = keyIn.nextInt();
							if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
								System.out.println(
										"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
							}
						} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
						if (playerTwo.isTurned(p2Row, p2Col) == true) {
							System.out
									.println("\n This card is already flipped. Please choose another card to flip.\n");
						}
						if (playerTwo.isTurned(p2Row, p2Col) == false) {
							break;
						}
					} while (playerTwo.isTurned(p2Row, p2Col) == true);

					playerTwo.turn(p2Row, p2Col);
					tempCard = pileDnD.getTopDiscardCard();
					pileDnD.switchWithBoard(playerTwo.cardAt(p2Row, p2Col));
					playerTwo.setTo(p2Row, p2Col, tempCard);

				}
			} else if (drawOrToss == 1) {
				System.out.println("The card you are playing is " + pileDnD.pickACard() + "\n");
				do {
					System.out.print("Replace a card(0) or toss it(1)? ");
					replaceOrToss = keyIn.nextInt();
					if (replaceOrToss > 1 || replaceOrToss < 0)
						System.out.println("\n  Please only enter 0 or 1 as your input.\n");
				} while (replaceOrToss > 1 || replaceOrToss < 0);

				// aaaaa

				if (replaceOrToss == 0) {
					do {
						System.out
								.print(player2 + ", do you want to replace a flipped card(0) or flip a new card(1)? ");
						replaceOrFlip = keyIn.nextInt();
						if (replaceOrFlip > 1 || replaceOrFlip < 0)
							System.out.println("\n  Please only enter 0 or 1 as your input.\n");
					} while (replaceOrFlip > 1 || replaceOrFlip < 0);
					if (replaceOrFlip == 0) {
						do {
							do {
								System.out.print("Which flipped card do you want to replace (row col)? ");
								p2Row = keyIn.nextInt();
								p2Col = keyIn.nextInt();
								if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
									System.out.println(
											"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
								}
							} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
							if (playerTwo.isTurned(p2Row, p2Col) == false) {
								System.out.println(
										"\n This card is not yet flipped. Please choose a card you have already flipped.\n");
							} else {
								tempCard = pileDnD.getTopCard();
								pileDnD.switchCard(playerTwo.cardAt(p2Row, p2Col));
								pileDnD.discard();
								playerTwo.setTo(p2Row, p2Col, tempCard);
							}
						} while (playerTwo.isTurned(p2Row, p2Col) == false);
					} else if (replaceOrFlip == 1) {
						do {
							do {
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								p2Row = keyIn.nextInt();
								p2Col = keyIn.nextInt();
								if ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0)) {
									System.out.println(
											"\n  Invalid input!! Please enter a number between 0 and 2, both inclusive.\n");
								}
							} while ((p2Row > 2 || p2Row < 0) || (p2Col > 2 || p2Col < 0));
							if (playerTwo.isTurned(p2Row, p2Col) == true) {
								System.out.println(
										"\n This card is already flipped. Please choose another card to flip.\n");
							}
							if (playerTwo.isTurned(p2Row, p2Col) == false) {
								break;
							}
						} while (playerTwo.isTurned(p2Row, p2Col) == true);
						playerTwo.flip(p2Row, p2Col);
						do {
							System.out.print("\nReplace this card(0) or toss(1)? ");
							replaceOrToss = keyIn.nextInt();
							if (replaceOrToss > 1 || replaceOrToss < 0)
								System.out.println("\n  Please only enter 0 or 1 as your input.\n");
						} while (replaceOrToss > 1 || replaceOrToss < 0);
						if (replaceOrToss == 0) {
							tempCard = pileDnD.getTopCard();
							pileDnD.switchCard(playerTwo.cardAt(p2Row, p2Col));
							pileDnD.discard();
							playerTwo.setTo(p2Row, p2Col, tempCard);
						}
					} else if (replaceOrToss == 1) {
						pileDnD.discard();

					}
				} else if (replaceOrToss == 1) {
					pileDnD.discard();
				}

				if (playerTwo.allTurned() == true) {
					System.out.println("\n" + player2 + " has turned all cards. The game is over.");
					break;
				}
				
				if (pileDnD.getDeckCards() == 0) {
					System.out.println("There are no more cards left in the deck. Game is over!");
					break;
				}
			}

		}
		
		//The program will reach this point after breaking out of the loop that alternate between turns.
		
		System.out.println("\nTime to calculate points! Here are your boards with all cards turned over.");
		//The complete board of each player is revealed before their scores are presented.
		playerOne.displayBoard();
		playerTwo.displayBoard();
		System.out.println("\nFinal result:");
		System.out.println("  " + player1 + " scored " + playerOne.calculatePts());
		System.out.println("  " + player2 + " scored " + playerTwo.calculatePts());
		if (playerOne.calculatePts() < playerTwo.calculatePts()) {
			System.out.println("Congratulations!! The winner is " + player1);
		} else {
			System.out.println("Congratulations!! The winner is " + player2);
		}

		System.out.println("\n\nThis is the end of the program. Hope you had fun playing Golf (card version)!");
		keyIn.close();
	}
	
	public static void displayRules() { //this method is called if the player wants to read the rules
		System.out.println("\nEach player is dealt a 3x3 board of cards, each card initially facing down.");
		System.out.println("To win, your objective is to do your best to score the lowest possible with the cards on your board at the end of the game.");
		System.out.println("Here are the scores pertaining to each card:");
		System.out.println(" ______________________________________________________________________________");
		System.out.println("| Card                       | Point Value                                     |");
		System.out.println(" ______________________________________________________________________________");
		System.out.println("| Ace                        | 1                                               |");
		System.out.println("| 2 to 10                    | Face Value                                      |");
		System.out.println("| Jack, Queen                | 10                                              |");
		System.out.println("| King                       | 0                                               |");
		System.out.println("| Joker                      | -5                                              |");
		System.out.println(" ______________________________________________________________________________");
		System.out.println("However, having identical cards in a column, row, or diagonal will give a value of 0 to every card in said column, row, or diagonal.");
		System.out.println("For example, having all [6] in the first column will give all the three [6] cards a value equal to zero!");
		System.out.println("The game will end when either player has turned all their cards to be facing up.");
		System.out.println("The game will also end if the deck of cards is empty!");
		System.out.println("During the game, you may choose to draw a new card or do a move with the card in the discard pile.");
		System.out.println("When you choose the card on the discard pile, you MUST swap it with any card on your board.");
		System.out.println("When you draw a card from the deck, you are given the choice to toss it away without affecting your board.");
		System.out.println("Do your best to win! And good luck!!!");
	}

}
