<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="currentAccount" scope="session" class="java.util.Vector"/>
<jsp:useBean id="movementFilter" scope="session" class="leonards.spring.domain.MovementSearchFilter"/>

<%
		java.util.Vector items = currentAccount;
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
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">

	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/date-picker.js")%>"></script>
	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/input-utils.js")%>"></script>

	<script language="javascript">
		<!--
			function submitPagingForm( pageNumber, mustSubmit )
			{
				document.pagingForm.pageNumber.value = pageNumber;
				if( mustSubmit ) 
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
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- Search Results Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
				<tr>
					<td align="center" class="title3_bg"><div class="title3_txt">
						Cuenta Corriente
					</div></td>
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
					<input type="hidden" name="webaction" value="CURRENT_ACCOUNT_MAIN">
					<input type="hidden" name="pageNumber" value="<%=pageNumber%>">					
					<table><tr>
						<td><div class="label">desde:</div></td>
						<td><input type="text" name="from" id="from" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(movementFilter.getFrom())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('actionForm.from', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						<td><div class="label">hasta:</div></td>
						<td><input type="text" name="to" id="to" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(movementFilter.getTo())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('actionForm.to', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						<td><input type="submit" value="Buscar"></td>
						<td><input type="reset" value="Reset"></td>
					</tr></table>
					</form>
					</td>
					
				</tr>
				<tr>
					<td align="center">
					<!-- Paging Table -->
					<form action="<%= leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("current_account_main.jsp")%>" method="post" name="pagingForm" id="pagingForm">
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
								<td align="center" class="results_table_header">Fecha</td>
								<td align="center" class="results_table_header">Movimiento</td>
								<td align="center" class="results_table_header">Usuario</td>
								<td align="center" class="results_table_header">Monto</td>
								<td align="center" class="results_table_header">Saldo</td>
								<td align="center" class="results_table_header">Documento Asociado</td>
								<td align="center" class="results_table_header">Comentarios</td>
							</tr>
                             <%
                             	leonards.spring.domain.Movement aMovement;
                             	String rowStyle;
		                      	for( int i = startIndex; i < startIndex + maxRowsPerPage && i < totalRows; i++ ) {
		                      		aMovement = (leonards.spring.domain.Movement)items.elementAt(i);
		                      		rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
                             %>
							<tr>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlShortDate(aMovement.getDate())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aMovement.getMovementType().getDescription())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=( aMovement.getUser() != null ? WebFrameworkToolkit.getHtmlString(aMovement.getUser().getCompleteName()) : "")%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=((aMovement.getMovementType().isExpenseMovement() ? "(" : "") + WebFrameworkToolkit.getHtmlDouble(aMovement.getAmount()) + (aMovement.getMovementType().isExpenseMovement() ? ")" : ""))%>&nbsp;</td>
								<td align="center" class="<%=rowStyle%>">&nbsp;<%=((aMovement.getBalance().doubleValue() < 0 ? "(" : "") + WebFrameworkToolkit.getHtmlDouble(aMovement.getBalance()) + (aMovement.getBalance().doubleValue() < 0  ? ")" : ""))%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aMovement.getDocumentData())%>&nbsp;</td>
								<td align="left" class="<%=rowStyle%>">&nbsp;<%=WebFrameworkToolkit.getHtmlString(aMovement.getComments())%>&nbsp;</td>
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
<!-- End Search Results Table -->
			</td>
		</tr>
	</table>
</body>
</html>
