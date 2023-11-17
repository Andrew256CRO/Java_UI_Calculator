import javax.swing.*; // Provides a set of "lightweight" (all-Java language) components that,
// to the maximum degree possible, work the same on all platforms.
// here, we import everything
import java.awt.*; // an API to develop Graphical User Interface (GUI) or windows-based applications in Java.
// here, we also import everything
import java.awt.event.*; // rovides interfaces and classes for dealing with different types of events
// fired by AWT components.
// we import everything here too

public class Main implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberOfButtons=new JButton[10]; // pentru numere de la 1 la 9, 0 inclusiv
    JButton[] functiiButoane=new JButton[9]; // pentru operatii
    JButton addButton, substractButton, multButton, divButton; // multButton to multiply, divButton to divide
    JButton decimalButton, equalButton, delButton, clrButton, negButton; // delButton to delete, clrButton to clear, negButton for inputing a negative number
    JPanel panel; // holds all the separate buttons

    Font font=new Font("Arial", Font.PLAIN, 30);

    double num1=0, num2=0, rezultat=0; // nr. reale
    char operator; // holds one of the operators

    Main(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the program
        frame.setSize(420, 550);
        frame.setLayout(null); // turns off the operations manager, for the frame at least

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(font);
        textField.setEditable(false); // can not be written in initially

        addButton = new JButton("+");
        substractButton = new JButton("-");
        multButton = new JButton("*");
        divButton = new JButton("/");
        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Cls"); // clear screen
        negButton = new JButton("(-)");

        functiiButoane[0] = addButton;
        functiiButoane[1] = substractButton;
        functiiButoane[2] = multButton;
        functiiButoane[3] = divButton;
        functiiButoane[4] = decimalButton;
        functiiButoane[5] = equalButton;
        functiiButoane[6] = delButton;
        functiiButoane[7] = clrButton;
        functiiButoane[8] = negButton;

        for(int i=0; i<9; i++){
            functiiButoane[i].addActionListener(this);
            functiiButoane[i].setFont(font);
            functiiButoane[i].setFocusable(false); // turns off the outline when you click/hover over a button
        }

        for(int i=0; i<10; i++){
            numberOfButtons[i]= new JButton(String.valueOf(i));
            numberOfButtons[i].addActionListener(this);
            numberOfButtons[i].setFont(font);
            numberOfButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel =new JPanel();
        panel.setBounds(50, 100, 300, 300); // so it will be a square
        panel.setLayout(new GridLayout(4, 4, 10 ,10)); // 10pixels distance between the buttons
        //panel.setBackground(Color.green); // temp, just to see. Will get rid of it later

        panel.add(numberOfButtons[1]); // add number 1
        panel.add(numberOfButtons[2]);
        panel.add(numberOfButtons[3]);
        panel.add(addButton); // + button
        panel.add(numberOfButtons[4]);
        panel.add(numberOfButtons[5]);
        panel.add(numberOfButtons[6]);
        panel.add(substractButton);
        panel.add(numberOfButtons[7]);
        panel.add(numberOfButtons[8]);
        panel.add(numberOfButtons[9]);
        panel.add(multButton);
        panel.add(decimalButton);
        panel.add(numberOfButtons[0]);
        panel.add(equalButton);
        panel.add(divButton);

        frame.add(negButton);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Main calculator=new Main();
    }

    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<10; i++){
            if(e.getSource() == numberOfButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decimalButton){
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource()==addButton){
            num1= Double.parseDouble(textField.getText());
            operator='+';
            textField.setText(null);
        }
        if(e.getSource()==substractButton){
            num1= Double.parseDouble(textField.getText());
            operator='-';
            textField.setText(null);
        }
        if(e.getSource()==multButton){
            num1= Double.parseDouble(textField.getText());
            operator='*';
            textField.setText(null);
        }
        if(e.getSource()==divButton){
            num1= Double.parseDouble(textField.getText());
            operator='/';
            textField.setText(null);
        }
        if(e.getSource()==equalButton){
            num2=Double.parseDouble(textField.getText());
            if (operator == '/' && num2 == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }

            switch (operator){
                case '+':
                    rezultat=num1 + num2;
                    break;
                case '-':
                    rezultat=num1 - num2;
                    break;
                case '*':
                    rezultat=num1 * num2;
                    break;
                case '/':
                    rezultat=num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(rezultat));
            num1=rezultat; // to continue if we want to reuse the same number
        }
        if(e.getSource()==clrButton){
            textField.setText(null);
        }
        if(e.getSource()==delButton){
            String string = textField.getText();
            textField.setText(null);
            for(int i=0; i<string.length()-1; i++){
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if(e.getSource()==negButton){
            double temp= Double.parseDouble(textField.getText());
            temp*=-1; // flips the sign
            textField.setText(String.valueOf(temp));
        }
    }
}