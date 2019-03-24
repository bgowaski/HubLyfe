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
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Restaurant> restuarants = new ArrayList<Restaurant>();
        
        String type = req.getParameter("restaurantType");
        Restaurant.RestaurantType type2 = Restaurant.RestaurantType.EATING_AND_DRINKING;
        if (type == null || type.trim().isEmpty()) {
            messages.put("success", "Please enter a valid restuarant type.");
        } else {
        	
        	try {
        		
        		if(type == "Eating & Drinking")
        		{
        			type2 = Restaurant.RestaurantType.EATING_AND_DRINKING;
        		}
        		else if(type == "Retail Food")
        		{
        			type2 = Restaurant.RestaurantType.RETAIL_FOOD;
        		}
        		else if(type == "Eating & Drinking w/ Take Out")
        		{
        			type2 = Restaurant.RestaurantType.EATING_AND_DRINKING_WITH_TAKEOUT;
        		}
        		else if(type == "Mobile Food Walk On")
        		{
        			type2 = Restaurant.RestaurantType.MOBILE_FOOD_WALK_ON;
        		}
        		restuarants = restaurantDao.getRestaurantByRestaurantType(type2);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + type);
        }
        req.setAttribute("restaurants", restuarants);
        
        req.getRequestDispatcher("/SearchRestaurants.jsp").forward(req, resp);
    }

}
