<%! int fontSize;
    String a;
 %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>

<jsp:include page="navigation.jsp"/>
<%-- This is JSP comment --%>

   <span>Hello <%=session.getAttribute("user")%></span>
    <div style="text-align: right">
        <a href="<%=request.getContextPath()%>/logout">Logout</a>
    </div>
    <% for ( fontSize = 1; fontSize <= 3; fontSize++){ %>
             <font color = "green" size = "<%= fontSize * 3 %>">
                BLOCK
          </font><br />
          <%}%>
</body>
</html>