package chompJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chompJava.model.Chomp;
import chompJava.model.ChompException;
import chompJava.model.ChompStatus;

public class ChompTest {
    @Test
    public void resetTest01(){
        Chomp game = new Chomp(5,4, ChompStatus.ONE_WINS,43, 1);
        game.reset();
        assertEquals(ChompStatus.ONGOING, game.getStatus());
        assertEquals(0,game.getChomped());
        assertEquals("player 1", game.getCurrentPlayer());
    }

    @Test
    public void chompTest02() throws ChompException{
        Chomp game = new Chomp(5,4);
        try {
            game.chomp(3,2);
        } catch (ChompException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(3, game.getChomped());
        assertEquals("player 2", game.getCurrentPlayer());
        assertEquals(ChompStatus.ONGOING, game.getStatus());
        assertEquals(ChompStatus.TWO_WINS, game.chomp(0,0));
        game.reset();
        //assertEquals("chompJava.model.ChompException: This location (6, 6) is not on the board", game.chomp(6,6));
    }
}
