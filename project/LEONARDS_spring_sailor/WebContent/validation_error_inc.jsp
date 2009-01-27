<jsp:useBean id="validationErrors" scope="session" class="java.util.Vector"/>
<%
	if( validationErrors != null && validationErrors.size() > 0 ) {
%>
            <table>
<%
		String anError;
		for( int i = 0; i < validationErrors.size(); i++ ) {
			anError = validationErrors.elementAt(i).toString();
%>
				<tr>
					<td align="left"><div class="validation_error"><%=anError%></div></td>
				</tr>
<%
		}
%>
			</table>
<%
	}
%>
