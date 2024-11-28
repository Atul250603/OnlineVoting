<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.PartyDAO" %>
<%@ page import="model.Party" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Online Voting System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        .hero-section {
            background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), 
                        url('images/voting-background.jpg');
            background-size: cover;
            background-position: center;
            min-height: 100vh;
            color: white;
        }
        .card {
            background-color: rgba(255, 255, 255, 0.9);
        }
        .feature-icon {
            font-size: 2rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="hero-section">
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="#">Online Voting System</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>

        <div class="container py-5">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <h1 class="display-4 mb-4">Exercise Your Right to Vote</h1>
                    <p class="lead mb-4">Secure, Transparent, and Easy-to-use Online Voting Platform</p>
                </div>
                <div class="col-md-5 offset-md-1">
                    <div class="card">
                        <div class="card-body">
                            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="pills-login-tab" data-bs-toggle="pill" 
                                            data-bs-target="#pills-login" type="button" role="tab">Login</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="pills-signup-tab" data-bs-toggle="pill" 
                                            data-bs-target="#pills-signup" type="button" role="tab">Sign Up</button>
                                </li>
                            </ul>
                            <div class="tab-content" id="pills-tabContent">
                                <div class="tab-pane fade show active" id="pills-login" role="tabpanel">
                                    <form action="login" method="post">
                                        <div class="mb-3">
                                            <label for="loginVoterId" class="form-label">Voter ID</label>
                                            <input type="text" class="form-control" id="loginVoterId" name="voterId" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="loginPassword" class="form-label">Password</label>
                                            <input type="password" class="form-control" id="loginPassword" name="password" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100">Login</button>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="pills-signup" role="tabpanel">
                                    <a href="signup.jsp" class="btn btn-success w-100">Create New Account</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%
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
                                 class="img-fluid mb-3 rounded-circle" style="max-height: 100px;">
                           
                            <h5 class="card-title text-dark"><%= party.getName() %></h5>
                            <div class="text-dark">Votes : <%= party.getVoteCount() %></div>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>
    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>