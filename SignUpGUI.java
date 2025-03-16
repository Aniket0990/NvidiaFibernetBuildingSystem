package projectnvidia;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SignUpGUI extends JFrame {

	JTextField username;

	JTextField phno;

	JButton signup;

	JButton goback;

	public SignUpGUI() {
		createFrame();
		createComponents();
	}

	public void createFrame() {
		setTitle("Sign Up");

		setSize(600, 400);

		Image image = Toolkit.getDefaultToolkit().getImage("D:\\Qspiders\\Internship\\car.jpg");

		setIconImage(image);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(createBackgroundPanel());

		setLayout(null);

		setVisible(true);
	}

//	private JPanel createBackgroundPanel() {
//		return new JPanel() {
//			protected void paintComponent(Graphics g) {
//				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icons/logo.jpg"));
//
//				Image image = imageIcon.getImage();
//
//				double panelWidth = getWidth();
//				double panelHeight = getHeight();
//				double imageWidth = image.getWidth(this);
//				double imageHeight = image.getHeight(this);
//
//				double scale = Math.max(panelWidth / imageWidth, panelHeight / imageHeight);
//
//				int scaledWidth = (int) (imageWidth * scale);
//				int scaledHeight = (int) (imageHeight * scale);
//
//				int x = (int) ((panelWidth - scaledWidth) / 2);
//				int y = (int) ((panelHeight - scaledHeight) / 2);
//
//				g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
//
//				g.setColor(new Color(0, 0, 0, 150));
//				g.fillRect(0, 0, getWidth(), getHeight());
//			}
//		};
//	}

	private JPanel createBackgroundPanel() {
		return new JPanel() {
			private Image backgroundImage = new ImageIcon(getClass().getResource("/icons/logo.jpg")).getImage();

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (backgroundImage != null) {
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

					g.setColor(new Color(0, 0, 0, 100));
					g.fillRect(0, 0, getWidth(), getHeight());
				} else {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};
	}

	private void createComponents() {
		JLabel userLabel = new JLabel("Username:");
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(630, 300, 150, 25);

		username = new JTextField();
		username.setBounds(700, 300, 200, 25);

		JLabel phoneLabel = new JLabel("Mobile Number:");
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(600, 350, 150, 25);

		phno = new JTextField();
		phno.setBounds(700, 350, 200, 25);

		JCheckBox termsCheckbox = new JCheckBox("Agree to Terms and Conditions");
		termsCheckbox.setBounds(680, 385, 220, 35);
		termsCheckbox.setOpaque(false);
		termsCheckbox.setForeground(Color.WHITE);
		termsCheckbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				signup.setEnabled(termsCheckbox.isSelected());
			}
		});

		signup = new JButton("Sign Up");
		signup.setBounds(680, 430, 100, 25);
		signup.setEnabled(false);
		signup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleSignup();
			}
		});

		goback = new JButton("GoBack");
		goback.setBounds(800, 430, 100, 25);
		goback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(userLabel);
		add(username);
		add(phoneLabel);
		add(phno);
		add(termsCheckbox);
		add(signup);
		add(goback);

	}

	private void handleSignup() {
		String user = username.getText().trim();
		String phone = phno.getText().trim();

		if (user.isEmpty() || phone.isEmpty()) {
			JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!phone.matches("\\d{10}")) {
			JOptionPane.showMessageDialog(this, "Enter a valid 10-digit phone number!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SignUpGUI();
			}
		});
	}
}
