import javax.swing.*;

public class EmptyState implements CoffeeMakerState{
    // Method to handle the start action in the "Empty" state
    @Override
    public void start(CoffeeMakerModel context) {
        // Display a warning message indicating that the coffee maker is empty
        JOptionPane.showMessageDialog(null, "Coffee maker is empty. Fill it first.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to handle the fill action in the "Empty" state
    @Override
    public void fill(int cups, CoffeeMakerModel context) {
        // Set the number of cups in the coffee maker and transition to the "Idle" state
        context.setNumberOfCups(cups);
        context.setState(new IdleState());
    }

    // Method to handle the reset action in the "Empty" state
    @Override
    public void reset(CoffeeMakerModel context) {
        // Display a warning message indicating that resetting is not possible
        JOptionPane.showMessageDialog(null, "Cannot reset. Coffee maker is already empty.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to get the name of the "Empty" state
    @Override
    public String getStateName() {
        // Return the string "EMPTY" as the name of the current state
        return "EMPTY";
    }
}
