package chompJava.view;

import java.util.Scanner;

import chompJava.model.Chomp;

public class ChompCLI {

  public static void playChomp(Chomp chomp, String p1Name, String p2Name) {
    throw new UnsupportedOperationException();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Chomp chomp = new Chomp(5, 5);
    System.out.print("Enter the name of player 1: ");
    String p1Name = scanner.nextLine();
    System.out.print("Enter the name of player 2: ");
    String p2Name = scanner.nextLine();

    scanner.close();

    playChomp(chomp, p1Name, p2Name);
  }
  
}
