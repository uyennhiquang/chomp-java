package chompJava.model;

public class Chomp {
  private static final int CHOMPED = 1;
  private static final int NOT_CHOMPED = 0;

  private final int rows;
  private final int cols;
  private final int[][] board;
  private int player; // 0 = p1, 1 = p2
  private int chomped;
  private ChompStatus status;

  //int  [0, 1, 2, 3, n]
  //int[][1]
  //int[][2]
   
  public Chomp(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new int[this.rows][this.cols];
    this.player = 0;
    this.chomped = 0;
    this.status = ChompStatus.ONGOING;
  }
  public int getChomped(){ //this is more to test
    return chomped;
  }

  public void reset() {
    this.chomped = 0;
    this.status = ChompStatus.ONGOING;
    this.player = 0; //this is assuming p1 always goes first
    for(int row = 0; row< this.rows; row++){ //resets the entire board to not chomped again
      for(int col = 0; col<this.cols; col++){
        board[row][col] = Chomp.NOT_CHOMPED;
      }
    }
  }

  public ChompStatus getStatus() {
    return status;
  }

  public String getCurrentPlayer() {
    if(player == 0){
      return "player 1"; //do you want to return it as "player 1" or just "p1"
    }
    else{
      return "player 2";
    }
    //throw new UnsupportedOperationException();
  }

  public ChompStatus chomp(int row, int col) {
    validateLocation(row, col);
    int rowindex = this.rows;
    while(rowindex>=row){ //while the index is still at the chomped param row and below
      if(board[rowindex][col]==0){
        board[rowindex][col] = 1; //chomps all rows at and below "row"
        chomped++; //updates # of pieces chomped
      }
      rowindex--; //updates rowindex
    }
    int colindex = this.cols;
    while(colindex>=col){ //while the index is still at the chomped column or to the right
      if(board[row][colindex]==0){
        board[row][colindex]=1; //chomps all rows to the right of "col"
        chomped++; //updates chomped
      }
      colindex--; //updates colindex
    }
    if(board[0][0]==CHOMPED){ //check if there is loser
      if(getCurrentPlayer()=="player 1"){
        this.status = ChompStatus.TWO_WINS;
      }
      else{
        this.status = ChompStatus.ONE_WINS;
      }
    }
    return getStatus(); //automatically will check from here if the poison piece is eaten
  }

  @Override
  public String toString() {
    return "board: " + board + ", current player: " + getCurrentPlayer() + ", game status: " + getStatus();
    //throw new UnsupportedOperationException();
  }

  private void validateLocation(int row, int col) {
    //if the row and col fall out of the 2d grid
    if(row>this.rows||col>this.cols){
      throw new UnsupportedOperationException();
  }
  }
}
