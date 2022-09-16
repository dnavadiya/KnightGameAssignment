<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.valanikevin.gameTable" %>
    <%@ page import = "java.sql.*" %>
    <%@ page import = "java.io.PrintWriter" %>
    <%@ page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><center>
<h1>Non Intelligent Game</h1>
<h2>
<%

int[][] gameTableIntCount = null;
gameTableIntCount = gameTable.getGameTable();
PrintWriter out1 = response.getWriter();

%>
<table border = "1">
<tr>
<th>A</th>
<th>B</th>
<th>C</th>
<th>D</th>
<th>E</th>
<th>F</th>
<th>G</th>
<th>H</th>
</tr>

<tr>
<%
for(int i=0;i<8;i++){
    for(int j=0;j<8;j++){
       
        %>
        <td>
        <% out.print("\t"+gameTableIntCount[i][j]); %>
        </td>
        
        <%
        
    }
    out.println("");%></tr><% 
}
//out1.println(gameTable.getTempValue());
%>

</table>
</h2>
<h2>Total Square Touch :<%=gameTable.getTempValue() %><br>

<h3>If The Square Is Remained Untouched, It Is Marked -1</h3>
<h3><u>Visit Console To See Different Outcomes Of The Trials.</u></h3>
<p>If User Entered Incorrect Row Or Col, Code Will Set It To Default 0, 0</p>
</h2>
</center>
</body>
</html>