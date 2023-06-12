import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hippodrome.class);

    private static List<Horse> horses ;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            throw new IllegalArgumentException("Horses cannot be null.");

        } else if (horses.isEmpty()) {
            throw new IllegalArgumentException("Horses cannot be empty.");

        }
        LOGGER.info("Создание Hippodrome, лошадей [7]");

        this.horses = horses;

    }


    public static List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
