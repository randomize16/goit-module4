<!DOCTYPE html>
<html>
<head>
    <title>Users page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>

<div class="container">
    <div class="row">
        <h2>Users page</h2>
    </div>

    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/user-create" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>