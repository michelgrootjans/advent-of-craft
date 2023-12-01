import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import food.Food;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

class EdibleTests {
    private static final LocalDate expirationDate = LocalDate.now();
    private static final UUID inspector = randomUUID();

    @Test
    void edible_before_expiration_date() {
        var food = new Food(expirationDate, true, inspector);
        assertThat(food.isEdible(() -> expirationDate.minusDays(1))).isTrue();
    }

    @Test
    void inedible_on_expiration_date() {
        var food = new Food(expirationDate, true, inspector);
        assertThat(food.isEdible(() -> expirationDate)).isFalse();
    }

    @Test
    void inedible_after_expiration_date() {
        var food = new Food(expirationDate, true, inspector);
        assertThat(food.isEdible(() -> expirationDate.plusDays(1))).isFalse();
    }

    @Test
    void inedible_when_not_approved_for_consumption() {
        var food = new Food(expirationDate, false, inspector);
        assertThat(food.isEdible(() -> expirationDate.minusDays(1))).isFalse();
    }

    @Test
    void inedible_without_inspector() {
        var food = new Food(expirationDate, true, null);
        assertThat(food.isEdible(() -> expirationDate.minusDays(1))).isFalse();
    }
}
