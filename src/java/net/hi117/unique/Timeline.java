package net.hi117.unique;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * An object that encapsulates the timeline and handles the progression of
 * in-game events.
 *
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public final class Timeline implements Serializable {

	private final Queue<Event> myQueue =
			new PriorityQueue<>(16, EventComparator.INSTANCE);

	private long myCurrentTime;

	private int myCurrentPriority = Integer.MIN_VALUE;


	public Timeline() {
		// do nothing
	}


	/* Serialization */

	private Timeline(final Event[] events) {
		myQueue.addAll(Arrays.asList(events));
	}

	private static final class SerialProxy implements Serializable {
		private static final long serialVersionUID = 1L;
		private transient Event[] myEvents;

		protected SerialProxy(final Collection<Event> events) {
			myEvents = events.toArray(new Event[events.size()]);
		}

		private void writeObject(final ObjectOutputStream s)
				throws IOException {
			s.defaultWriteObject();
			s.writeInt(myEvents.length);
			for (final Event event : myEvents) {
				s.writeObject(event);
			}
		}

		private void readObject(final ObjectInputStream s)
				throws ClassNotFoundException, IOException {
			s.defaultReadObject();
			final int eventCount = s.readInt();
			final Event[] events = new Event[eventCount];
			for (int i = 0; i < eventCount; ++i) {
				events[i] = (Event) s.readObject();
			}
			myEvents = events;
		}

		private Object readResolve() {
			return new Timeline(myEvents);
		}
	}

	private void readObject(final ObjectInputStream s)
			throws InvalidObjectException {
		throw new InvalidObjectException("Proxy required!");
	}

	private Object writeReplace() {
		synchronized (myQueue) {
			return new SerialProxy(myQueue);
		}
	}


	public final boolean tick()
			throws CausalityViolationException, EventException,
			       InterruptedException {
		if (!setCurrentTime()) {
			return false;
		}
		while (true) {
			final Event event;
			synchronized (myQueue) {
				event = myQueue.peek();
				if (event == null || event.getTime() != myCurrentTime) {
					break;
				}
				myCurrentPriority = event.getPriority();
			}
			event.trigger();
			synchronized (myQueue) {
				myQueue.remove(event);
			}
		}
		return true;
	}

	public final void addEvent(final Event event)
			throws CausalityViolationException {
		synchronized (myQueue) {
			final long newTime = event.getTime();
			if (myCurrentTime > newTime) {
				throw new CausalityViolationException(event, myQueue.peek());
			}
			if (myCurrentTime == newTime &&
					event.getPriority() <= myCurrentPriority) {
				throw new CausalityViolationException(event, myQueue.peek());
			}
			myQueue.add(event);
		}
	}

	public final long getCurrentTime() {
		synchronized (myQueue) {
			return myCurrentTime;
		}
	}

	private boolean setCurrentTime() {
		synchronized (myQueue) {
			final Event peek = myQueue.peek();
			if (peek == null) {
				return false;
			}
			myCurrentTime = peek.getTime();
			return true;
		}
	}
}
