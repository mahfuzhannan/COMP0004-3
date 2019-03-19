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
        <div class="form-group">
            <label for="name">Name</label>
            <input class="form-control" type="text" id="name" name="name" placeholder="Enter a name">
        </div>
        <input type="submit" value="Submit" class="btn btn-block btn-primary">
        <br/>
        <button class="btn btn-block btn-secondary">
            <a href="/patients">View ALL Patients</a>
        </button>
    </form>
</div>


<jsp:include page="./fragments/footer.jsp"/>

</body>
</html>