package tddmicroexercises.turnticketdispenser;

import static tddmicroexercises.turnticketdispenser.TurnNumberSequence.getNextTurnNumber;

public class TicketDispenser
{
    public TurnTicket getTurnTicket()
    {
        return new TurnTicket(getNextTurnNumber());
    }
}
