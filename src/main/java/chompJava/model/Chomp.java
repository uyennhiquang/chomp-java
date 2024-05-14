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

  public void reset() {
    chomped = 0;
    status = ChompStatus.ONGOING;
    //reset board size??
    throw new UnsupportedOperationException();
  }

  public ChompStatus getStatus() {
    if(isLoser()==true){
      if(player == 0){
        return ChompStatus.ONE_WINS;
      }
      else{
        return ChompStatus.TWO_WINS;
      }
    }
    else{
      return ChompStatus.ONGOING;
    }
    //throw new UnsupportedOperationException();
  }

  public boolean isLoser() {
    if(board[0][0]==1){
      return true;
    }
    else{
      return false;
    }
    //throw new UnsupportedOperationException();
  }

  public String getCurrentPlayer() {
    if(player == 0){
      return "player 1";
    }
    else{
      return "player 2";
    }
    //throw new UnsupportedOperationException();
  }

  public ChompStatus chomp(int row, int col) {
    validateLocation(row, col);
    int rowindex = this.rows;
    while(rowindex>=rows){
      if(board[rowindex][col]==0){
        board[rowindex][col] = 1;
        chomped++;
      }
      rowindex--;
    }
    int colindex = this.cols;
    while(colindex>=cols){
      if(board[row][colindex]==0){
        board[row][colindex]=1;
        chomped++;
      }
      colindex--;
    }
    return getStatus();
  }

  @Override
  public String toString() {
    String currentplayer="";
    if(player ==0){
      currentplayer = "p1";
    }
    else{
      currentplayer = "p2";
    }
    return "board: " + board + ", current player: " + currentplayer + ", game status: " + status;
    //throw new UnsupportedOperationException();
  }

  private void validateLocation(int row, int col) {
    //also if the row and col fall out of the 2d grid
    if(row==0&&col==0){
      throw new UnsupportedOperationException();
  }
  }
}
