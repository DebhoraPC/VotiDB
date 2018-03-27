import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DI SOLITO NON FAREMO COSI' MA AVREMO UN POSTO PRECISO IN CUI METTERE TUTTE QUESTE COSE (PATTERN DAO)

public class TestDB {

	public static void main(String[] args) {
		
		// STRINGA CHE IDENTIFICA LA CONNESSIONE AL DATABASE
		String jdbcURL = "jdbc:mysql://localhost/voti?user=root&password=root";
		
		// QUALUNQUE OPERAZIONE DI INPUT O OUTPUT PUO' FALLIRE, QUINDI: SQL EXCEPTION
		try {
			// L'OGGETTO CONNESSIONE PERMETTE DI PARLARE CON IL DATABASE
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			// NAVETTA CHE TRASPORTERA' IL MESSAGGIO SQL
			Statement st = conn.createStatement();
			// ATTENZIONE: NOI PERO' QUESTO OGGETTO STATEMENT NON LO USIAMO MAI, TENDIAMO AD UTILIZZARE 
			// IL PREPARED STATEMENT CHE CONTERRA' LA QUERY PIU' I PARAMETRI 
			
			// STRINGA CHE VOGLIO ESEGUIRE: COPIATA DA HEIDI
			String sql = "SELECT nome,voto " + 
					"FROM libretto " + 
					"ORDER BY voto";
			
			// MECCANISMO PER OTTENERE IL RISULTATO DELLE QUERY
			ResultSet res = st.executeQuery(sql);
			
			while(res.next()) { 
				
				String nome = res.getString("nome");
				int voto = res.getInt("voto");
				
				System.out.format("Voto %d dell'esame %s\n", voto, nome);
			}
			// CHIUDO LO STATEMENT E LIBERO LA MEMORIA DELLA NAVETTA
			st.close(); 
			
			// CHIUDO LA CONNESSIONE
			conn.close();
			
		} catch (SQLException e) {
			// PER ORA QUESTA ECCEZIONE VIENE SEMPLICEMENTE STAMPATA
			e.printStackTrace();
		}

	}

}
