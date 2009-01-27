<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>

<%
		java.util.Vector items = leonards.spring.domain.ReportManager.reports();
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
	<title>Sistema de Reservas Frana</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">

	<script language="javascript">
		<!--
			function submitPagingForm( pageNumber, mustSubmit )
			{
				document.pagingForm.pageNumber.value = pageNumber;
				if( mustSubmit ) {
					document.pagingForm.submit();
				} else {
					return true;
				}
			}
			
			function submitActionForm( actionCommand, reportName, mustSubmit )
			{
				document.actionForm.webaction.value = actionCommand;
				document.actionForm.reportname.value = reportName;
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
						Reportes
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
					<!-- Paging Table -->
					<form action="<%= leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("users_main.jsp")%>" method="post" name="pagingForm" id="pagingForm">
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
						<form name="actionForm" id="actionForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
						<input type="hidden" name="webaction" value="EXECUTE_REPORT">
						<input type="hidden" name="reportname" value="">
						<input type="hidden" name="pageNumber" value="<%=pageNumber%>">
						<table>
							<tr>
								<td align="center" class="results_table_header">Reporte</td>
							</tr>
                             <%
                             	leonards.spring.domain.Report aReport;
                             	String rowStyle;
		                      	for( int i = startIndex; i < startIndex + maxRowsPerPage && i < totalRows; i++ ) {
		                      		aReport = (leonards.spring.domain.Report)items.elementAt(i);
		                      		rowStyle = ( i % 2 == 0 ? "even_results_table_row" : "odd_results_table_row" );
                             %>
							<tr>
								<td align="left" class="<%=rowStyle%>">&nbsp;<a class="action_link" href="javascript:submitActionForm('EXECUTE_REPORT','<%=aReport.getKey()%>', true)"><%=WebFrameworkToolkit.getHtmlString(aReport.getName())%></a>&nbsp;</td>
							</tr>
							<%
								}
							%>
						</table>
						</form>
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
