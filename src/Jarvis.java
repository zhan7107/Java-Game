import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Jarvis class is a AI that moves around in board. Player need to catch Jarvis to win the game. (Makes the program stop)
 * 
 * @author Hongli Zhang
 */
public class Jarvis extends Mobile{
	/**This stores the number of moves that Jarvis made. It clears it to zero when he moves 4 times.*/
	private int moveCounter;
	
	/**
	 * This is a constructor for Jarvis. It takes in Board.
	 * 
	 * @param board (Takes in board class)
	 */
	public Jarvis(Board board) {
		super(board);
		moveCounter = 0;
	}
	
	/**
	 * This method lay homework traps when Jarvis moves every 4th steps
	 */
	private void layTrap() {
		boolean checkPlace;
		ArrayList<Point> coordinates;
		HomeworkTrap trap;
		
		coordinates = new ArrayList<Point>();
		trap        = new HomeworkTrap(board);

		coordinates.add(new Point(-1, -1));
		coordinates.add(new Point(-1, 0));
		coordinates.add(new Point(-1, 1));
		coordinates.add(new Point(0, -1));
		coordinates.add(new Point(0, 1));
		coordinates.add(new Point(1, -1));
		coordinates.add(new Point(1, 0));
		coordinates.add(new Point(1, 1));
		Collections.shuffle(coordinates);
				
		do {
			checkPlace = board.placeElement(trap, board.getRow(Jarvis.this)+((int)coordinates.get(0).getX()), board.getColumn(Jarvis.this)+((int)coordinates.get(0).getY()));
			coordinates.remove(0);
		}while(!checkPlace && !coordinates.isEmpty()); //Randomized adjacent place by Jarvis.
	}
	
	/**
	 * This method moves Jarvis around the board and Jarvis move is randomized.
	 */
	protected void move() { //Emun random value stuff		
		boolean checkMove;
		ArrayList<Integer> moveValues;
		
		moveValues = new ArrayList<Integer>();
		
		for(int index = 0; index < Direction.values().length; index++) { //Generate random enum values
			moveValues.add(index);
			Collections.shuffle(moveValues);
		}
		
		do {
			checkMove = board.move(Direction.values()[moveValues.remove(0)], Jarvis.this);
		}while(!checkMove && !moveValues.isEmpty()); //Jarvis random move
		
		if(checkMove) { //Check Jarvis if he really moved
			moveCounter++;
			if(moveCounter == 4) { //When it's 4 steps he will lay a trap
				layTrap();
				moveCounter = 0;
			}
		}
	}
	
	/**
	 * This method checks if Jarvis can share with another element in same cell.
	 * 
	 * @param elem (Takes in Boardable element)
	 * @return True if a element can share with other element. False it can't share.
	 */
	public boolean share(Boardable elem) {
		if(elem instanceof Player) {
			board.setHugged(true);
			System.out.println("You soothed the savage Jarvis. GAME OVER!");
			return true;
		}
		if(elem instanceof Mobile) {
			return true;
		}
		return false;
	}

	/**
	 * This is the run methods to run the program in Multi-Threaded.
	 */
	@Override
	public void run() {
		while(!board.beenHugged()) {
			try {
				move();
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method checks if the element is visible. Since Jarvis is invisible. It should be false.
	 * 
	 * @return True when its visible. False when it's not.
	 */
	@Override
	public boolean isVisible() {
		return false;
	}
	
	/**
	 * This method returns element's string. To use it to show it's character on board.
	 * 
	 * @return element's string. Jarvis string should be ?
	 */
	public String toString() {
		return "?";
	}
}
