package Controllers;

import Dao.RequestDao;
import model.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private RequestDao requestDao = new RequestDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminUser") == null) {
            response.sendRedirect("login");
            return;
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        List<Request> activeRequests = requestDao.fetchRequests(false);
        List<Request> archivedRequests = requestDao.fetchRequests(true);
        request.setAttribute("activeRequests", activeRequests);
        request.setAttribute("archivedRequests", archivedRequests);
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminUser") == null) {
            response.sendRedirect("login");
            return;
        }
        String idStr = request.getParameter("id");
        String action = request.getParameter("action");
        if (idStr != null && action != null) {
            int id = Integer.parseInt(idStr);
            try {
                if ("archive".equals(action)) {
                    requestDao.archiveRequest(id);
                } else if ("activate".equals(action)) {
                    requestDao.activateRequest(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("dashboard");
    }
}


