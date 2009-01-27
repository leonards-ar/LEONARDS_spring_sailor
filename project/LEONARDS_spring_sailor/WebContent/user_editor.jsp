<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="userToEdit" scope="session" type="leonards.spring.domain.User"/>

<%
String pageNumber;
try {
	pageNumber = new Integer( request.getParameter("pageNumber") ).toString();
} catch( Exception ex ) {
	pageNumber = "";
}
%>

<html>
<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/date-picker.js")%>"></script>
	<script language="JavaScript" type="text/javascript" src="<%=WebFrameworkToolkit.getDocumentUrl("includes/input-utils.js")%>"></script>
	
	<link rel="stylesheet" href="<%=leonards.common.web.framework.WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	
	<script language="javascript">
		<!--
			function doSubmit(webAction)
			{
				document.userForm.webaction.value = webAction;
				return true;
			}
		-->
	</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
              <form name="userForm" id="userForm" method="post" action="<%=leonards.common.web.framework.WebFrameworkToolkit.getServletUrl("WebActionRouterServlet")%>">
              <input type="hidden" name="webaction" value="SAVE_USER">     
              <input type="hidden" name="pageNumber" value="<%=pageNumber%>">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<br>
			</td>
		</tr>
		
		<tr>
			<td align="center">
<!-- User Table -->
			<table class="outer_table"><tr><td>
            <table class="inner_table">
						<tr>
							<td colspan="2" align="center" class="title3_bg"><div class="title3_txt">Edici&oacute;n de Usuario</div></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<jsp:include page="validation_error_inc.jsp"/>
							<jsp:include page="success_message_inc.jsp"/>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) usuario:</div></td>
							<td align="left">
							<% if( !userToEdit.isPersistent() ) { %>
							<input type="text" name="username" id="username" maxlength="50" size="20" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getUsername())%>">
							<% } else { %>
							<div class="value"><%=WebFrameworkToolkit.getHtmlString(userToEdit.getUsername())%></div>
							<% } %>
							</td>
						</tr>
						<% if( !userToEdit.isPersistent() ) { %>
						<tr>
							<td align="right"><div class="label">(*) contraseña:</div></td>
							<td align="left"><input type="password" name="password" id="password" maxlength="50" size="20"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) confirmaci&oacute;n contrase&ntilde;a:</div></td>
							<td align="left"><input type="password" name="passwordConfirmation" id="passwordConfirmation" maxlength="50" size="20"></td>
						</tr>
						<% } %>
						<tr>
							<td align="right"><div class="label">(*) apellido:</div></td>
							<td align="left"><input type="text" name="surname" id="surname" maxlength="255" size="30" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getSurname())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) nombre:</div></td>
							<td align="left"><input type="text" name="name" id="name" maxlength="255" size="30" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getName())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">(*) e-mail:</div></td>
							<td align="left"><input type="text" name="email" id="email" maxlength="255" size="30" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getEmail())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">direcci&oacute;n:</div></td>
							<td align="left"><input type="text" name="address" id="address" maxlength="255" size="40" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getAddress())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">tel&eacute;fono:</div></td>
							<td align="left"><input type="text" name="telephone" id="telephone" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getTelephone())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">celular:</div></td>
							<td align="left"><input type="text" name="celphone" id="celphone" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getCelPhone())%>"></td>
						</tr>
						<tr>
							<td align="right"><div class="label">documento:</div></td>
							<td align="left">
								<select name="documentTypeId" id="documentTypeId">
								<%
									java.util.Iterator documentTypes = leonards.spring.domain.StaticDataManager.getDocumentTypes().iterator();
									leonards.spring.domain.ReferenceData aDocumentType;
									while( documentTypes != null && documentTypes.hasNext() ) {
										aDocumentType = (leonards.spring.domain.ReferenceData)documentTypes.next();
								%>
									<option value="<%=aDocumentType.getIdAsInteger()%>" <%=(aDocumentType.getIdAsInteger().equals(userToEdit.getDocument().getDocumentTypeId()) ? "selected" : "")%>><%=aDocumentType.getDescription()%></option>
								<%
									}
								%>
								</select>
								&nbsp;
								<input type="text" name="document" id="document" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getDocument().getNumber())%>">
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">habilitaci&oacute;n:</div></td>
							<td align="left">
								<select name="permitTypeId" id="permitTypeId">
								<%
									java.util.Iterator permitTypes = leonards.spring.domain.StaticDataManager.getPermitTypes().iterator();
									leonards.spring.domain.ReferenceData aPermitType;
									while( permitTypes != null && permitTypes.hasNext() ) {
										aPermitType = (leonards.spring.domain.ReferenceData)permitTypes.next();
								%>
									<option value="<%=aPermitType.getIdAsInteger()%>" <%=(aPermitType.getIdAsInteger().equals(userToEdit.getPermit().getPermitTypeId()) ? "selected" : "")%>><%=aPermitType.getDescription()%></option>
								<%
									}
								%>
								</select>
								&nbsp;
								<input type="text" name="permit" id="permit" maxlength="50" size="15" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getPermit().getNumber())%>">
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">vencimiento:</div></td>
							<td align="left"><input type="text" name="permit_due_date" id="permit_due_date" maxlength="50" size="15" onKeyPress="javascript:noInput();" value="<%=WebFrameworkToolkit.getHtmlShortDate(userToEdit.getPermit().getDueDate())%>">&nbsp;<input type="button" value="..." onClick="javascript:show_calendar('userForm.permit_due_date', 'white', '#006699','#0099CC', '#333399', '<%=WebFrameworkToolkit.getImageUrl("button_01.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_02.gif")%>','<%=WebFrameworkToolkit.getImageUrl("button_03.gif")%>', '<%=WebFrameworkToolkit.getImageUrl("button_04.gif")%>')"></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">legajo:</div></td>
							<td align="left"><input type="text" name="dossier" id="dossier" maxlength="10" size="10" value="<%=WebFrameworkToolkit.getHtmlString(userToEdit.getDossier())%>"></td>
						</tr>						
						<tr>
							<td align="right"><div class="label">estado:</div></td>
							<td align="left">
								<select name="stateId" id="stateId">
								<%
									java.util.Iterator states = leonards.spring.domain.StaticDataManager.getUserStates().iterator();
									leonards.spring.domain.UserState aState;
									while( states != null && states.hasNext() ) {
										aState = (leonards.spring.domain.UserState)states.next();
								%>
									<option value="<%=aState.getIdAsInteger()%>" <%=(aState.getIdAsInteger().equals(userToEdit.getStateId()) ? "selected" : "")%>><%=aState.getDescription()%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>						
						<tr>
							<td align="right"><div class="label">perfil:</div></td>
							<td align="left">
								<select name="profileId" id="profileId">
								<%
									java.util.Iterator profiles = leonards.spring.domain.StaticDataManager.getProfiles().iterator();
									leonards.spring.domain.Profile aProfile;
									while( profiles != null && profiles.hasNext() ) {
										aProfile = (leonards.spring.domain.Profile)profiles.next();
								%>
									<option value="<%=aProfile.getIdAsInteger()%>" <%=(aProfile.getIdAsInteger().equals(userToEdit.getProfileId()) ? "selected" : "")%>><%=aProfile.getDescription()%></option>
								<%
									}
								%>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right"><div class="label">asociaci&oacute;n:</div></td>
							<td align="left">
								<select name="department" id="department">
									<option value="CEITBA" <%=("CEITBA".equalsIgnoreCase(userToEdit.getDepartment()) ? "selected" : "")%>>CEITBA</option>
									<option value="AGITBA" <%=("AGITBA".equalsIgnoreCase(userToEdit.getDepartment()) ? "selected" : "")%>>AGITBA</option>
									<option value="ITBA" <%=("ITBA".equalsIgnoreCase(userToEdit.getDepartment()) ? "selected" : "")%>>ITBA</option>
									<option value="Ninguno" <%=("Ninguno".equalsIgnoreCase(userToEdit.getDepartment()) ? "selected" : "")%>>Ninguno</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td align="center" width="50%"><input type="submit" value="Cancelar" name="formCancel" id="formCancel" onClick="return doSubmit('CANCEL_EDIT_USER');"></td>
									<td align="center" width="50%"><input type="submit" value="Guardar" name="formSubmit" id="formSubmit" onClick="return doSubmit('SAVE_USER');"></td>
								</tr>
							</table>
							</td>
						</tr>
					
				</table>
			</td></tr></table>
<!-- End User Table -->
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
