package tabular;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class TabularSolver {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabularSolver window = new TabularSolver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabularSolver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tabular x = new tabular();
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter the number of variables :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 11, 222, 28);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter minterms values :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(32, 72, 169, 23);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Enter Don't care values :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(32, 134, 159, 23);
		frame.getContentPane().add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(42, 39, 279, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(42, 103, 279, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(42, 168, 285, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_2 = new JButton("Solve");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String noofvariables = textField.getText();
				String minterms = textField_1.getText();
				String doncare = textField_2.getText();
				x.num_variable = Integer.parseInt(noofvariables);
				x.minterms = scan(minterms);
				if (doncare.length() != 0) {
					x.donot_care = scan(doncare);
				}
				x.fill_array();
				x.groubing();
				x.createchart();
				x.petrick();
				String answer = printoutput(x.answer,x.answersize);
				textField_3.setText(answer);
			}
		});
		btnNewButton_2.setBounds(141, 219, 89, 23);
		frame.getContentPane().add(btnNewButton_2);

		textField_3 = new JTextField();
		textField_3.setBounds(42, 253, 291, 110);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}

	public LinkedList<Integer> scan(String input) {
		List<String> list = new ArrayList<String>();
		input = input.replaceAll("[^-?0-9]+", " ");
		list = Arrays.asList(input.trim().split(" "));
		LinkedList<Integer> inputinteger = new LinkedList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			inputinteger.add(Integer.parseInt(list.get(i)));
		}

		return inputinteger;

	}

public String printoutput(String arr[],int size){
	String output = "";
	for(int i =0;i<size;i++){
		output = output + arr[i];
		if(i!=size-1)
		{		
			output = output + "/";
		}
		}
	return output;
	}
	
}


