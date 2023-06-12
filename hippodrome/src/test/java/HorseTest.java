import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.junit.runners.Parameterized;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @Test
    public void nullNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());

    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank. ", e.getMessage());
    }
    @Test
    public void SpeedNegative(){
        IllegalArgumentException e =assertThrows(IllegalArgumentException.class, () -> new Horse(null, -1, 1 ));
        assertEquals("Speed cannot be negative.", e.getMessage());

    }
    @Test
    public  void DistanceNegative() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());

    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Horse horse = new Horse("flame", 1, 1 );

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("flame", nameValue);
    }
    @Test
    public void getSpeed(){
        double speedHorse = 156;
        Horse horse = new Horse("flame", speedHorse, 1 );

        assertEquals(speedHorse, horse.getSpeed());

    }
    @Test
    public void getDistance(){
        Horse horse =new Horse("flame", 1, 353);

        assertEquals(353, horse.getDistance());
    }
    @Test
    public void zeroDistance() {
        Horse horse = new Horse("flame", 1);

        assertEquals(0, horse.getDistance());
    }
    @Test
    void moveUsesGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("flame", 13, 432).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0 })
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic =mockStatic(Horse.class)) {
            Horse horse = new Horse("flame", 24, 451);
            mockedStatic.when(() ->Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(451 + 24 * random, horse.getDistance());
        }
    }


}
