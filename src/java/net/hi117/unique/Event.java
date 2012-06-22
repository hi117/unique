package net.hi117.unique;

import java.io.Serializable;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public interface Event<G> extends Serializable {
	long getTime();

	int getPriority();

	void trigger(final G game)
			throws CausalityViolationException, EventException,
			       InterruptedException;
}
