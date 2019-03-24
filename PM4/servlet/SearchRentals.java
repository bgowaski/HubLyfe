package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.*;
import model.*;

@WebServlet("/searchrentals")
public class SearchRentals extends HttpServlet  {
	protected RentDao rentDao;
	
	@Override
	public void init() throws ServletException {
		rentDao = RentDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Rent> rents = new ArrayList<Rent>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String neighborhoodName = req.getParameter("neighborhoodname");
        if (neighborhoodName == null || neighborhoodName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid occupancy type.");
        } else {
        	// Retrieve Users, and store as a message.
        	try {
            	rents = rentDao.getRentByNeighborhoodName(neighborhoodName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + neighborhoodName);
        
        }
        req.setAttribute("rents", rents);
        
        req.getRequestDispatcher("/SearchRentals.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Rent> rents = new ArrayList<Rent>();
        
        String neighborhoodName = req.getParameter("neighborhoodname");
        if (neighborhoodName == null || neighborhoodName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid neighborhood name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	rents = rentDao.getRentByNeighborhoodName(neighborhoodName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + neighborhoodName);
        }
        req.setAttribute("rents", rents);
        
        req.getRequestDispatcher("/SearchRentals.jsp").forward(req, resp);
    }

}
