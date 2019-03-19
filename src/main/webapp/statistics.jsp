<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.entities.Patient" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="uk.ac.ucl.web.servlets.PatientsServlet" %>
<%@ page import="uk.ac.ucl.web.servlets.StatisticsServlet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.SortedMap" %>
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
        Patient youngest = (Patient) request.getAttribute(StatisticsServlet.YOUNGEST_PARAM);
        Patient oldest = (Patient) request.getAttribute(StatisticsServlet.OLDEST_PARAM);
        Double averageAge = (Double) request.getAttribute(StatisticsServlet.AVERAGEAGE_PARAM);
        Map<Integer, Long> ageRange = (Map) request.getAttribute(StatisticsServlet.AGERANGE_PARAM);
        Integer totalPatients = (Integer) request.getAttribute(StatisticsServlet.TOTAL_PARAM);

    %>
    <div class="row">
        <div class="col-6">
            <h1>Patients Statistics</h1>
        </div>

    </div>
    <h4>Total Patients: <span class="badge badge-primary"><%=totalPatients%></span> </h4>
    <h2>Age Stats</h2>
    <h4>Average Age: <span class="badge badge-primary"><%=averageAge%></span> </h4>
    <table class="table table-striped">
        <thead>
        <th scope="col">Stat</th>
        <th scope="col">Patient</th>
        <th scope="col">Age</th>
        </thead>
        <tbody>
        <tr>
            <td scope="row">Youngest</td>
            <td scope="row"><%=youngest.getPatientName()%></td>
            <td scope="row"><%=LocalDate.now().getYear()-youngest.getBirthDate().getYear()%></td>
        </tr>
        <tr>
            <td scope="row">Oldest</td>
            <td scope="row"><%=oldest.getPatientName()%></td>
            <td scope="row"><%=LocalDate.now().getYear()-oldest.getBirthDate().getYear()%></td>
        </tr>
        </tbody>
    </table>
    <h2>Age Ranges</h2>
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Age</th>
            <th scope="col">Count</th>
        </tr>
        </thead>
        <tbody>
        <%
            int total = 0;
            for (Iterator<Map.Entry<Integer, Long>> it = ageRange.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<Integer, Long> entry = it.next();
                int age = entry.getKey();
                long count = entry.getValue();
                total += count;
        %>
        <tr>
            <td scope="row"><%=age+1%> - <%=age+10%></td>
            <td scope="row"><%=count%>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td scope="row">Total</td>
            <td scope="row"><%=total%></td>
        </tr>
        </tbody>
    </table>
</div>
<jsp:include page="./fragments/footer.jsp"/>
</body>
</html>
