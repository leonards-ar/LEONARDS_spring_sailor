<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<%
	String defaultHomePage = null;
	try {
		defaultHomePage = user.getProfile().getDefaultMenuItem("blank.jsp").getTargetUrl();
	} catch( Exception ex ) {
		defaultHomePage = "blank.jsp";
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
</head>
<frameset framespacing="0" cols="15%,*">
	<frame name="menuFrame" noresize frameborder="no" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("menu.jsp")%>" marginheight="1px" marginwidth="1px" scrolling="auto">
	<frame name="bodyFrame" noresize frameborder="no" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet?webaction=" + defaultHomePage)%>" marginheight="1px" marginwidth="1px" scrolling="auto">
</frameset>
	<noframes>
		Para ver &eacute;sta p&aacute;gina se necesita un navegador que soporte frames. 
	</noframes>
</html>
