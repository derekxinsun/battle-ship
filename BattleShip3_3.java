/**
 * Xi Chen 27276605 & Xin Sun 40038821
 * COMP249
 * Assignment #1
 * Due Date 09/22/2017
 * 
 * Program introduction: A game of battleship between a player and computer.
 * This program illustrate on an 8 by 8 grid battlefield, the player will have to 
 * place 6 ships and 4 grenades with no overlaps; and the computer place its
 * ships and grenades randomly.Then the player and the computer will place rockets 
 * by turns trying to hit all opponent's ships to win the game and to avoid hitting grenades.
 *
 * Technical design: Create different static fields and static methods within 
 * one class, so that all attributes belong to all objects in this class and
 * All static methods can be called without creating new a object. All the function implementation
 * can be accomplished by calling these methods under main method. 
 * 
 * Program Recap: Program sets up with a grid by method fieldReady. Then prompt 
 * user to enter the coordinates for his/her ships and grenades by method placeYourShip 
 * and placeYourGrenade. Computer sets them randomly by method placePC. In the method 
 * Battle which is game starting part by calling placeYourRocket for user's turn and calling
 * placeComputerRocketfor computer's turn. Also, printGrid and printGame methods are used to 
 * display the grid of the battlefield.
 * 
 * There are 2 arrays of grid set up in this program, array of "grid" saves the info of 
 * where battleships and grenades are placed, while the array of "grid2" saves how the grid
 * looks like as the game goes.
 */
import java.util.Scanner;

/**
 * Create a class to encapsulate all the attributes and methods needed in the program
 * @author GT-
 *
 */
public class BattleShip3_3 {
	/**
	 * Declare all the attributes which should be static 
	 */
	/**
	 * Save info of where battleships and grenades are placed
	 */
	 private static String[][] grid = new String[8][8];
	 /**
	  * saves how the grid looks like as the game goes
	  */
	 private static String[][] grid2 = new String[8][8];
	 private static int userShipLeft=6;//Count the how many user's ship have left after hit during the game
	 private static int computerShipLeft=6;//Count the how many computer's ship have left after hit during the game
	 private static boolean gameNotOver = true;//A boolean manipulate if the game is over until all the ships are hit 
	 /**
	  * A boolean manipulate the user will lose the next turn when it becomes true if user places a rocket to hit a grenade
	  */
	 private static boolean userPenalty = false;
	 /**
	  * A boolean manipulate the computer will lose the next turn when it becomes true if computer places a rocket to hit a grenade
	  */
	 private static boolean computerPenalty = false;
	
	public static void main(String[] args) {
		
		fieldReady();// Sets the initial grid.
		Scanner sc = new Scanner(System.in);// Declare a Scanner object to place in ships, grenades and rockets
		placeYourShip(sc);// Prompt user to place all his ships and display the coordinates.
		System.out.println();
		placeYourGrenade(sc);// Prompt user to place all his grenades and display the coordinates.
		placePc();// Computer places all its ships and grenades.
		//battle now.
		battle(sc);// Prompt user to place rockets, while computer plays randomly. Display the game process and final result.
		sc.close();			
		System.exit(0);

	}
	
