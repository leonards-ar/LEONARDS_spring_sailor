<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<jsp:useBean id="boatTimeTable" scope="session" class="leonards.spring.domain.BoatTimeTable"/>
<%
	leonards.spring.domain.Shift aShift;
 	leonards.spring.domain.BoatTimeTableDay aTimeTableDay;
 	leonards.spring.domain.BoatTimeTableShift aTimeTableShift;
 	java.util.Vector shifts = null;
 	java.util.Vector days = null;
 	int daysToDisplay = 0;
 	try {
 		days = boatTimeTable.getDays();
 		daysToDisplay = boatTimeTable.getDaysToDisplay();
 		shifts = leonards.spring.domain.DBHome.retrieveShifts();
 	} catch( Exception ex ) {
%>
<!--
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
<%
 	}
%>

<html>
<head>
	<title>Sistema de Reservas Frana</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<link rel="stylesheet" href="<%=WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function submitActionForm( actionCommand, dayIndex, shiftIndex, mustSubmit )
			{
				document.actionForm.webaction.value = actionCommand;
				document.actionForm.dayIndex.value = dayIndex;
				document.actionForm.shiftIndex.value = shiftIndex;
				if( mustSubmit ) {
					document.actionForm.submit();
				} else {
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
										<td align="left" colspan="2" class="value"><%=WebFrameworkToolkit.getHtmlString(boatTimeTable.getBoat().getName())%></td>
									</tr>
									<tr>
										<td align="right" class="label">Estado:</td>
										<td align="left" class="value"><%=WebFrameworkToolkit.getHtmlString(boatTimeTable.getBoat().getState().getDescription())%></td>
										<td align="left">
									<% if( !boatTimeTable.getBoat().getState().canReserve() ) { %>
										<table><tr>
										<td class="label">&nbsp;desde:&nbsp;</td>
										<td class="value"><%=(boatTimeTable.getBoat().getStateDateFrom() != null ? WebFrameworkToolkit.getHtmlShortDate(boatTimeTable.getBoat().getStateDateFrom()) : "No definido" )%></td>
										<td class="label">&nbsp;hasta:&nbsp;</td>
										<td class="value"><%=(boatTimeTable.getBoat().getStateDateTo() != null ? WebFrameworkToolkit.getHtmlShortDate(boatTimeTable.getBoat().getStateDateTo()) : "No definido" )%></td>
										</tr></table>
									<% } else { %>
										&nbsp;
									<% } %>
										</td>
									</tr>
									<tr>
										<td align="right" class="label">Comentarios:</td>
										<td align="left" colspan="2" class="value"><%=WebFrameworkToolkit.getHtmlString(boatTimeTable.getBoat().getStateComments())%></td>
									</tr>
								</table>
							</td>
							<td align="right" valign="top">
								<table>
									<tr>
										<td align="right" class="label">Su saldo:</td>
										<td align="left" class="value"><b><%=WebFrameworkToolkit.getHtmlDouble(user.getBalance())%><b></td>
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
							<td align="center" class="title3_bg"><div class="title3_txt">Reservas para el "<%=boatTimeTable.getBoat().getName()%>"</div></td>
						</tr>
						<tr>
							<td align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="center">
						<form name="actionForm" id="actionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
						<input type="hidden" name="webaction" value="">
						<input type="hidden" name="dayIndex" value="">
						<input type="hidden" name="shiftIndex" value="">
						<table>
							<tr>
								<td align="center" class="results_table_header">D&iacute;a</td>
								<%
									
									for(int i = 0; shifts != null && i < shifts.size(); i++ ) {
										aShift = (leonards.spring.domain.Shift)shifts.elementAt(i);
								%>
								<td align="center" class="results_table_header"><%=aShift.getDescription()%></td>
								<% 
									}
								%>
							</tr>
                             <%
                             	String rowStyle;
                             	String dayStyle;
		                      	for( int i =0; days != null && i < daysToDisplay && i < days.size(); i++ ) {
		                      		aTimeTableDay = (leonards.spring.domain.BoatTimeTableDay)days.elementAt(i);
		                      		rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
		                      		dayStyle = aTimeTableDay.isWeekendDay() ? "weekend_day_label" : "week_day_label";
                             %>
							<tr>
								<td align="center" valign="middle" nowrap class="<%=rowStyle%>"><span class="<%=dayStyle%>"><%=WebFrameworkToolkit.getHtmlLongDate(aTimeTableDay.getDay())%></span></td>
								<%
									for( int j = 0; aTimeTableDay.getShifts() != null && j < aTimeTableDay.getShifts().size(); j++ ) {
										aTimeTableShift = (leonards.spring.domain.BoatTimeTableShift)aTimeTableDay.getShifts().elementAt(j);
								%>
								<td align="center" class="<%=rowStyle%>">
									<%
										// The Boat is not available for any shift on this day
										if( !aTimeTableDay.isBoatAvailable() ) {
									%>
									<table><tr>
									<td><span class="not_available_label"><%=WebFrameworkToolkit.getHtmlString(aTimeTableDay.getBoat().getState().getDescription())%></span></td>
									</tr></table>
									<%
										// Reserved by the logged user
										}else if( aTimeTableShift.isReserved() && user.equals(aTimeTableShift.getReservation().getUser()) ) {
									%>
									<table><tr>
									<td nowrap class="reserved_label" align="left"><a href="mailto:<%=user.getEmail()%>"><%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%></a></td>
									<td nowrap class="reserved_label" align="right">(<%=WebFrameworkToolkit.getHtmlString(aTimeTableShift.getReservation().getType().getDescription())%>)</td>
									</tr><tr>
									<td nowrap align="center" <%=(!aTimeTableShift.canBeCancelled()?"colspan=\"2\"":"")%>><a href="javascript:submitActionForm('DETAILS_RESERVATION',<%=i%>,<%=j%>, true);" class="action_link">detalles</a></td>
									<%
										if( aTimeTableShift.canBeCancelled() ) {
									%>
									<td nowrap align="center"><a href="javascript:submitActionForm('CANCEL_USER_RESERVATION',<%=i%>,<%=j%>, true);" class="action_link" onClick="return confirm('Confirma que cancela la reserva <%= WebFrameworkToolkit.getJSString(aTimeTableShift.getReservation().getType().getDescription())%>?');">cancelar</a></td>
									<%
										}
									%>
									</tr></table>
									<%
										// Reserved by another user
										} else if( aTimeTableShift.isReserved() ) {
									%>
									<table><tr>
									<td nowrap class="reserved_label" align="left"><a href="mailto:<%=aTimeTableShift.getReservation().getUser().getEmail()%>"><%=WebFrameworkToolkit.getHtmlString(aTimeTableShift.getReservation().getUser().getCompleteName())%></a></td>
									<td nowrap class="reserved_label" align="right">(<%=WebFrameworkToolkit.getHtmlString(aTimeTableShift.getReservation().getType().getDescription())%>)</td>
									</tr></table>
									<%
									    // The shift is disabled
										} else if ( !aTimeTableShift.getShift().isEnabled() ) {
									%>
									<table><tr>
									<td><span class="not_available_label">No disponible</span></td>
									</tr></table>
									<%
										// Boat is free to be reserved
										
										} else {
									%>
									<table><tr>
									<td class="available_label">Libre</td>
									<td class="label">&nbsp;(valor:&nbsp;<%=WebFrameworkToolkit.getHtmlDouble(aTimeTableShift.getValue())%>)&nbsp;</td>
									<td><a class="action_link" href="javascript:submitActionForm('START_RESERVATION_PROCESS',<%=i%>,<%=j%>, true)">Reservar</a></td>
									</tr></table>
									<%
										}
									%>
								</td>
								<%
									}
								%>
							</tr>
							<%
								}
							%>
						</table>
						</form>
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