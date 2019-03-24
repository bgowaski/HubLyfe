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

@WebServlet("/userschools")
public class UserSchools extends HttpServlet {
	
	protected PublicSchoolDao schoolsDao;
	protected ZipCodeDao zipCodeDao;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		schoolsDao = PublicSchoolDao.getInstance();
		userDao = UserDao.getInstance();
		zipCodeDao = ZipCodeDao.getInstance();
		}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		// Retrieve and validate UserName.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("title", "Invalid username.");
        } else {
        	messages.put("title", "Public School Details for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<PublicSchool> schools = new ArrayList<PublicSchool>();
        try {
        	User user = userDao.getUserByUserName(userName);
        	ZipCode zipCode = zipCodeDao.getZipCodeByZip(user.getResidenceZip());
        	schools = schoolsDao.getPublicSchoolByNeighborhoodName(zipCode.getNeighborhood().getNeighborhoodName());
        
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("schools", schools);
        req.getRequestDispatcher("/UserSchools.jsp").forward(req, resp);
	}

}
