
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static  java.util.Objects.isNull;


public class HippodromeTest {

    @Test
    public void nullIllegalArgumentExceptionInConstructor(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome((null)));
        assertEquals("Horses cannot be null.", e.getMessage());

    }

    @Test
    public  void emptyHorsesException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }



    @Test
    public void getHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30 ; i++) {
            horses.add(new Horse("" +i, i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());

    }

  /*  @Test
    public void move(){
        ArrayList<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50 ; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

          assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }*/


    @Test
    public void getWinner(){
        Horse horse1 = new Horse("nyrzik1",1,1);
        Horse horse2 = new Horse("nyrzik2",1,5);
        Horse horse3 = new Horse("nyrzik3",1,2);
        Horse horse4 = new Horse("nyrzik4",1,3);
        Horse horse5 = new Horse("nyrzik5",1,4);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4,horse5));

        assertSame(horse2,hippodrome.getWinner());
    }
}
