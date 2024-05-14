package chompJava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chompJava.model.Chomp;
import chompJava.model.ChompStatus;

public class ChompTest {
    @Test
    public void Test01(){
        Chomp game = new Chomp(5,4);
        game.reset();
        assertEquals(ChompStatus.ONGOING, game.getStatus());
        assertEquals(0,game.getChomped());
        //assertEquals()
        
    }
}
