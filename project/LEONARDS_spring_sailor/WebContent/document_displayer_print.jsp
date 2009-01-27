<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="newReservation" scope="session" class="java.lang.String"/>

<%
	boolean isNewReservation = newReservation != null && newReservation.equalsIgnoreCase("yes");
%>
<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function printDocument()
			{
				parent.documentBodyFrame.print();
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" <%=(isNewReservation?"onLoad=\"printDocument();\"":"")%>>
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>
			<a href="javascript:printDocument()" class="action_link">
			Imprimir
			</a>
			</td>
		</tr>
	</table>
</body>
</html>