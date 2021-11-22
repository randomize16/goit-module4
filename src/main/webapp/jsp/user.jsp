<!DOCTYPE html>
<html>
<head>
    <title>View user</title>
    <meta charset="UTF-8">
    <jsp:include page="headers.jsp"/>
</head>
<body>
<jsp:include page="navigation.jsp"/>
<% ua.goit.model.User user = (ua.goit.model.User) request.getAttribute("user"); %>
<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/users" type="button" class="btn btn-success">Back to users</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">ID</label>
            <input type="text" disabled class="form-control"
                   value="<%= user.getId() == null ? "" : user.getId() %>"
                   id="id" placeholder="Id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control"
                   value="<%= user.getName() == null ? "" : user.getName() %>"
                   id="name" placeholder="User name">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <input type="text" class="form-control"
                   value="<%= user.getDescription() == null ? "" : user.getDescription() %>"
                   id="description" placeholder="User description">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Description</label>
            <input type="password" class="form-control"
                   value=""
            id="password" placeholder="Password">
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
    let password = document.getElementById('password');

    function save() {
     let body = {
     <% if(user.getId() != null) {%>
         id: id.value,
      <% } %>
        password: password.value,
        name: name.value,
        description: description.value,
      }
      <% if(user.getId() == null) {%>
         let url = '/users';
         let method = 'POST';
      <% } else { %>
         let url = '/users/<%= user.getId() %>';
         let method = 'PUT';
      <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
        .then( _ => {
            window.location.href = '/users';
        }
        );
    }
</script>
</body>
</html>