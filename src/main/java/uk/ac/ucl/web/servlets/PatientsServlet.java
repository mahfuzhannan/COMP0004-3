package uk.ac.ucl.web.servlets;

import uk.ac.ucl.entities.Patient;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(
        name = "PatientListServlet",
        urlPatterns = {"/patients"}
)
public class PatientsServlet extends HttpServlet {

    private static final Integer PAGE_SIZE = 15;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String name = request.getParameter("name");
        if(name == null) {
            name = "";
        }
        String address = request.getParameter("address");
        if(address == null) {
            address = "";
        }
        List<Patient> patients;
        patients = model.filterPatients(name, address);

        String pageParam = request.getParameter("pageNo");
        int page = 0;

        if(pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        String direction = request.getParameter("direction");
        if (direction != null) {
            if(direction.equalsIgnoreCase("next")) {
                page = page+1;
            } else {
                page = page-1;
            }
        }

        if (page < 0) {
            page = 0;
        }

        if(page*PAGE_SIZE > patients.size()) {
            page = page - 1;
        }
        patients = patients.stream().skip(page * PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());

        request.setAttribute("patients", patients);
        request.setAttribute("name", name);
        request.setAttribute("address", address);
        request.setAttribute("pageNo", page);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patients.jsp");
        dispatch.forward(request, response);
    }
}
