package net.hi117.singularity.events;

import net.hi117.singularity.Game;
import net.hi117.singularity.AbstractEvent;
import net.hi117.singularity.CausalityViolationException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class DefeatEvent extends AbstractEvent {

	DefeatEvent(final long time, final Game game) {
		super(time, 10, game);
	}

	@Override
	public void trigger() throws CausalityViolationException {
		System.out.println("Oh snap! Your value is too low!");
		System.out.println("YOU HAVE LOST THE GAME!");
		System.out.println();
	}

}
