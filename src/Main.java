import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = null; //Divido l'istanza
        try{
            db = new Database();
        } catch (SQLException e) {  //Se il database non si connette correttamente --> esce dal programma
            System.err.println("Errore di connessione al database" + e.getMessage());
            System.exit(-1);
        }

        //Creo la tabella da parte grafica

        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci il nome del piatto: ");
        String nomePiatto = sc.nextLine();
        System.out.print("Inserisci il prezzo: ");
        float prezzo = sc.nextFloat();
        sc.nextLine(); //
        System.out.print("Inserisci la quantità: ");
        int quantita = sc.nextInt();
        sc.nextLine();

        if(db.insert(nomePiatto, prezzo, quantita))
            System.out.println("Piatto inserito con successo");


        //if(db.insert("Udon, 5, 1'); DROP DB sushi; --", 5, 1))    Devo fare attenzione alle SQL injection --> Le blocco nell'insert con i '?' e il stmt.set...()
        //    System.out.println("Piatto inserito con successo");

        //Faccio le select
        System.out.println(db.selectAll());
    }
}