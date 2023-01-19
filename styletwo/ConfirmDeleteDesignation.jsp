<%@ taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION' />
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean'/>
<jsp:include page='/MasterPageTopSection.jsp' />
<h3>Delete Designation (Module)</h3>
<br>
Delete <b>${designationBean.title}</b> desingation? <br></br>
<br>
<form method='post' action='/styletwo/DeleteDesignation.jsp'>
<input type='hidden' id='code' name='code' value='${designationBean.code}'>
<button type='submit'> Delete </button>
<a href='/styletwo/Designations.jsp'>
<button type='button'>Cancel</button>
</a>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />