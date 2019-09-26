package zavrsniProjekat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) throws SQLException {
		Baza db = new Baza("jdbc:sqlite:C:\\Users\\Dell\\Downloads\\Dictionary.db");
		db.connect();
		try {
			Knjiga knjiga = new Knjiga("C:\\Users\\Dell\\Downloads\\knjiga");
//			db.insertNew(knjiga.nepostojeceReci(db.getRecnik().keySet()));
			knjiga.najcesce();
			knjiga.sveReci();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nije nadjen fajl");
		}
		db.disconnect();
	}

}
