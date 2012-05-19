package net.hi117.unique;

import java.io.Serializable;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public interface Event extends Serializable {
	long getTime();

	int getPriority();

	void trigger() throws CausalityViolationException, EventException,
	                      InterruptedException;
}
