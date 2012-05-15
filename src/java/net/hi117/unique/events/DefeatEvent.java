package net.hi117.unique.events;

import net.hi117.unique.Game;
import net.hi117.unique.AbstractEvent;
import net.hi117.unique.CausalityViolationException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class DefeatEvent extends AbstractEvent {

	DefeatEvent(final long time, final Game game) {
		super(time, 10, game);
	}

	@Override
	public void trigger() throws CausalityViolationException {
		ourGameUserInterface.endGame(false);
	}

}
