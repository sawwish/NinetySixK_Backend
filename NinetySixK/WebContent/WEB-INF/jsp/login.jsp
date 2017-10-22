<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="title"/></title>
<style>
   .error { color: red; }
 </style>
</head>
<body>
	<form>
		<table>
			<tr>
				<td>Username</td>
				<td><input path="username" /></td>
			</tr>
			<tr>
				<td>Pasword</td>
				<td><input path="password" /></td>
			</tr>
			<tr>
				<td>
					<input type="submit"  value="Login"/>
				</td>
			</tr>
			
		</table>
				
	</form>
	
</body>
</html>