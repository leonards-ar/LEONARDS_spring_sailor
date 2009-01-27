<%@ page import="leonards.common.web.framework.*"%>
<jsp:include page="user_check_session_inc.jsp"/>
<jsp:useBean id="user" scope="session" class="leonards.spring.domain.User"/>
<jsp:useBean id="reservation" scope="session" class="leonards.spring.domain.Reservation"/>
<html>
	<head>
	<title>Sistema de Reservas Primavera</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<jsp:include page="html_header_inc.jsp"/>

	<link rel="stylesheet" href="<%=WebFrameworkToolkit.getDocumentUrl("includes/spring.css")%>" type="text/css">
	</head>
<BODY>
<table width="100%" border="0">
  <tr> 
    <td height="90" align="left"><img src="<%=WebFrameworkToolkit.getDocumentUrl("images/yca.gif")%>" height="82"></td>
    <td width="100%" height="90" align="center"><font size="+2">Formulario Rol (<%=WebFrameworkToolkit.getHtmlLongDate(reservation.getFrom())%>)</font></td>
    <td height="90" align="right"><img src="<%=WebFrameworkToolkit.getDocumentUrl("images/logoitba.jpg")%>" width="182" height="68"></td>
  </tr>
</table>
<hr>
<font size="3">Sres. YCA:<br>
Por la presente se autoriza al Sr/a<b> 
<%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%>
,</b> Documento<b> 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getDocumentType().getDescription())%>
&nbsp; 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getNumber())%>
</b>a embarcarse en el &quot;<%=WebFrameworkToolkit.getHtmlString(reservation.getBoat().getName())%>&quot; el d&iacute;a (mes/d&iacute;a/a&ntilde;o) 
<b>
<%=WebFrameworkToolkit.getHtmlLongDate(reservation.getFrom())%>
,</b> por lo que le solicito se autoricen el ingreso a las instalaciones del YCA 
a todos los miembros de la tripulaci&oacute;n. </font> 
<p><font size="3">Muchas Gracias</font></p>
<p><font size="3">Comisi&oacute;n de N&aacute;utica del Centro de Estudiantes 
  del ITBA.</font></p>
<p><font size="4">Timonel:</font><br>
  <font size="3">Apellido, Nombre: 
  <%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%>
  <br>
  Documento: 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getDocumentType().getDescription())%>
&nbsp; 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getNumber())%>
  <br>
  Habilitaci&oacute;n: 
  <%=WebFrameworkToolkit.getHtmlString(user.getPermit().getPermitType().getDescription())%>
  &nbsp; 
  <%=WebFrameworkToolkit.getHtmlString(user.getPermit().getNumber())%>
  <br>
  Direcci&oacute;n: 
  <%=WebFrameworkToolkit.getHtmlString(user.getAddress())%>
  <br>
  Tel&eacute;fono: 
  <%=WebFrameworkToolkit.getHtmlString(user.getTelephone())%>
  </font></p>
<p><font size="4">Tripulantes: </font></p>
<table width="100%" border="1">
  <tr> 
    <td width="37%"> 
      <div align="center"><font size="3">Nombre y Apellido</font></div>
    </td>
    <td width="28%"> 
      <div align="center"><font size="3">Documento</font></div>
    </td>
    <td width="16%"> 
      <div align="center"><font size="3">Tipo</font></div>
    </td>
    <td width="19%"> 
      <div align="center"><font size="3">Tel&eacute;fono</font></div>
    </td>
  </tr>
  <%
  	leonards.spring.domain.Contact aContact;
  	for( int i = 0; i < leonards.spring.domain.StaticDataManager.getParameter(leonards.spring.domain.Parameter.MAX_INVITED_CREW).getValueAsInt(); i++ ) {
  		if( reservation.getContacts().size() > i ) {
  			aContact = (leonards.spring.domain.Contact)reservation.getContacts().elementAt(i);
  		} else {
  			aContact = null;
  		}
  %>
  <tr> 
    <td width="37%" height="35" align="center" valign="middle"> <font size="3">&nbsp; 
       <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getCompleteName():"")%> </font></td>
    <td width="28%" height="35" align="center" valign="middle"> <font size="3">&nbsp; 
       <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getDocument().getNumber():"")%></font></td>
    <td width="16%" height="35" align="center" valign="middle"> 
      <div align="center"><font size="3">&nbsp; <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getDocument().getDocumentType().getDescription():"")%></font></div>
    </td>
    <td width="19%" height="35" align="center" valign="middle"> <font size="3">&nbsp; 
      <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getTelephone():"")%> </font></td>
  </tr>
  <%
  	}
  %>

