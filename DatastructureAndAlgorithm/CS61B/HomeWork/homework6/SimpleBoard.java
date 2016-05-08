package homework6;

/* SimpleBoard.java */

/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
  private final static int DIMENSION = 8;
  private int[][] grid;

  /**
   *  Invariants:  
   *  (1) grid.length == DIMENSION.
   *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
   *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
   **/

  /**
   *  Construct a new board in which all cells are zero.
   */

  public SimpleBoard() {
    grid = new int[DIMENSION][DIMENSION];
  }

  /**
   *  Set the cell (x, y) in the board to the given value mod 3.
   *  @param value to which the element should be set (normally 0, 1, or 2).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   **/

  public void setElementAt(int x, int y, int value) {
    grid[x][y] = value % 3;
    if (grid[x][y] < 0) {
      grid[x][y] = grid[x][y] + 3;
    }
  }

  /**
   *  Get the valued stored in cell (x, y).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @return the stored value (between 0 and 2).
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   */

  public int elementAt(int x, int y) {
    return grid[x][y];
  }

  /**
   *  Returns true if "this" SimpleBoard and "board" have identical values in
   *    every cell.
   *  @param board is the second SimpleBoard.
   *  @return true if the boards are equal, false otherwise.
   */

  public boolean equals(Object board) {
    // Replace the following line with your solution.  Be sure to return false
    //   (rather than throwing a ClassCastException) if "board" is not
    //   a SimpleBoard.
	  try{
		  if(DIMENSION != ((SimpleBoard)board).grid.length){
			  return false;
		  }
		  else{
			  for(int row = 0; row < DIMENSION; row ++){
				  for(int col = 0; col < DIMENSION; col ++){
					  if(elementAt(row, col) != ((SimpleBoard)board).elementAt(row, col)){
						  return false;
					  }
				  }
			  }
		  }
	  }
	  catch(ClassCastException e){
		  return false;
	  }
    return true;
  }

  /**
   *  Returns a hash code for this SimpleBoard.
   *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
   */

  public int hashCode() {
    // Replace the following line with your solution.
	  long hashcode = 0;
	  int cnt = 0;
	  for(int row = DIMENSION - 1; row >= 0; row --){
		  for(int col = DIMENSION - 1; col >= 0; col --){
			  int value = elementAt(row, col);
			  hashcode += (int)(value * Math.pow(3, cnt));
			  cnt ++;
		  }
	  }
    return (int)hashcode;
  }
  
  public void printSelf(){
	  for(int row = 0; row < DIMENSION; row ++){
		  System.out.print("[");
		  for(int col = 0; col < DIMENSION; col ++){
			  System.out.print(" " + elementAt(row, col) + " ");
		  }
		  System.out.println("]");
	  }
  }

}
