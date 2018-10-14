import java.util.Scanner;

/**
Author : Dewmal dewmalnilanka@gmail.comm
Date : 2018.10.14
**/
public class TicTocGame {

	//Game Screen Details
	private int gameBoardNumberOfRows=4;
	private int gameBoardNumberOfColumns=gameBoardNumberOfRows;
	private char gameBoard[][]=new char[gameBoardNumberOfRows][gameBoardNumberOfColumns];
	private boolean isGameEnd = false;
	private char gameDefaultMark='-'; // Check to set 

	// Iinitiate Player Array
	private int numberOfPlayers=3;
	private String players[]=new String[numberOfPlayers];
	private char playSymbols[]=new char[]{'X','O','Y'};

	// Getting Keyboard inputs
	private Scanner keyboard= new Scanner(System.in);


	TicTocGame(){
		initiateGameScreen(); // Initiate Game Screen Here
	}

	/**
	Show Game Screen
	**/
	private void initiateGameScreen(){
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				gameBoard[r][c] = gameDefaultMark;
			}			
		}
	}
		

	/**
	Print Game Screen In Console
	**/
	private void printGameScreen(){
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {
			for (int k=0;k<gameBoardNumberOfColumns;k++ ) {
				System.out.print(" ---");
			} 
			System.out.println();
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				char gameCell =gameBoard[r][c];				
				System.out.print(" :"+gameCell+":");				
			}
			System.out.println();
			//System.out.println("------");
		}
		System.out.println();
	}

	/**
	Return true if all player are initiate
	**/
	private boolean checkAllPlayersAreInitiate(){
		for (int a=0;a<numberOfPlayers; a++) {
			if(players[a]==null||players[a].isEmpty()){
				return false;
			}
		}
		return true;
	}

	/**
		Get player name belong to given Index
	**/
	private String getPlayerName(int currentPlayerIndex){
		return players[currentPlayerIndex];
	}



	private boolean checkIsPlayerWin(char playerMark){
		boolean isWin=false;
		boolean rowCheck=false;
		boolean colCheck=false;
		boolean diagonalCheck1=true;
		boolean diagonalCheck2=true;
		int nextMarkPos=0;

		


		// Row Check
		L1:for (int r=0;r<gameBoardNumberOfRows;r++ ) {
			boolean isRowHasSame=true;

			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				char boardMark=gameBoard[r][c];
				if(boardMark==playerMark){
					isRowHasSame&=isRowHasSame;
				}else{
					isRowHasSame=false;
				}

			}

			rowCheck=isRowHasSame;	

			if(rowCheck){
				break L1;	
			}	
		}


		// Col Check
		L2:for (int r=0;r<gameBoardNumberOfRows;r++ ) {
			boolean isColHasSame=true;
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				char boardMark=gameBoard[c][r];
				if(boardMark==playerMark){
					isColHasSame&=isColHasSame;
				}else{
					isColHasSame=false;
				}
			}
			colCheck=isColHasSame;	
			if(colCheck){
				break L2;	
			}	
		}

		// Diag 1 Check
		L3:for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				if (c==r){
					char boardMark=gameBoard[r][c];
					//System.out.print("boardMark"+boardMark);
					if(boardMark==playerMark){
						diagonalCheck1&=diagonalCheck1;
					}else{
						diagonalCheck1=false;
					}
				}				
			}
		}

		// Diag 2 Check
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				int x=c;
				int y=gameBoardNumberOfColumns-1-r;
				if (x==y){
					char boardMark=gameBoard[r][c];					
					if(boardMark==playerMark){
						diagonalCheck2&=diagonalCheck2;
					}else{
						diagonalCheck2=false;
					}
				}				
			}
		}

		return rowCheck||colCheck||diagonalCheck1||diagonalCheck2;
	}
	/**
	Game Loop run here
	**/
	private void play(){

		// Initiate Game
		System.out.println("---Game Started---");
		printGameScreen();
		System.out.println("---Number Of players Can play the game "+numberOfPlayers);


		int currentPlayer=0;

		while (!isGameEnd){

			String playerName = currentPlayer+"";
			char playerMark = playSymbols[currentPlayer];
			// Get player inputs
			if (!checkAllPlayersAreInitiate()){
				
				System.out.println("Hello Player "+ playerName +", Please Insert Your Name Here :");
				playerName=keyboard.nextLine();
				System.out.println(playerName);
				players[currentPlayer]=playerName;
				System.out.println("Wellcome "+playerName+" Your Symbol is :"+playerMark);
			}else {
				System.out.println("--All players are Initiated--\n");

			}

			playerName=getPlayerName(currentPlayer);
			

			// Get User Game Play Inputs
			boolean isSuccessInput=false;
			do{

				System.out.println("P: "+playerName+" Next Position x.y=>");			
				String nextPositionLine=keyboard.nextLine();
				char pos[]=nextPositionLine.toCharArray();

			
				int posX=Integer.parseInt(pos[0]+"");
				int posY=Integer.parseInt(pos[2]+"");

				if (posX>0 && posY>0 && posX<=gameBoardNumberOfRows&&posY<=gameBoardNumberOfColumns){
					char gameMark=gameBoard[posX-1][posY-1];
					if (gameMark==gameDefaultMark){
						gameBoard[posX-1][posY-1]=playerMark;
						isSuccessInput=true;
					}else{
						System.out.println("You can't Insert Marks to That Position");
					}	
				}else{
					System.out.println("Invalid Position");
				}

						

			}while(!isSuccessInput);
			
			// Check Winner
			boolean isCurruntPlayerWin=checkIsPlayerWin(playerMark);
			

			// Show game screen & Result
			printGameScreen();

			if (isCurruntPlayerWin){
				System.out.println("Player "+playerName+" Win");
				isGameEnd=true;
			}

			// Update Current Player to next player
			currentPlayer++;
			currentPlayer = currentPlayer==(numberOfPlayers)?0:currentPlayer;
			

		}

		System.out.println("Game End");

	}


	public static void main(String[] args) {
		
		TicTocGame game= new TicTocGame();
		game.play();
		
	}
	

}