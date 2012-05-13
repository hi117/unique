package net.hi117.singularity.events;

import net.hi117.singularity.AbstractEvent;
import net.hi117.singularity.Game;
import net.hi117.singularity.CausalityViolationException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class DecreaseValueEvent extends AbstractEvent {

	DecreaseValueEvent(final long time, final Game game) {
		super(time, 2, game);
	}

	@Override
	public void trigger() throws CausalityViolationException {
		System.out.println("It is now end of turn, " +
				"and all values in town are being decreased!");
		System.out.println("Your value has become " + myGame.decreaseValue());
		myGame.getTimeline().addEvent(new TurnStartEvent(
				getTime() + 1,
				myGame
		));
		System.out.println();
	}

}
