<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<%
	java.util.Iterator menuGroups = null;
	try {
		menuGroups = user.getProfile().getMenu().getMenuGroups().iterator();
	} catch( Exception ex ) {
		menuGroups = null;
%>
<!--
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
<%
	}
%>

<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
		function submitForm() 
		{
			document.logoutForm.submit();
			return;
		}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
<!-- Menu Table -->
				<table cellspacing="0" cellpadding="0" width="95%"><tr><td>
					<table cellspacing="0" cellpadding="3" width="100%">
					<%
						leonards.spring.domain.MenuGroup aMenuGroup = null;
						java.util.Iterator menuItems = null;
						leonards.spring.domain.MenuItem aMenuItem = null;
						while( menuGroups != null && menuGroups.hasNext() ) {
							aMenuGroup = (leonards.spring.domain.MenuGroup)menuGroups.next();
							menuItems = aMenuGroup.getMenuItems().iterator();
					%>
						<tr>
							<td class="menu_title_td" colspan="2"><%=aMenuGroup.getDescription()%></td>
						</tr>
					<%
							while( menuItems.hasNext() ) {
								aMenuItem = (leonards.spring.domain.MenuItem)menuItems.next();
					%>
						<tr>
							<td class="menu_opt_td"></td>
							<td class="menu_opt_td"><a class="menu_option" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet?webaction=" + aMenuItem.getTargetUrl())%>" target="<%=aMenuItem.getTarget()%>"><%=aMenuItem.getDescription()%></a></td>
						</tr>
					<%
							}
						}
					%>
						<tr>
							<td class="menu_title_td" colspan="2">Archivos</td>
						</tr>					
						<tr>
							<td class="menu_opt_td"></td>
							<td class="menu_opt_td">
 							   <a class="menu_option" target="bodyFrame" href="http://www.nauticaenelitba.com.ar/spring-files/Primavera/">Archivos / Fotos</a>
							</td>
						</tr>

						<tr>
							<td class="menu_title_td" colspan="2">Salir</td>
						</tr>					
						<tr>
							<td class="menu_opt_td"></td>
							<td class="menu_opt_td">
							<form name="logoutForm" id="logoutForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>" target="_top">
              					<input type="hidden" name="webaction" value="LOGOUT"> 
								<a class="menu_option" href="javascript:submitForm();">Salir</a>
							</form>
							</td>
						</tr>
					</table>
				</td></tr></table>
<!-- End Menu Table -->
			</td>
		</tr>
	</table>
</body>
</html>
