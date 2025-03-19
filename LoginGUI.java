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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginGUI extends JFrame {

	JTextField username;

	JTextField phno;

	JButton login;

	JButton goback;

	public LoginGUI() {
		createFrame();
		createComponents();
	}

	public void createFrame() {
		setTitle("User Sign in");

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

		JLabel titleLabel = new JLabel("USER LOGIN");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(720, 230, 150, 25);

		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Arial", Font.BOLD, 16));
		userLabel.setForeground(Color.WHITE);
		userLabel.setBounds(610, 300, 150, 25);

		username = new JTextField();
		username.setBounds(700, 300, 200, 25);

		JLabel phoneLabel = new JLabel("Mobile Number:");
		phoneLabel.setFont(new Font("Arial", Font.BOLD, 16));
		phoneLabel.setForeground(Color.WHITE);
		phoneLabel.setBounds(570, 350, 150, 25);

		phno = new JTextField();
		phno.setBounds(700, 350, 200, 25);

		JCheckBox termsCheckbox = new JCheckBox("Agree to Terms and Conditions");
		termsCheckbox.setFont(new Font("Arial", Font.BOLD, 13));
		termsCheckbox.setBounds(695, 385, 220, 35);
		termsCheckbox.setOpaque(false);
		termsCheckbox.setForeground(Color.WHITE);
		termsCheckbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login.setEnabled(termsCheckbox.isSelected());
			}
		});

		login = new JButton("Login");
		login.setFont(new Font("Arial", Font.BOLD, 15));
		login.setBounds(690, 430, 100, 25);
		login.setEnabled(false);
		login.setForeground(Color.black);
		login.setBackground(Color.green);

		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username1 = username.getText();
				String phone = new String(phno.getText());

				// Validate using DatabaseConnection
				boolean isValid = dbLogin.validateUserlogin(username1, phone);
				
				if (isValid) {
					JOptionPane.showMessageDialog(login, "Login Successful!");
					// Show Home Page
					new HomeGUI();

				} else {
					JOptionPane.showMessageDialog(login, "Invalid Username or Mobile Number!");

				}
			}
		});

//		login.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				handlelogin();
//			}
//		});

		goback = new JButton("GoBack");
		goback.setFont(new Font("Arial", Font.BOLD, 15));
		goback.setBounds(810, 430, 100, 25);
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
		add(username);
		add(phoneLabel);
		add(phno);
		add(termsCheckbox);
		add(login);
		add(goback);

	}

//	private void handlelogin() {
//		
//		
//		String user = username.getText().trim();
//		String phone = phno.getText().trim();
//
//		if (user.isEmpty() || phone.isEmpty()) {
//			JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		if (!phone.matches("\\d{10}")) {
//			JOptionPane.showMessageDialog(this, "Enter a valid 10-digit phone number!", "Error",
//					JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//     
//		JOptionPane.showMessageDialog(this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//	  new HomeGUI();
//	}
//	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginGUI();
			}
		});
	}
}

