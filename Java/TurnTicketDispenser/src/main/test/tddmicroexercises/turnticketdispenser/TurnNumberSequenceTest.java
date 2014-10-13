package tddmicroexercises.turnticketdispenser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tddmicroexercises.turnticketdispenser.TurnNumberSequence.getNextTurnNumber;
import static tddmicroexercises.turnticketdispenser.TurnNumberSequence.reset;
import static tddmicroexercises.turnticketdispenser.TurnNumberSequence.setChangeableTurnNumber;

public class TurnNumberSequenceTest {

    private static final int NEW_TURN_NUMBER = 12;
    private static final int IMPOSSIBLE_TURN_NUMBER = -1;

    @Test
    public void should_change_the_original_turn_number() {
        setChangeableTurnNumber(NEW_TURN_NUMBER);

        int newTurnNumber = getNextTurnNumber();

        assertThat(newTurnNumber).isEqualTo(NEW_TURN_NUMBER);
    }

    @Test
    public void should_reset_to_the_original_turn_number() {
        setChangeableTurnNumber(IMPOSSIBLE_TURN_NUMBER);

        reset();

        assertThat(getNextTurnNumber()).isNotEqualTo(IMPOSSIBLE_TURN_NUMBER);

    }

}