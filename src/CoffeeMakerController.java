import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeMakerController {
    private CoffeeMakerModel model;
    private CoffeeMakerView view;

    public CoffeeMakerController(CoffeeMakerModel model, CoffeeMakerView view){
        this.model = model;
        this.view = view;

        // ActionListener for the Start button
        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleStartButton();
            }
        });

        // ActionListener for the Filled button
        view.getFillButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFilledButton();
            }
        });

        // ActionListener for the Reset button
        view.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetButton();
            }
        });
    }

    // Method to handle the Start button click
    private void handleStartButton(){
        model.start();
        model.notifyObservers();
    }

    //Method to handle the Filled button click
    private void handleFilledButton(){
        try {
            // Parse the number of cups from the text field
            int cups = Integer.parseInt(view.getCupsTextField().getText());

            // Show an option dialog for selecting brewing type
            Object[] options = {"Standard Brew", "Fast Brew"};
            int brewTypeChoice = JOptionPane.showOptionDialog(null,
                    "Select brewing type: ",
                       "Brewing Type",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                       null,
                            options,
                            options[0]);

            String brewType;
            // Set the brewing strategy based on the user's choice
            if (brewTypeChoice == 0){
                brewType = "Standard Brew";
                model.setBrewingStrategy(new StandardBrewingStrategy());
            } else {
                brewType = "Fast Brew";
                model.setBrewingStrategy(new FastBrewingStrategy());
            }
            // Fill the coffee maker, notify observers, and show an information message
            model.fill(cups);
            model.notifyObservers();

            JOptionPane.showMessageDialog(null, "Brewing " + brewType + " for " + cups + " cups.", "Information", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex){
            // Show a warning message if the entered number of cups is not valid
            JOptionPane.showMessageDialog(null, "Please enter a valid number of cups.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Method to handle the Reset button click
    private void handleResetButton(){
        // Reset the coffee maker, notify observers, and clear the cups text field
        model.reset();
        model.notifyObservers();
        view.getCupsTextField().setText("");
    }
}

