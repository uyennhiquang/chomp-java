package chompJava.model;

//import java.util.Arrays;

public class Chomp {
  public static final int CHOMPED = 1;
  public static final int NOT_CHOMPED = 0;
  public static final int DEFAULT_ROWS = 8;
  public static final int DEFAULT_COLS = 6;

  private final int rows;
  private final int cols;
  private final int[][] board;
  private int player; // 0 = p1, 1 = p2
  private int chomped;
  private ChompStatus status;

  // int [0, 1, 2, 3, n]
  // int[][1]
  // int[][2]

  /**
   * Creates a game of chomp. Any user interface who wishes to play this game must implement their own play() method.
   * Chomp is a grid-based 2-player combinatorial game where the objective is to make the other player eat the poisoned piece (in this iteration it will always be the top left tile). Players take turn "chomp" a tile on the board -- when a tile is chomped, all the tiles to the right and bottom of that piece will also be chomped, forming a filled mini-rectangle. 
   * The board size is arbitrary. It is up to the programmer's discretion to handle any errors that might rise from the user of this method inputting illegal values of rows and cols.
   * @param rows
   * @param cols
   */
  public Chomp(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new int[this.rows][this.cols];
    reset();
  }

  /**
   * A safe bare-bones constructor.
   */
  public Chomp() {
    this(Chomp.DEFAULT_ROWS, Chomp.DEFAULT_COLS);
  }

  /**
   * A specific constructor that specifies the configuration of a Chomp game.
   * @param rows
   * @param cols
   * @param status
   * @param chomped
   */
  public Chomp(int rows, int cols, ChompStatus status, int chomped) {
    this.rows = rows;
    this.cols = cols;
    this.board = new int[this.rows][this.cols];
    this.status = status;
    this.chomped = chomped;
    this.player = 0;
  }

  /**
   * A method that resets the current version of Chomp.
   */
  public void reset() {
    this.chomped = 0;
    this.status = ChompStatus.ONGOING;
    this.player = 0; // this is assuming p1 always goes first
    for (int row = 0; row < this.rows; row++) { // resets the entire board to not chomped again
      for (int col = 0; col < this.cols; col++) {
        board[row][col] = Chomp.NOT_CHOMPED;
      }
    }
  }
  
  public int getChomped() { // this is more to test
    return this.chomped;
  }

  public ChompStatus getStatus() {
    return this.status;
  }

  public int[][] getBoard() {
    return this.board;
  }

  public String getCurrentPlayer() {
    if (player == 0) {
      return "player 1"; // do you want to return it as "player 1" or just "p1"
    } else {
      return "player 2";
    }
  }

  /**
   * When a tile is chomped, all the tiles to the right and bottom of that piece will also be chomped, forming a filled mini-rectangle. 
   * @param row
   * @param col
   * @return
   * @throws ChompException
   */
  public ChompStatus chomp(int row, int col) throws ChompException {
    validateLocation(row, col);
    //the entire column right of col is chomped in row, then moves on to the next row all the way until this.rows
    for(int r = row; r<this.rows;r++){
      for(int c = col; c<this.cols;c++) { // while the index is still at the chomped column or to the right
        if (board[r][c] == Chomp.NOT_CHOMPED) {
          board[r][c] = Chomp.CHOMPED; // chomps all rows to the right of "col"
          chomped++; // updates chomped
        }
      }
    }
    //updates the player
    if(getCurrentPlayer().equals("player 1")){
      player = 1;
    }
    else{
      player = 0;
    }
    if (board[0][0] == CHOMPED) { // check if there is loser
      if (getCurrentPlayer() == "player 1") {
        this.status = ChompStatus.TWO_WINS;
      } else {
        this.status = ChompStatus.ONE_WINS;
      }
    }
    return getStatus(); // automatically will check from here if the poison piece is eaten
  }

  @Override
  public String toString() {
    return "board: " + board + ", current player: " + getCurrentPlayer() + ", game status: " + getStatus();
  }

  private void validateLocation(int row, int col) throws ChompException {
    // if the row and col fall out of the 2d grid
    if (row >= this.rows || col >= this.cols) {
      throw new ChompException(String.format("This location (%d, %d) is not on the board", row, col));
    }
  }
}
