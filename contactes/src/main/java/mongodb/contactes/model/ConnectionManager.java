package mongodb.contactes.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionManager {
    private static final String URI = "mongodb+srv://robinb389:Robin2006@cluster0.rtzlmey.mongodb.net/";
    private static MongoClient mongoClient;

    public static MongoDatabase getConnection() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase("Contactes");
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}