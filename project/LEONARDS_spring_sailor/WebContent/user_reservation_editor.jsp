<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="timeTableShift" scope="session" type="leonards.spring.domain.BoatTimeTableShift"/>
<jsp:useBean id="freeShifts" scope="session" type="java.lang.Integer"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<jsp:useBean id="contactToEdit" scope="session" class="leonards.spring.domain.Contact"/>
<jsp:useBean id="selectedContacts" scope="session" class="java.util.Vector"/>
<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function doSubmit(webAction, contactAction, selectedContactIndex, mustSubmit)
			{
				document.actionForm.webaction.value = webAction;
				document.actionForm.contactaction.value = contactAction;
				document.actionForm.selectedcontactindex.value = selectedContactIndex;
				if(mustSubmit)
				{
					document.actionForm.submit();
				}
				else
				{
					return true;
				}
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="actionForm" id="actionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_RESERVATION">
              <input type="hidden" name="contactaction" value="ADD">
              <input type="hidden" name="selectedcontactindex" value="">

	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Reservation Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Reserva en Curso</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">fecha:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlShortDate(timeTableShift.getDay())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) tipo de reserva:</div></td>
							<td align="left">
								<select name="typeId" id="typeId">
								<%
									java.util.Iterator types = leonards.spring.domain.StaticDataManager.getReservationTypes().iterator();
									leonards.spring.domain.ReservationType aType;
									String extraValue = "";
									while( types != null && types.hasNext() ) {
										aType = (leonards.spring.domain.ReservationType)types.next();
										if( aType.getShiftsQty().intValue() <= freeShifts.intValue() ) {
											if (aType.getExtraValue() != null && aType.getExtraValue().doubleValue() > 0) {
												extraValue = " (valor extra: " + WebFrameworkToolkit.getHtmlDouble(aType.getExtraValue()) + ")";
											}
								%>
									<option value="<%=aType.getIdAsInteger()%>" <%=(aType.getIdAsInteger().equals(timeTableShift.getReservation().getTypeId()) ? "selected" : "")%>><%=aType.getDescription()%><%=extraValue%></option>
								<%
										}
									}
								%>
								</select>
							</td>
						</tr>
				</table>
			</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				<br>
			</td>
		</tr>
		<tr>
			<td align="center">

			<table class="outer_table"><tr><td>
            <table class="inner_table">
						
						<!-- Contacts Table -->
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Acompa&ntilde;antes</div></td>
						</tr>
						<tr>
							<td colspan="2">
							&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<table><tr>
								<td class="label">contactos:</td>
								<td>
									<select name="contactIndex" id="contactIndex">
								<%
									java.util.Vector contacts = user.getContacts();
									boolean hasContactsToSelect = false;
									leonards.spring.domain.Contact aContact;
									for( int i = 0; contacts != null && i < contacts.size(); i++ ) {
										aContact = (leonards.spring.domain.Contact)contacts.elementAt(i);
										if( selectedContacts != null && !selectedContacts.contains(aContact) ) {
											hasContactsToSelect = true;
								%>
										<option value="<%=i%>"><%=aContact.getCompleteName()%></option>
								<%
										}
									}
									if( contacts == null || contacts.size() < 1 || !hasContactsToSelect ) {
								%>
										<option value="-1">-- No tiene contactos --</option>
								<% 
									}
								%>
									</select>
								</td>
								<%
									if( contacts != null && contacts.size() > 0 && hasContactsToSelect ) {
								%>
								<td>
									&nbsp;<a href="javascript:doSubmit('CONTACT_ACTION','ADD', '-1', true)" class="action_link">Agregar</a>&nbsp;
								</td>
								<td>
									&nbsp;<a href="javascript:doSubmit('CONTACT_ACTION','DELETE', '-1', true)" class="action_link" onClick="return confirm('Confirma que elimina el acompañante?');">Eliminar</a>&nbsp;
								</td>
								<%
									}
								%>
							</tr></table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table><tr>
									<td colspan="" class="label"><b>nuevo contacto:</b></td>
								</tr><tr>
									<td class="label" align="left">nombre completo</td>
									<td class="label" align="left">tel&eacute;fono</td>
									<td class="label" align="left" colspan="2">documento</td>
									<td class="label" align="center">&nbsp;</td>
								</tr><tr>
									<td align="center"><input id="contactName" name="contactName" type="text" size="30" maxlength="100" value="<%=WebFrameworkToolkit.getHtmlString(contactToEdit.getCompleteName())%>"></td>
									<td align="center"><input id="contactTelephone" name="contactTelephone" type="text" size="15" maxlength="50" value="<%=WebFrameworkToolkit.getHtmlString(contactToEdit.getTelephone())%>"></td>
									<td align="center">
										<select name="contactDocumentTypeId" id="contactDocumentTypeId">
										<%
											java.util.Iterator documentTypes = leonards.spring.domain.StaticDataManager.getDocumentTypes().iterator();
											leonards.spring.domain.ReferenceData aDocumentType;
											while( documentTypes != null && documentTypes.hasNext() ) {
												aDocumentType = (leonards.spring.domain.ReferenceData)documentTypes.next();
										%>
											<option value="<%=aDocumentType.getIdAsInteger()%>" <%=(aDocumentType.getIdAsInteger().equals(contactToEdit.getDocument().getDocumentTypeId()) ? "selected" : "")%>><%=aDocumentType.getDescription()%></option>
										<%
											}
										%>
										</select>									
									</td>
									<td align="center"><input id="contactDocument" name="contactDocument" type="text" size="15" maxlength="50" value="<%=WebFrameworkToolkit.getHtmlString(contactToEdit.getDocument().getNumber())%>"></td>
									<td align="center" nowrap>&nbsp;<a class="action_link" href="javascript:doSubmit('CONTACT_ACTION','SAVE_ADD', '-1', true)">crear y agregar</a>&nbsp;</td>
								</tr></table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
							&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table>
							<tr>
								<td align="center" class="results_table_header">Nombre Completo</td>
								<td align="center" class="results_table_header">Tel&eacute;fono</td>
								<td align="center" class="results_table_header">Documento</td>
								<td align="center" class="results_table_header">&nbsp;</td>							
							</tr>
							<%
								if( selectedContacts != null && selectedContacts.size() > 0 ) {
									String rowStyle;
									for( int i = 0; i < selectedContacts.size(); i++ ) {
										aContact = (leonards.spring.domain.Contact)selectedContacts.elementAt(i);
										rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
							%>
							<tr>
								<td align="left" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getCompleteName())%></td>	
								<td align="center" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getTelephone())%></td>	
								<td align="center" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getDocument().getDocumentType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(aContact.getDocument().getNumber())%></td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<a class="action_link" href="javascript:doSubmit('CONTACT_ACTION','DELETE_SELECTED', '<%=i%>', true)">Eliminar</a>&nbsp;</td>	
							</tr>	
							<%
									}
								} else {
							%>
							<tr>
							<td colspan="4" class="even_results_table_row" align="center">No hay contactos seleccionados</td>
							</tr>
							<%
								}
							%>
							</table>
							</td>
						</tr>						
						<!-- Contacts Table End -->
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="50%"><input type="submit" value="Cancelar" name="formCancel" id="formCancel" onClick="return doSubmit('CANCEL_SAVE_RESERVATION', '', '-1', false);"></td>
									<td align="center" width="50%"><input type="submit" value="Reservar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_RESERVATION', '', '-1', false);"></td>
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
