package tddmicroexercises.turnticketdispenser;

import org.assertj.core.util.VisibleForTesting;

public class TurnNumberSequence
{
    private static int _turnNumber = 0;
    private static Integer changeableTurnNumber;

    public static int getNextTurnNumber()
    {
        if (changeableTurnNumber != null)
              return changeableTurnNumber;
        return _turnNumber++;
    }

    @VisibleForTesting
    static void setChangeableTurnNumber(int newTurnNumber) {
        changeableTurnNumber = newTurnNumber;
    }

    public static void reset() {
        changeableTurnNumber = null;
    }
}
