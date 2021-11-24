<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Category page</title>
    <meta charset="UTF-8">
    <%@ include file="headers.jsp" %>

</head>
<body>
<% ua.goit.model.Category category = (ua.goit.model.Category) request.getAttribute("category"); %>
<%@ include file="navigation.jsp" %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/categories" type="button" class="btn btn-success">Back to categories</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="${category.id}"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value="${category.name}"
                   id="name" placeholder="Category name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value="${category.description}"
                   id="description" placeholder="Category description">
        </div>
        <div class="form-floating">
            <select class="form-select" id="parentId" value="{category.parentId}"
                    aria-label="Floating label select example">
                <option selected disabled>Select parent category</option>
                <c:forEach var="element" items="${categories}">
                    <option value="${element.id}">${element.name}</option>
                </c:forEach>
            </select>
            <label for="parentId">Parent category</label>
        </div>
    </div>
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>
<script>
    let id = document.getElementById('id');
    let name = document.getElementById('name');
    let description = document.getElementById('description');
    let parentId = document.getElementById('parentId');

    function save() {
     let body = {
     <% if(category.getId() != null) {%>
         id: id.value,
      <% } %>
        parentId: parentId.value,
        name: name.value,
        description: description.value,
      }
      <% if(category.getId() == null) {%>
         let url = '/categories';
         let method = 'POST';
      <% } else { %>
         let url = '/categories/<%= category.getId() %>';
         let method = 'PUT';
      <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/categories';
        }
        );
    }
</script>
</body>
</html>