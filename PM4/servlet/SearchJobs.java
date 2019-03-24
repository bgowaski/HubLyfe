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

@WebServlet("/searchjobs")
public class SearchJobs extends HttpServlet  {
	protected JobDetailDao jobsDao;
	
	@Override
	public void init() throws ServletException {
		jobsDao = JobDetailDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<JobDetail> jobs = new ArrayList<JobDetail>();
        
    
        String jobTitle = req.getParameter("jobtitle");
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            messages.put("success", "Please enter a valid job title.");
        } else {
        	
        	try {
            	jobs = jobsDao.getJobDetailByPartialTitle(jobTitle);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + jobTitle);
        
        }
        req.setAttribute("jobs", jobs);
        
        req.getRequestDispatcher("/SearchJobs.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<JobDetail> jobs = new ArrayList<JobDetail>();
        
        String jobTitle = req.getParameter("jobtitle");
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            messages.put("success", "Please enter a valid job title.");
        } else {
        	
        	try {
            	jobs = jobsDao.getJobDetailByPartialTitle(jobTitle);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + jobTitle);
        }
        req.setAttribute("jobs", jobs);
        
        req.getRequestDispatcher("/SearchJobs.jsp").forward(req, resp);
    }

}
