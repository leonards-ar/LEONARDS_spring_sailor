<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="report" scope="session" class="leonards.common.report.Report"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<html>
<head>
	<title><%=report.getName()%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">

	<script language="javascript">
		<!--

		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="100%" border="1">
	  <tr> 
	    <td align="center"><img border="0" width="147" height="90" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/yca.gif")%>" align="absmiddle"></td>
	    <td> 
	      <div align="center"><font size="5"><%=report.getName()%></font></div>
	    </td>
	    <td align="center"><img width="203" height="75" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/logoitba.jpg")%>" align="absmiddle"></td>
	  </tr>
	</table>
	<table width="100%" border="1">
		<tr>
	<%
		leonards.common.report.ColumnMetaData aCol;
		for( int i = 0; i < report.getMetaData().getColumnCount(); i++) {
			aCol = report.getMetaData().getColumn(i);
		
	%>
			<td align="center"><b><%=aCol.getLabel()%></b></td>
	<%
		}
	%>
		</tr>
                             <%
                             	leonards.common.report.ReportDataRow aRow;
                              	for( int i = 0; i < report.getData().getRowCount(); i++ ) {
		                      		aRow = (leonards.common.report.ReportDataRow)report.getData().getRow(i);
		                     %>
		<tr>
							<%
									for( int j = 0; j < report.getMetaData().getColumnCount(); j++) {
										aCol = report.getMetaData().getColumn(j);
									
							%>
			<td align="<%=aCol.getAlignment()%>"><%=aCol.getFormatter().format(aRow.getValue(j))%></td>
							<%
									}
							%>
		</tr>
							<%
								}
							%>
	</table>
<%
leonards.spring.domain.User currentAdministrator = null;
try {
	currentAdministrator = user != null ? user : leonards.spring.domain.DBHome.retrieveUser(leonards.spring.domain.StaticDataManager.getParameter(leonards.spring.domain.Parameter.MAIN_ADMIN_ID).getValueAsInteger());	
} catch( Throwable ex ) {
%>
<!--
Error:
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
<%
	}
%>	
<p>Fecha de actualizaci&oacute;n: <%=leonards.common.web.framework.WebFrameworkToolkit.getHtmlShortDate(new java.util.Date())%><br>
  Cantidad de autorizados:  <%=report.getData().getRowCount()%>
</p>
<p align="left">Firma:</p>
<p align="left">&nbsp;</p>
<p align="left">Aclaraci&oacute;n: <%=currentAdministrator.getCompleteName()%></p>
<p>&nbsp; </p>	
</body>
</html>
