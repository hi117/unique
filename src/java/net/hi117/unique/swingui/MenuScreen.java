package net.hi117.unique.swingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yanus Poluektovich (ypoluektovich@gmail.com)
 */
class MenuScreen extends JPanel {

	private final JLabel myLabel;

	private final JButton myNewGameButton;

	MenuScreen(final Runnable newGameHandler) {
		super(new GridBagLayout());

		myLabel = new JLabel();
		myLabel.setFont(myLabel.getFont().deriveFont(32.0f));
		setLabel(null);

		myNewGameButton = new JButton("New game");
		myNewGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				newGameHandler.run();
			}
		});

		final GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(0, 0, 12, 0);
		add(myLabel, constraints);

		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.insets = new Insets(12, 0, 0, 0);
		add(myNewGameButton, constraints);
	}

	void setLabel(final Boolean victory) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (victory == null) {
					myLabel.setText("UNIQUE");
		            myLabel.setForeground(new Color(128, 0, 255));
				} else if (victory) {
					myLabel.setText("VICTORY");
		            myLabel.setForeground(new Color(0, 128, 0));
				} else {
					myLabel.setText("DEFEAT");
		            myLabel.setForeground(Color.RED);
				}
			}
		});
	}

}
