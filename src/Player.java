import java.io.IOException;
import java.util.Scanner;
/**
 * Player is the main character of the Game. The only class that user can move it's character around the board.
 * 
 * @author Hongli Zhang
 */
public class Player extends Mobile{
	/**Takes the user's input*/
	private Scanner input;
	
	/**Store the delay time when player steps on a trap*/
	private long delayTime;
	
	/**
	 * Constructor for the Player.
	 * 
	 * @param board (it takes input of the board)
	 */
	public Player(Board board) {
		super(board);
		delayTime = 0;
		input = new Scanner(System.in);
	}

	/**
	 * This method moves Player around on board.
	 */
	protected void move() {
		String pos;
		
		delay();
		System.out.print(">>");
		pos = input.nextLine();
		
		if(pos.toLowerCase().equals("q")) { //up left
			board.move(Direction.UP_LEFT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("w")) { //up
			board.move(Direction.UP, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("e")) { //up right
			board.move(Direction.UP_RIGHT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("a")) { //left
			board.move(Direction.LEFT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("d")) { //right
			board.move(Direction.RIGHT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("z")) { //down left
			board.move(Direction.DOWN_LEFT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("x")) { //down
			board.move(Direction.DOWN, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("c")) { //down right
			board.move(Direction.DOWN_RIGHT, Player.this);
			board.printBoard();
		}else if (pos.toLowerCase().equals("s")) { //print board
			board.printBoard();
		}else { 
			System.out.println("Try Again");
		}
	}
	
	/**
	 * This is the run methods to run the program in Multi-Threaded.
	 */
	@Override
	public void run() {
		board.printBoard();
		while(!board.beenHugged()) {
			move();
		}
		System.out.println("Here is last position for Jarvis. WINNER WINNER CHICKEN DINNER!");
		input.close();
	}
	
	/**
	 * This method pause the player's movement when they're step on the trap.
	 */
	private void delay() {
		try {
			if(delayTime != 0) {
				System.out.println("Trap have been set off. You have to wait "+delayTime/1000+ " seconds");
				Thread.sleep(delayTime);
				while(System.in.available() > 0) {
					System.in.read();
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.delayTime = 0;
	}
	
	/**
	 * This method sets the time to pause player's movement.
	 * 
	 * @param time
	 */
	public void setDelay(long time) {
		this.delayTime = time;
	}
	
	/**
	 * This method checks if Homework Trap can share with another element in same cell.
	 * 
	 * @param elem (Takes in Boardable element)
	 * @return True if a element can share with other element. False it can't share.
	 */
	public boolean share(Boardable elem) {
		return false;
	}

	/**
	 * This method checks if the element is visible. Since Player is visible. It should be true.
	 * 
	 * @return True when its visible. False when it's not
	 */
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * This method returns element's string. To use it to show it's character on board.
	 * 
	 * @return element's string. Player string should be *
	 */
	public String toString() {
		return "*";
	}
}