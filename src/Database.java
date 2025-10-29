import java.sql.*;

public class Database {
    //conntetti

    //query
    private Connection connection;

    public Database() throws SQLException {     //Mettendo qui l'eccezione la propago --> Chi mi chiama deve gestire l'eccezione --> Nel main blocco l'istanza di un che non funziona
        String url = "jdbc:sqlite:database/sushi.db"; //Specifico cosa uso ed il file da usare
        connection = DriverManager.getConnection(url);  //Stiamo usando un Driver --> software che mette in comunicazione ......FINISCI
        System.out.println("Connected to database");
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
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(); //Creo un insieme
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
}
