package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {

    public boolean isEdible(Supplier<LocalDate> now) {
        if (!approvedForConsumption) return false;
        if (inspectorId == null) return false;

        return isFresh(now);
    }

    private boolean isFresh(Supplier<LocalDate> now) {
        return now.get().isBefore(expirationDate);
    }
}