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
   
  public Chomp(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.board = new int[this.rows][this.cols];
    this.player = 0;
    this.chomped = 0;
    this.status = ChompStatus.ONGOING;
  }

  public void reset() {
    throw new UnsupportedOperationException();
  }

  public ChompStatus getStatus() {
    throw new UnsupportedOperationException();
  }

  public boolean isLoser() {
    throw new UnsupportedOperationException();
  }

  public int getCurrentPlayer() {
    throw new UnsupportedOperationException();
  }

  public ChompStatus chomp(int row, int col) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException();
  }

  private void validateLocation(int row, int col) {
    throw new UnsupportedOperationException();
  }
}
