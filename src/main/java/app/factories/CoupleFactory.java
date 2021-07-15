package app.factories;

import app.entities.Couple;

public final class CoupleFactory {

    public CoupleFactory() {
    }

    public static Couple execute(long firstEmplId, long secondEmplId, long overlapDuration) {
        return new Couple(
                firstEmplId,
                secondEmplId,
                overlapDuration);
    }
}
