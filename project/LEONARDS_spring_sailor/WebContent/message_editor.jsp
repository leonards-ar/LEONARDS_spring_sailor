<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="messageToEdit" scope="session" type="leonards.spring.domain.Message"/>

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
				document.messageForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="messageForm" id="messageForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_MESSAGE">     
              <input type="hidden" name="successPage" value="<%=WebFrameworkToolkit.getHtmlString(request.getParameter("successPage"))%>">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Message Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Nuevo Mensaje</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top"><div class="label">(*) mensaje:</div></td>
							<td align="left">
							<textarea name="message" id="message" rows="15" cols="30"><%=WebFrameworkToolkit.getHtmlString(messageToEdit.getMessage())%></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="50%"><input type="submit" value="Cancelar" name="formCancel" id="formCancel" onClick="return doSubmit('CANCEL_EDIT_MESSAGE');"></td>
									<td align="center" width="50%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_MESSAGE');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End Message Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
