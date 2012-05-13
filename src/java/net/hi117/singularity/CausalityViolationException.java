package net.hi117.singularity;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class CausalityViolationException extends RuntimeException {

	public CausalityViolationException(
			final Event newEvent,
			final Event oldEvent
	) {
		super(String.format(
				"Event %s attempted to cause event %s",
				oldEvent,
				newEvent
		));
	}
}
