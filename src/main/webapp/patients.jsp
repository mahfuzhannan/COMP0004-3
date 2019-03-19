<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.entities.Patient" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="uk.ac.ucl.web.servlets.PatientsServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="./fragments/header.jsp"/>
<div class="main">
    <%
        List<Patient> patients = (List<Patient>) request.getAttribute(PatientsServlet.PATIENTS_PARAM);
        String firstName = (String) request.getAttribute(PatientsServlet.FIRSTNAME_PARAM);
        String lastName = (String) request.getAttribute(PatientsServlet.LASTNAME_PARAM);
        String gender = (String) request.getAttribute(PatientsServlet.GENDER_PARAM);
        String address = (String) request.getAttribute(PatientsServlet.ADDRESS_PARAM);
        Integer currentPageNo = (Integer) request.getAttribute(PatientsServlet.PAGENO_PARAM);
        Integer pageLimit = (Integer) request.getAttribute(PatientsServlet.PAGELIMIT_PARAM);
    %>
    <div class="row">
        <div class="col-6">
            <h2>Patients</h2>
        </div>
        <div class="col-6">
            <span>FirstName: <span class="badge badge-info"><%=(firstName == null || "".equals(firstName)) ? "None" : firstName%></span></span> |
            <span>LastName: <span class="badge badge-info"><%=(lastName == null || "".equals(lastName)) ? "None" : lastName%></span></span> |
            <span>Gender: <span class="badge badge-info"><%=(gender == null || "".equals(gender)) ? "None" : gender%></span></span> |
            <span>Address: <span class="badge badge-info"><%=(address == null || "".equals(address)) ? "None" : address%></span></span> |
        </div>
    </div>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Patient Name</th>
            <th scope="col">Address</th>
            <th scope="col">SSN</th>
            <th scope="col">Drivers</th>
            <th scope="col">Passport</th>
            <th scope="col">Maiden</th>
            <th scope="col">Marital</th>
            <th scope="col">Race</th>
            <th scope="col">Ethnicity</th>
            <th scope="col">Birthplace</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Patient patient : patients) {
        %>
        <tr>
            <td scope="row"><%=patient.getPatientName().toString()%></td>
            <td scope="row"><%=patient.getAddress().toString()%></td>
            <td scope="row"><%=patient.getIdentification().getSsn()%></td>
            <td scope="row"><%=patient.getIdentification().getDrivers()%></td>
            <td scope="row"><%=patient.getIdentification().getPassport()%></td>
            <td scope="row"><%=patient.getPatientDetails().getMaiden()%></td>
            <td scope="row"><%=patient.getPatientDetails().getMarital()%></td>
            <td scope="row"><%=patient.getPatientDetails().getRace()%></td>
            <td scope="row"><%=patient.getPatientDetails().getEthnicity()%></td>
            <td scope="row"><%=patient.getPatientDetails().getBirthplace()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <form action="/patients" method="get">
        <input type="hidden" name="firstName" value="<%=firstName%>">
        <input type="hidden" name="lastName" value="<%=lastName%>">
        <input type="hidden" name="gender" value="<%=gender%>">
        <input type="hidden" name="address" value="<%=address%>">
        <div class="form-row">
            <div class="btn-toolbar col" role="toolbar">
                <div class="btn-group btn-block" role="group">
                    <%
                        int firstPage = currentPageNo - 3;
                        if (firstPage < 1) {
                            firstPage = 1;
                        }
                        int btnLimit = firstPage + 9;
                        if (btnLimit > pageLimit) {
                            btnLimit = pageLimit;
                        }
                        for (int i = firstPage; i <= btnLimit; i++) {
                    %>
                    <input type="submit" class="btn <%=(currentPageNo==i) ? "btn-primary" : "btn-secondary"%>" name="pageNo" value="<%=i%>">
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </form>
    <ul>
    </ul>
</div>
<jsp:include page="./fragments/footer.jsp"/>
</body>
</html>
