import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

        assertEquals("Name cannot be null.", e.getMessage());
    }

    /*    @Test
        public void numberSecondIsNegative() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, -1, 1));

            assertEquals("Speed cannot be negative.", e.getMessage());
        }*/
   /* @Test
    public void numberSecondIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, -1, 1));

    }

    @Test
    public void numberIsSecondIsNegative() {
        try {
            new Horse(null, -1, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void numberThirdIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, -1));

    }

    @Test
    public void numbIsNegativel() {
        try {
            new Horse(null, 1, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }*/

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n\n\n\n"})
    public void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("asdf", 1, 1);


        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("asdf", nameValue);

    }

    @Test
    public void getSpeed() {
        double expectedSpeed = 443;
        Horse horse = new Horse("asdf", expectedSpeed, 1);

        assertEquals(expectedSpeed, horse.getSpeed());

    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("asdf", 1, 354);

        assertEquals(354, horse.getDistance());
    }

    @Test
    public void zeroDstanceByDedault() {
        Horse horse = new Horse("asdf", 1);

        assertEquals(0, horse.getDistance());
    }


    @Test
    void moveUsesGetRandon() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("asdf", 48, 120).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0, 0})
    void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("asdf", 48, 120);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(120 + 48 * random, horse.getDistance());

        }
    }
}

