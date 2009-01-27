<%
String username;
try {
	username = leonards.common.web.framework.WebFrameworkToolkit.getHtmlString(request.getParameter("username"));
} catch( Throwable ex ) {

	username = "";
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
			function doLogin()
			{
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="loginForm" id="loginForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="LOGIN">     

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br><br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Login Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" class="title3_bg"><div class="title3_txt">Ingrese sus datos para entrar al sistema</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">usuario:</div></td>
							<td align="left"><input type="text" name="username" id="username" maxlength="50" size="20" value="<%=username%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">contraseña:</div></td>
							<td align="left"><input type="password" name="password" id="password" maxlength="50" size="20"></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="Entrar" name="loginSubmit" id="loginSubmit" onClick="return doLogin();"></td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End Login Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
