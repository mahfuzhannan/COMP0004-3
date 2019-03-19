<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Patient Data</title>
</head>
<body>

<jsp:include page="./fragments/header.jsp"/>

<div class="main">
  <h4>Search Patients</h4>
  <form action="/patients" method="get">
    <label>Name</label>
    <input type="text" name="name" placeholder="Entername">
    <input type="submit" value="Submit" class="btn btn-primary">
    <hr/>
    <button class="btn btn-danger">
      <a href="/patients">View ALL Patients</a>
    </button>
  </form>
</div>


<jsp:include page="./fragments/footer.jsp"/>

</body>
</html>