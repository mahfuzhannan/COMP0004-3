<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/src/main/webapp/meta.jsp/webapp/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/src/main/webapp/header.jspebapp/header.jsp"/>
<div class="main">
  <h1>Search Result</h1>
  <%
    List<String> patients = (List<String>) request.getAttribute("result");
    if (patients.size() !=0)
    {
    %>
    <ul>
      <%
        for (String patient : patients)
        {
      %>
      <li><%=patient%></li>
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
  </ul>
</div>
<jsp:include page="/src/main/webapp/footer.jspebapp/footer.jsp"/>
</body>
</html>