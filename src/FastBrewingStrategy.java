import javax.swing.*;

public class FastBrewingStrategy implements BrewingStrategy{

    // Method to perform fast brewing for a specified number of cups
    @Override
    public void brew(int numberOfCups) {
        // Display an information message indicating fast brewing for the specified number of cups
        JOptionPane.showMessageDialog(null, "Fast brewing for " + numberOfCups + " cups.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to get the brew time for the fast brewing strategy
    @Override
    public int getBrewTime() {
        // Return the brew time for fast brewing (in milliseconds)
        return 2000; // 2 Seconds
    }
}
