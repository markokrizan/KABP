package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThingDAO {
	
	
	public static List<Thing> getAllThings() {
		List<Thing> things = new ArrayList<Thing>();

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM dbo.thing;";

			pstmt = conn.prepareStatement(query);
			

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Integer id = rset.getInt("thing_id");
				String name = rset.getString("thing_name");
				Date date = rset.getDate("thing_date");

				Thing thing = new Thing(id, name, date);
				things.add(thing);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return things;
	}
	
	public static Thing insertThing(Thing thing) {
		
		Connection conn = ConnectionManager.getConnection();
		System.out.println("Connection: " + conn);

		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO thing (thing_name, thing_date) VALUES (?, ?)";

			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
			System.out.println(thing.getName());
			pstmt.setString(1, thing.getName());
			
			
			System.out.println(thing.getDate());
			pstmt.setObject(2, thing.getDate());
			
			
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (INSERT, UPDATE, DELETE), jednom za svaki SQL upit
			pstmt.executeUpdate();
			
			ResultSet returnedKeys = pstmt.getGeneratedKeys();
			if(returnedKeys.next()) {
				Integer generatedId = returnedKeys.getInt(1);
				thing.setId(generatedId);
				
			}
			
			
		
			return thing;
			
			
			
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return null;
	}
	
	

}
