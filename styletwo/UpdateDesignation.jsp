<%@ taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION' />
<jsp:useBean id="designationBean" scope="request" class="com.thinking.machines.hr.beans.DesignationBean" />
<jsp:setProperty name="designationBean" property="*" />
<jsp:forward page="/updateDesignation" />