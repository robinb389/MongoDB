package mongodb.contactes;

import mongodb.contactes.controller.ContacteController;
import mongodb.contactes.model.ConnectionManager;

public class App {
    public static void main(String[] args) {
        ContacteController controller = new ContacteController();
        controller.iniciar();
        ConnectionManager.closeConnection();
    }
}