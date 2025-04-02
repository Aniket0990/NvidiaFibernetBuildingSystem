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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginGUI extends JFrame {

	JTextField username;

	JTextField phno;

	JButton login;

	JButton goback;

	public LoginGUI() {
		createFrame();
		createComponents();
		lookAndFeel();
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

				boolean isValid = dbUserDashboard.validateUserlogin(username1, phone);

				if (isValid) {
					JOptionPane.showMessageDialog(login, "Login Successful!");
					new UserDashboard();

				} else {
					JOptionPane.showMessageDialog(login, "Invalid Username or Mobile Number!");

				}
			}
		});

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

	public void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new LoginGUI();
			}
		});
	}
}