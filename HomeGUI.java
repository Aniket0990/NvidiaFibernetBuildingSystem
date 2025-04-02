package projectnvidia;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HomeGUI extends JFrame {

	private JButton exsistingUserButton;
	private JButton adminLoginButton;
	private JButton newConnectionButton;

	private Color exsistingUserColor = Color.red;
	private Color adminLoginColor = Color.magenta;
	private Color newConnectioncolor = Color.blue;

	public HomeGUI() {
		SetUpFrame();
		intializeButtonComponents();
		addComponents();
		initializeListeners();
		lookAndFeel();

	}

	private void SetUpFrame() {
		setSize(1920, 1080);
		Image image = Toolkit.getDefaultToolkit()
				.getImage("D:\\Qspiders\\Project\\NvidiaFibernetBuildingSystem\\src\\main\\java\\icons\\logo.jpg");

		setIconImage(image);
		setVisible(true);
		setTitle("Home");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(createBackgroundPanel());
	}

	private JPanel createBackgroundPanel() {

		return new JPanel() {
			@Override

			protected void paintComponent(Graphics g) {
				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icons/logo.jpg"));

				Image image = imageIcon.getImage();

				double panelWidth = getWidth();
				double panelHeight = getHeight();
				double imageWidth = image.getWidth(this);
				double imageHeight = image.getHeight(this);

				double scale = Math.max(panelWidth / imageWidth, panelHeight / imageHeight);

				int scaledWidth = (int) (imageWidth * scale);
				int scaledHeight = (int) (imageHeight * scale);

				int x = (int) ((panelWidth - scaledWidth) / 2);
				int y = (int) ((panelHeight - scaledHeight) / 2);

				g.drawImage(image, x, y, scaledWidth, scaledHeight, this);

				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
	}

//	private JPanel createBackgroundPanel() {
//        return new JPanel() {
//            private Image backgroundImage = new ImageIcon(getClass().getResource("/icons/logo.jpg")).getImage();
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//
//                if (backgroundImage != null) {
//                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//
//                    g.setColor(new Color(0, 0, 0, 100));
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                } else {
//                    g.setColor(Color.LIGHT_GRAY);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                }
//            }
//        };
//    }

	private void intializeButtonComponents() {
		exsistingUserButton = createStyleButton("Existing User", exsistingUserColor);
		newConnectionButton = createStyleButton("Buy Connection", newConnectioncolor);
		adminLoginButton = createStyleButton("Admin Sign in", adminLoginColor);
	}

	private JButton createStyleButton(String text, Color backgroundColor) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 19));
		button.setBackground(backgroundColor);
		button.setForeground(Color.black);
		button.setFocusable(false);

		button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2),
				BorderFactory.createEmptyBorder(12, 25, 12, 25)));

		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(backgroundColor.GRAY);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(backgroundColor);
			}

		});
		return button;
	}

	private void initializeListeners() {
		exsistingUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledExsistingUser();
			}

		});
		adminLoginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledAdminUser();
			}

		});
		newConnectionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handledConnection();
			}

		});
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

	private void addComponents() {

		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(false);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setOpaque(false);

		JLabel label = new JLabel("Welcome To Nvidia Fibernet");
		label.setFont(new Font("Arial", Font.BOLD, 24));

		label.setForeground(Color.WHITE);
		titlePanel.add(label);

		mainPanel.add(Box.createVerticalStrut(185));
		mainPanel.add(titlePanel);
		mainPanel.add(Box.createVerticalStrut(185));
		JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel1.setOpaque(false);
		buttonPanel1.add(exsistingUserButton);
		buttonPanel1.add(adminLoginButton);
		buttonPanel1.add(newConnectionButton);

		mainPanel.add(buttonPanel1);
		add(mainPanel);
	}

	private void handledExsistingUser() {
		int choice = JOptionPane.showConfirmDialog(this, "Existing User?", "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			new LoginGUI();
		} else {

			JOptionPane.showMessageDialog(null, "Please Sign Up", "Alert", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void handledAdminUser() {
		int choice = JOptionPane.showConfirmDialog(this, "Are You Admin?", "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			new AdminLogin();
		} else {
			JOptionPane.showMessageDialog(this, "Exit", "Alert" + "" + "", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private void handledConnection() {
		int choice = JOptionPane.showConfirmDialog(this, "Are You New User?", "Confirm", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION) {
			new SignUpGUI();
		} else {
			JOptionPane.showMessageDialog(this, "Please Sign In", "Alert", JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new HomeGUI();
			}
		});
	}
}
