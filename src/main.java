import businessLayer.Utilities.alertSystem.AlertSystem;
import serviceLayer.SystemController;

public class main {
    public static void main(String args[]){
        SystemController systemController = SystemController.SystemController();
        systemController.setAlertSystem(new AlertSystem());
        SystemController systemController1 = SystemController.SystemController();
        int a = 2;
    }
}
