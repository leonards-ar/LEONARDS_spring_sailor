<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<%
	if( user == null || !user.isLoggedOn() ) {
%>
	<table width="100%">
	
	</table>
<%
	}
%>