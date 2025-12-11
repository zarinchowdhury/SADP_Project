import com.coachingcenter.CoachingCenterFacade;
import com.coachingcenter.Student1;

import javax.swing.*;
import java.awt.*;


public class CoachingCenterManagementSystem extends JFrame {


    private final CoachingCenterFacade facade = new CoachingCenterFacade();


    public CoachingCenterManagementSystem(){
        setTitle("Coaching Center");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JButton add = new JButton("Add Student");


        add.addActionListener(e -> {
            Student1 s = StudentFactory.create(name.getText(), Integer.parseInt(age.getText()), "000", "none@mail.com");
            facade.getStudents().add(s);
            JOptionPane.showMessageDialog(this, "Student Added: " + s.getName());
        });


        setLayout(new GridLayout(3,2));
        add(new JLabel("Name:")); add(name);
        add(new JLabel("Age:")); add(age);
        add(add);
    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new CoachingCenterManagementSystem().setVisible(true));
    }
}