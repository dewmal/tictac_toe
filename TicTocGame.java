import java.util.Scanner;


public class TicTocGame {

	//Game Screen Details
	private int gameBoardNumberOfRows=3;
	private int gameBoardNumberOfColumns=3;


	private char gameBoard[][]=new char[gameBoardNumberOfRows][gameBoardNumberOfColumns];
	private boolean isGameEnd = false;
	private char gameDefaultMark='-';

	// Iinitiate Player Array
	private int numberOfPlayers=2;
	private String players[]=new String[numberOfPlayers];
	private char playSymbols[]=new char[]{'X','O'};

	// Getting Keyboard inputs
	private Scanner keyboard= new Scanner(System.in);


	TicTocGame(){
		initiateGameScreen();
	}


	private void initiateGameScreen(){
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				gameBoard[r][c] = gameDefaultMark;
			}			
		}
	}
		


	private void printGameScreen(){
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {
			System.out.println(" --- --- ---");
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

	private String getPlayerName(int currentPlayerIndex){
		return players[currentPlayerIndex];
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

				char gameMark=gameBoard[posX-1][posY-1];

				if (gameMark==gameDefaultMark){
					gameBoard[posX-1][posY-1]=playerMark;
					isSuccessInput=true;
				}else{
					System.out.println("You can't Insert Marks to That Position");
				}

				

			}while(!isSuccessInput);
			

			// Show game screen & Result
			printGameScreen();

			// Update Current Player to next player
			currentPlayer++;
			currentPlayer = currentPlayer==(numberOfPlayers)?0:currentPlayer;
			

		}

	}


	public static void main(String[] args) {
		
		TicTocGame game= new TicTocGame();
		game.play();
		
	}
	

}