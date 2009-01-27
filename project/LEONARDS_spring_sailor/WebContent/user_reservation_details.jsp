<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="reservation" scope="session" class="leonards.spring.domain.Reservation"/>
<jsp:useBean id="newReservation" scope="session" class="java.lang.String"/>

<%
	boolean isNewReservation = newReservation != null && newReservation.equalsIgnoreCase("yes");
%>
<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function doSubmit(webAction, mustSubmit)
			{
				document.actionForm.webaction.value = webAction;
				if( mustSubmit ) {
					document.actionForm.submit();
				} else {
					return true;
				}
			}
			
			function openImportantConsiderations()
			{
				window.open('<%=WebFrameworkToolkit.getDocumentUrl("document_displayer.jsp?document=important_considerations_body.jsp")%>', 'important_considerations','status=no;toolbar=no;scrollbars=yes;menubar=yes',false);
			}


			function openReservationDocuments()
			{
				window.open('<%=WebFrameworkToolkit.getDocumentUrl("document_displayer.jsp?document=reservation_documents_body.jsp")%>', 'reservation_documents','status=no;toolbar=no;scrollbars=yes;menubar=yes',false);
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" <%=(isNewReservation?"onLoad=\"openReservationDocuments();\"":"")%>>
              <form name="actionForm" id="actionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="FINISH_RESERVATION_DETAILS">
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
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Datos de la Reserva</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">barco:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(reservation.getBoat().getName())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">fecha desde:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlDateTime(reservation.getFrom())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">fecha hasta:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlDateTime(reservation.getTo())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">tipo de reserva:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(reservation.getType().getDescription())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">valor total:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlDouble(reservation.getValue())%></div></td>
						</tr>
						<tr>
							<td align="right"><div class="label">estado:</div></td>
							<td align="left"><div class="value"><%=WebFrameworkToolkit.getHtmlString(reservation.getState().getDescription())%></div></td>
						</tr>
						<tr>
							<td colspan="2" align="center" nowrap><span class="warning_message">No se olvide de imprimir los documentos que lo habilitan a navegar</span></td>
						</tr>
						<tr>
							<td width="50%" align="center">&nbsp;&nbsp;<a href="javascript:openReservationDocuments();" class="action_link">Documentos</a>&nbsp;&nbsp;</td>
							<td width="50%" align="center">&nbsp;&nbsp;<a href="javascript:openImportantConsiderations();" class="action_link">Consideraciones Importantes</a>&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Turnos</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table>
							<tr>
								<td align="center" class="results_table_header">Turno</td>
								<td align="center" class="results_table_header">Desde</td>
								<td align="center" class="results_table_header">Hasta</td>
								<td align="center" class="results_table_header">Valor</td>
							</tr>
							<%
								String rowStyle = "";
								int i = 0;
								if( reservation.getShifts().size() > 0 ) {
									leonards.spring.domain.ReservationShift aShift;
									for( i = 0; i < reservation.getShifts().size(); i++ ) {
										aShift = (leonards.spring.domain.ReservationShift)reservation.getShifts().elementAt(i);
										rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
							%>
							<tr>
								<td align="left" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlString(aShift.getShift().getDescription())%></td>
								<td align="center" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlDateTime(aShift.getFrom())%></td>	
								<td align="center" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlDateTime(aShift.getTo())%></td>	
								<td align="center" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlDouble(aShift.getValue())%></td>	
							</tr>	
							<%
									}
								} else {
							%>
							<tr>
							<td colspan="3" class="even_results_table_row" align="center">No posee turnos seleccionados</td>
							</tr>
							<%
								}
							
								if( reservation.getType() != null && reservation.getType().getExtraValue() != null && reservation.getType().getExtraValue().doubleValue() > 0) {
									rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
									i++;
							%>
							<tr>
								<td align="left" class="<%=rowStyle%>" nowrap>Valor Extra Tipo Reserva</td>
								<td align="center" class="<%=rowStyle%>" nowrap>&nbsp;</td>	
								<td align="center" class="<%=rowStyle%>" nowrap>&nbsp;</td>	
								<td align="center" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlDouble(reservation.getType().getExtraValue())%></td>	
							</tr>	
							<%									
								}
								rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
							%>
							<tr>
								<td align="left" class="<%=rowStyle%>" nowrap>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>" nowrap>&nbsp;</td>	
								<td align="center" class="<%=rowStyle%>" nowrap><b>Total</b></td>	
								<td align="center" class="<%=rowStyle%>" nowrap><%=WebFrameworkToolkit.getHtmlDouble(reservation.getValue())%></td>	
							</tr>	
							</table>
							</td>
						</tr>						
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Acompa&ntilde;antes</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table>
							<tr>
								<td align="center" class="results_table_header">Nombre Completo</td>
								<td align="center" class="results_table_header">Tel&eacute;fono</td>
								<td align="center" class="results_table_header">Documento</td>
							</tr>
							<%
								if( reservation.getContacts().size() > 0 ) {
									leonards.spring.domain.Contact aContact;
									for( i = 0; i < reservation.getContacts().size(); i++ ) {
										aContact = (leonards.spring.domain.Contact)reservation.getContacts().elementAt(i);
										rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
							%>
							<tr>
								<td align="left" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getCompleteName())%></td>	
								<td align="center" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getTelephone())%></td>	
								<td align="center" class="<%=rowStyle%>"><%=WebFrameworkToolkit.getHtmlString(aContact.getDocument().getDocumentType().getDescription())%>&nbsp;<%=WebFrameworkToolkit.getHtmlString(aContact.getDocument().getNumber())%></td>
							</tr>	
							<%
									}
								} else {
							%>
							<tr>
							<td colspan="3" class="even_results_table_row" align="center">No hay acompa&ntilde;antes seleccionados</td>
							</tr>
							<%
								}
							%>
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="100%"><input type="submit" value="Volver" name="formBack" id="formBack" onClick="return doSubmit('FINISH_RESERVATION_DETAILS', false);"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>