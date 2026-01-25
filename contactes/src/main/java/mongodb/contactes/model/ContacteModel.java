package mongodb.contactes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;

public class ContacteModel {
    private MongoCollection<Document> collection;

    public ContacteModel() {
        MongoDatabase db = ConnectionManager.getConnection();
        this.collection = db.getCollection("contactes");
    }

    // Inserir contacte
    public void inserirContacte(Contacte contacte) {
        collection.insertOne(contacte.toDocument());
    }

    // Actualitzar contacte
    public void updateContacte(String name, Contacte contacte) {
        collection.updateOne(eq("name", name),
                new Document("$set", contacte.toDocument()));
    }

    // Eliminar contacte
    public void deleteContacte(String name) {
        collection.deleteOne(eq("name", name));
    }

    // Obtenir tots els contactes
    public List<Contacte> getAllContactes() {
        List<Contacte> contactes = new ArrayList<>();
        for (Document doc : collection.find()) {
            contactes.add(Contacte.fromDocument(doc));
        }
        return contactes;
    }

    // Obtenir contactes entre dates
    public List<Document> getByDate(Date inici, Date fi) {
        return collection.find(
            and(
                gte("createdAt", inici),
                lte("createdAt", fi)
            )
        ).into(new ArrayList<>());
    }

    // Cerca filtre per nom
    public List<Contacte> getFilteredContactes(String searchName) {
        List<Contacte> contactes = new ArrayList<>();
        for (Document doc : collection.find(regex("name", ".*" + searchName + ".*", "i"))) {
            contactes.add(Contacte.fromDocument(doc));
        }
        return contactes;
    }
}