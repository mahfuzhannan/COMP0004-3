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
        String address = request.getParameter("address");
        List<Patient> patients;
        if(name == null && address == null) {
            patients = model.getPatients();
        } else {
            patients = model.filterPatients(name, address);
        }

        String pageParam = request.getParameter("page");
        int page = 1;

        if(pageParam != null) {
            page = Integer.parseInt(pageParam);
        }

        patients = patients.stream().skip(page * PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());

        request.setAttribute("patients", patients);
        request.setAttribute("name", name);
        request.setAttribute("address", address);
        request.setAttribute("prev", page-1);
        request.setAttribute("next", page+1);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patients.jsp");
        dispatch.forward(request, response);
    }
}