</table>
<p><font size="3"><b>Fecha y Horario de zarpada: </b> 
   <%=WebFrameworkToolkit.getHtmlDateTime(reservation.getFrom())%>
  <br>
  <b>Fecha y Horario de regreso:</b> 
   <%=WebFrameworkToolkit.getHtmlDateTime(reservation.getTo())%>
  </font></p>
<p><font size="3"><b>Zona de Navegaci&oacute;n:</b> Rio de la Plata </font></p>
<p align="center">&nbsp;</p>
<p align="center"><font size="3"><br>
  Firma del Timonel</font></p>
<p align="center"><font size="3">Aclaraci&oacute;n: &nbsp; 
  <%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%>
  </font>
<p><font size="3">(Aclaraciones y novedades al dorso) </font>
<p>&nbsp; 
<p>&nbsp; 
<p><font size="3"><br>
  </font>
<hr>
<p><font size="3">Apellido, Nombre: 
  <%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%>
  <br>
  Turno:
  <%
  	leonards.spring.domain.ReservationShift shift = (leonards.spring.domain.ReservationShift)reservation.getShifts().firstElement();
  	String shiftDescription = (shift != null ? shift.getShift().getDescription() : "");
  %> 
  <%=WebFrameworkToolkit.getHtmlString(shiftDescription)%>
  <br>
  Tel&eacute;fono: 
  <%=WebFrameworkToolkit.getHtmlString(user.getTelephone())%>
  <br>
  Fecha (mes/dia/a&ntilde;o): <%=WebFrameworkToolkit.getHtmlLongDate(reservation.getFrom())%> </font></p>
<p>
<hr>
<br>
<p align="center"><font size="5">Liberaci&oacute;n de Responsabilidad </font></p>
<br>
<font size="3">En tanto y en cuanto el barco se encuentra habilitado por los organismos 
correspondientes y el timonel tiene t&iacute;tulo habilitante, &eacute;ste y la 
tripulaci&oacute;n liberan de responsabilidad sin condicionamiento alguno al ITBA, 
al Centro de Estudiantes del ITBA y/o al titular a nombre de quien se haya inscripto 
la nave, respecto de cualquier da&ntilde;o que puedan causarse a s&iacute; mismos 
o a terceras personas o a sus bienes o a los de terceros. </font><br>
<p><br>
  <font size="4">Timonel</font></p>
<p><font size="3">Apellido, Nombre: 
 <%=WebFrameworkToolkit.getHtmlString(user.getCompleteName())%>
  <br>
  Documento: 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getDocumentType().getDescription())%>
&nbsp; 
<%=WebFrameworkToolkit.getHtmlString(user.getDocument().getNumber())%>
  <br>
  </font> </p>
<p>&nbsp;</p>
<p><font size="3">Firma: </font></p>
<p></p>
<p><br>
  <font size="4">Tripulantes</font></p>
<table width="100%" border="1">
  <tr> 
    <td width="41%"> 
      <div align="center"><b><font size="3">Nombre y Apellido</font></b></div>
    </td>
    <td width="28%"> 
      <div align="center"><b><font size="3"> Documento</font></b></div>
    </td>
    <td width="12%"> 
      <div align="center"><b><font size="3">Tipo de Documento</font></b></div>
    </td>
    <td width="19%"> 
      <div align="center"><b><font size="3">Firma</font></b></div>
    </td>
  </tr>
  <%
  	for( int i = 0; i < leonards.spring.domain.StaticDataManager.getParameter(leonards.spring.domain.Parameter.MAX_INVITED_CREW).getValueAsInt(); i++ ) {
  		if( reservation.getContacts().size() > i ) {
  			aContact = (leonards.spring.domain.Contact)reservation.getContacts().elementAt(i);
  		} else {
  			aContact = null;
  		}
  %>  
  <tr> 
    <td width="41%" height="35" align="center" valign="middle"> <font size="3">&nbsp; 
       <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getCompleteName():"")%> </font></td>
    <td width="28%" height="35" align="center" valign="middle"> <font size="3">&nbsp; 
      <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getDocument().getNumber():"")%>
	  
	   </font></td>
    <td width="12%" height="35" align="center" valign="middle"> 
      <div align="center"> <font size="3">&nbsp; <%=WebFrameworkToolkit.getHtmlString(aContact!=null?aContact.getDocument().getDocumentType().getDescription():"")%></font></div>
    </td>
    <td width="19%" height="35" align="center" valign="middle"><font size="3">&nbsp;</font> 
    </td>
  </tr>
	<%
	}
	%>
</table>
<p align="center">&nbsp; </p>
</body>	
</html>