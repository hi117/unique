package net.hi117.unique.events;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.CausalityViolationException;
import net.hi117.unique.EventException;
import net.hi117.unique.Game;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class DecreaseValueEvent extends AbstractEvent {

	DecreaseValueEvent(final long time, final Game game) {
		super(time, 3, game);
	}

	@Override
	public void trigger() throws CausalityViolationException, EventException {
		try {
			ourGameUserInterface.message(
					"It is now end of turn, " +
							"and all values in town are being decreased!"
			);
			ourGameUserInterface.message(
					"Your value has become " + myGame.decreaseValue()
			);
			ourGameUserInterface.message("");
		} catch (Exception e) {
			throw new EventException(this, e);
		}
		myGame.getTimeline().addEvent(new TurnStartEvent(
				getTime() + 1,
				myGame
		));
	}

}
