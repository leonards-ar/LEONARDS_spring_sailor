<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="messages" scope="session" type="java.util.Vector"/>
<jsp:useBean id="activeReservations" scope="session" type="java.util.Vector"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<%
	String rowStyle;
	Double balance, credit;
	try {
		balance = leonards.spring.domain.DBHome.getCurrentBalance();
		credit = leonards.spring.domain.DBHome.getCreditBalance();
	} catch( Throwable ex ) {
		balance = null;
		credit = null;
%>
<!--
Error:
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
<%
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
			function submitActionForm( actionCommand, index, mustSubmit )
			{
				document.msgActionForm.webaction.value = actionCommand;
				document.msgActionForm.messageIndex.value = index;
				if( mustSubmit ) {
					document.msgActionForm.submit();
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
<!-- General Info Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center">
							<table width="100%"><tr>
							<td align="left">
								<table>
									<tr>
										<td align="right" class="label">Saldo de <i><b><%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%></b></i>:</td>
										<td align="left" class="value"><%=WebFrameworkToolkit.getHtmlDouble(user.getBalance())%></td>
									</tr>
								</table>
							</td>
							<td align="right">
								<table>
									<tr>
										<td align="right" class="label">Saldo Cuenta Corriente Nautica:</td>
										<td align="left" class="value"><%=WebFrameworkToolkit.getHtmlDouble(balance)%></td>
									</tr>
									<tr>
										<td align="right" class="label">Cr&eacute;dito para salidas de todos los usuarios:</td>
										<td align="left" class="value"><%=WebFrameworkToolkit.getHtmlDouble(credit)%></td>
									</tr>
								</table>
							</td>
							</tr></table>
							</td>
						</tr>
				</table>
			</td></tr></table>
<!-- General Info Table -->
			</td>
		</tr>
		<tr>
			<td>
				<br>
			</td>
		</tr>		
		<%
			if( activeReservations != null && activeReservations.size() > 0 ) {
		%>
		<tr>
			<td align="center">
<!-- Active Reservations Results Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center" class="title3_bg"><div class="title3_txt">Reservas Activas</div></td>
						</tr>
						<tr>
							<td align="center">
						<form name="reservationsActionForm" id="reservationsActionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
						<input type="hidden" name="webaction" value="">
						<input type="hidden" name="reservationIndex" value="">
						<table>
							<tr>
								<td align="center" class="results_table_header">Barco</td>
								<td align="center" class="results_table_header">Desde</td>
								<td align="center" class="results_table_header">Hasta</td>
								<td align="center" class="results_table_header">Tipo de Reserva</td>
								<td align="center" class="results_table_header">Valor</td>
								<td align="center" class="results_table_header">Estado</td>
							</tr>
                             <%
                             	leonards.spring.domain.Reservation aReservation;
		                      	for( int i = 0; activeReservations != null && i < activeReservations.size(); i++ ) {
		                      		aReservation = (leonards.spring.domain.Reservation)activeReservations.elementAt(i);
		                      		rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
                             %>
							<tr>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getBoat().getName())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDateTime(aReservation.getFrom())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDateTime(aReservation.getTo())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getType().getDescription())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlDouble(aReservation.getValue())%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aReservation.getState().getDescription())%>&nbsp;</td>
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
<!-- End Active Reservations Table -->
			</td>
		</tr>
		<tr>
			<td>
				<br>
			</td>
		</tr>
		<%
			}
		%>
		<tr>
			<td align="center">
<!-- Last Messages Results Table -->
			<table class="outer_table" width="100%"><tr><td>
            <table class="inner_table" width="100%">
						<tr>
							<td align="center" class="title3_bg"><div class="title3_txt">&Uacute;ltimos Mensajes</div></td>
						</tr>
						<tr>
							<td align="center">
						<form name="msgActionForm" id="msgActionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
						<input type="hidden" name="webaction" value="">
						<input type="hidden" name="messageIndex" value="">
						<input type="hidden" name="successPage" value="home_main.jsp">
						<table>
                             <%
                             	leonards.spring.domain.Message aMsg;
		                      	for( int i =0; messages != null && i < messages.size() && i < 10; i++ ) {
		                      		aMsg = (leonards.spring.domain.Message)messages.elementAt(i);
                             %>
							<tr>
								<td align="left" nowrap class="even_results_table_row"><div class="label"><%=WebFrameworkToolkit.getHtmlDateTime(aMsg.getDate())%></div></td>
								<td align="left" class="even_results_table_row" width="100%"><div class="label"><b><%=WebFrameworkToolkit.getHtmlString(aMsg.getUser().getCompleteName())%></b></div></td>
							</tr>
							<tr>
								<td class="odd_results_table_row" colspan="2">
								<p><%=WebFrameworkToolkit.getHtmlParagraph(aMsg.getMessage())%></p>
								</td>
							</tr>
							<%
								}
								if( messages == null || messages.size() < 1 ) {
							%>
							<tr>
							<td align="center" class="even_results_table_row">
							No hay mensajes para mostrar
							</td>
							<tr>
							<%
								}
							%>
							<tr>
								<td align="center" colspan="2" class="action_link">
									<a href="javascript:submitActionForm('CREATE_MESSAGE','-1', true)">Nuevo Mensaje</a>
								</td>
							</tr>
						</table>
						</form>
							</td>
						</tr>
				</table>
			</td></tr></table>
<!-- End Last Messages Results Table -->				
			</td>
		</tr>				
	</table>
</body>
</html>
