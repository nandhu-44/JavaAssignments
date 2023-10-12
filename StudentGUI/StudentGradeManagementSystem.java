package Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeManagementSystem extends JFrame {
    private ArrayList<String> studentNames;
    private ArrayList<int[]> studentMarks;

    private JLabel nameLabel, marksLabel;
    private JTextField nameTextField, marksTextField1, marksTextField2, marksTextField3;
    private JButton addStudentButton, addMarksButton, displayNamesButton, displayGradesButton, findAvgButton,
            findClassAvgButton, clearDataButton;
    private JTable dataTable;

    public StudentGradeManagementSystem() {
        studentNames = new ArrayList<>();
        studentMarks = new ArrayList<>();

        nameLabel = new JLabel("Name:");
        nameLabel.setPreferredSize(new Dimension(25, 25));
        nameTextField = new JTextField(10);

        marksLabel = new JLabel("Marks:");
        marksLabel.setPreferredSize(new Dimension(25, 25));
        marksTextField1 = new JTextField(5);
        marksTextField2 = new JTextField(5);
        marksTextField3 = new JTextField(5);

        addStudentButton = new JButton("Add Student");

        addMarksButton = new JButton("Add Marks");

        displayNamesButton = new JButton("Display Names");

        displayGradesButton = new JButton("Display Grades");

        findAvgButton = new JButton("Find Student Average");

        findClassAvgButton = new JButton("Find Class Average");

        clearDataButton = new JButton("Clear Data");

        String[] columnNames = { "Name", "Mark 1", "Mark 2", "Mark 3", "Total Marks" };
        Object[][] data = new Object[0][5];
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        dataTable = new JTable(model);

        // Layout setup
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 5, 5));
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(marksLabel);
        JPanel marksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        marksPanel.add(marksTextField1);
        marksPanel.add(marksTextField2);
        marksPanel.add(marksTextField3);
        inputPanel.add(marksPanel);
        inputPanel.add(addStudentButton);
        inputPanel.add(addMarksButton);
        inputPanel.add(displayNamesButton);
        inputPanel.add(displayGradesButton);
        inputPanel.add(findAvgButton);
        inputPanel.add(findClassAvgButton);
        inputPanel.add(clearDataButton);

        // Action Listeners
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        addMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMarks();
            }
        });

        displayNamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNames();
            }
        });

        displayGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGrades();
            }
        });

        findAvgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findAverageForStudent();
            }
        });

        findClassAvgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findClassAverage();
            }
        });

        clearDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
            }
        });

        // Frame setup
        setLayout(new BorderLayout());
        setTitle("Student Grade Management System");
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Function to add student
    private void addStudent() {
        String name = nameTextField.getText().trim();
        String marksString1 = marksTextField1.getText().trim();
        String marksString2 = marksTextField2.getText().trim();
        String marksString3 = marksTextField3.getText().trim();

        if (!name.isEmpty()) {
            if (!marksString1.isEmpty() && !marksString2.isEmpty() && !marksString3.isEmpty()) {
                try {
                    int[] marks = new int[4];
                    marks[0] = Integer.parseInt(marksString1);
                    marks[1] = Integer.parseInt(marksString2);
                    marks[2] = Integer.parseInt(marksString3);
                    marks[3] = marks[0] + marks[1] + marks[2];

                    studentNames.add(name);
                    studentMarks.add(marks);

                    updateTable();
                    nameTextField.setText("");
                    marksTextField1.setText("");
                    marksTextField2.setText("");
                    marksTextField3.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input for marks. Please enter valid integers.");
                }
            } else {
                studentNames.add(name);
                studentMarks.add(new int[4]);
                updateTable();
                nameTextField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please provide a name.");
        }
    }

    // Function to add marks for a student
    private void addMarks() {
        String name = nameTextField.getText().trim();
        String marksString1 = marksTextField1.getText().trim();
        String marksString2 = marksTextField2.getText().trim();
        String marksString3 = marksTextField3.getText().trim();

        if (!name.isEmpty() && !marksString1.isEmpty() && !marksString2.isEmpty() && !marksString3.isEmpty()) {
            try {
                int[] marks = new int[4];
                marks[0] = Integer.parseInt(marksString1);
                marks[1] = Integer.parseInt(marksString2);
                marks[2] = Integer.parseInt(marksString3);
                marks[3] = marks[0] + marks[1] + marks[2];

                int studentIndex = studentNames.indexOf(name);
                if (studentIndex != -1) {
                    studentMarks.set(studentIndex, marks);
                    updateTable();
                    marksTextField1.setText("");
                    marksTextField2.setText("");
                    marksTextField3.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Student '" + name + "' not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for marks. Please enter valid integers.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please provide all the required fields.");
        }
    }

    // Function to display names of all students using a dialog box
    private void displayNames() {
        StringBuilder namesList = new StringBuilder();
        for (String name : studentNames) {
            namesList.append(name).append("\n");
        }
        JOptionPane.showMessageDialog(this, "List of Names:\n" + namesList.toString());
    }

    // Function to display grades of a student
    private void displayGrades() {
        int row = dataTable.getSelectedRow();

        if (row != -1) {
            String name = (String) dataTable.getValueAt(row, 0);
            int studentIndex = studentNames.indexOf(name);

            if (studentIndex != -1) {
                int[] marks = studentMarks.get(studentIndex);
                JOptionPane.showMessageDialog(this,
                        "Grades for student '" + name + "':\nMark 1: " + marks[0] + "\nMark 2: " + marks[1]
                                + "\nMark 3: "
                                + marks[2] + "\nTotal Marks: " + marks[3]);
            } else {
                JOptionPane.showMessageDialog(this, "Student '" + name + "' not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to display grades.");
        }
    }

    // Function to find average marks for a student
    private void findAverageForStudent() {
        int row = dataTable.getSelectedRow();

        if (row != -1) {
            String name = (String) dataTable.getValueAt(row, 0);
            int studentIndex = studentNames.indexOf(name);

            if (studentIndex != -1) {
                int[] marks = studentMarks.get(studentIndex);
                double average = marks[3] / 3.0;
                JOptionPane.showMessageDialog(this, "Average marks for student '" + name + "': " + average);
            } else {
                JOptionPane.showMessageDialog(this, "Student '" + name + "' not found.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to find the average.");
        }
    }

    // Function to find class average marks
    private void findClassAverage() {
        if (!studentNames.isEmpty()) {
            int totalMarks = 0;
            int totalSubjects = 0;

            for (int[] marks : studentMarks) {
                totalMarks += marks[3];
                totalSubjects += 3;
            }

            double average = totalMarks / (double) totalSubjects;
            JOptionPane.showMessageDialog(this, "Average marks for the entire class: " + average);
        } else {
            JOptionPane.showMessageDialog(this, "No data available to calculate class average.");
        }
    }

    // Function to clear all data
    private void clearData() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear all data?",
                "Confirm Clear Data",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            studentNames.clear();
            studentMarks.clear();
            updateTable();
        }
    }

    private void updateTable() {
        String[] columnNames = { "Name", "Mark 1", "Mark 2", "Mark 3", "Total Marks" };
        Object[][] data = new Object[studentNames.size()][5];

        for (int i = 0; i < studentNames.size(); i++) {
            data[i][0] = studentNames.get(i);
            int[] marks = studentMarks.get(i);
            data[i][1] = marks[0];
            data[i][2] = marks[1];
            data[i][3] = marks[2];
            data[i][4] = marks[3];
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        dataTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeManagementSystem();
            }
        });
    }
}
