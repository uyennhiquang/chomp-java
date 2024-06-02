package chompJava.model;
//import java.util.Arrays;

public class Chomp {
  public static final int CHOMPED = 1;
  public static final int NOT_CHOMPED = 0;
  public static final String CHOMPEDCOLOR = AsciiColorCodes.RED;
  public static final String NOT_CHOMPEDCOLOR = AsciiColorCodes.GREEN;
  public static final int DEFAULT_ROWS = 8;
  public static final int DEFAULT_COLS = 6;
  public static final int PLAYER_ONE = 0;
  public static final int PLAYER_TWO = 1;
  

  private final int rows;
  private final int cols;
  private final int[][] board;
  private final int player1 = Chomp.PLAYER_ONE;
  //private final int player2 = 1;
  private int player; // 0 = p1, 1 = p2
  private int chomped;
  private ChompStatus status;

  //this was just for testing
  public static void main(String[] args){
    Chomp game = new Chomp(10, 10);
    int rowStart = 0;
    int colStart = 0;
    try {
      game.chomp(rowStart, colStart);
    } catch (ChompException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(game.toString());
  }

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
    this.player = Chomp.PLAYER_ONE; // this is assuming p1 always goes first
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

  public int getCurrentPlayer() {
    return this.player;
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
    for (int r = row; r<this.rows ;r++){
      for (int c = col; c<this.cols ;c++) { // while the index is still at the chomped column or to the right
        if (board[r][c] == Chomp.NOT_CHOMPED) {
          board[r][c] = Chomp.CHOMPED; // chomps all rows to the right of "col"
          chomped++; // updates chomped
        }
        else{
          break;
        }
      }
    }

    //updates the player
    this.player = this.player == Chomp.PLAYER_ONE ? Chomp.PLAYER_TWO : Chomp.PLAYER_ONE;

    if (board[0][0] == CHOMPED) { // check if there is loser
      this.status = this.player == Chomp.PLAYER_ONE ? ChompStatus.TWO_WINS : ChompStatus.ONE_WINS;
    }
    
    return this.status; // automatically will check from here if the poison piece is eaten
  }

  @Override
  public String toString() {
    String boardString = "\n" + " " + AsciiColorCodes.RESET;
    for(int k=0; k<board[0].length;k++){
      boardString+=" " + k;
    }
    for(int i =0; i<rows;i++){
      boardString += "\n" + i + " ";
      for(int j = 0;j<cols;j++){
        if(board[i][j]==Chomp.NOT_CHOMPED){
          boardString += NOT_CHOMPEDCOLOR + (board[i][j] + " ") + AsciiColorCodes.RESET;
        }
        else{
          boardString += CHOMPEDCOLOR + (board[i][j] + " ") + AsciiColorCodes.RESET;
        }
      }
    }
    return " Board: " + boardString + "\n Current player: " + getCurrentPlayer() + ", Game Status: " + getStatus();
  }

  private void validateLocation(int row, int col) throws ChompException {
    // if the row and col fall out of the 2d grid
    if (row >= this.rows || col >= this.cols) {
      throw new ChompException(String.format("This location (%d, %d) is not on the board", row, col));
    }
  }
}
