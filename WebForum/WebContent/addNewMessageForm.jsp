<%@ page import="miniforum.IConstants"%>
<%@ page pageEncoding="windows-1251"%>
<form method="POST" action="doAddMessage">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td>
				<table border="0">
					<tr>
						<td align="center">Тема: <input type="text" size="46"
							name="<%=IConstants.SUBJECT_PARAM%>">
						</td>
					</tr>
					<tr>
						<td>Съобщение:</td>
					</tr>
					<tr>
						<td><textarea name="<%=IConstants.CONTENTS_PARAM%>"
								type="text" cols="40" rows="5"></textarea></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="Добави">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>