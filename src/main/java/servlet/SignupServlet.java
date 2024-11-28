package servlet;

import dao.VoterDAO;
import model.Voter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private VoterDAO voterDAO;
    
    @Override
    public void init() throws ServletException {
        try {
            voterDAO = new VoterDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize VoterDAO", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from request
        String voterId = request.getParameter("voterId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Validate input
        if (!validateInput(request, response, voterId, name, password, confirmPassword)) {
            return;
        }
        
        try {
            // Check if voter already exists
            if (voterDAO.voterExists(voterId)) {
                request.setAttribute("error", "Voter ID already exists!");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
                return;
            }
            
            // Create new voter object
            Voter voter = new Voter();
            voter.setVoterId(voterId);
            voter.setName(name);
            voter.setPassword(password);
            voter.setHasVoted(false);
            
            // Register the voter
            boolean success = voterDAO.registerVoter(voter);
            
            if (success) {
                request.setAttribute("message", "Registration successful! Please login.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed! Please try again.");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error occurred. Please try again later.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
    
    private boolean validateInput(HttpServletRequest request, HttpServletResponse response,
            String voterId, String name, String password, String confirmPassword) 
            throws ServletException, IOException {
        
        if (voterId == null || voterId.trim().isEmpty()) {
            request.setAttribute("error", "Voter ID is required!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "Name is required!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Password is required!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return false;
        }
        
        return true;
    }
}