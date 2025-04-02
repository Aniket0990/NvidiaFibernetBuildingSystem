package projectnvidia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BuyConnection extends JFrame {

	static String accountNo;
	static String getPhoneNo;
	Plan selectedPlan;

	static final Color NVIDIA_GREEN = new Color(118, 185, 0);
	static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
	static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
	static final Font PRICE_FONT = new Font("Segoe UI", Font.BOLD, 24);

	public static class RoundedPanel extends JPanel {

		private Color backgroundColor;
		private int radius = 15;

		public RoundedPanel(Color backgroundColor) {
			this.backgroundColor = backgroundColor;
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(backgroundColor);
			g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
			g2d.dispose();
		}
	}

	public static class GradientPanel extends JPanel {

		private Image backgroundImage;

		public GradientPanel(String imagePath) {
			URL imageURL = getClass().getResource(imagePath);
			if (imageURL != null) {
				backgroundImage = new ImageIcon(imageURL).getImage();
			} else {
				System.err.println("Image Not Found");
			}
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			if (backgroundImage != null) {
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}

			int w = getWidth();
			int h = getHeight();
			Color color1 = new Color(76, 161, 175, 200);
			Color color2 = new Color(196, 224, 229, 200);
			GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, w, h);
			g2d.dispose();
		}
	}

	public static class Plan {

		String planType;
		double planPrice;
		String planData;
		String planSpeed;
		String planDuration;
		ArrayList<String> appSubscriptions;
		Color themeColor;

		public Plan(String planType, double planPrice, String planData, String planSpeed, String planDuration,
				ArrayList<String> appSubscriptions, Color themeColor) {
			this.planType = planType;
			this.planPrice = planPrice;
			this.planData = planData;
			this.planSpeed = planSpeed;
			this.planDuration = planDuration;
			this.appSubscriptions = appSubscriptions;
			this.themeColor = themeColor;
		}
	}

	private void generateAccountNo() {
		Random random = new Random();
		accountNo = String.valueOf(100000000 + random.nextInt(900000000));
	}

	public BuyConnection() {
		setTitle("Nvidia Fibernet Broadband");
	Image image = Toolkit.getDefaultToolkit()
				.getImage("D:\\Qspiders\\Project\\NvidiaFibernetBuildingSystem\\src\\main\\java\\icons\\logo.jpg");
		setIconImage(image);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);

		GradientPanel mainPanel = new GradientPanel("/icons/logo.jpg"); // Replace with your image path
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		addHeader(mainPanel);
		createPlans();
		addPlansPanel(mainPanel);
		addRegistrationForm(mainPanel);

		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(scrollPane);

		setLocationRelativeTo(null);
	}

	private void addHeader(JPanel mainPanel) {
		JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Nvidia Fibernet");
		titleLabel.setFont(TITLE_FONT);
		titleLabel.setForeground(NVIDIA_GREEN);
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel subtitleLabel = new JLabel("Experience Lightning-Fast Internet");
		subtitleLabel.setFont(SUBTITLE_FONT);
		subtitleLabel.setForeground(Color.DARK_GRAY);
		subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		headerPanel.add(titleLabel);
		headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		headerPanel.add(subtitleLabel);
		headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		mainPanel.add(headerPanel);
	}

	private ArrayList<Plan> createPlans() {
		ArrayList<Plan> plans = new ArrayList<>();
		plans.add(new Plan("Basic", 49.99, "500GB", "100 Mbps", "30 Days",
				new ArrayList<>(Arrays.asList("Peacock", "Amazon Prime Basic")), new Color(135, 206, 235)));
		plans.add(new Plan("Standard", 69.99, "1000GB", "300 Mbps", "30 Days",
				new ArrayList<>(Arrays.asList("Peacock", "Amazon Prime", "Hulu Standard")), new Color(100, 149, 237)));
		plans.add(new Plan("Premium", 89.99, "2000GB", "500 Mbps", "30 Days",
				new ArrayList<>(Arrays.asList("Peacock", "Amazon Prime", "Hulu Premium", "HBO Max")),
				new Color(65, 105, 225)));
		plans.add(new Plan("Ultra", 119.99, "4000GB", "1 Gbps", "30 Days",
				new ArrayList<>(Arrays.asList("Peacock", "Amazon Prime", "Hulu Premium", "HBO Max", "ESPN+")),
				new Color(0, 0, 139)));
		plans.add(new Plan("Gamer Pro", 149.99, "Unlimited", "2 Gbps", "30 Days",
				new ArrayList<>(Arrays.asList("All Streaming Apps", "Gaming Server Priority", "Cloud Gaming")),
				NVIDIA_GREEN));
		return plans;
	}

	private void addPlansPanel(JPanel mainPanel) {
		JPanel plansPanel = new JPanel(new GridLayout(0, 3, 20, 20));
		plansPanel.setOpaque(false);
		plansPanel.setBorder(new EmptyBorder(10, 10, 30, 10));

		ArrayList<Plan> plans = createPlans();
		ButtonGroup planGroup = new ButtonGroup();

		for (Plan plan : plans) {
			RoundedPanel planCard = new RoundedPanel(Color.WHITE);
			planCard.setLayout(new BoxLayout(planCard, BoxLayout.Y_AXIS));
			planCard.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

			JLabel typeLabel = new JLabel(plan.planType);
			typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
			typeLabel.setForeground(plan.themeColor);
			typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			JLabel priceLabel = new JLabel("$" + String.format("%.2f", plan.planPrice) + "/month");
			priceLabel.setFont(PRICE_FONT);
			priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

			JPanel featuresPanel = new JPanel();
			featuresPanel.setLayout(new BoxLayout(featuresPanel, BoxLayout.Y_AXIS));
			featuresPanel.setOpaque(false);

			addFeature(featuresPanel, "\uD83D\uDE80 " + plan.planSpeed);
			addFeature(featuresPanel, "\uD83D\uDCCA " + plan.planData);
			addFeature(featuresPanel, "\u23F1\uFE0F " + plan.planDuration);
			addFeature(featuresPanel, "\uD83D\uDCF1 Apps: " + String.join(", ", plan.appSubscriptions));

			JRadioButton radioBtn = new JRadioButton("Select Plan");
			radioBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			radioBtn.setForeground(plan.themeColor);
			radioBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					selectedPlan = plan;
				}
			});

			planGroup.add(radioBtn);

			planCard.add(typeLabel);
			planCard.add(Box.createRigidArea(new Dimension(0, 10)));
			planCard.add(priceLabel);
			planCard.add(Box.createRigidArea(new Dimension(0, 15)));
			planCard.add(featuresPanel);
			planCard.add(Box.createRigidArea(new Dimension(0, 15)));
			planCard.add(radioBtn);

			plansPanel.add(planCard);
		}
		mainPanel.add(plansPanel);
	}

	private void addFeature(JPanel panel, String feature) {
		JLabel label = new JLabel(feature);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(label);
	}

	private void addRegistrationForm(JPanel mainPanel) {
		RoundedPanel formPanel = new RoundedPanel(Color.WHITE);
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel formTitle = new JLabel("Complete Your Registration");
		formTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		formTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		formTitle.setForeground(NVIDIA_GREEN);

		JTextField mobileField = createStyledTextField("Mobile Number");
		JTextField userNameField = createStyledTextField("Username");
		JComboBox<String> cityCombo = createStyledComboBox();
		JButton submitBtn = new JButton("Register Now");

		submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		submitBtn.setForeground(Color.BLACK);
		submitBtn.setBackground(NVIDIA_GREEN);
		submitBtn.setBorder(new EmptyBorder(10, 30, 10, 30));
		submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSubmission(mobileField, userNameField, cityCombo);
			}
		});

		formPanel.add(formTitle);
		formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		formPanel.add(mobileField);
		formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		formPanel.add(userNameField);
		formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		formPanel.add(cityCombo);
		formPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		formPanel.add(submitBtn);

		mainPanel.add(formPanel);
	}

	private JComboBox<String> createStyledComboBox() {
		JComboBox<String> combo = new JComboBox<>(
				new String[] { "Select City", "Austin, Texas", "Raleigh, North Carolina", "Kansas City, Missouri",
						"New York City, Brooklyn", "Manhattan - New York City" });

		combo.setMaximumSize(new Dimension(300, 40));
		combo.setAlignmentX(Component.CENTER_ALIGNMENT);
		combo.setBorder(
				BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(5, 5, 5, 5)));
		return combo;
	}

	private void setPlaceHolder(final JTextComponent textComponent, String placeholder) {
		textComponent.setText(placeholder);
		textComponent.setForeground(Color.GRAY);

		textComponent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textComponent.getText().equals(placeholder)) {
					textComponent.setText("");
					textComponent.setForeground(Color.DARK_GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textComponent.getText().isEmpty()) {
					textComponent.setText(placeholder);
					textComponent.setForeground(Color.GRAY);
				}
			}
		});
	}

	private JTextField createStyledTextField(String placeHolder) {
		JTextField field = new JTextField(20);
		field.setFont(new Font("Arial", Font.PLAIN, 16));
		field.setForeground(Color.LIGHT_GRAY);
		field.setBorder(
				BorderFactory.createCompoundBorder(new LineBorder(Color.LIGHT_GRAY), new EmptyBorder(10, 10, 10, 10)));
		field.setMaximumSize(new Dimension(300, 40));
		field.setAlignmentX(Component.CENTER_ALIGNMENT);
		setPlaceHolder(field, placeHolder);
		return field;
	}

	private void handleSubmission(JTextField mobileField, JTextField userNameField, JComboBox<String> cityCombo) {
		if (selectedPlan == null) {
			JOptionPane.showMessageDialog(this, "Please Select a plan first!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (mobileField.getText().equals("Mobile Number") || userNameField.getText().equals("Username")
				|| cityCombo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Please Fill All Fields Firstly!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			long phoneNumber = Long.parseLong(mobileField.getText());
			
			if(mobileField.getText().equals(getPhoneNo)) {
				JOptionPane.showMessageDialog(this,"Please use the same Phone number to Register","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// if (phoneNumber != getPhoneNo) { //getPhoneNo is not initialized anywhere.
			// JOptionPane.showMessageDialog(this, "Please Use The Same Phone Number To
			// Register ", "Error", JOptionPane.ERROR_MESSAGE);
			// return;
			// }
			 
			
			String query = null;

			String planType = selectedPlan.planType;
			double planPrice = selectedPlan.planPrice;
			String planData = selectedPlan.planData;
			String planDuration = selectedPlan.planDuration;
			String speed = selectedPlan.planSpeed;

			dbBuyConnetion con = new dbBuyConnetion(); 
			generateAccountNo();

			query = "insert into broadband_plans (mobilenumber, planType, planPrice, planData, speed, planDuration, accountNumber)"
					+ "\n" + "values('" + phoneNumber + "','" + planType + "','" + planPrice + "','" + planData + "','"
					+ speed + "','" + planDuration + "','" + accountNo + "');";

			 try {
			 con.s.executeUpdate(query);
			 } catch (SQLException e) {
			 e.printStackTrace();
			 }

			JOptionPane.showMessageDialog(this, "Registration successful!\nPlan: " + selectedPlan.planType + "\nCity: "
					+ cityCombo.getSelectedItem() + "\nPlease Re-Login To Load The New Plan");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Invalid Mobile Number", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new BuyConnection());
	}
}
