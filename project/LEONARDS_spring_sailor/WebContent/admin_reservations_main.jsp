<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="reservations" scope="session" class="java.util.Vector"/>
<jsp:useBean id="reservationFilter" scope="session" class="leonards.spring.domain.ReservationSearchFilter"/>
<jsp:useBean id="boat" scope="session" class="leonards.spring.domain.Boat"/>
<jsp:useBean id="shifts" scope="session" class="java.util.Vector"/>

<%
		java.util.Vector items = reservations;
		int DEFAULT_ROWS_PER_PAGE = leonards.spring.web.actions.SpringActionCommand.DEFAULT_ROWS_PER_PAGE;
		int DEFAULT_MAX_PAGE_SHORTCUTS = leonards.spring.web.actions.SpringActionCommand.DEFAULT_MAX_PAGE_SHORTCUTS;
		
		// Paging
		int pageNumber = 1, startIndex = 0, totalRows = 0, totalPages = 0, startShortcutPage = 0, endShortcutPage = 0;		
		boolean hasItemsToShow = items != null && items.size() > 0;
		int maxRowsPerPage;
		try {
			maxRowsPerPage = Integer.parseInt( request.getParameter("maxRowsPerPage") );
		} catch( Exception ex ) {
			maxRowsPerPage = DEFAULT_ROWS_PER_PAGE;
		}
		if( hasItemsToShow ) {
			try {
				pageNumber = Integer.parseInt( request.getParameter("pageNumber") );
				pageNumber = pageNumber > 0 ? pageNumber : 1;
			} catch( Exception ex ) {
				pageNumber = 1;
			}
			totalRows = items.size();
			totalPages = (int)Math.ceil( (double)totalRows/maxRowsPerPage );
			pageNumber = pageNumber > totalPages ? totalPages : pageNumber;
			startIndex = maxRowsPerPage * ( pageNumber - 1 );
	
			// Page Shortcuts
			startShortcutPage = ( pageNumber - (DEFAULT_MAX_PAGE_SHORTCUTS / 2) > 0 ) ? pageNumber - (DEFAULT_MAX_PAGE_SHORTCUTS / 2) : 1;
	
			if( startShortcutPage + DEFAULT_MAX_PAGE_SHORTCUTS > totalPages + 1 ) {
				startShortcutPage = ( totalPages - DEFAULT_MAX_PAGE_SHORTCUTS + 1 > 0 ) ? totalPages - DEFAULT_MAX_PAGE_SHORTCUTS + 1: 1;
				endShortcutPage = totalPages + 1;
			} else {
				endShortcutPage = startShortcutPage + DEFAULT_MAX_PAGE_SHORTCUTS;
			}
		}
