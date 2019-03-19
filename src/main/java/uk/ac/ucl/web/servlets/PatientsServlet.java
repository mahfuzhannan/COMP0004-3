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

    public static final String FIRSTNAME_PARAM = "firstName";
    public static final String LASTNAME_PARAM = "lastName";
    public static final String GENDER_PARAM = "gender";
    public static final String ADDRESS_PARAM = "address";
    public static final String PAGENO_PARAM = "pageNo";
    public static final String PAGELIMIT_PARAM = "pageLimit";
    public static final String PATIENTS_PARAM = "patients";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String firstName = request.getParameter(FIRSTNAME_PARAM);
        String lastName = request.getParameter(LASTNAME_PARAM);
        String gender = request.getParameter(GENDER_PARAM);
        String address = request.getParameter(ADDRESS_PARAM);
        String pageParam = request.getParameter(PAGENO_PARAM);

        List<Patient> patients = model.filterPatients(firstName, lastName, gender, address);

        int page = getPage(pageParam, patients.size());
        int pageLimit = patients.size() / PAGE_SIZE;
        patients = patients.stream().skip(page * PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());

        request.setAttribute(PATIENTS_PARAM, patients);
        request.setAttribute(FIRSTNAME_PARAM, firstName);
        request.setAttribute(LASTNAME_PARAM, lastName);
        request.setAttribute(GENDER_PARAM, gender);
        request.setAttribute(ADDRESS_PARAM, address);
        request.setAttribute(PAGENO_PARAM, page);
        request.setAttribute(PAGELIMIT_PARAM, pageLimit);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patients.jsp");
        dispatch.forward(request, response);
    }

    private int getPage(String pageParam, int limit) {
        int page = 1;

        if(pageParam != null && !"".equals(pageParam)) {
            page = Integer.parseInt(pageParam);
        }

        if (page < 1) {
            page = 1;
        }

        if(page * PAGE_SIZE > limit) {
            page = page - 1;
        }
        return page;
    }
}
