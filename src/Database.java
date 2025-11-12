import java.sql.*;

public class Database {
    //connetti

    //query
    private Connection connection;
    private static Database instance; //Database static --> uso sempre istance in tutte le classi

    private Database() throws SQLException {     //Mettendo qui l'eccezione la propago --> Chi mi chiama deve gestire l'eccezione --> Nel main blocco l'istanza di un che non funziona
        String url = "jdbc:sqlite:database/sushi.db"; //Specifico cosa uso ed il file da usare
        connection = DriverManager.getConnection(url);  //Stiamo usando un Driver --> software che mette in comunicazione ......FINISCI
        System.out.println("Connected to database");
    }

    //Metto il costruttore private perché non vengano creati oggetti diversi --> creo l'istance (nuovo DB) con getIstance
    public static Database getInstance() throws SQLException {
        if(instance == null)
            instance = new Database();
        return instance;
    }

    public String selectAll() {
        String result = "";

        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }
        
        String query = "SELECT * FROM menu";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(); //Creo un insieme
            while (rs.next()) {
                result += rs.getString("id") + "\t";
                result += rs.getString("piatto") + "\t";
                result += rs.getString("prezzo") + "\t";
                result += rs.getString("quantita") + "\n";
            }
        } catch (SQLException e) {
            System.err.println("Errore di query: " +  e.getMessage());
            return null;
        }

        return result;
    }

    public String select(int id) {
        String result = "";

        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }

        String query = "SELECT * FROM menu WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery(); //Creo un insieme
            while (rs.next()) {
                result += rs.getString("id") + "\t";
                result += rs.getString("piatto") + "\t";
                result += rs.getString("prezzo") + "\t";
                result += rs.getString("quantita") + "\n";
            }
        } catch (SQLException e) {
            System.err.println("Errore di query: " +  e.getMessage());
            return null;
        }

        return result;
    }

    public String select(String nomePiatto) {
        String result = "";

        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }

        String query = "SELECT * FROM menu WHERE piatto LIKE ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + nomePiatto + "%");

            ResultSet rs = stmt.executeQuery(); //Creo un insieme
            while (rs.next()) {
                result += rs.getString("id") + "\t";
                result += rs.getString("piatto") + "\t";
                result += rs.getString("prezzo") + "\t";
                result += rs.getString("quantita") + "\n";
            }
        } catch (SQLException e) {
            System.err.println("Errore di query: " +  e.getMessage());
            return null;
        }

        return result;
    }

    public boolean insert(String nomePiatto, float prezzo, int quantita) {
        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        String query = "INSERT INTO menu(piatto, prezzo, quantita) VALUES(?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, nomePiatto);  //il primo ? è il nomePiatto --> Tecnica per bloccare le SQL injection
            stmt.setFloat(2, prezzo);
            stmt.setInt(3, quantita);

            stmt.executeUpdate();

        } catch (SQLException e)
        {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean update(int id, String nomePiatto, float prezzo, int quantita) {
        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        String query = "UPDATE menu SET piatto = ?, prezzo = ?, quantita = ? WHERE id = ?";

        try{
            PreparedStatement stmt  = connection.prepareStatement(query);
            stmt.setString(1, nomePiatto);
            stmt.setFloat(2, prezzo);
            stmt.setInt(3, quantita);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id){
        //Gestisco l'eccezione al posto di propagarla
        try {
            if(connection == null || !connection.isValid(5)) {  //Contolla la connessione al database
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        String query = "DELETE FROM menu WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        return true;
    }
}