//
//@SuppressWarnings("serial")
//public class LoginGUI extends JFrame {
//	// Components declaration
//	JTextField usernameField;
//	JTextField mobileNumberField;
//	JCheckBox termsCheckBox;
//	JButton loginButton;
//	JButton goBackButton;
//
//	// Constructor - Initializes frame, components, and listeners
//	public LoginGUI() {
//		setUpFrame();
//		initializeComponents();
//		addComponents();
//		initializeListeners();
//	}
//
//	// Frame setup
//	private void setUpFrame() {
//		setSize(1920, 1080);
//		setVisible(true);
//		setTitle("User Login");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setLocationRelativeTo(null);
//		setContentPane(createBackgroundPanel());
//	}
//
//	// Component initialization
//	private void initializeComponents() {
//		usernameField = new JTextField(15);
//		mobileNumberField = new JTextField(15);
//		termsCheckBox = new JCheckBox("I agree to the terms and conditions");
//		loginButton = new JButton("Login");
//
//		// Increased font size for better visibility
//		usernameField.setFont(new Font("Arial", Font.PLAIN, 24));
//		mobileNumberField.setFont(new Font("Arial", Font.PLAIN, 24));
//		termsCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
//		loginButton.setFont(new Font("Arial", Font.BOLD, 18));
//
//		loginButton.setBackground(Color.GREEN);
//		loginButton.setForeground(Color.BLACK);
//
//		// TnC Checkbox Transparent
//		termsCheckBox.setOpaque(false);
//		termsCheckBox.setForeground(Color.WHITE);
//
//		goBackButton = new JButton("Go Back");
//		goBackButton.setFont(new Font("Arial", Font.BOLD, 18));
//		goBackButton.setBackground(Color.RED);
//		goBackButton.setForeground(Color.WHITE);
//
//	}
//
//	// Adding GUI components to the frame
//	private void addComponents() {
//		JPanel mainPanel = new JPanel();
//		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//		mainPanel.setOpaque(false);
//
//		JLabel titleLabel = new JLabel("USER LOGIN", SwingConstants.CENTER);
//		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//		titleLabel.setForeground(Color.WHITE);
//		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//		mainPanel.add(Box.createVerticalStrut(210));
//		mainPanel.add(titleLabel);
//		mainPanel.add(Box.createVerticalStrut(50));
//
//		JPanel userDetailsPanel = new JPanel();
//		userDetailsPanel.setOpaque(false);
//		userDetailsPanel.setLayout(new BoxLayout(userDetailsPanel, BoxLayout.Y_AXIS));
//
//		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		usernamePanel.setOpaque(false);
//		JLabel usernameLabel = new JLabel("User Name:");
//		usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
//		usernameLabel.setForeground(Color.WHITE);
//		usernamePanel.add(usernameLabel);
//		usernamePanel.add(usernameField);
//		userDetailsPanel.add(usernamePanel);
//
//		JPanel mobilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		mobilePanel.setOpaque(false);
//		JLabel mobileLabel = new JLabel("Mobile No :");
//		mobileLabel.setFont(new Font("Arial", Font.BOLD, 20));
//		mobileLabel.setForeground(Color.WHITE);
//		mobilePanel.add(mobileLabel);
//		mobilePanel.add(mobileNumberField);
//		userDetailsPanel.add(mobilePanel);
//
//		mainPanel.add(userDetailsPanel);
//		mainPanel.add(Box.createVerticalStrut(20));
//
//		termsCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
//		mainPanel.add(termsCheckBox);
//		mainPanel.add(Box.createVerticalStrut(20));
//
//		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//		mainPanel.add(loginButton);
//
//		goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//		mainPanel.add(Box.createVerticalStrut(20)); // Adds spacing
//		mainPanel.add(goBackButton);
//
//		add(mainPanel);
//	}
//
//	// Adding listeners for button clicks
//	private void initializeListeners() {
//		loginButton.addActionListener(e -> handleLogin());
//
//		goBackButton.addActionListener(e -> {
//			int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to go back?", "Confirmation",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//
//			if (choice == JOptionPane.YES_OPTION) {
//				new HomeGUI(); // Open Home GUI
//				dispose(); // Close Login GUI
//			}
//		});
//	}
//
//	// Handling login button click
//	private void handleLogin() {
//		String username = usernameField.getText().trim();
//		String mobileNumber = mobileNumberField.getText().trim();
//
//		if (username.isEmpty() || mobileNumber.isEmpty()) {
//			JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
//		} else if (!termsCheckBox.isSelected()) {
//			JOptionPane.showMessageDialog(this, "Please agree to the terms and conditions.", "Error",
//					JOptionPane.ERROR_MESSAGE);
//		} else {
//			boolean isValid = ConnectionJDBC.validateFromDatabase(username, mobileNumber);
//
//			if (isValid) {
//				JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
//				new HomeGUI(); // Navigate back to Home or next page
//				dispose(); // Close Login GUI
//			} else {
//				JOptionPane.showMessageDialog(this, "Invalid username or mobile number.", "Error",
//						JOptionPane.ERROR_MESSAGE);
//			}
//		}
//	}
//	private JPanel createBackgroundPanel() {
//		return new JPanel() {
//			private Image backgroundImage = new ImageIcon(getClass().getResource("/icons/logo.jpg")).getImage();
//
//			@Override
//			protected void paintComponent(Graphics g) {
//				super.paintComponent(g);
//
//				if (backgroundImage != null) {
//					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//
//					g.setColor(new Color(0, 0, 0, 100));
//					g.fillRect(0, 0, getWidth(), getHeight());
//				} else {
//					g.setColor(Color.LIGHT_GRAY);
//					g.fillRect(0, 0, getWidth(), getHeight());
//				}
//			}
//		};
//	}
//
//
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//
//			@Override
//			public void run() {
//				new LoginGUI();
//			}
//		});
//
//	}
//}
