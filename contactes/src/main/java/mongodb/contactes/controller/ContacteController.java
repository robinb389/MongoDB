package mongodb.contactes.controller;

import java.util.Date;

import mongodb.contactes.model.Contacte;
import mongodb.contactes.model.ContacteModel;
import mongodb.contactes.view.View;

public class ContacteController {
    private ContacteModel model;
    private View view;

    public ContacteController() {
        this.model = new ContacteModel();
        this.view = new View();
    }

    public void iniciar() {
        boolean run = true;
        while (run) {
            int opcio = view.mostrarMenu();
            switch (opcio) {
                case 1:
                    afegirContacte();
                    break;
                case 2:
                    eliminarContacte();
                    break;
                case 3:
                    modificarContacte();
                    break;
                case 4:
                    llistarContactes();
                    break;
                case 5:
                   
                    Date inici = view.askDate("Data inici (yyyy-mm-dd)");
                    Date fi = view.askDate("Data fi (yyyy-mm-dd)");
                    view.show(model.getByDate(inici, fi));
                    break;
                case 6:
                    cercarContacte();
                    break;
                case 7:
                    run = false;
                    view.mostrarMissatge("Sortint...");
                    break;
                default:
                    view.mostrarMissatge("Opció invàlida!");
            }
        }
        view.tancar();
    }

    private void afegirContacte() {
        Contacte contacte = view.demanarDadesContacte();
        model.inserirContacte(contacte);
        view.mostrarMissatge("Contacte afegit!");
    }

    private void eliminarContacte() {
        String nom = view.demanarNom();
        model.deleteContacte(nom);
        view.mostrarMissatge("Contacte eliminat!");
    }

    private void modificarContacte() {
        view.mostrarContactes(model.getAllContactes());
        String nomAntic = view.demanarNom();
        Contacte nouContacte = view.demanarDadesContacte();
        model.updateContacte(nomAntic, nouContacte);
        view.mostrarMissatge("Contacte modificat!");
    }

    private void llistarContactes() {
        view.mostrarContactes(model.getAllContactes());
    }

    private void cercarContacte() {
        String cerca = view.demanarNom();
        view.mostrarContactes(model.getFilteredContactes(cerca));
    }
}