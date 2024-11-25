import javax.swing.*;

public class CoffeeMakerApp {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CoffeeMakerModel model = new CoffeeMakerModel();
                CoffeeMakerView view = new CoffeeMakerView(model);
                CoffeeMakerController controller = new CoffeeMakerController(model, view);
            }
        });
    }
}
