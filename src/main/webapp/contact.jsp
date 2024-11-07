<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
</head>
<body>
    <h2>Contact Us</h2>
    <c:if test="${param.status == 'success'}">
        <p style="color:green; font-weight:bold;">Form Submitted Successfully</p>
    </c:if>
    <c:if test="${param.status == 'error'}">
        <p style="color:red; font-weight:bold;">There was an error submitting your form. Please try again.</p>
    </c:if>

    <form action="contact" method="post">
        <label>Full Name:</label><br>
        <input type="text" name="fullName" required><br><br>
        
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>
        
        <label>Message:</label><br>
        <textarea name="message" required></textarea><br><br>
        
        <button type="submit">Submit</button>
    </form>

    <p><a href="login">Admin Login</a></p>
</body>
</html>