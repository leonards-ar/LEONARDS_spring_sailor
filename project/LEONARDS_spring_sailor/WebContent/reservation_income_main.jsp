<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="movementToEdit" scope="session" type="leonards.spring.domain.Movement"/>

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
			function doSubmit(webAction)
			{
				document.movementForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="movementForm" id="movementForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_MOVEMENT">     
              <input type="hidden" name="movementTypeId" value="<%=movementToEdit.getMovementTypeId()%>">
              <input type="hidden" name="successPage" value="reservation_income_main.jsp">
              <input type="hidden" name="validationErrorPage" value="reservation_income_main.jsp">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Movement Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Ingresos para Reservas</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) usuario:</div></td>
							<td align="left">
								<select name="userId" id="userId">
								<%
									java.util.Iterator users = leonards.spring.domain.DBHome.searchUsers().iterator();
									leonards.spring.domain.User anUser;
									while( users != null && users.hasNext() ) {
										anUser = (leonards.spring.domain.User)users.next();
								%>
									<option value="<%=anUser.getIdAsInteger()%>" <%=(anUser.getIdAsInteger().equals(movementToEdit.getUserId()) ? "selected" : "")%>><%=anUser.getCompleteName()%></option>
								<%
									}
								%>
								</select>							
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) cantidad:</div></td>
							<td align="left"><input type="text" name="amount" id="amount" maxlength="15" size="15" value="<%=WebFrameworkToolkit.getHtmlDouble(movementToEdit.getAmount())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">documento asociado:</div></td>
							<td align="left"><input type="text" name="document_data" id="document_data" maxlength="255" size="30" value="<%=WebFrameworkToolkit.getHtmlString(movementToEdit.getDocumentData())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">comentarios:</div></td>
							<td align="left"><input type="text" name="comments" id="comments" maxlength="255" size="60" value="<%=WebFrameworkToolkit.getHtmlString(movementToEdit.getComments())%>"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="100%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_MOVEMENT');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End Movement Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
