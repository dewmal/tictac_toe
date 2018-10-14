import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
Author : Dewmal dewmalnilanka@gmail.comm
Date : 2018.10.14
**/

class TicTocGameHelper{	

	public static boolean isPosValid(char[][] gameBoard,int posX,int posY){
		int gameBoardNumberOfRows=gameBoard[0].length;
		int gameBoardNumberOfColumns=gameBoard[1].length;

		return posX>0 && posY>0 && posX<=gameBoardNumberOfRows&&posY<=gameBoardNumberOfColumns;
	}

	// Check Game Draw Status
	public static boolean isGameBoardDraw(char gameDefaultMark,char[][] gameBoard){
		int gameBoardNumberOfRows=gameBoard[0].length;
		int gameBoardNumberOfColumns=gameBoard[1].length;

		boolean gameDraw=true;
		L1:for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				if (gameDefaultMark==gameBoard[r][c]){
					gameDraw=false;
					break L1;
				}
			}			
		}
		return gameDraw;
	}

	// Static Class Help To Check with Others
	public static boolean checkBoardWin(char playerMark,char[][] gameBoard){
		boolean isWin=false;
		boolean rowCheck=false;
		boolean colCheck=false;
		boolean diagonalCheck1=true;
		boolean diagonalCheck2=true;
		int nextMarkPos=0;

		int gameBoardNumberOfRows=gameBoard[0].length;
		int gameBoardNumberOfColumns=gameBoard[1].length;

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

	public static void printGameScreen(char[][] gameBoard){
		int gameBoardNumberOfRows=gameBoard[0].length;
		int gameBoardNumberOfColumns=gameBoard[1].length;

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

}


public class TicTocGame {

	//Game Screen Details
	private int gameBoardNumberOfRows=3;
	private int gameBoardNumberOfColumns=gameBoardNumberOfRows;
	private char gameBoard[][]=new char[gameBoardNumberOfRows][gameBoardNumberOfColumns];
	private boolean isGameEnd = false;
	private char gameDefaultMark='-'; // Check to set 

	// Iinitiate Player Array
	private int numberOfPlayers=4;
	private String players[]=new String[numberOfPlayers];
	private char playSymbols[]=new char[]{'X','O','Y','M','J','K'};

	// Getting Keyboard inputs
	private Scanner keyboard= new Scanner(System.in);

	//
	private Player agents[];

	TicTocGame(int gameBoardPositions){
		this.gameBoardNumberOfRows=gameBoardPositions;
		initiateGameScreen(); // Initiate Game Screen Here
	}

	TicTocGame(int gameBoardPositions,Player ...agents){
		this.agents=agents;
		this.gameBoardNumberOfRows=gameBoardPositions;
		initiateGameScreen(); // Initiate Game Screen Here
	}

	/**
	Show Game Screen
	**/
	private void initiateGameScreen(){
		this.gameBoardNumberOfColumns=this.gameBoardNumberOfRows;
		this.gameBoard=new char[this.gameBoardNumberOfRows][this.gameBoardNumberOfColumns];

		for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				gameBoard[r][c] = gameDefaultMark;
			}			
		}

		// Call Player Initiate Methods
		//for (int p=0;p<players.length ;p++ ) {
		//	agents[p].init(gameBoard);			
		//}
	}

	// Get Game Status
	private char[][] getGameState(){
		char[][] gameBoardStatus=new char[this.gameBoardNumberOfRows][this.gameBoardNumberOfColumns];
		for (int r=0;r<gameBoardNumberOfRows;r++ ) {			
			for (int c=0;c<gameBoardNumberOfColumns;c++ ) {
				gameBoardStatus[r][c]=gameBoard[r][c];
			}			
		}
		return gameBoardStatus;
	}
		
	

	private boolean isGameBoardDraw(){
		return TicTocGameHelper.isGameBoardDraw(gameDefaultMark,gameBoard);
	}
	/**
	Print Game Screen In Console
	**/
	private void printGameScreen(){
		TicTocGameHelper.printGameScreen(this.gameBoard);
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
		return TicTocGameHelper.checkBoardWin(playerMark,this.gameBoard);
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

			Player agent=null;

			if(agents.length>currentPlayer){
				agent=agents[currentPlayer];
			}
			// Get player inputs
			if (!checkAllPlayersAreInitiate()){				
				System.out.println("Hello Player "+ playerName +", Please Insert Your Name Here :");
				playerName=keyboard.nextLine();
				System.out.println(playerName);
				players[currentPlayer]=playerName;
				System.out.println("Wellcome "+playerName+" Your Symbol is :"+playerMark);
				// Initialize Agent If there any
				if(agent!=null){
					agent.init(getGameState());
				}
			}else {
				//System.out.println("--All players are Initiated--\n");

			}

			playerName=getPlayerName(currentPlayer);
			

			// Get User Game Play Inputs
			boolean isSuccessInput=false;
			System.out.print("P: "+playerName+" Mark Position x.y : ");	
			do{

				int posX=-1;
				int posY=-1;

				

				

				if (agent!=null){
					Position pos=agent.playGame(playerMark,getGameState());
					posX=pos.x;
					posY=pos.y;

				}else{
					String nextPositionLine=keyboard.nextLine();
					char pos[]=nextPositionLine.toCharArray();
					posX=Integer.parseInt(pos[0]+"");
					posY=Integer.parseInt(pos[2]+"");
				}

			
				

				if (TicTocGameHelper.isPosValid(gameBoard,posX,posY)){
					char gameMark=gameBoard[posX-1][posY-1];
					if (gameMark==gameDefaultMark){
						gameBoard[posX-1][posY-1]=playerMark;
						isSuccessInput=true;
						System.out.println(" X ="+posX+", Y="+posY);
					}else{
						//System.out.println("You can't Insert Marks to That Position");
					}	
				}else{
					//System.out.println("Invalid Position");
				}
						

			}while(!isSuccessInput);
			
			// Check Winner
			boolean isCurruntPlayerWin=checkIsPlayerWin(playerMark);
			

			// Show game screen & Result
			printGameScreen();

			if (isCurruntPlayerWin){
				System.out.println("Player "+playerName+"( "+playerMark+" ) Win");
				isGameEnd=true;
			}

			if(!isGameEnd&&isGameBoardDraw()){
				System.out.println("---Game Draw---");
				isGameEnd=true;
			}

			// Update Current Player to next player
			currentPlayer++;
			currentPlayer = currentPlayer==(numberOfPlayers)?0:currentPlayer;
			

		}

		System.out.println("Game End");

	}


	public static void main(String[] args) {
		int gameBoardPositions=3;

		if(args.length==1)
			gameBoardPositions=Integer.parseInt(args[0]);

		Player p1=new AIPlayerRandom();
		Player p2=new AIPlayer();
		Player p3=new AIPlayer();
		Player p4=new AIPlayer();
		// AIPlayer p1=new AIPlayer();
		
		TicTocGame game= new TicTocGame(gameBoardPositions,p1,p2,p3,p4);
		game.play();
		
	}
	

}

