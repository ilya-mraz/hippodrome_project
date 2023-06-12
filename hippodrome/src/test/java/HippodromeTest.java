import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.configuration.IMockitoConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void emptyHorsesException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be null.", e.getMessage());

    }

    @Test
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>( )));

        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <30; i++) {
            horses.add(new Horse( "" + i, i));
        }

        Hippodrome hippodrome =new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void move() {
        List<Horse> horses =new ArrayList<>();
        for (int i = 0; i <50; i++){
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse: horses){
            verify(horse).move();

        }
    }
    @Test
    public void getWinner(){
        Horse horse1 = new Horse("flame1", 1, 2);
        Horse horse2 = new Horse("flame2", 1, 3);
        Horse horse3 = new Horse("flame3", 1, 4);
        Horse horse4 = new Horse("flame4", 1, 1);

        Hippodrome hippodrome =new Hippodrome(List.of(horse1, horse2, horse3, horse4));

        assertSame(horse3, hippodrome.getWinner());
    }

}
