package net.hi117.unique;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * An object that encapsulates the timeline and handles the progression of
 * in-game events.
 *
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
public final class Timeline<G> implements Serializable {

	private final Queue<Event<G>> myQueue =
			new PriorityQueue<>(16, EventComparator.INSTANCE);

	private long myCurrentTime;

	private int myCurrentPriority = Integer.MIN_VALUE;


	public Timeline() {
		// nothing to do
	}


	/* Serialization */

	private Timeline(final Collection<Event<G>> events) {
		myQueue.addAll(events);
	}

	private static final class SerialProxy<G> implements Serializable {
		private static final long serialVersionUID = 1L;
		private transient List<Event<G>> myEvents;

		protected SerialProxy(final Collection<Event<G>> events) {
			myEvents = new ArrayList<>(events);
		}

		private void writeObject(final ObjectOutputStream s)
				throws IOException {
			s.defaultWriteObject();
			s.writeInt(myEvents.size());
			for (final Event<G> event : myEvents) {
				s.writeObject(event);
			}
		}

		private void readObject(final ObjectInputStream s)
				throws ClassNotFoundException, IOException {
			s.defaultReadObject();
			final int eventCount = s.readInt();
			final List<Event<G>> events = new ArrayList<>(eventCount);
			for (int i = 0; i < eventCount; ++i) {
				// this may throw java.lang.ClassCastException
				// if the game save is compromised
				@SuppressWarnings("unchecked")
				final Event<G> event = (Event<G>) s.readObject();
				events.add(event);
			}
			myEvents = events;
		}

		private Object readResolve() {
			return new Timeline<>(myEvents);
		}
	}

	private void readObject(final ObjectInputStream s)
			throws InvalidObjectException {
		throw new InvalidObjectException("Proxy required!");
	}

	private Object writeReplace() {
		synchronized (myQueue) {
			return new SerialProxy<>(myQueue);
		}
	}


	public final boolean tick(final G game)
			throws CausalityViolationException, EventException,
			       InterruptedException {
		if (!setCurrentTime()) {
			return false;
		}
		while (true) {
			final Event<G> event;
			synchronized (myQueue) {
				event = myQueue.peek();
				if (event == null || event.getTime() != myCurrentTime) {
					break;
				}
				myCurrentPriority = event.getPriority();
			}
			event.trigger(game);
			synchronized (myQueue) {
				myQueue.remove(event);
			}
		}
		return true;
	}

	public final void addEvent(final Event<G> event)
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
			final Event<G> peek = myQueue.peek();
			if (peek == null) {
				return false;
			}
			myCurrentTime = peek.getTime();
			return true;
		}
	}
}
