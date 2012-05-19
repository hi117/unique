package net.hi117.unique.swingui;

import net.hi117.unique.AbstractEvent;
import net.hi117.unique.Game;
import net.hi117.unique.GameUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class GameScreen extends JPanel {

	private volatile Game myGame;

	private final Callback<Boolean> myEndGameCallback;

	private final JScrollPane myConsoleScroll;

	private final JTextArea myConsole;

	private final JLabel myTimeLabel;

	private final JLabel myValueLabel;

	private final JButton myIncreaseButton;


	GameScreen(final Callback<Boolean> endGameCallback)
			throws InvocationTargetException, InterruptedException {
		super(new GridBagLayout());
		myEndGameCallback = endGameCallback;

		myConsole = createConsoleTextArea();
		myConsoleScroll = createConsoleScrollPane(myConsole);
		myTimeLabel = createTimeLabel();
		myValueLabel = createValueLabel();
		myIncreaseButton = createIncreaseButton();
		createLayout();

		addEventHandlers();

		lockInputControls();
	}

	private JScrollPane createConsoleScrollPane(final JTextArea console) {
		return new JScrollPane(console);
	}

	private JLabel createTimeLabel() {
		final JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(15f));
		return label;
	}

	private JLabel createValueLabel() {
		final JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(15f));
		return label;
	}

	private JButton createIncreaseButton() {
		final JButton button = new JButton("Increase by 5");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				myGame.setIncreaseThisTurn(true);
				lockInputControls();
			}
		});
		return button;
	}

	private JTextArea createConsoleTextArea() {
		final JTextArea jTextArea = new JTextArea();
		jTextArea.setAutoscrolls(true);
		jTextArea.setLineWrap(true);
		jTextArea.setWrapStyleWord(true);
		jTextArea.setEditable(false);
		return jTextArea;
	}

	private void createLayout() {
		final GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridwidth = 3;
		constraints.insets = new Insets(12, 12, 3, 12);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		this.add(myConsoleScroll, constraints);

		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 1.0 / 3.0;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(3, 12, 12, 3);
		this.add(myTimeLabel, constraints);

		constraints.gridx = 1;
		constraints.insets = new Insets(3, 3, 12, 3);
		this.add(myValueLabel, constraints);

		constraints.gridx = 2;
		constraints.insets = new Insets(3, 3, 12, 12);
		this.add(myIncreaseButton, constraints);
	}


	private void addEventHandlers() {
		AbstractEvent.ourGameUserInterface = new GameUserInterfaceImpl();
	}


	private void lockInputControls() {
		myIncreaseButton.setEnabled(false);
	}

	private void unlockInputControls() {
		myIncreaseButton.setEnabled(true);
	}


	private void printlnToConsole(final String message) throws Exception {
		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				myConsole.append(message);
				myConsole.append("\n");
				myConsole.setCaretPosition(myConsole.getDocument().getLength());
			}
		};
		if (SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			SwingUtilities.invokeAndWait(runnable);
		}
		myConsole.repaint();
	}


	private class GameUserInterfaceImpl implements GameUserInterface {
		@Override
		public void init(final Game game) {
			myConsole.setText("");
			myGame = game;
		}

		@Override
		public void updateTime() {
			SwingUtilities.invokeLater(
					new Runnable() {
						@Override
						public void run() {
							final long currentTime =
									myGame.getTimeline().getCurrentTime();
							myTimeLabel.setText("Time: " + currentTime);
						}
					}
			);
		}

		@Override
		public void updateValue() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					final int value = myGame.getValue();
					myValueLabel.setText("Value: " + value);
				}
			});
		}

		@Override
		public void unlockControls() {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					unlockInputControls();
				}
			});
		}

		@Override
		public void message(final String message) throws Exception {
			printlnToConsole(message);
		}

		@Override
		public void endGame(final Boolean victory) {
			myEndGameCallback.doWith(victory);
		}
	}
}
