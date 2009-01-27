<jsp:useBean id="lastError" scope="session" class="java.lang.String"/>
<!--
Last Error:
<%=lastError%>
-->
<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
			<table class="outer_table"><tr><td>
            <table class="inner_table">
				<tr>
					<td align="center" class="title3_bg"><div class="title3_txt">Error</div></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td class="error" align="center">Actualmente el sistema no se encuentra disponible.</td>
				</tr>
				<tr>
					<td class="value" align="center">Por favor contacte al administrador para informar el problema.</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr align="center">
					<td><a href="javascript:window.history.back();" class="action_link">Volver</a></td>
				</tr>
			</table>
			</td></tr></table>
			</td>
		</tr>
	</table>
</body>
</html>