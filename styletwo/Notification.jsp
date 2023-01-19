<jsp:useBean id='messageBean' scope='request' class='com.thinking.machines.hr.beans.MessageBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<h3>
<jsp:getProperty name='messageBean' property='heading' />
</h3>
<br>
<jsp:getProperty name='messageBean' property='message' /> <br></br>

<tm:If condition='${messageBean.generateButtons}'>
<table>
<tr>
<td>
<form action='/styletwo/${messageBean.buttonOneAction}'>
<button type='submit'>${messageBean.buttonOneText}</button>
</form>
</td>

<tm:If condition='${messageBean.generateTwoButtons}'>
<td>
<form action='/styletwo/${messageBean.buttonTwoAction}'>
<button type='submit'>${messageBean.buttonTwoText}</button>
</form>
</td>

</tm:If>

</tr>
</table>

</tm:If>

<jsp:include page='/MasterPageBottomSection.jsp' />