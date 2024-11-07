<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Prevent caching so that users can't go back to the dashboard after logging out
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Contact Requests</title>
    <style>
        .logout-button {
            position: fixed;
            top: 20px;
            right: 20px;
            background-color: #f44336;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-button:hover {
            background-color: #d32f2f;
        }
        .request-container {
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
    <a href="logout" class="logout-button">Logout</a>
    <h2>Admin Dashboard - Contact Requests</h2>

    <h3>Active Requests</h3>
    <c:if test="${empty activeRequests}">
        <p>No active requests available.</p>
    </c:if>
    <c:forEach var="request" items="${activeRequests}">
        <div class="request-container">
            <p><strong>Name:</strong> ${request.fullName}</p>
            <p><strong>Email:</strong> ${request.email}</p>
            <p><strong>Message:</strong> ${request.message}</p>
            <!-- Archive form -->
            <form action="dashboard" method="post" style="display:inline;">
                <input type="hidden" name="id" value="${request.id}">
                <input type="hidden" name="action" value="archive">
                <button type="submit">Archive</button>
            </form>
        </div>
    </c:forEach>

    <h3>Archived Requests</h3>
    <c:if test="${empty archivedRequests}">
        <p>No archived requests available.</p>
    </c:if>
    <c:forEach var="request" items="${archivedRequests}">
        <div class="request-container">
            <p><strong>Name:</strong> ${request.fullName}</p>
            <p><strong>Email:</strong> ${request.email}</p>
            <p><strong>Message:</strong> ${request.message}</p>
            <!-- Activate form -->
            <form action="dashboard" method="post" style="display:inline;">
                <input type="hidden" name="id" value="${request.id}">
                <input type="hidden" name="action" value="activate">
                <button type="submit">Activate</button>
            </form>
        </div>
    </c:forEach>
</body>
</html>
