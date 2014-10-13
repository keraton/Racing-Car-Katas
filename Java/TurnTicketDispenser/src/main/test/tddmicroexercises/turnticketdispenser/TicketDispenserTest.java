package tddmicroexercises.turnticketdispenser;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tddmicroexercises.turnticketdispenser.TurnNumberSequence.setChangeableTurnNumber;

public class TicketDispenserTest  {

    private TicketDispenser ticketDispenser = new TicketDispenser();
    private final int expectedTurnNumber = 12;

    @Test
    public void should_return_the_correct_turn_number() {
        setChangeableTurnNumber(expectedTurnNumber);

        TurnTicket turnTicket = ticketDispenser.getTurnTicket();

        assertThat(turnTicket.getTurnNumber()).isEqualTo(expectedTurnNumber);
    }

}