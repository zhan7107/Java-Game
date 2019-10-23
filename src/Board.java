import java.util.ArrayList;
import java.util.HashMap;
/**
 * Board class is responsible making the board and printing the board. It shows the sketches that are made.
 * 
 * <p>Bugs: No bugs found yet.
 * 
 * @author Hongli Zhang
 */
public class Board {
	/**
	 * Cell is a nested class in Board. It is used to create the individual cells of the grid.
	 * 
	 * @author Hongli Zhang
	 */
	private class Cell { //Done
		/**Position of the Cell. Row location*/
		private int row;
		
		/**Position of the Cell. Column location*/
		private int col;
		
		/**To check if the cell is visible or not. Default set is false (not visible)*/
		private boolean isVisible;
		
		/**Store a elements of it's toString*/
		private ArrayList<Boardable> elements;	
		
		/**
		 * This is the constructor for the cell. Generating Cell's locations on the board.
		 * 
		 * @param row (row position for the cell)
		 * @param col (column position for the cell)
		 */
		public Cell(int row, int col) {
			elements  = new ArrayList<Boardable>();
			isVisible = false;
			this.row  = row;
			this.col  = col;
		}
		
		/**
		 * This method is adding Boardable element in to a cell.
		 * 
		 * @param elem (takes in the Boardable class)
		 * @return True when it can be place in this cell. False if it contains it already.
		 */
		public boolean addElement(Boardable elem) { //EDIT!!!
			if(elements.isEmpty()) {
				elements.add(elem);
				if(elem.isVisible()) {
					this.isVisible = elem.isVisible();
				}
				return true;
			}
			if(elements.get(elements.size()-1).share(elem)) {
				elements.add(elem);
				if(elem.isVisible()) {
					this.isVisible = elem.isVisible();
				}
				return true;
			}
			return false;
		}
		
		/**
		 * This method removes the element if it contains that element and returns True. Otherwise it will return false when it dosen't not contain.
		 * 
		 * @param elem (element that's related to Boardable)
		 * @return True if it contains that element. False when it's not there.
		 */
		public boolean removeElement(Boardable elem) {
			if(elements.contains(elem)) {
				elements.remove(elements.indexOf(elem));
				return true;
			}else {
				return false;
			}
		}
		
		/**
		 * This method shows the toString of the element.
		 */
		public String toString() {
			if(!isVisible) { //If it's not visible. It returns #. If it conctains a elem but it's invisible it return #.
				return "#";
			}else if(isVisible && elements.isEmpty()) { //If it's visible and it dosen't contain a element. It return " "
				return " ";
			}else {
				return elements.get(elements.size()-1).toString(); //It returns last elem in the array. Need a if statement if there is more elements.
			}
		}
	}
	
	/**Store Cell class in a 2 dimensional array*/
	private Cell[][] board;
	
	/**Store Height of the board*/
	private int height;
	
	/**Store Width of the board*/
	private int width;
	
	/**Map of the location of the element*/
	private HashMap<Boardable,Cell> elementPlace;
	
	/**This check that if Jarvis is hugged or not. It would return true if he got hugged. False if it's not hugged*/
	private boolean hugged;
	
	/**
	 * This is the board constructor. Creating the board and generating size of cell it needs.
	 * 
	 * @param height (height of the board)
	 * @param width (width of the board)
	 */
	public Board(int height, int width) { //Error Checking on height and width
		hugged = false; //Default is false. When Jarvis is not hugged.
		
		if(1 <= height && 100 >= height && 1 <= width && 100 >= width) { //Prints the board
			elementPlace = new HashMap<Boardable,Cell>();
			this.height  = height;
			this.width   = width;
			this.board   = new Cell[height][width];
		
			for(int i = 0; i<height; i++) {//Generating cells
				for(int j = 0; j < width; j++) {
					board[i][j] = new Cell(i,j);
				}
			}
		}else{
			throw new IllegalArgumentException("width and/or height that is not in the range [1-100]. Please try again");
		}
	}
	
