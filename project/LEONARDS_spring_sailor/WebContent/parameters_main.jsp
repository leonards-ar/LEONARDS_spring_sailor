<jsp:include page="user_check_session_inc.jsp"/>

<%
	java.util.Iterator params = null;
	try {
		params = leonards.common.base.CommonUtils.getVector(leonards.spring.domain.StaticDataManager.getParameters()).iterator();
	} catch( Exception ex ) {
		params = null;
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
			function doLogin()
			{
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="parametersForm" id="parametersForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_PARAMETERS">     
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br><br>
			</td>
		</tr>
		<tr>
			<td align="center">
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Parámetros del Sistema de Reservas</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
				<%
					leonards.spring.domain.Parameter aParam;
					java.util.Iterator items;
					leonards.spring.domain.ReferenceData anItem;
					while(params != null && params.hasNext() ) {
						aParam = (leonards.spring.domain.Parameter)params.next();
				%>
						<tr>
							<td align="right"><div class="label"><%=aParam.getLabel()%>:</div></td>
							<td align="left">
							
				<%
						if( aParam.hasReferenceDataSql() ) {
				%>
							<select name="<%=aParam.getName()%>">
				<%
							try {
								items = aParam.getReferenceData().iterator();
							} catch( Throwable ex ) {
				%>
<!--
Error:
<%= ex instanceof leonards.common.base.NestedException ? ((leonards.common.base.NestedException)ex).getNestedMessage() : ex.getMessage() %>
-->
				<%
								items = null;
							}
							while( items != null && items.hasNext() ) {
								anItem = (leonards.spring.domain.ReferenceData)items.next();
				%>
									<option value="<%=anItem.getIdAsString()%>" <%=(anItem.getIdAsString().equals(aParam.getValue()) ? "selected" : "")%>><%=anItem.getDescription()%></option>
				<%
							}
				%>
							</select>
				<%
						} else { 
				%>
							<input type="text" name="<%=aParam.getName()%>" id="<%=aParam.getName()%>" maxlength="255" size="30" value="<%=aParam.getValue()%>">
				<%
						}
				%>
							</td>
						</tr>
				<%
					}
				%>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="Guardar" name="paramsSubmit" id="paramsSubmit" onClick="return doLogin();"></td>
						</tr>
					
				</table>
			</td></tr></table>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
