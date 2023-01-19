<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<tm:Module name='DESIGNATION' />
<jsp:include page='/MasterPageTopSection.jsp' />
<table border='1'>
<h2>Designations</h2>
<tr>
<th colspan='4' style='text-align:right;padding:4px;'>
<a href='/styletwo/DesignationAddForm.jsp'>Add Designation</a>
</th>
</tr>
<tr>
<th style='width:60px'>S.No.</th>
<th style='width:200px'>Designation</th>
<th style='width:100px'>Edit</th>
<th style='width:100px'>Delete</th>
</tr>
<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='designationBean'> 
<%-- <tm:Designations> --%>
<tr>
<td style='text-align:right'>${serialNumber}</td>
<td>${designationBean.title}</td>
<td style='text-align:center'><a href='/styletwo/editDesignation?code=${designationBean.code}'>Edit</a></td>
<td style='text-align:center'><a href='/styletwo/confirmDeleteDesignation?code=${designationBean.code}'>Delete</a></td>
</tr>
<%-- </tm:Designations> --%>
</tm:EntityList>
</table>
<jsp:include page='/MasterPageBottomSection.jsp' />