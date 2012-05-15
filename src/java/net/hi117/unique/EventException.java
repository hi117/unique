package net.hi117.unique;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public class EventException extends Exception {
	public EventException(final Event event, final Exception exception) {
		super(String.format("Exception in event %s", event), exception);
	}
}
