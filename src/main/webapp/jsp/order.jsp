<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Order page</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
<body>
<%@ include file="navigation.jsp" %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/orders" type="button" class="btn btn-success">Back to categories</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="name" class="form-label">Number</label>
            <input type="text" class="form-control"
                   value="${order.number}"
                   id="name" placeholder="Category name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value="${order.description}"
                   id="description" placeholder="Order description">
        </div>
        <div class="mb-3">
            <label for="userName" class="form-label">User</label>
            <input type="text" class="form-control"
                   value="${order.userName}"
                   id="userName" placeholder="User">
        </div>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Category</th>
                <th scope="col">Item</th>
                <th scope="col">Amount</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="line" items="${order.lines}">
                <tr>
                    <td>${line.category}</td>
                    <td>${line.item}</td>
                    <td>${line.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>