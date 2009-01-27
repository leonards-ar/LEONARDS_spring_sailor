<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="userToEdit" scope="session" type="leonards.spring.domain.User"/>

<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/date-picker.js")%>"></script>
	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/input-utils.js")%>"></script>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function doSubmit(webAction, mustSubmit)
			{
				document.userForm.webaction.value = webAction;
				if( mustSubmit ) {
					document.userForm.submit();
				} else {
					return true;
				}
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="userForm" id="userForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_MY_PROFILE">     
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
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Mi Perfil</div></td>
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
							<td align="right"><div class="label">apellido:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getSurname())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">nombre:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getName())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) e-mail:</div></td>
							<td align="left"><input type="text" name="email" id="email" maxlength="255" size="30" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getEmail())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">direcci&oacute;n:</div></td>
							<td align="left"><input type="text" name="address" id="address" maxlength="255" size="40" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getAddress())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">tel&eacute;fono:</div></td>
							<td align="left"><input type="text" name="telephone" id="telephone" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getTelephone())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">celular:</div></td>
							<td align="left"><input type="text" name="celphone" id="celphone" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getCelPhone())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">documento:</div></td>
							<td align="left">
								<div class="value">
								<%=WebFrameworkToolkit.getHtmlString(userToEdit.getDocument().getDocumentType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(userToEdit.getDocument().getNumber())%>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">habilitaci&oacute;n:</div></td>
							<td align="left">
								<div class="value">
								<%=WebFrameworkToolkit.getHtmlString(userToEdit.getPermit().getPermitType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(userToEdit.getPermit().getNumber())%>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">vencimiento:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlShortDate(userToEdit.getPermit().getDueDate())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">legajo:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getDossier())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">estado:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getState().getDescription())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">perfil:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getProfile().getDescription())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">asociaci&oacute;n:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getDepartment())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">saldo:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlDouble(userToEdit.getBalance())%></div></td>
						</tr>
						<tr>
							<td align="right" class="action_link" colspan="2"><a href="javascript:doSubmit('MY_PROFILE_PASSWORD', true);">Cambiar Contrase&ntilde;a</a></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="100%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_MY_PROFILE', false);"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End User Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
