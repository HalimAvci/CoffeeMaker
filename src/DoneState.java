import javax.swing.*;

public class DoneState implements CoffeeMakerState {
    // Method to handle the start action in the "Done" state
    @Override
    public void start(CoffeeMakerModel context) {
        // Display a warning message indicating that the coffee maker is already done
       JOptionPane.showMessageDialog(null, "Already done. Reset to start again.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to handle the fill action in the "Done" state
    @Override
    public void fill(int cups, CoffeeMakerModel context) {
        // Display a warning message indicating that filling is not possible
        JOptionPane.showMessageDialog(null, "Cannot fill. Coffee maker is already done.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to handle the reset action in the "Done" state
    @Override
    public void reset(CoffeeMakerModel context) {
        // Reset the coffee maker to the empty state
        context.resetToEmptyState();
    }

    // Method to get the name of the "Done" state
    @Override
    public String getStateName() {
        // Return the string "DONE" as the name of the current state
        return "DONE";
    }
}
