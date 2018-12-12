package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import util.Thing;
import util.ThingDAO;

/**
 * Servlet implementation class InsertTestData
 */
public class InsertTestData extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsertTestData() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Thing> things = ThingDAO.getAllThings();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status", "success");
		data.put("things", things);
		

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Car car = objectMapper.readValue(json, Car.class);
		
		BufferedReader reader = request.getReader();
		
		ObjectMapper mapper = new ObjectMapper();
		Thing thing = mapper.readValue(reader, Thing.class);
		
		//System.out.println(thing);
		Thing returningThing = ThingDAO.insertThing(thing);
		
		//vrati ono sto je doslo u bazu da bi dobio id, vidi kako bi to islo
		
		String jsonData = mapper.writeValueAsString(returningThing);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
		
		
	}

}
