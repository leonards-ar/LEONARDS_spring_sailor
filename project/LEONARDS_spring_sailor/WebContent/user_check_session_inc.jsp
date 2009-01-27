<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<%
	if( user == null || !user.isLoggedOn() ) {
		response.sendRedirect(leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("index.jsp?breakframes=X"));
	}
%>
