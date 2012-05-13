package net.hi117.singularity.events;

import net.hi117.singularity.Game;
import net.hi117.singularity.AbstractEvent;
import net.hi117.singularity.CausalityViolationException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class VictoryEvent extends AbstractEvent {

	VictoryEvent(final long time, final Game game) {
		super(time, 10, game);
	}

	@Override
	public void trigger() throws CausalityViolationException {
		System.out.println("Congratulations! You won!");
		System.out.println();
	}
}
