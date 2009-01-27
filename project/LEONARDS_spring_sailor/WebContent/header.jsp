<%
leonards.spring.domain.User defaultAdministrator = null;
try {
	defaultAdministrator = leonards.spring.domain.DBHome.retrieveUser(leonards.spring.domain.StaticDataManager.getParameter(leonards.spring.domain.Parameter.MAIN_ADMIN_ID).getValueAsInteger());	
} catch( Throwable ex ) {
%>
<!--
Error:
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
<%
	}
%>

<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
</head>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
    <td align="center"> 
      <table background="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/header-bkg.gif")%>" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" width="100%">
					<tr>
						<td valign="top" rowspan="2"><img width="305px" height="75px" border="0" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/header-left.gif")%>"></td>
						<td width="100%" valign="top"><img border="0" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/header-trans.gif")%>"></td>
						<td valign="top"><img border="0" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("images/header-right.gif")%>"></td> 
					</tr>
					<tr>
						<td colspan="2" class="value" align="right">
						<% if( defaultAdministrator != null ) { %>
						Pod&eacute;s contactar al administrador: <a href="mailto:<%=defaultAdministrator.getEmail()%>"><%=defaultAdministrator.getCompleteName()%></a>&nbsp;&nbsp;
						<% } else { %>
						&nbsp;
						<% } %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>