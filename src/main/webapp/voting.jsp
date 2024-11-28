<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.PartyDAO" %>
<%@ page import="model.Party" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voting System - Cast Your Vote</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%
        if(session.getAttribute("voter") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        PartyDAO partyDAO = new PartyDAO();
        List<Party> parties = partyDAO.getAllParties();
    %>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Cast Your Vote</h2>
        <div class="row">
            <% for(Party party : parties) { %>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <img src="images/<%= party.getSymbol() %>" alt="<%= party.getName() %> Symbol" 
                                 class="img-fluid mb-3" style="max-height: 100px;">
                            <h5 class="card-title"><%= party.getName() %></h5>
                            <form action="vote" method="post">
                                <input type="hidden" name="partyId" value="<%= party.getId() %>">
                                <button type="submit" class="btn btn-primary">Vote</button>
                            </form>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>