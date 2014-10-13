package tddmicroexercises.turnticketdispenser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTicketTest {

    @Test
    public void should_return_original_turn_number() {
        final int originalTurnNumber = 12;

        TurnTicket turnTicket = new TurnTicket(originalTurnNumber);

        assertThat(turnTicket.getTurnNumber()).isEqualTo(originalTurnNumber);
    }


}