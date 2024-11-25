public interface BrewingStrategy {
    // Method to brew coffee based on the specified number of cups
    void brew(int numberOfCups);
    // Method to get the brew time for the strategy
    int getBrewTime();
}
