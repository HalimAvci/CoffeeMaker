import javax.swing.*;

public class BrewingState implements CoffeeMakerState{
    private BrewingStrategy brewingStrategy;

    public void setBrewingStrategy(BrewingStrategy brewingStrategy){
        this.brewingStrategy = brewingStrategy;
    }

    // Method to handle the start action in the "Brewing" state
    @Override
    public void start(CoffeeMakerModel context) {
        // Check if the brewing strategy is set
       if (brewingStrategy != null){
           // If a strategy is set, invoke its brew method
           brewingStrategy.brew(context.getNumberOfCups());
       } else {
           // If no strategy set, show a warning message
           JOptionPane.showMessageDialog(null, "No brewing strategy set.", "Warning", JOptionPane.WARNING_MESSAGE);
       }
    }

    // Method to handle the fill action in the "Brewing" state
    @Override
    public void fill(int cups, CoffeeMakerModel context) {
        // Display a warning message indicating that filling is not possible
        JOptionPane.showMessageDialog(null, "Cannot fill. Coffee maker is already brewing.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to handle the reset action in the "Brewing" state
    @Override
    public void reset(CoffeeMakerModel context) {
        // Display a warning message indicating that resetting is not possible
        JOptionPane.showMessageDialog(null, "Cannot reset. Coffee maker is currently brewing.", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    // Method to get the name of the "Brewing" state
    @Override
    public String getStateName() {
        // Return the string "BREWING" as the name of the current state
        return "BREWING";
    }
}
