package Controllers;

import Dao.RequestDao;
import model.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/contact")
public class ContactusServlet extends HttpServlet {
    private RequestDao requestDao = new RequestDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        try {
            Request newRequest = new Request();
            newRequest.setFullName(fullName);
            newRequest.setEmail(email);
            newRequest.setMessage(message);
            newRequest.setArchived(false);
            requestDao.saveRequest(newRequest);
            response.sendRedirect("contact?status=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("contact?status=error");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("contact.jsp").forward(request, response);
    }
}
  

