<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<%
	String bodyDocument = request.getParameter("document");
%>
<html>
<head>
	<title>Sistema de Reservas Frana</title>
	<jsp:include page="html_header_inc.jsp"/>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<frameset framespacing="0" rows="15px,*">
	<frame name="printDocumentFrame" noresize frameborder="no" src="<%=WebFrameworkToolkit.getDocumentUrl("document_displayer_print.jsp")%>" marginheight="0px" marginwidth="0px" scrolling="auto">
	<frame name="documentBodyFrame" noresize frameborder="no" src="<%=WebFrameworkToolkit.getDocumentUrl(bodyDocument)%>" marginheight="0px" marginwidth="0px" scrolling="auto">
</frameset>
<noframes>
	Para ver &eacute;sta p&aacute;gina se necesita un navegador que soporte frames. 
</noframes>
</html>