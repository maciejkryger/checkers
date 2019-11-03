<%@ page import="javafx.scene.control.TextField" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--Expression language jest włączony--%>
<%@ page isELIgnored="false" %>
<%--JSTL jest włączony--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Checkers</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>


<body>
<div>
    <h1>Checkers</h1>
</div>

<div class="boardDiv">
    <table>
        <thead>
        <tr>
            <th></th>
            <th>a</th>
            <th>b</th>
            <th>c</th>
            <th>d</th>
            <th>e</th>
            <th>f</th>
            <th>g</th>
            <th>h</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>


            <%
                int line = 1;
            %>
            <th><%=line%>
            </th>
            <c:forEach items="${board}" var="boardItem" varStatus="status">

            <c:if test="${(boardItem.value)!=null}">
            <td class="boardFieldBlack"></c:if>
                <c:if test="${(boardItem.value)==null}">
            <td class="boardFieldWhite"></c:if>

                    ${boardItem.value}</td>

            <c:if test="${(boardItem.key+1)%8==0}">
            <th><%=line%>
            </th>
        </tr>
        <%
            line++;
        %>
        <c:if test="<%=line<9%>">
        <tr>
            <th><%=line%>
            </th>
            </c:if>
            </c:if>
            </c:forEach>
        </tr>
        </tbody>
        <thead>
        <tr>
            <th></th>
            <th>a</th>
            <th>b</th>
            <th>c</th>
            <th>d</th>
            <th>e</th>
            <th>f</th>
            <th>g</th>
            <th>h</th>
            <th></th>
        </tr>
        </thead>
    </table>
</div>

<div class="moveFields">

    <c:if test="${requestScope.currentPawn=='X'}">
        <h3><c:out value="Now is move of: X"/></h3>
    </c:if>
    <c:if test="${requestScope.currentPawn=='O'}">
        <h3><c:out value="Now is move of: O"/></h3>
    </c:if>
    <br>
    <form method="post">
        <label for="pawnToMove">point of pawn you move:</label>
        <input type="text" id="pawnToMove" name="moveFrom" class="moveFieldsToFill"><br>
        <label for="wherePawnMove">point where you move:</label>
        <input type="text" id="wherePawnMove" name="moveWhere" class="moveFieldsToFill"><br>

        <input type="submit" class="accept-button" value="Accept move">
    </form>
    <br><br>
    <c:if test="${requestScope.winner!=null}">
        <c:if test="${requestScope.winner=='X'}">
            <h3><c:out value="The winner is: X"/></h3>
        </c:if>
        <c:if test="${requestScope.winner=='O'}">
            <h3><c:out value="The winner is: O"/></h3>
        </c:if>
    </c:if>
    <c:if test="${requestScope.isMoveCorrect==false}">
        <h3 class="error"><c:out value="Wrong move!"/></h3>
    </c:if>

</div>

</body>

<div class="footer">
    Author: Maciej Kryger
</div>
</html>