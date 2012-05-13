package net.hi117.singularity.events;

import net.hi117.singularity.Game;
import net.hi117.singularity.AbstractEvent;

import java.io.IOException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class UserInputEvent extends AbstractEvent {

	UserInputEvent(final long time, final Game game) {
		super(time, 1, game);
	}

	@Override
	public void trigger() {
		System.out.print("Do you want to increase your value? [y/N] ");
		final String cmd;
		try {
			cmd = myGame.getUserInput().trim();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		if ("y".equalsIgnoreCase(cmd)) {
			System.out.println(
					"You increased the value! It is now " +
							myGame.increaseValue()
			);
		} else {
			System.out.println("You chose not to increase the value. Ok.");
		}
		myGame.getTimeline().addEvent(
				new DecreaseValueEvent(
						getTime(), myGame
				)
		);
	}
}
