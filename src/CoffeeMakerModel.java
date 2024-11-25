import java.util.*;
public class CoffeeMakerModel {
    private CoffeeMakerState currentState;                        // Current state of the coffee maker
    private BrewingStrategy brewingStrategy;                      // Strategy for brewing coffee
    private int numberOfCups;                                     // Number of cups for brewing
    private List<StateObserver> observerList = new ArrayList<>(); // List of observers for state changes
    private Date currentDate;                                     // Current date and time
    private int totalCupsThisMonth;                               // Total cups brewed in the current month
    private Timer BrewTimer;                                      // Timer for tracking brewing time
    private CoffeeMakerDatabase database;                         // Database for storing coffee maker usage history

    public CoffeeMakerModel(){
        currentState = new EmptyState();      // Initial state is empty state
        numberOfCups = 0;                     // Initial number of cups is 0
        currentDate = null;                   // Current date is initially null
        totalCupsThisMonth = 0;               // Total cups brewed in the month is initially 0
        database = new CoffeeMakerDatabase(); // Initialize the database
        BrewTimer = new Timer();              // Initialize the brewing timer
    }

    // Method to set the brewing strategy for the coffee maker
    public void setBrewingStrategy(BrewingStrategy brewingStrategy) {
        this.brewingStrategy = brewingStrategy;
    }

    // Method to set the state of the coffee maker
    public void setState(CoffeeMakerState state){
        this.currentState = state;
    }

    // Method to set the number of cups for brewing
    public void setNumberOfCups(int numberOfCups){
        this.numberOfCups = numberOfCups;
    }

    // Method to get the current state of the coffee maker
    public CoffeeMakerState getCurrentState() {
        return currentState;
    }

    // Method to get the number of cups for brewing
    public int getNumberOfCups(){
        return numberOfCups;
    }

    // Method to get the total cups brewed in the current month
    public int getTotalCupsThisMonth(){
        return totalCupsThisMonth;
    }

    // Method to start the coffee maker, delegating to the current state
    public void start(){
        currentState.start(this);
    }

    // Method to fill the coffee maker with a specified number of cups, delegating to the current state
    public void fill(int cups){
        currentState.fill(cups, this);
    }

    // Method to reset the coffee maker, delegating to the current state
    public void reset(){
        currentState.reset(this);
    }

    // Method to start the brewing timer based on the brewing strategy
    void startBrewingTimer(){
      BrewTimer.schedule(new TimerTask() {
          @Override
          public void run() {
              currentState = new DoneState();                 // Set the state to DoneState when brewing is done
              currentDate = new Date();                       // Record the current date and time
              totalCupsThisMonth += numberOfCups;             // Update the total cups brewed in the current month
              database.insertData(numberOfCups, currentDate); // Insert data into the database
              notifyObservers();
          }
      }, brewingStrategy.getBrewTime());
    }

    // Method to add an observer to the list of observers
    public void addObserver(StateObserver observer){
        observerList.add(observer);
    }

    // Method to remove an observer from the list of observers
    public void removeObserver(StateObserver observer){
        observerList.remove(observer);
    }

    // Method to notify all observers about a state change
    public void notifyObservers(){
        for (StateObserver observer : observerList){
            observer.updateState();
        }
    }

    // Method to reset the coffee maker to the EmptyState
    public void resetToEmptyState(){
        currentState = new EmptyState();
        numberOfCups = 0;
    }
}
