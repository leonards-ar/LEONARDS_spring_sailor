<%
	String value = request.getParameter("breakframes");
	boolean breakFrames = (value != null && value.length() > 0 ); 
%>
<html>
<head>
	<title>Sistema de Reservas Frana</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	
	<script language="javascript">
		<!--
		<% if(breakFrames) {%>
			function break_out_frames()
			{
			  if (top.location != location) {
			    top.location.href = document.location.href ;
			  }
			}
			
			break_out_frames();		
		<% } %>
		-->
	</script>
</head>
<frameset framespacing="0" rows="90px,*">
	<frame name="headerFrame" noresize frameborder="no" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("header.jsp")%>" marginheight="1px" marginwidth="1px" scrolling="no">
	<frame name="mainFrame" noresize frameborder="no" src="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("login.jsp")%>" marginheight="1px" marginwidth="1px" scrolling="auto">
	<noframes>
		Para ver &eacute;sta p&aacute;gina se necesita un navegador que soporte frames. 
	</noframes>
</frameset>
</html>
