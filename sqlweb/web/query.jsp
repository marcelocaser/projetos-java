<%-- 
    Document   : index
    Created on : 09/11/2015, 17:25:06
    Author     : marce
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Query</title>
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/favicon.ico" />
    </head>
    <body>
        <style>
            .collapse{
                /*font-size: 10px;*/
                display:block;
            }
            .collapse + input{
                display:none;
            }
            .collapse + input + *{
                display:none;
            }
            .collapse+ input:checked + *{
                display:block;
            }
            textarea{text-transform:uppercase; color: steelblue};
        </style>  
        <%
            String query = String.valueOf(request.getParameter("query")).toUpperCase();
        %>
        <c:set var="tipoQuery" value="${param.query}" />
        <h3 style="color: steelblue"></h3>
        <form method="POST" action="${pageContext.request.contextPath}/query.jsp">
            <textarea name="query" autofocus="" style="width:60%; height: 200px; border-color: steelblue; border-radius: 5px; border-top: none; border-left: none; border-right: none" required="" ></textarea>
            <br/>
            <table>
                <input style="height: 25px; background-color: cornflowerblue; color: white; border: none" type="submit" title="Executar query" value="Executar" />
                <input style="height: 25px; background-color: tomato; color: white; border: none" type="hidden" value="Limpar" />
            </table>
            <br/>
        </form>
        <sql:setDataSource var="snapshot" driver="oracle.jdbc.OracleDriver"
                           url="jdbc:oracle:thin:@10.5.101.29:1521:XE"
                           user="visitacao"  password="visitacao"/>
        <label type="button" class="collapse" style="width: 110px; background-color: yellowgreen; border: none; color: white" for="show">Exibir Consulta</label>
        <input id="show" type="checkbox">
        <div>
            <c:if test="${param.query != null}">
                <br/>
                <table style="width: 100%; text-align: left;">
                    <th>
                        <span style="color: steelblue"><%= query%></span>
                    </th>
                </table>
                <div>
                    <br/>
                    <c:choose>
                        <c:when test="${fn:contains(tipoQuery, 'SELECT') or fn:contains(tipoQuery, 'select')}">
                            <sql:query dataSource="${snapshot}" var="result" maxRows="1000">
                                <%= query%>
                            </sql:query>
                        </c:when>
                        <c:otherwise>
                            <sql:update dataSource="${snapshot}" var="count">
                                <%= query%>
                            </sql:update>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
        </div>

        <table style="width: 100%; border: background;">
            <c:forEach var="columnName" items="${result.columnNames}">
                <th style="background-color: steelblue; color: white">
                    <c:out value="${columnName}"/>
                </th>
            </c:forEach>
            <c:forEach var="row" items="${result.rowsByIndex}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td style="background-color: floralwhite; color: cornflowerblue;">
                            <c:out value="${column}"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            <c:if test="${result.rowCount > 0}">
                <tfoot style="height: 50%; background-color: steelblue; color: white; word-wrap: inherit">
                    <tr>
                        <td>${result.rowCount} LINHAS</td>
                    </tr>
                </tfoot>
            </c:if>
        </table>
    </body>
</html>
