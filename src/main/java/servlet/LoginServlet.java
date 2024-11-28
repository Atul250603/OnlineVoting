package servlet;

import dao.VoterDAO;
import model.Voter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private VoterDAO voterDAO;
    
    public void init() {
        voterDAO = new VoterDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String voterId = request.getParameter("voterId");
        String password = request.getParameter("password");
        
        Voter voter = voterDAO.validateVoter(voterId, password);
        
        if (voter != null) {
            if (voter.isHasVoted()) {
                request.setAttribute("error", "You have already voted!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("voter", voter);
                response.sendRedirect("voting.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid credentials!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}