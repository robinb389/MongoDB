package mongodb.contactes.view;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import mongodb.contactes.model.Contacte;

public class View {
    private Scanner sc;

    public View() {
        sc = new Scanner(System.in);
    }

    public int mostrarMenu() {
        System.out.println("\n--- MENÚ DE CONTACTES ---");
        System.out.println("1. Afegir contacte");
        System.out.println("2. Eliminar contacte");
        System.out.println("3. Modificar contacte");
        System.out.println("4. Llistar tots els contactes");
        System.out.println("5. Llistar contactes entre dates");
        System.out.println("6. Cercar contacte per nom");
        System.out.println("7. Sortir");
        System.out.print("Tria una opció: ");
        return sc.nextInt();
    }

    public Contacte demanarDadesContacte() {
        sc.nextLine(); 
        System.out.print("Nom: ");
        String name = sc.nextLine();
        System.out.print("Cognom: ");
        String surname = sc.nextLine();
        System.out.print("Telèfon: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        return new Contacte(name, surname, phone, email);
    }

    public String demanarNom() {
        sc.nextLine();
        System.out.print("Introdueix el nom: ");
        return sc.nextLine();
    }

    public void mostrarContactes(List<Contacte> contactes) {
        if (contactes.isEmpty()) {
            System.out.println("No hi ha contactes.");
        } else {
            for (Contacte c : contactes) {
                System.out.println(c);
            }
        }
    }

    public void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }

    public Date askDate(String text) {
        System.out.print(text + ": ");
        
        String input = sc.next();

        try {
            return java.sql.Date.valueOf(input); // yyyy-mm-dd
        } catch (Exception e) {
            System.out.println("Format incorrecte. Usa yyyy-mm-dd");
            return askDate(text);
        }
    }

public void show(List<Document> docs) {
    if (docs.isEmpty()) {
        System.out.println("No s'han trobat contactes.");
        return;
    }

    for (Document d : docs) {
        System.out.println("Nom: " + d.getString("name"));
        System.out.println("Cognom: " + d.getString("surname"));
        System.out.println("Telèfon: " + d.getString("phone"));
        System.out.println("Email: " + d.getString("email"));
        System.out.println("Data creació: " + d.getDate("createdAt"));
        System.out.println("---------------------------");
        }
    }


    public void tancar() {
        sc.close();
    }
}