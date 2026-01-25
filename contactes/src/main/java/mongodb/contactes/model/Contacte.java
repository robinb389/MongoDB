package mongodb.contactes.model;

import java.util.Date;

import org.bson.Document;

public class Contacte {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Date createdAt;

    // Constructor buit
    public Contacte() {}

    // Constructor complet
    public Contacte(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.createdAt = new Date();
    }

    // Getters i Setters
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getSurname() { 
        return surname; 
    }
    public void setSurname(String surname) { 
        this.surname = surname; 
    }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { 
        this.phone = phone; 
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public Date getCreatedAt() { 
        return createdAt; 
    }
    public void setCreatedAt(Date createdAt) {
         this.createdAt = createdAt; 
    }

    // Mètode per convertir a Document de MongoDB
    public Document toDocument() {
        return new Document("name", name)
                .append("surname", surname)
                .append("phone", phone)
                .append("email", email)
                .append("createdAt", createdAt);
    }

    // Mètode per crear Contacte des de Document
    public static Contacte fromDocument(Document doc) {
        Contacte c = new Contacte();
        c.setName(doc.getString("name"));
        c.setSurname(doc.getString("surname"));
        c.setPhone(doc.getString("phone"));
        c.setEmail(doc.getString("email"));
        c.setCreatedAt(doc.getDate("createdAt"));
        return c;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Surname: " + surname + "\n" +
               "Phone: " + phone + "\n" +
               "Email: " + email + "\n" +
               "Created At: " + createdAt + "\n" +
               "---------------------------";
    }
}