%>
<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	<link rel="stylesheet" href="<%=WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">

	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/date-picker.js")%>"></script>
	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/input-utils.js")%>"></script>
	
	<script language="javascript">
		<!--
			function submitActionForm( actionCommand, reservationIndex, mustSubmit )
			{
				document.actionForm.webaction.value = actionCommand;
				document.actionForm.reservationIndex.value = reservationIndex;
				if(mustSubmit)
				{
					document.actionForm.submit();
				}
				else
				{
					return true;
				}
			}
			
			function submitPagingForm( pageNumber, mustSubmit )
			{
				document.pagingForm.pageNumber.value = pageNumber;
				if(mustSubmit)
				{
					document.pagingForm.submit();
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center">
<!-- Shifts Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="center" class="title3_bg"><div class="title3_txt">Administraci&oacute;n Turnos</div></td>
						</tr>            
						<tr>
							<td align="center">
							<form name="shiftActionForm" id="shiftActionForm" method="post" action="<%=WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
							<table width="100%"><tr>
							<td align="center">
								
								<input type="hidden" name="webaction" value="SAVE_SHIFT_TIMES">
								<input type="hidden" name="pageNumber" value="<%=pageNumber%>">
								<table>
								<%
									leonards.spring.domain.Shift aShift;
									for(int i=0; shifts != null && i < shifts.size(); i++ ) {
										aShift = (leonards.spring.domain.Shift)shifts.elementAt(i);
									
								%>
									<tr>
										<td align="right" class="label"><b><%=aShift.getDescription()%>:&nbsp;</b></td>
										<td align="center" class="label">&nbsp;desde:&nbsp;</td>
										<td align="center">
											<select name="fromHour_<%=i%>" id="fromHour_<%=i%>">
											<%
												for(int hour=0; hour < 24; hour++) {
											%>
											<option value="<%=hour%>" <%=(aShift.getTimeFromHour()==hour?"selected":"")%>><%=WebFrameworkToolkit.completeWithZeros(Integer.toString(hour), 2)%></option>
											<%
												}
											%>
											</select>
										</td>
										<td align="center" class="label">&nbsp;:&nbsp;</td>
										<td align="center">
											<select name="fromMinute_<%=i%>" id="fromMinute_<%=i%>">
											<%
												for(int min=0; min < 60; min+=30) {
											%>
											<option value="<%=min%>" <%=(aShift.getTimeFromMinutes()==min?"selected":"")%>><%=WebFrameworkToolkit.completeWithZeros(Integer.toString(min), 2)%></option>
											<%
												}
											%>
											</select>
										</td>
										<td align="center" class="label">&nbsp;hasta:&nbsp;</td>
										<td align="center">
											<select name="toHour_<%=i%>" id="toHour_<%=i%>">
											<%
												for(int hour=0; hour < 24; hour++) {
											%>
											<option value="<%=hour%>" <%=(aShift.getTimeToHour()==hour?"selected":"")%>><%=WebFrameworkToolkit.completeWithZeros(Integer.toString(hour), 2)%></option>
											<%
												}
											%>
											</select>
										</td>
										<td align="center" class="label">&nbsp;:&nbsp;</td>
										<td align="center">
											<select name="toMinute_<%=i%>" id="toMinute_<%=i%>">
											<%
												for(int min=0; min < 60; min+=30) {
											%>
											<option value="<%=min%>" <%=(aShift.getTimeToMinutes()==min?"selected":"")%>><%=WebFrameworkToolkit.completeWithZeros(Integer.toString(min), 2)%></option>
											<%
												}
											%>
											</select>
										</td>
										<td class="label">
										<input type="checkbox" name="enabled_<%=i%>" value="1" <%=(aShift.isEnabled() ? "checked" : "")%>>&nbsp;Habilitado
										</td>
									</tr>
								<%
									}
								%>
									<tr>
										<td colspan="10" align="center">
											<table width="100%"><tr>
											<td width="50%" align="center"><input type="submit" value="Actualizar"></td>
											<td width="50%" align="center"><input type="reset" value="Reset"></td>
											</tr></table>
										</td>
									</tr>
									
								</table>
							</td>
							</tr></table>
							</form>
							</td>
						</tr>
				</table>
			</td></tr></table>
<!-- End Shifts Table -->
			</td>
		</tr>
		<tr>
			<td>
				<br>
			</td>
		</tr>
		<tr>
			<td align="center">
<!-- Reservation Header Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center">
							<table width="100%"><tr>
							<td align="left">
								<table>
									<tr>
										<td align="right" class="label">Barco:</td>
										<td align="left" colspan="2" class="value"><%=WebFrameworkToolkit.getHtmlString(boat.getName())%></td>
									</tr>
								</table>
							</td>
							</tr></table>
							</td>
						</tr>
				</table>
			</td></tr></table>
<!-- Reservation Header Table -->
			</td>
		</tr>
		<tr>
			<td>
				<br>
			</td>
		</tr>
		<tr>
			<td align="center">
<!-- Boat Time Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center" class="title3_bg"><div class="title3_txt">Administraci&oacute;n reservas para el "<%=boat.getName()%>"</div></td>
						</tr>
						<tr>
					
					<td align="center">
					<form name="actionForm" id="actionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
					<input type="hidden" name="webaction" value="ADMIN_SEARCH_RESERVATION">
					<input type="hidden" name="reservationIndex" value="">
					<input type="hidden" name="pageNumber" value="<%=pageNumber%>">
					<table><tr>
						<td><div class="label">desde:</div></td>
						<td><input type="text" name="from" id="from" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(reservationFilter.getDateFrom())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('actionForm.from', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						<td><div class="label">hasta:</div></td>
						<td><input type="text" name="to" id="to" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(reservationFilter.getDateTo())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('actionForm.to', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						<td><input type="submit" value="Buscar" onClick="return submitActionForm('ADMIN_SEARCH_RESERVATION', -1, false);"></td>
						<td><input type="reset" value="Reset"></td>
					</tr></table>
					</form>
					</td>
					
						</tr>
				<tr>
					<td align="center">
					<!-- Paging Table -->
					<form action="<%= leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("admin_reservations_main.jsp")%>" method="post" name="pagingForm" id="pagingForm">
					<input type="hidden" name="pageNumber" value="">
					<% if( hasItemsToShow && totalPages > 1 ) { %>
						<table>
							<tr>
								<td align="left" class="paging_number">
								<% if( pageNumber > 1 ) { %>
									<a href="javascript:submitPagingForm('<%=(1)%>', true);"><<</a>&nbsp;&nbsp;
									<a href="javascript:submitPagingForm('<%=(pageNumber-1)%>', true);"><</a>
								<% } else { %>
									<<&nbsp;&nbsp;
									<
								<% } %>
								</td>
								<td>
									<table border="0" cellpadding="0" cellspacing="0"><tr>
									<% 
										if( startShortcutPage < endShortcutPage ) {
											for( int i = startShortcutPage; i < endShortcutPage; i++ ) {
												if( i != pageNumber ) {
									%>
									<td valign="middle"><a href="javascript:submitPagingForm('<%=i%>', true);"><span class="paging_number_link"><%=i%></span></a></td>
									<%
												} else {
									%>
									<td valign="middle"><span class="paging_number"><b><%=i%></b></span></td>
									<%
												}
												if( i + 1 < endShortcutPage ) {
									%>
									<td valign="middle"><span class="paging_number">&nbsp;-&nbsp;</span></td>
									<%
												}
											}
										} else {
									 %>
									<td valign="middle"><span class="paging_number"><b>1</b></span></td>
									<%
										}
									%>
									</tr></table>								
								</td>
								<td align="left" class="paging_number">
								<% if( pageNumber < totalPages ) { %>
									<a href="javascript:submitPagingForm('<%=(pageNumber+1)%>', true);">></a>&nbsp;&nbsp;
									<a href="javascript:submitPagingForm('<%=(totalPages)%>', true);">>></a>
								<% } else { %>
									>&nbsp;&nbsp;
									>>
								<% } %>
								</td>
							</tr>
						</table>
					<% } %>
						</form>
					<!-- End Paging Table -->
					<!-- Results Table -->
						<table>
							<tr>
								<td align="center" class="results_table_header">Fecha Desde</td>
								<td align="center" class="results_table_header">Fecha Hasta</td>
								<td align="center" class="results_table_header">Tipo Reserva</td>
								<td align="center" class="results_table_header">Usuario</td>
								<td align="center" class="results_table_header">Valor</td>
								<td align="center" class="results_table_header">Estado</td>
								<td align="center" class="results_table_header">Fecha Estado</td>
								<td align="center" class="results_table_header">&nbsp;</td>
							</tr>
                             <%
                             	leonards.spring.domain.Reservation aReservation;
                             	String rowStyle;
		                      	for( int i = startIndex; i < startIndex + maxRowsPerPage && i < totalRows; i++ ) {
		                      		aReservation = (leonards.spring.domain.Reservation)items.elementAt(i);
		                      		rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
                             %>
							<tr>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDateTime(aReservation.getFrom())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDateTime(aReservation.getTo())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getType().getDescription())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getUser().getCompleteName())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDouble(aReservation.getValue())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getState().getDescription())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlShortDate(aReservation.getStateDate())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">
								&nbsp;
								<%
									if(aReservation.isActive()) {
								%>
								<a class="action_link" href="javascript:submitActionForm('CANCEL_ADMIN_RESERVATION',<%=i%>, true);" onClick="return confirm('Confirma que cancela la reserva <%=WebFrameworkToolkit.getJSString(aReservation.getType().getDescription())%> de <%=WebFrameworkToolkit.getJSString(aReservation.getUser().getCompleteName())%>?');">cancelar</a>
								<%
									}
								%>
								&nbsp;
								</td>
							</tr>
							<%
								}
							%>
						</table>
					<!-- End Results Table -->		
					</td>
				</tr>						
				</table>
			</td></tr></table>
<!-- End Boat Time Table -->				
			</td>
		</tr>				
	</table>
</body>
</html>