package projectnvidia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SignUpGUI extends JFrame {

	JButton signup;

	JButton goback;

	public SignUpGUI() {
		createFrame();
		createComponents();
//		lookAndFeel();
	}

	public void createFrame() {
		setTitle("User Sign Up");

		setSize(1920, 1080);

		Image image = Toolkit.getDefaultToolkit()
				.getImage("D:\\Qspiders\\Project\\NvidiaFibernetBuildingSystem\\src\\main\\java\\icons\\logo.jpg");

		setIconImage(image);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(createBackgroundPanel());

		setLayout(null);

		setVisible(true);
	}

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
		JLabel titleLabel = new JLabel("USER SIGNUP");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 21));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(720, 230, 150, 25);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Arial", Font.BOLD, 16));
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(610, 300, 150, 25);

		JTextField usernameField = new JTextField();
		usernameField.setBounds(700, 300, 200, 25);

		JLabel phoneLabel = new JLabel("Mobile Number:");
		phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(570, 350, 150, 25);

		JTextField phoneField = new JTextField();
		phoneField.setBounds(700, 350, 200, 25);

		JLabel cityLabel = new JLabel("Select City:");
		cityLabel.setFont(new Font("Arial", Font.BOLD, 15));
		cityLabel.setBounds(605, 385, 220, 35);
		cityLabel.setForeground(Color.white);
		String[] cities = { "Austin, Texas", "New York City, Brooklyn", "Hyderabad", "Kansas City, Missouri",
				"Bangalore" };
		JComboBox<String> cityDropdown = new JComboBox<>(cities);
		cityDropdown.setBounds(700, 390, 150, 25);

		JCheckBox termsCheckbox = new JCheckBox("Agree to Terms and Conditions");
		termsCheckbox.setFont(new Font("Arial", Font.BOLD, 13));
		termsCheckbox.setBounds(695, 420, 220, 35);
		termsCheckbox.setOpaque(false);
		termsCheckbox.setForeground(Color.WHITE);
		termsCheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				signup.setEnabled(termsCheckbox.isSelected());
			}
		});

		signup = new JButton("Sign Up");
		signup.setFont(new Font("Arial", Font.BOLD, 15));
		signup.setBounds(690, 460, 100, 25);
		signup.setEnabled(false);
		signup.setForeground(Color.black);
		signup.setBackground(Color.green);

		signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username1 = usernameField.getText();
				String phone = phoneField.getText();
				String city = cityDropdown.getSelectedItem().toString(); 

				boolean isValid = dbSignUp.validateUser(username1, phone, city);

				if (isValid) {
					JOptionPane.showMessageDialog(null,
							"Sign-up Successful!\nUsername: " + username1 + "\nMobile: " + phone + "\nCity: " + city);
					new HomeGUI();
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Credentials! Try again.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		goback = new JButton("GoBack");
		goback.setFont(new Font("Arial", Font.BOLD, 15));
		goback.setBounds(810, 460, 100, 25);
		goback.setForeground(Color.black);
		goback.setBackground(Color.red);
		goback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(titleLabel);
		add(userLabel);
		add(usernameField);
		add(phoneLabel);
		add(phoneField);
		add(termsCheckbox);
		add(signup);
		add(goback);
		add(cityLabel);
		add(cityDropdown);

	}
//	public void lookAndFeel() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SignUpGUI();
			}
		});
	}
}
