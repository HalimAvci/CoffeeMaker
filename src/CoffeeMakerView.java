import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeMakerView extends JFrame implements StateObserver {
    private final CoffeeMakerModel model;
    private final JButton startButton;
    private final JButton fillButton;
    private final JButton resetButton;
    private final JButton totalCupsButton;
    private final JTextField cupsTextField;
    private final JTextArea messagesTextArea;
    private final JPanel statePanel;

    // Getter methods for accessing the buttons and text fields
    public JButton getStartButton(){
        return  startButton;
    }
    public JButton getFillButton(){
        return fillButton;
    }
    public JButton getResetButton(){
        return  resetButton;
    }
    public JTextField getCupsTextField() {
        return cupsTextField;
    }

    // Constructor initializing the view with a model and setting up the GUI
    public CoffeeMakerView(CoffeeMakerModel model){
        this.model = model;
        model.addObserver(this);

        setTitle("Coffee Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons, text fields, and other components
        startButton = new JButton("Start");
        fillButton = new JButton("Filled");
        resetButton = new JButton("Reset");
        cupsTextField = new JTextField(5);
        messagesTextArea = new JTextArea(5, 30);
        statePanel = new JPanel();
        totalCupsButton = new JButton("Total Cups");

        // ActionListener for the Total Cups button
        totalCupsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTotalCups();
            }
        });

        // Create and add a Close button with an ActionListener
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCloseButton();
            }
        });

        // Create a control panel and add components to it
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Cup(s):"));
        controlPanel.add(cupsTextField);
        controlPanel.add(fillButton);
        controlPanel.add(startButton);
        controlPanel.add(resetButton);
        controlPanel.add(totalCupsButton);
        controlPanel.add(closeButton);

        // Add components to the frame
        add(controlPanel, BorderLayout.NORTH);
        add(new JScrollPane(messagesTextArea), BorderLayout.CENTER);
        add(statePanel, BorderLayout.SOUTH);

        statePanel.setLayout(new FlowLayout());

        // Update the state and set up the frame
        updateState();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to handle the Close button click
    private void handleCloseButton(){
        closeView();
    }

    // Method to show the total cups consumed this month
    private void showTotalCups(){
        int totalCupsThisMonth = model.getTotalCupsThisMonth();
        JOptionPane.showMessageDialog(null, "Total cups consumed this month: " + totalCupsThisMonth, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to update the graphical representation of the coffee maker's state
    @Override
    public void updateState() {
        statePanel.removeAll();

        // Add panels for different states with corresponding colors
        addStatePanel("EMPTY", Color.YELLOW, "EMPTY");
        addStatePanel("IDLE", Color.GREEN, "IDLE");
        addStatePanel("BREWING", Color.ORANGE, "BREWING");
        addStatePanel("DONE", Color.BLUE, "DONE");

        // Update the state panel
        statePanel.revalidate();
        statePanel.repaint();
    }

    // Method to add a state panel with a label, background color, and state name
    private void addStatePanel(String labelText, Color backgroundColor, String state){
        JPanel panel = new JPanel();
        panel.add(new JLabel(labelText));

        // Set the background color based on the current state
        if (model.getCurrentState().getStateName().equals(state)) {
            panel.setBackground(backgroundColor);
        }
        // Add the panel to the state model
        statePanel.add(panel);
    }

    // Method to close the view with a confirmation dialog
    public void closeView(){
        int userResponse = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to close the application?",
                "Confirm close",
                JOptionPane.YES_NO_OPTION
        );
        // If the user confirms, remove the observer, dispose the frame, and exit the application
        if (userResponse == JOptionPane.YES_OPTION){
            model.removeObserver(this);

            this.dispose();
            System.exit(0);
        }
    }
}

