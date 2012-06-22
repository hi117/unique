package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.CausalityViolationException;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class DecreaseValueEvent extends AbstractEvent<Game> {

	DecreaseValueEvent(final long time) {
		super(time, 3);
	}

	@Override
	public void trigger(final Game game)
			throws CausalityViolationException, EventException {
		game.decreaseValue();
		ourGameUserInterface.updateValue();

		game.getTimeline().addEvent(new TurnStartEvent(getTime() + 1));
	}

}
