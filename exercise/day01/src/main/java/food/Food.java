package food;

import java.time.LocalDate;
import java.util.UUID;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {

    public boolean isEdible(LocalDate now) {
        if (!approvedForConsumption) return false;
        if (inspectorId == null) return false;

        return isFresh(now);
    }

    private boolean isFresh(LocalDate now) {
        return now.isBefore(expirationDate);
    }
}