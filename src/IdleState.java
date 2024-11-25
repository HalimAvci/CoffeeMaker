import javax.swing.*;

public class IdleState implements CoffeeMakerState{

    // Method to handle the start action in the "Idle" state
    @Override
    public void start(CoffeeMakerModel context) {
        // Transition to the "Brewing" state and start the brewing timer
        context.setState(new BrewingState());
        context.startBrewingTimer();
    }

    // Method to handle the fill action in the "Idle" state
    @Override
    public void fill(int cups, CoffeeMakerModel context) {
        // Display a warning message indicating that filling is not possible
        JOptionPane.showMessageDialog(null, "Cannot fill. Coffee maker is not empty.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to handle the reset action in the "Idle" state
    @Override
    public void reset(CoffeeMakerModel context) {
        // Reset the coffee maker to the empty state
        context.resetToEmptyState();
    }

    // Method to get the name of the "Idle" state
    @Override
    public String getStateName() {
        // Return the string "IDLE" as the name of the current state
        return "IDLE";
    }
}
