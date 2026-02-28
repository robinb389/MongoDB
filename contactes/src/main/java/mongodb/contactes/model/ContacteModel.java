package mongodb.contactes.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContacteModel {

    private static final String APIURL = "https://api-iota-eosin-79.vercel.app";
    private final HttpClient client = HttpClient.newHttpClient();

    public void inserirContacte(Contacte contacte) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIURL + "/add"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(contacte.toJson()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                System.out.println("Error afegint contacte: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteContacte(String id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIURL + "/delete/" + id))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error eliminant contacte: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateContacte(String id, Contacte nouContacte) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIURL + "/update/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(nouContacte.toJson()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error modificant contacte: " + response.body());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Contacte> getAllContactes() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIURL + "/list"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseContactes(new JSONArray(response.body()));
        } catch (Exception e) {
            System.out.println("Error carregant contactes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Contacte> getByDate(Date inici, Date fi) {
        String start = new java.text.SimpleDateFormat("yyyy-MM-dd").format(inici);
        String end   = new java.text.SimpleDateFormat("yyyy-MM-dd").format(fi);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(APIURL + "/listrange?start=" + start + "&end=" + end))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseContactes(new JSONArray(response.body()));
        } catch (Exception e) {
            System.out.println("Error cercant per data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Contacte> getFilteredContactes(String cerca) {
        List<Contacte> tots = getAllContactes();
        List<Contacte> filtrats = new ArrayList<>();
        for (Contacte c : tots) {
            if (c.getName().toLowerCase().contains(cerca.toLowerCase()) ||
                c.getSurname().toLowerCase().contains(cerca.toLowerCase()) ||
                c.getEmail().toLowerCase().contains(cerca.toLowerCase())) {
                filtrats.add(c);
            }
        }
        return filtrats;
    }

    private List<Contacte> parseContactes(JSONArray jsonArray) {
        List<Contacte> llista = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Contacte c = new Contacte(
                obj.getString("_id"),
                obj.getString("name"),
                obj.getString("surname"),
                obj.getString("phone"),
                obj.getString("email")
            );
            llista.add(c);
        }
        return llista;
    }
}