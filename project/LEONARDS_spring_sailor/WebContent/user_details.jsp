<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="userToShow" scope="session" type="leonards.spring.domain.User"/>

<%
String pageNumber;
try {
	pageNumber = new Integer( request.getParameter("pageNumber") ).toString();
} catch( Exception ex ) {
	pageNumber = "";
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
			function doSubmit(webAction)
			{
				document.userForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="userForm" id="userForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="CANCEL_VIEW_USER">
              <input type="hidden" name="pageNumber" value="<%=pageNumber%>">
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
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Informaci&oacute;n de Usuario</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">usuario:</div></td>
							<td align="left">
							<div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getUsername())%></div>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">apellido:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getSurname())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">nombre:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getName())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">e-mail:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getEmail())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">direcci&oacute;n:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getAddress())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">tel&eacute;fono:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getTelephone())%></divº></td>
						</tr>
						<tr>
							<td align="right"><div class="label">celular:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getCelPhone())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">documento:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getDocument().getDocumentType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(userToShow.getDocument().getNumber())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">habilitaci&oacute;n:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getPermit().getPermitType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(userToShow.getPermit().getNumber())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">vencimiento:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlShortDate(userToShow.getPermit().getDueDate())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">legajo:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getDossier())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">estado:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getState().getDescription())%></div></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">perfil:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getProfile().getDescription())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">asociaci&oacute;n:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(userToShow.getDepartment())%></div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="50%"><input type="submit" value="Volver" name="formCancelar" id="formCancelar" onClick="return doSubmit('CANCEL_VIEW_USER');"></td>
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
