package mongodb.contactes.model;

import org.json.JSONObject;

public class Contacte {

    private String id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public Contacte() {}

    public Contacte(String id,String name, String surname, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

       public Contacte(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("surname", surname);
        json.put("phone", phone);
        json.put("email", email);
        return json.toString();
    }

    // Getters and setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID = "  + id +  " || Name = " + name + " || surname = " + surname + " || phone = " + phone + " || email = " + email;
    }

}