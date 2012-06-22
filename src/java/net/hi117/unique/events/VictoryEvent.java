package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.CausalityViolationException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class VictoryEvent extends AbstractEvent<Game> {

	VictoryEvent(final long time) {
		super(time, 10);
	}

	@Override
	public void trigger(final Game game) throws CausalityViolationException {
		ourGameUserInterface.endGame(true);
	}
}
