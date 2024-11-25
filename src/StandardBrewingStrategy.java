import javax.swing.*;

public class StandardBrewingStrategy implements BrewingStrategy{

    // Method to perform standard brewing for a specified number of cups
    @Override
    public void brew(int numberOfCups) {
        // Display an information message indicating standard brewing for the specified number of cups
        JOptionPane.showMessageDialog(null, "Standard brewing for "+ numberOfCups + " cups.", "Information",JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to get the brew time for the standard brewing strategy
    @Override
    public int getBrewTime() {
        // Return the brew time for standard brewing (in milliseconds)
        return 6000; // 6 Seconds
    }
}
