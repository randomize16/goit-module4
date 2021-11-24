<%! int fontSize;
    String a;
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main page</title>
    <%@ include file="headers.jsp" %>

</head>
<body>

<%@ include file="navigation.jsp" %>

<%-- This is JSP comment --%>

   <span>Hello <c:out value="${sessionScope.user}"/></span>
    <div style="text-align: right">
        <a href="<%=request.getContextPath()%>/logout">Logout</a>
    </div>
    <c:forEach var = "i" begin = "1" end = "3">
        <font color = "green" size = '<c:out value = "${i * 3}"/>'>
            BLOCK
        </font><br />
    </c:forEach>
</body>
</html>