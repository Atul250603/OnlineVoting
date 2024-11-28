package servlet;

import dao.PartyDAO;
import dao.VoterDAO;
import model.Voter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
    private PartyDAO partyDAO;
    private VoterDAO voterDAO;
    
    public void init() {
        partyDAO = new PartyDAO();
        voterDAO = new VoterDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Voter voter = (Voter) session.getAttribute("voter");
        
        if (voter == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int partyId = Integer.parseInt(request.getParameter("partyId"));
        
        partyDAO.incrementVoteCount(partyId);
        voterDAO.updateVoteStatus(voter.getId());
        
        session.invalidate();
        response.sendRedirect("thank-you.jsp");
    }
}