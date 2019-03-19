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
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(
        name = "StatisticsServlet",
        urlPatterns = {"/statistics"}
)
public class StatisticsServlet extends HttpServlet {

    private static final Integer PAGE_SIZE = 15;

    public static final String YOUNGEST_PARAM = "youngest";
    public static final String OLDEST_PARAM = "oldest";
    public static final String AVERAGEAGE_PARAM = "averageAge";
    public static final String AGERANGE_PARAM = "ageRange";
    public static final String TOTAL_PARAM = "totalPatients";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();

        List<Patient> patients = model.getPatients();

        Patient youngest = patients.stream().sorted(Comparator.comparing(Patient::getBirthDate)).findFirst().get();
        Patient oldest = patients.stream().sorted(Comparator.comparing(Patient::getBirthDate).reversed()).findFirst().get();
        double averageAge = patients.stream().collect(Collectors.averagingDouble(p -> getAge(p.getBirthDate())));
        Map<Integer, Long> ageRange = patients.stream().map(p -> ((getAge(p.getBirthDate()))/10)*10).sorted().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        request.setAttribute(YOUNGEST_PARAM, youngest);
        request.setAttribute(OLDEST_PARAM, oldest);
        request.setAttribute(AVERAGEAGE_PARAM, averageAge);
        request.setAttribute(AGERANGE_PARAM, new TreeMap(ageRange));
        request.setAttribute(TOTAL_PARAM, patients.size());

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/statistics.jsp");
        dispatch.forward(request, response);
    }

    private int getAge(LocalDate date) {
        return LocalDate.now().getYear() - date.getYear();
    }
}
