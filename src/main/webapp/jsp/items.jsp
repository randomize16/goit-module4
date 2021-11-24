<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Items page</title>
    <%@ include file="headers.jsp" %>

</head>
<body>

<%@ include file="navigation.jsp" %>


<div class="container">
    <div class="row">
        <h2>Items page</h2>
    </div>

    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/users/new" type="button" class="btn btn-primary">New</a>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">CategoryId</th>
                <th scope="col">Description</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td><a href="/categories/${item.categoryId}">${item.categoryId}</a></td>
                    <td>${item.description}</td>
                    <td>
                        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                            <div class="btn-group me-2" role="group" aria-label="Second group">
                                <a href="/items/${item.id}" type="button" class="btn btn-warning">Edit</a>
                                <a href="/items?deleteId=${item.id}" type="button" class="btn btn-danger">Remove</a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>