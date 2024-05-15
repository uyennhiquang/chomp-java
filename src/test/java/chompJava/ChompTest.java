package chompJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chompJava.model.Chomp;
import chompJava.model.ChompStatus;

public class ChompTest {
    @Test
    public void test01(){
        Chomp game = new Chomp(5,4, ChompStatus.ONE_WINS,43, 1);
        game.reset();
        assertEquals(ChompStatus.ONGOING, game.getStatus());
        assertEquals(0,game.getChomped());
        assertEquals("player 1", game.getCurrentPlayer());
    }
    public void test02(){
        Chomp game = new Chomp(5,4);
        game.chomp(3,2);
        assertEquals(4, game.getChomped());
    }
}
