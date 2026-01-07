package mongodb.contactes;


import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App {
    public static void main(String[] args) {

        
        String uri = "mongodb://localhost:27017";

        try (MongoClient client = MongoClients.create(uri)) {

            MongoDatabase db = client.getDatabase("Contactes");
            MongoCollection<Document> collection = db.getCollection("contactes");

            System.out.println("📇 ALL CONTACTS:\n");

            
            for (Document doc : collection.find()) {
                System.out.println("Name: " + doc.getString("name"));
                System.out.println("Surname: " + doc.getString("surname"));
                System.out.println("Phone: " + doc.getString("phone"));
                System.out.println("Email: " + doc.getString("email"));
                System.out.println("Created At: " + doc.getDate("createdAt"));
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