class Position{
	public int x;
	public int y;

	Position(){		
	}

	Position(int x,int y){
		this.x=x;
		this.y=y;
	}

	public String toString(){
		return this.x+"."+this.y;
	}

}

class HelperFunc{

}

interface Player{
	// Initialize Player With Open Game Screen
	void init(char[][] gameScreenState);
	// Play Act based on game screen
	Position playGame(char playerMark,char[][] gameScreenState);
}

class AIPlayer  implements Player{

	private Random rand=new Random();
	private Position winPos[][];

	public void init(char[][] gameScreenState){
		int rowLength=gameScreenState[0].length;
		int colLength=gameScreenState[1].length;
		int numberOfWinCombinations=rowLength+colLength+2;
		winPos=new Position[numberOfWinCombinations][rowLength];
	}


	public Position playGame(char playerMark,char[][] gameScreenState){		
		

		int rowLength=gameScreenState[0].length;
		int colLength=gameScreenState[1].length;

		int x=-1;
		int y=-1;
		
		boolean alogRunDone=false;

		Map<String,List<Position>> possiblePos=new HashMap<String,List<Position>>();


		do{
			x=Math.abs(rand.nextInt()%rowLength)+1;
			y=Math.abs(rand.nextInt()%colLength)+1;
			Position tempPos=new Position(x,y);		
			if(TicTocGameHelper.isPosValid(gameScreenState,x,y)){
					

				List<Position> posList=possiblePos.get(tempPos);
				if(posList==null){
					posList=new ArrayList<Position>();
				}

				posList.add(tempPos);
				possiblePos.put(tempPos+"",posList);

				gameScreenState[x-1][y-1]=playerMark;
				// Check Goal Is found
				boolean isItWiningPos=TicTocGameHelper.checkBoardWin(playerMark,gameScreenState);
				if (isItWiningPos){
					tempPos=posList.get(0);
					x=tempPos.x;
					y=tempPos.y;
					alogRunDone=true;
				}

			}
			System.out.println("\n......Player Thinking...");
			TicTocGameHelper.printGameScreen(gameScreenState);
			System.out.println("Last Pos "+tempPos);
			System.out.println("...........\n");
			


		}while(!alogRunDone&&TicTocGameHelper.isPosValid(gameScreenState,x,y));
		

		Position pos=new Position(x,y);


		//System.out.println(pos.x+","+pos.y);

		return pos;
	} 

}

class AIPlayerRandom implements Player{

	private Random rand=new Random();

	public void init(char[][] gameScreen){
		System.out.println("Agent Init");
	}

	public Position playGame(char playerMark,char[][] gameScreen){		
		Position pos=new Position();

		int rowLength=gameScreen[0].length;
		int colLength=gameScreen[1].length;

		pos.x=Math.abs(rand.nextInt()%rowLength)+1;
		pos.y=Math.abs(rand.nextInt()%colLength)+1;
		//System.out.println(pos.x+","+pos.y);

		return pos;
	} 

}