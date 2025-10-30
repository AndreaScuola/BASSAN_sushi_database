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

        /*
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
        */

        //Prove
        System.out.println("Select all: \n" + db.selectAll());
        System.out.println("--------\nPiatto con id 1: " + db.select(1));
        System.out.println("--------\nPiatto con nome 'Udon': " + db.select("Udon"));

        if(db.insert("tiger roll", 3, 2))
            System.out.println("--------\nInsert piatto tiger roll: " + db.select("tiger roll"));

        if(db.update(4, "Udon frutti di mare", 4.50F, 1))
            System.out.println("--------\nUpdated Udon: " + db.select("Udon frutti di mare"));

        db.delete(12);
        System.out.println("-------\nSelect all dopo cancellazione del 11: \n" + db.selectAll());

        //if(db.insert("Udon, 5, 1'); DROP DB sushi; --", 5, 1))    Devo fare attenzione alle SQL injection --> Le blocco nell'insert con i '?' e il stmt.set...()
        //    System.out.println("Piatto inserito con successo");

        System.out.println(db.selectAll());
    }
}