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

@WebServlet("/userjobdetails")
public class UserJobDetail extends HttpServlet {
	
	protected JobDetailDao jobDetailDao;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		jobDetailDao = JobDetailDao.getInstance();
		userDao = UserDao.getInstance();
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
        	messages.put("title", "Job Details for " + userName);
        }
        
        // Retrieve BlogUsers, and store in the request.
        List<JobDetail> jobdetails = new ArrayList<JobDetail>();
        try {
        	User user = userDao.getUserByUserName(userName);
        	jobdetails = jobDetailDao.getJobDetailByZipCode(user.getOccupationZip());
        
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("jobdetails", jobdetails);
        req.getRequestDispatcher("/UserJobDetails.jsp").forward(req, resp);
	}

}
