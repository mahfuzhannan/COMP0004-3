<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.entities.Patient" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="./fragments/header.jsp"/>
<div class="main">
    <h2>Patients:</h2>
    <table class="table">
        <thead class="thead-dark">
        <tr>
        <th scope="col">Patient Name</th>
        <th scope="col">Address</th>
        <th scope="col">Identification</th>
        <th scope="col">Details</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Patient> patients = (List<Patient>) request.getAttribute("patients");
            Integer currentPageNo = (Integer) request.getAttribute("pageNo");
            String name = (String) request.getAttribute("name");
            String address = (String) request.getAttribute("address");

            for (Patient patient : patients) {
        %>
        <tr>
            <td scope="row"><%=patient.getPatientName().toString()%></td>
            <td scope="row"><%=patient.getAddress().toString()%></td>
            <td scope="row"><%=patient.getIdentification().toString()%></td>
            <td scope="row"><%=patient.getPatientDetails().toString()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
        <div class="row col-6">
            <form action="/patients" method="get">
                <input type="hidden" name="page" value="<%=currentPageNo%>">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="address" value="<%=address%>">
                <input type="submit" name="direction" value="prev" class="btn btn-primary">
                <input type="submit" name="direction" value="next" class="btn btn-primary">
            </form>
        </div>
    </table>
    <ul>
    </ul>
</div>
<jsp:include page="./fragments/footer.jsp"/>
</body>
</html>