	/**
	 * Initialize both grids to empty.
	 */
	private static void fieldReady(){
		System.out.println("Hi, let's play Battleship!\n");
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				grid[i][j]="_";
				grid2[i][j]="_";
			}
		}
	}
	
	/**
	 * User places his/her ships
	 * @param sc
	 */
	private static void placeYourShip(Scanner sc){
		for(int n=0;n<6;n++){// place 6 ships.
			boolean flag = true;// flag becomes false, only if the ship is placed.
			while(flag){// loop until a ship is placed.
				String p;
				do{
					System.out.print("Enter the coordinates of your ship #" +(n+1)+": ");
					p = (sc.next()).toUpperCase();// convert to upper cases.
				}while(p.length()!=2);// this loop only allows user enters 2 characters.
				int j = p.charAt(0)-'A';//convert A~H to 0~7 for the horizontal index of array
				int i = p.charAt(1)-'1';//convert 1~8 to 0~7 for the vertical index of array
				if(i>=grid.length || j>=grid.length || i<0 || j<0){
					System.out.println("sorry, coordinates outside the grid. try again.");
				}
				else if(grid[i][j]!="_"){
					System.out.println("sorry, coordinates already used. try again.");
				}
				else{			
					grid[i][j] = "s";//place user's ship denoted as "s".
					flag = false;
				}
			}
		}
	}
	
	/**
	 * User places his/her grenades
	 * @param sc
	 */
	private static void placeYourGrenade(Scanner sc){
		for(int n=0;n<4;n++){// place 4 grenades.
			boolean flag = true;// flag becomes false, only if the grenade is placed.
			while(flag){
				String p;
				do{
					System.out.print("Enter the coordinates of your grenade #" +(n+1)+": ");
					p = (sc.next()).toUpperCase();
				}while(p.length()!=2);
				int j = p.charAt(0)-'A';
				int i = p.charAt(1)-'1';
				if(i>=grid.length || j>=grid.length || i<0 || j<0){
					System.out.println("sorry, coordinates outside the grid. try again.");
				}
				else if(grid[i][j]!="_"){
					System.out.println("sorry, coordinates already used. try again.");
				}
				else{			
					grid[i][j] = "g";// place user's grenade denoted as "g".
					flag = false;
				}
			}	
		}
	}
	
	/**
	 * Computer place its ships & grenades randomly
	 */
	private static void placePc(){
		for(int n=0;n<6;n++){
			boolean flag = true;// flag becomes false, only if the ship is placed.
			while(flag){
				int j = (int)(Math.random()*grid.length);// randomly pick on from 0 to 7
				int i = (int)(Math.random()*grid.length);
				if(grid[i][j]=="_"){ // "_" means that spot is not taken.
					grid[i][j] = "S";// then place computer's ship and denote it as "S".
					flag = false;
				}
			}	
		}
		for(int n=0;n<4;n++){
			boolean flag = true;	
			while(flag){
				int j = (int)(Math.random()*grid.length);
				int i = (int)(Math.random()*grid.length);
				if(grid[i][j]=="_"){
					grid[i][j] = "G";// computer's grenade denoted as "G".
					flag = false;
				}
			}	
		}
		System.out.println("\nOK, the computer placed its ships and grenades at random. Let's play.");
	}
	
	/**
	 * Rockets get placed by user
	 * @param sc
	 */
	private static void placeYourRocket(Scanner sc){
		boolean outOfBound;// if a rocket is place out of grid, keep prompting.
		int i,j;
		
		do{
			String p;
			do{
				System.out.print("position of your rocket: ");
				p = (sc.next()).toUpperCase();
			}while(p.length()!=2);
			
			j = p.charAt(0)-'A';
			i = p.charAt(1)-'1';
			outOfBound = (i>=grid.length || j>=grid.length || i<0 || j<0);
			if(outOfBound){
				System.out.println("sorry, coordinates outside the grid. try again.");
			}
		}while(outOfBound);
		
		boolean flag=true;// flag is false if an rocket is placed.
		while(flag){// out of loop if rocket is placed.
			if(grid2[i][j]!="_"){// here we test if grid2[i][j] is "_", since the grid2 saves where the all rockets is placed so far.
				System.out.println("position already called.");// user sets a useless spot, which is not "_"(already called).
				flag = false;
			}
			else{
				if(grid[i][j]=="_"){
					grid2[i][j]="*";// if it is neither ship nor grenade, display an "*".
				}
				else{
					grid2[i][j]=grid[i][j];// if it is either ship or grenade, display whatever it is there.	
				}
				switch(grid[i][j]){// counting how many ships for computer and user have left.
					case "s": {
						userShipLeft--;// hit "s", user loses 1 ship.
						if(userShipLeft==0){
							System.out.println("ship hit! Computer Wins!");// if user has no ship left, computer wins.
							gameNotOver = false;
						}
						else{
							System.out.println("ship hit!");
						}
						break;
					}
					case "S":{
						computerShipLeft--;// hit "S", computer loses 1 ship.
						if(computerShipLeft==0){
							System.out.println("ship hit! You Win!");// if computer has no ship left, user wins.
							gameNotOver = false;
						}
						else{
							System.out.println("ship hit!");
						}
						break;
					}
					
					case "g":
					case "G":{
						System.out.println("boom! grenade.");//user hits grenade, he/she gets the penalty.
						userPenalty = true;//penalty sets to be true.
						break;
					}
					case "_": {
						System.out.println("nothing.");						
						break;
					}
				}
				flag = false;
			}
		}
	}
	
	/**
	 * Computer's turn of game
	 */
	private static void placeComputerRocket(){
		System.out.print("position of my rocket: ");
		int j = (int)(Math.random()*grid.length);// position is called randomly. the position can be duplicate.
		int i = (int)(Math.random()*grid.length);
		
		System.out.println((char)(j+'A')+""+(i+1));// display the coordinates.
		
		boolean flag=true;
		while(flag){
			if(grid2[i][j]!="_"){			
				System.out.println("position already called.");
				flag = false;
			}
			else{
				if(grid[i][j]=="_"){
					grid2[i][j]="*";
				}
				else{
					grid2[i][j]=grid[i][j];
				}
				switch(grid[i][j]){
					case "s": {
						userShipLeft--;
						if(userShipLeft==0){
							System.out.println("ship hit! Computer Wins!");
							gameNotOver = false;
						}
						else{
							System.out.println("ship hit!");
						}
						break;
					}
					case "S":{
						computerShipLeft--;
						if(computerShipLeft==0){
							System.out.println("ship hit! You Win!");
							gameNotOver = false;
						}
						else{
							System.out.println("ship hit!");
						}
						break;
					}
					
					case "g":
					case "G":{
						System.out.println("boom! grenade.");
						computerPenalty = true;
						break;
					}
					case "_": {
						System.out.println("nothing.");						
						break;
					}
				}
				flag = false;
			}
		}
	}

	/**
	 *  After each turn, the grid is displayed showing ships and grenades already got hit.
	 */
	private static void printGrid(){
		for (int i=0;i<grid2.length;i++){
			for (int j=0;j<grid2.length;j++){
				System.out.print(grid2[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Once the winner is declared, the grid is then displayed showing the position of all ships and grenades.
	 */
	private static void printGame(){
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid.length;j++){
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}	

	/**
	 * A method to manipulate the game by calling different methods defined for game process 
	 * @param sc
	 */
	private static void battle(Scanner sc){
		while(gameNotOver){// while game is not over, then the game is always playing in the loop.
			placeYourRocket(sc);
			if(gameNotOver){// if the game is over, display how the ships and grenades placed on the grid.
				printGrid();
			}
			else{
				printGame();
				break;
			}
			if(computerPenalty && !userPenalty){// when computer has a penalty and user doesn't have one, user has an extra turn to play.
							    // if both parties have penalties, then neither of them will have the extra turn.
				placeYourRocket(sc);	    
				computerPenalty = false;// in the this turn, computer's penalty is lift.
				if(gameNotOver){
					printGrid();
				}
				else{
					printGame();
					break;
				}
			}
			placeComputerRocket();
			if(gameNotOver){
				printGrid();
			}
			else{
				printGame();
				break;
			}
			if(userPenalty && !computerPenalty){// when user has a penalty and computer doesn't have one, computer has an extra turn to play.
							    // if both parties have penalties, then neither of them will have the extra turn.
				placeComputerRocket();
				userPenalty = false;// in the this turn, user's penalty is lift.
				if(gameNotOver){
					printGrid();
				}
				else{
					printGame();
					break;
				}	
			}
		}
	}
	


}

