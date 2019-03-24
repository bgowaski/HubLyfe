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

@WebServlet("/searchrestaurants")
public class SearchRestaurants extends HttpServlet  {
	protected RestaurantDao restaurantDao;
	
	@Override
	public void init() throws ServletException {
		restaurantDao = RestaurantDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        
    
        String zip = req.getParameter("zipcode");
        if (zip == null || zip.trim().isEmpty()) {
            messages.put("success", "Please enter a valid zip code.");
        } else {
        	
        	try {
        		zip = zip.substring(1,4);
        		int zipInt = Integer.parseInt(zip);
        		restaurants = restaurantDao.getRestaurantByZipCode(zipInt);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for zipcode" + zip);
        
        }
        req.setAttribute("restaurants", restaurants);
        
        req.getRequestDispatcher("/SearchRestaurants.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        
    
        String zip = req.getParameter("zipcode");
        if (zip == null || zip.trim().isEmpty()) {
            messages.put("success", "Please enter a valid zip code.");
        } else {
        	
        	try {
        		int zipInt = Integer.parseInt(zip);
        		restaurants = restaurantDao.getRestaurantByZipCode(zipInt);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + zip);
        
        }
        req.setAttribute("restaurants", restaurants);
        
        req.getRequestDispatcher("/SearchRestaurants.jsp").forward(req, resp);
	}
	


}
