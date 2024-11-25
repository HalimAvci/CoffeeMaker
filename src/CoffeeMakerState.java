public interface CoffeeMakerState {
    // Method to handle the start action in the current state
    void start(CoffeeMakerModel context);

    // Method to handle the fill action with a specified number of cups in the current state
    void fill(int cups, CoffeeMakerModel context);

    // Method to handle the reset action in the current state
    void reset(CoffeeMakerModel context);

    // Method to get the name of the current state
    String getStateName();
}
