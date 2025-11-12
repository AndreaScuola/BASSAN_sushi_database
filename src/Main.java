import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database db = null; //Divido l'istanza
        try{
            db = Database.getInstance(); //--> Non creo un nuovo db ogni volta ma creo un db comune a tutti --> lo prendo con getIstance
        } catch (SQLException e) {  //Se il database non si connette correttamente --> esce dal programma
            System.err.println("Errore di connessione al database" + e.getMessage());
            System.exit(-1);
        }

        gestioneUtente(db);
        System.out.println("\n\nGrazie per aver usato 'Sushi_DB'!");
    }

    public static void gestioneUtente(Database db){
        Scanner sc = new Scanner(System.in);
        boolean continua = true;
        System.out.println("BENVENUTO NEL SUSHI DB\n-------------------------------\n" +
                "Opzioni: \n1)Insert\t2)Select\t3)Update\n4)Delete\t5)Esci dal DB");

        do{
            int scelta;

            do {
                System.out.print("Cosa vuoi fare: ");
                scelta = sc.nextInt();
                sc.nextLine();
            } while (scelta < 1 || scelta > 5);

            switch (scelta){
                case 1:
                    System.out.print("Inserisci il nome del piatto: ");
                    String nome = sc.nextLine();
                    System.out.print("Inserisci prezzo: ");
                    float prezzo = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Inserisci il numero di pezzi: ");
                    int pezzi = sc.nextInt();
                    sc.nextLine();

                    db.insert(nome, prezzo, pezzi);
                    System.out.println("\nPiatto inserito con successo: " + db.select(nome) + "\n");
                    break;
                case 2:
                    System.out.print("Inserisci 'ALL' per vedere tutto il db, oppure il nome del piatto per vederne le informazioni:");
                    nome = sc.nextLine();

                    if(nome.equals("ALL"))
                        System.out.println("\nMENU:\n" + db.selectAll() + "\n");
                    else
                        System.out.println("\nPiatto cercato: " + db.select(nome) + "\n");
                    break;
                case 3:
                    System.out.print("Inserisci l'id del piatto da modificare:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Inserisci il nuovo nome del piatto: ");
                    nome = sc.nextLine();
                    System.out.print("Inserisci il nuovo prezzo: ");
                    prezzo = sc.nextFloat();
                    sc.nextLine();
                    System.out.print("Inserisci il nuovo numero di pezzi: ");
                    pezzi = sc.nextInt();
                    sc.nextLine();

                    db.update(id, nome, prezzo, pezzi);
                    System.out.println("\nPiatto modificato con successo: " + db.select(nome) + "\n");
                    break;
                case 4:
                    System.out.print("Inserisci l'id del piatto da eliminare:");
                    id = sc.nextInt();
                    sc.nextLine();

                    db.delete(id);
                    System.out.println("\nPiatto eliminato con successo");
                    break;
                case 5:
                    continua = false;
                    break;
            }
        } while(continua);
    }
}