<jsp:useBean id="successMessage" scope="session" class="java.lang.String"/>
<%
	if( successMessage != null && successMessage.trim().length() > 0 ) {
%>
            <table>
				<tr>
					<td align="left"><div class="success_message"><%=successMessage.trim()%></div></td>
				</tr>
			</table>
<%
	}
%>
