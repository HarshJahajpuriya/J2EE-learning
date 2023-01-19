<%@taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean'/>
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean'/>
<tm:Module name="DESIGNATION" />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/DesignationAddForm.js'></script>
<h3>Edit Designation (Module)</h3>
<br>
Desingation : 
<br>
<span class='error'>
<jsp:getProperty name='errorBean' property='error' />
</span>
<form method='post' action='/styletwo/UpdateDesignation.jsp' onsubmit='return validateForm(this)'>
<input type='hidden' id='code' name='code' value='${designationBean.code}'>
<input type='text' id='title' name='title' maxlength='35' size='36' value='<jsp:getProperty name="designationBean" property="title" />'>
<span id='designationErrorSection' class='error'> </span>
<br>
<br>
<button type='submit'> Update </button>
<a href='/styletwo/Designations.jsp'>
<button type='button'>Cancel</button>
</a>
</form>
<jsp:include page='/MasterPageBottomSection.jsp' />