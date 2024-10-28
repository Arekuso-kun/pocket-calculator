import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CalculatorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField display;

	private CalculatorLogic logic = new CalculatorLogic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGUI frame = new CalculatorGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorGUI() {
		setTitle("Calculator de Buzunar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton Button_0 = new JButton("0");
		Button_0.setBounds(10, 415, 70, 70);
		contentPane.add(Button_0);

		JButton Button_1 = new JButton("1");
		Button_1.setBounds(10, 334, 70, 70);
		contentPane.add(Button_1);

		JButton Button_2 = new JButton("2");
		Button_2.setBounds(90, 334, 70, 70);
		contentPane.add(Button_2);

		JButton Button_3 = new JButton("3");
		Button_3.setBounds(170, 334, 70, 70);
		contentPane.add(Button_3);

		JButton Button_4 = new JButton("4");
		Button_4.setBounds(10, 253, 70, 70);
		contentPane.add(Button_4);

		JButton Button_5 = new JButton("5");
		Button_5.setBounds(90, 253, 70, 70);
		contentPane.add(Button_5);

		JButton Button_6 = new JButton("6");
		Button_6.setBounds(170, 253, 70, 70);
		contentPane.add(Button_6);

		JButton Button_7 = new JButton("7");
		Button_7.setBounds(10, 172, 70, 70);
		contentPane.add(Button_7);

		JButton Button_8 = new JButton("8");
		Button_8.setBounds(90, 172, 70, 70);
		contentPane.add(Button_8);

		JButton Button_9 = new JButton("9");
		Button_9.setBounds(170, 172, 70, 70);
		contentPane.add(Button_9);

		JButton Button_add = new JButton("+");
		Button_add.setBounds(250, 172, 70, 151);
		contentPane.add(Button_add);

		JButton Button_sub = new JButton("-");
		Button_sub.setBounds(250, 91, 70, 70);
		contentPane.add(Button_sub);

		JButton Button_multiply = new JButton("*");
		Button_multiply.setBounds(170, 91, 70, 70);
		contentPane.add(Button_multiply);

		JButton Button_devide = new JButton("/");
		Button_devide.setBounds(90, 91, 70, 70);
		contentPane.add(Button_devide);

		JButton Button_equal = new JButton("=");
		Button_equal.setBounds(250, 334, 70, 151);
		contentPane.add(Button_equal);

		JButton Button_clear = new JButton("Clear");
		Button_clear.setBounds(10, 91, 70, 70);
		contentPane.add(Button_clear);

		JButton Button_delete = new JButton("Del");
		Button_delete.setBounds(170, 415, 70, 70);
		contentPane.add(Button_delete);

		display = new JTextField();
		display.setEditable(false);
		display.setAlignmentY(Component.TOP_ALIGNMENT);
		display.setHorizontalAlignment(SwingConstants.LEFT);
		display.setFont(new Font("Tahoma", Font.PLAIN, 24));
		display.setBounds(10, 11, 310, 69);
		contentPane.add(display);

		JButton Button_point = new JButton(".");
		Button_point.setBounds(90, 415, 70, 70);
		contentPane.add(Button_point);

		addEventListeners();
	}

	/**
	 * Loop trough all the buttons and add listeners using handleButtonPress
	 */
	private void addEventListeners() {
		for (Component comp : contentPane.getComponents()) {
			if (comp instanceof JButton) {
				JButton button = (JButton) comp;
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleButtonPress(button.getText());
					}
				});
			}
		}
	}

	/**
	 * 
	 */
	private void handleButtonPress(String text) {
		try {
			if (text.equals("Clear")) {
				logic.clear();
			} else if (text.equals("Del")) {
				logic.delete();
			} else if (text.equals("=")) {
				logic.calculate();
			} else if ("+-*/".contains(text)) {
				logic.updateExpression(text);
			} else {
				logic.updateExpression(text);
			}
			display.setText(logic.getExpression());
		} catch (ArithmeticException ex) {
			display.setText("Error");
		} catch (NumberFormatException ex) {
			display.setText("Invalid input");
		}
	}
}
