<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="userToEdit" scope="session" type="leonards.spring.domain.User"/>

<%

%>

<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function doSubmit(webAction)
			{
				document.userPaswordForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="userPaswordForm" id="userPaswordForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="MY_PROFILE_SAVE_PASSWORD">     
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- User Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Cambio de Contrase&ntilde;a</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">usuario:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getUsername())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">nombre:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getCompleteName())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) contraseña actual:</div></td>
							<td align="left"><input type="password" name="currentpassword" id="currentpassword" maxlength="50" size="20"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) nueva contraseña:</div></td>
							<td align="left"><input type="password" name="password" id="password" maxlength="50" size="20"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) confirmaci&oacute;n nueva contrase&ntilde;a:</div></td>
							<td align="left"><input type="password" name="passwordConfirmation" id="passwordConfirmation" maxlength="50" size="20"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="50%"><input type="submit" value="Cancelar" name="formCancelar" id="formCancelar" onClick="return doSubmit('MY_PROFILE_CANCEL_PASSWORD');"></td>
									<td align="center" width="50%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('MY_PROFILE_SAVE_PASSWORD');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End USer Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
