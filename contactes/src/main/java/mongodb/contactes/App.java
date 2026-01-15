package mongodb.contactes;

import java.util.Date;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String uri = "mongodb+srv://robinb389:Robin2006@cluster0.rtzlmey.mongodb.net/";

        try (MongoClient client = MongoClients.create(uri)) {
            MongoDatabase db = client.getDatabase("Contactes");
            MongoCollection<Document> collection = db.getCollection("contactes");

            boolean run = true;
            while (run) {
                System.out.println("\n--- CONTACT MENU ---");
                System.out.println("1. Add Contact");
                System.out.println("2. List Contacts");
                System.out.println("3. Update Phone");
                System.out.println("4. Delete Contact");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int option = sc.nextInt();
                sc.nextLine(); 

                switch (option) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Surname: ");
                        String surname = sc.nextLine();
                        System.out.print("Phone: ");
                        String phone = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        Document newContact = new Document("name", name)
                                .append("surname", surname)
                                .append("phone", phone)
                                .append("email", email)
                                .append("createdAt", new Date());
                        collection.insertOne(newContact);
                        System.out.println("Contact added!"); 
                        break;

                    case 2:
                        for (Document doc : collection.find()) {
                            printContact(doc);
                        }
                        break;

                    case 3:
                        System.out.print("Name to update: ");
                        String updateName = sc.nextLine();
                        System.out.print("New phone: ");
                        String newPhone = sc.nextLine();
                        collection.updateOne(eq("name", updateName), set("phone", newPhone));
                        System.out.println("Phone updated!");
                        break;

                    case 4:
                        System.out.print("Name to delete: ");
                        String deleteName = sc.nextLine();
                        collection.deleteOne(eq("name", deleteName));
                        System.out.println("Contact deleted!");
                        break;

                    case 5:
                        run = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
        sc.close();  
    }

    private static void printContact(Document doc) {
        System.out.println("Name: " + doc.getString("name"));
        System.out.println("Surname: " + doc.getString("surname"));
        System.out.println("Phone: " + doc.getString("phone"));
        System.out.println("Email: " + doc.getString("email"));
        System.out.println("Created At: " + doc.get("createdAt"));
        System.out.println("---------------------------");
    }

}
