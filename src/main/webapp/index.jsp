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
        <div class="form-row">
            <div class="form-group col-sm-4">
                <label for="firstName">First Name</label>
                <input class="form-control" type="text" id="firstName" name="firstName"
                       placeholder="Enter a first name">
            </div>
            <div class="form-group col-sm-4">
                <label for="lastName">Last Name</label>
                <input class="form-control" type="text" id="lastName" name="lastName" placeholder="Enter a last name">
            </div>
            <div class="form-group col-sm-4">
                <label for="gender">Gender</label>
                <select id="gender" name="gender" class="form-control">
                    <option value="" selected>Any</option>
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input class="form-control" type="text" id="address" name="address">
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