	/**
	 * This method is use to move the Element of the board.
	 * 
	 * @param dir (Direction that a element will move)
	 * @param elem (Element of Boardable)
	 * @return (True when the board is able to move. False when not able to.)
	 */
	synchronized public boolean move(Direction dir, Boardable elem) {
		if(!elementPlace.containsKey(elem)) {
			throw new IllegalArgumentException("Does not contain a element.");
		}
		
		Cell curC;
		int curRow;
		int curCol;
		int moveR;
		int moveC;
		
		curC   = elementPlace.get(elem);
		curRow = curC.row;
		curCol = curC.col;
		moveR  = curRow;
		moveC  = curCol;
		
		if(dir == Direction.UP_LEFT) { //(-1,-1)
			moveR = moveR - 1;
			moveC = moveC - 1;
		}
		if(dir == Direction.UP) { //(-1,0)
			moveR = moveR - 1;	
		}
		if(dir == Direction.UP_RIGHT) { //(-1,+1)
			moveR = moveR - 1;
			moveC = moveC + 1;
		}
		if(dir == Direction.LEFT) { //(0,-1)
			moveC = moveC - 1;	
		}
		if(dir == Direction.RIGHT) { //(0,+1)
			moveC = moveC + 1;
		}
		if(dir == Direction.DOWN_LEFT) { //(+1,-1)
			moveR = moveR + 1;
			moveC = moveC - 1;
		}
		if(dir == Direction.DOWN) { //(+1,0)
			moveR = moveR + 1;
		}
		if(dir == Direction.DOWN_RIGHT) { //(+1,+1)
			moveR = moveR + 1;
			moveC = moveC + 1;
		}
		if(moveR >= 0 && moveC >= 0 && moveR <= (height-1) && moveC <= (width-1) && board[moveR][moveC].addElement(elem)) {
			curC.removeElement(elem);
			elementPlace.replace(elem, board[moveR][moveC]);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * This method is use for placing the element to the board.
	 * 
	 * @param elem (A element)
	 * @param row (row position)
	 * @param col (column position)
	 * @return (true if element is placed else it will throw error.)
	 */
	synchronized public boolean placeElement(Boardable elem, int row, int col) { //DONE
		if(row >= 0 && col >= 0 && row <= height-1 && col <= width-1) {
			if(board[row][col].addElement(elem)) {
				elementPlace.put(elem, board[row][col]);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method removes the element on the board.
	 * 
	 * @param elem (takes in a Boardable element)
	 * @return True when it contains element and it removes it for you. False when there is no element.
	 */
	synchronized public boolean removeElement(Boardable elem) { //Done
		if(elementPlace.containsKey(elem)) {
			elementPlace.get(elem).removeElement(elem);
			elementPlace.remove(elem);
			return true;
		}
		return false;
	}
	
	/**
	 * This method is a getter method for getting row of a element
	 * 
	 * @param elem (takes in a Boardable element)
	 * @return row integer location of the element
	 */
	public int getRow(Boardable elem) { //Done
		if(elementPlace.containsKey(elem)) {
			return elementPlace.get(elem).row;
		}else {
			throw new IllegalArgumentException("element was not on the board");
		}
	}
	
	/**
	 * This method is a getter method for getting column of a element
	 * 
	 * @param elem (takes in a Boardable element)
	 * @return column integer location of the element
	 */
	public int getColumn(Boardable elem) { //Done
		if(elementPlace.containsKey(elem)) {
			return elementPlace.get(elem).col;
		}else {
			throw new IllegalArgumentException("element was not on the board");
		}
	}
	
	/**
	 * This method is use for to print the board out in a grid with contains it's cell.
	 */
	synchronized public void printBoard() { //Only responsive for grid. Only prints cells. Sync?
		for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
            	System.out.print(board[i][j]);
            }
            System.out.println();
		}
	}
	
	/**
	 * This method sets Hugged to True when Jarvis been hugged.
	 * 
	 * @param hugged (Takes in true or false for hugged)
	 */
	public void setHugged(boolean hugged) {
		this.hugged = hugged;
	}
	
	/**
	 * This checks if Jarvis is been hugged or not.
	 * 
	 * @return True if Jarvis is hugged. False if Jarvis is not hugged
	 */
	synchronized public boolean beenHugged() { //if it's true then the game is over.
		return this.hugged;
	}
}
