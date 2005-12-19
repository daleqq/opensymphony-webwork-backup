<%--
  Created by IntelliJ IDEA.
  User: rene
  Date: 17.12.2005
  Time: 13:51:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="webwork" prefix="ww" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Available Skills</title></head>

<body>
<h1>Available Skills</h1>
<table>
    <tr>
        <th>Name</th><th>Description</th>
    </tr>
    <ww:iterator value="availableItems">
        <tr>
            <td><a href="<ww:url action="edit"><ww:param name="skillName" value="name"/></ww:url>"><ww:property value="name"/></a></td>
            <td><ww:property value="description"/></td>
        </tr>
    </ww:iterator>
</table>
<!-- Although namescape not correctly specified, the following link should find the right action -->
<p><a href="<ww:url action="showcase" includeParams="false"/>">Back to Showcase Startpage</a></p>
</body>
</html>