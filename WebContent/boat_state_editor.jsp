<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="boatToEdit" scope="session" type="leonards.spring.domain.Boat"/>

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
				document.boatForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
			<form name="boatForm" id="boatForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
            <input type="hidden" name="webaction" value="SAVE_BOAT_STATE">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Boat Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Cambio de Estado del Barco</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">nombre:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(boatToEdit.getName())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) estado:</div></td>
							<td align="left">
								<select name="stateId" id="stateId">
								<%
									java.util.Iterator states = leonards.spring.domain.StaticDataManager.getBoatStates().iterator();
									leonards.spring.domain.BoatState aState;
									while( states != null && states.hasNext() ) {
										aState = (leonards.spring.domain.BoatState)states.next();
								%>
									<option value="<%=aState.getIdAsInteger()%>" <%=(aState.getIdAsInteger().equals(boatToEdit.getStateId()) ? "selected" : "")%>><%=aState.getDescription()%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">fecha desde:</div></td>
							<td align="left"><input type="text" name="date_from" id="date_from" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(boatToEdit.getStateDateFrom())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('boatForm.date_from', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">fecha hasta:</div></td>
							<td align="left"><input type="text" name="date_to" id="date_to" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(boatToEdit.getStateDateTo())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('boatForm.date_to', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">comentarios:</div></td>
							<td align="left"><input type="text" name="comments" id="comments" maxlength="255" size="60" value="<%=WebFrameworkToolkit.getHtmlString(boatToEdit.getStateComments())%>"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="33%"><input type="submit" value="Cancelar" name="formCancel" id="formCancel" onClick="return doSubmit('CANCEL_EDIT_BOAT');"></td>
									<td align="center" width="33%"><input type="reset" value="Resetear" name="formReset" id="formReset"></td>
									<td align="center" width="33%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_BOAT_STATE');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>

<!-- End Boat Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
