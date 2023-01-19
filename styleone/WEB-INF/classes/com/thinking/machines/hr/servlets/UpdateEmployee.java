package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.text.*;
import java.io.*;

public class UpdateEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
response.setContentType("text/html");
PrintWriter pw = response.getWriter();
String employeeId = request.getParameter("employeeId");
String name = request.getParameter("name");
int designationCode = Integer.parseInt(request.getParameter("designationCode"));
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender = request.getParameter("gender");
String isIndian = request.getParameter("isIndian");
if(isIndian == null) isIndian="N";
BigDecimal basicSalary = new BigDecimal(request.getParameter("basicSalary"));
String panNumber = request.getParameter("panNumber");
String aadharCardNumber = request.getParameter("aadharCardNumber");
boolean designationCodeExists=false, panNumberExists=false, aadharCardNumberExists=false, employeeIdExists=false;
// Perform checks for
// 1. Employee Id is valid
EmployeeDAO employeeDAO = new EmployeeDAO();
employeeIdExists = employeeDAO.employeeIdExists(employeeId);
System.out.println("101"+employeeIdExists);
if(employeeIdExists == false)
{
sendBackView(response);
}
// 2. Designation should exist
DesignationDAO designationDAO = new DesignationDAO();
designationCodeExists = designationDAO.designationCodeExists(designationCode);
// 3. PAN Number should not exist against any other employee
EmployeeDTO employee;
try 
{
employee = employeeDAO.getByPANNumber(panNumber);
if(employee.getEmployeeId().equalsIgnoreCase(employeeId)==false) 
{
panNumberExists = true;
}
} catch(DAOException daoException)
{
panNumberExists = false;
}
// 4. Aadhar Card Number should not exist
try 
{
employee = employeeDAO.getByAadharCardNumber(aadharCardNumber);
if(employee.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists = true;
}
} catch(DAOException daoException)
{
aadharCardNumberExists = false;
}
if(designationCodeExists == false || panNumberExists || aadharCardNumberExists) 
{
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("</head>");
pw.println("<script>");
pw.println("function validateForm(form)");
pw.println("{");
pw.println("var isValid = true;");
pw.println("var inFocus = null;");
pw.println("");
pw.println("form.name.value = form.name.value.trim();");
pw.println("document.getElementById('nameErrorSection').innerHTML='';");
pw.println("if(form.name.value.length == 0) ");
pw.println("{");
pw.println("isValid = false;");
pw.println("inFocus = form.name;");
pw.println("document.getElementById('nameErrorSection').innerHTML='&nbsp;&nbsp;&nbsp;Name Required';");
pw.println("}");
pw.println("");
pw.println("document.getElementById('designationCodeErrorSection').innerHTML = '';");
pw.println("if(form.designationCode.value == -1)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.designationCode;");
pw.println("document.getElementById('designationCodeErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Select Designation';");
pw.println("}");
pw.println("");
pw.println("document.getElementById('dateOfBirthErrorSection').innerHTML = '';");
pw.println("if(form.dateOfBirth.value.length == 0)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.dateOfBirth;");
pw.println("document.getElementById('dateOfBirthErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Date of Birth Required';");
pw.println("}");
pw.println("");
pw.println("document.getElementById('genderErrorSection').innerHTML = '';");
pw.println("if(form.male.checked == false && form.female.checked == false)");
pw.println("{");
pw.println("isValid = false;");
pw.println("document.getElementById('genderErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Select Gender';");
pw.println("}");
pw.println("");
pw.println("document.getElementById('basicSalaryErrorSection').innerHTML = '';");
pw.println("form.basicSalary.value = form.basicSalary.value.trim();");
pw.println("if(form.basicSalary.value.length == 0)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.basicSalary;");
pw.println("document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Basic Salary Required';");
pw.println("} else");
pw.println("{");
pw.println("var e = '1234567890.';");
pw.println("var validBasicSalary = true;");
pw.println("for(var i=0; i<basicSalary.value.length; i++)");
pw.println("{");
pw.println("if(e.indexOf(basicSalary.value.charAt(i)) == -1)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.basicSalary;");
pw.println("document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Basic Salary';");
pw.println("validBasicSalary = false;");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("if(validBasicSalary)");
pw.println("{");
pw.println("var dot = basicSalary.value.indexOf('.');");
pw.println("if(dot != -1)");
pw.println("{");
pw.println("var fractionalPartLength = basicSalary.value.length - (dot + 1);");
pw.println("if(fractionalPartLength > 2)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.basicSalary;");
pw.println("document.getElementById('basicSalaryErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Basic Salary';");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("");
pw.println("form.panNumber.value = form.panNumber.value.trim();");
pw.println("document.getElementById('panNumberErrorSection').innerHTML = '';");
pw.println("if(form.panNumber.value.length == 0)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.panNumber;");
pw.println("document.getElementById('panNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;PAN Number Required';");
pw.println("} else if(form.panNumber.value.length != 10) ");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.panNumber;");
pw.println("document.getElementById('panNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid PAN Number';");
pw.println("}");
pw.println("");
pw.println("form.aadharCardNumber.value = form.aadharCardNumber.value.trim();");
pw.println("document.getElementById('aadharCardNumberErrorSection').innerHTML = '';");
pw.println("if(form.aadharCardNumber.value.length == 0)");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.aadharCardNumber;");
pw.println("document.getElementById('aadharCardNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Aadhar Card Number Required';");
pw.println("} else if(form.aadharCardNumber.value.length != 12) ");
pw.println("{");
pw.println("isValid = false;");
pw.println("if(inFocus == null) inFocus = form.aadharCardNumber;");
pw.println("document.getElementById('aadharCardNumberErrorSection').innerHTML = '&nbsp;&nbsp;&nbsp;Invalid Aadhar Card Number';");
pw.println("}");
pw.println("");
pw.println("inFocus.focus();");
pw.println("return isValid;");
pw.println("}");
pw.println("</script>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left-section starts here -->");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<a href='/styleone/designationsView'> Designations </a> <br>");
pw.println("<b> Employees </b>");
pw.println("</div>");
pw.println("<!-- left-section ends here -->");
pw.println("<!-- right-section starts here -->");
pw.println("<div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<h3>Edit Employee (Module)</h3>");
pw.println("<br>");
pw.println("<form method='POST' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");
pw.println("<table>");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Name : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='name' name='name' maxlength='35' size='36' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Designation : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
List<DesignationDTO> designations;
designations = designationDAO.getAll();
int code;
String title;
for(DesignationDTO designation: designations)
{
code = designation.getCode();
title = designation.getTitle();
if(designationCode == code)
pw.println("<option selected value='"+code+"'>"+title+"</option>");
else 
pw.println("<option value='"+code+"'>"+title+"</option>");
}
pw.println("</select>");
if(designationCodeExists == false)
pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid designation</span>");
else 
pw.println("<span id='designationCodeErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Date Of Birth : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Gender : ");
pw.println("</td>");
pw.println("<td>");
if(gender.equals("M"))
pw.println("<input checked type='radio' id='male' name='gender' value='M'>");
else 
pw.println("<input type='radio' id='male' name='gender' value='M'>");
pw.println("Male");
pw.println("&nbsp;&nbsp;&nbsp;");
if(gender.equals("F"))
pw.println("<input checked type='radio' id='female' name='gender' value='F'>");
else
pw.println("<input type='radio' id='female' name='gender' value='F'>");
pw.println("Female");
pw.println("<span id='genderErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Indian : ");
pw.println("</td>");
pw.println("<td>");
if(isIndian.equals("Y"))
pw.println("<input type='checkbox' checked id='isIndian' name='isIndian' value='Y'>");
else 
pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Y'>");
pw.println("<span id='isIndianErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Basic salary : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='basicSalary' name='basicSalary' maxlength='12' size='13' value='"+basicSalary.toPlainString()+"'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("PAN Number : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='"+panNumber+"'>");
if(panNumberExists)
pw.println("<span id='panNumberErrorSection' style='color:red'>PAN number exists.</span>");
else 
pw.println("<span id='panNumberErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("");
pw.println("<tr style='height:30px'>");
pw.println("<td>");
pw.println("Aadhar Card Number : ");
pw.println("</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
if(aadharCardNumberExists)
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar card number exists.</span>");
else
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'> </span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("</tr>");
pw.println("");
pw.println("<tr style='height:80px'>");
pw.println("<td style='text-align:right'>");
pw.println("<button type='submit'> Update </button>&nbsp;&nbsp;");
pw.println("</td>");
pw.println("<td style='text-align:left'>");
pw.println("<a href='/styleone/employeesView'>");
pw.println("<button type='button'>Cancel</button>");
pw.println("</a>");
pw.println("</td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</form>");
pw.println("</div>");
pw.println("<!-- right-section ends here -->");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
return;
}

employee = new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian.equals("Y"));
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
System.out.println("SDF");
employeeDAO.update(employee);
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<a href='/styleone/designationsView'> Designations </a> <br>");
pw.println("<a href='/styleone/employeesView'> Employees </a>");
pw.println("</div>");
pw.println("<div style='width:auto;height:72vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<h3> Employee Updated </h3>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'> No </button>");
pw.println("</form>");
pw.println("</div>");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
}catch(Exception exception)
{
// send back addEmployee form with the error messages
System.out.println(exception);	// remove after testing
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request, response);
}
private void sendBackView(HttpServletResponse response) 
{
try 
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
EmployeeDAO employeeDAO;
employeeDAO = new EmployeeDAO();
List <EmployeeDTO> employees = employeeDAO.getAll();
PrintWriter pw;
response.setContentType("text/html");
pw = response.getWriter();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title> CRUD Application (Style One) </title>");
pw.println("<script>");
pw.println("function Employee()");
pw.println("{");
pw.println("this.employeeId='';");
pw.println("this.name='';");
pw.println("this.designationCode=0;");
pw.println("this.desingation='';");
pw.println("this.dateOfBirth='';");
pw.println("this.gender='';");
pw.println("this.isIndian='';");
pw.println("this.basicSalary=0;");
pw.println("this.panNumber='';");
pw.println("this.aadharCardNumber='';");
pw.println("}");
pw.println("var selectedRow = null;");
pw.println("var employees = [];");
int i=0;
for(EmployeeDTO employee: employees)
{
pw.println("var employee = new Employee();");
pw.println("employee.employeeId='"+employee.getEmployeeId()+"';");
pw.println("employee.name='"+employee.getName()+"';");
pw.println("employee.designationCode="+employee.getDesignationCode()+";");
pw.println("employee.designation='"+employee.getDesignation()+"';");
pw.println("employee.dateOfBirth='"+simpleDateFormat.format(employee.getDateOfBirth())+"';");
pw.println("employee.gender='"+employee.getGender()+"';");
pw.println("employee.isIndian="+employee.getIsIndian()+";");
pw.println("employee.basicSalary="+employee.getBasicSalary().toPlainString()+";");
pw.println("employee.panNumber='"+employee.getPANNumber()+"';");
pw.println("employee.aadharCardNumber='"+employee.getAadharCardNumber()+"';");
pw.println("employees["+i+"] = employee;");
i++;
}
pw.println("function selectEmployee(row, employeeId)");
pw.println("{");
pw.println("if(selectedRow != null)");
pw.println("{");
pw.println("selectedRow.style.background='white';");
pw.println("selectedRow.style.color='black';");
pw.println("}");
pw.println("row.style.background='#7f8185';");
pw.println("row.style.color='white';");
pw.println("selectedRow = row;");
pw.println("for(var i=0; i<employees.length; i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp = employees[i];");
pw.println("document.getElementById('detailPanel_employeeId').innerHTML = emp.employeeId;");
pw.println("document.getElementById('detailPanel_name').innerHTML = emp.name;");
pw.println("document.getElementById('detailPanel_designation').innerHTML = emp.designation;");
pw.println("document.getElementById('detailPanel_dateOfBirth').innerHTML = emp.dateOfBirth;");
pw.println("document.getElementById('detailPanel_basicSalary').innerHTML = emp.basicSalary;");
pw.println("document.getElementById('detailPanel_isIndian').innerHTML = emp.isIndian===true?'Yes':'No';");
pw.println("document.getElementById('detailPanel_panNumber').innerHTML = emp.panNumber;");
pw.println("document.getElementById('detailPanel_aadharCardNumber').innerHTML = emp.aadharCardNumber;");
pw.println("document.getElementById('detailPanel_gender').innerHTML = emp.gender;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- main starts here -->");
pw.println("<div style='width:90hw;height:94vh;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a>");
pw.println("<div style='height:60px;font-size:40px;margin:5px;margin-bottom:0'>Thinking Machines</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:74vh;margin:5px;border:1px solid white'>");
pw.println("<div style='width:auto;height:70.8vh;margin:5px;border:1px solid black;float:left;padding:4px 8px'>");
pw.println("<a href='/styleone/designationsView'> Designations </a> <br>");
pw.println("<b> Employees </b>");
pw.println("</div>");
pw.println("<!-- right section starts here -->");
pw.println("<div style='width:auto;height:71.46vh;margin:5px;margin-left:110px;border:1px solid black;'>");
pw.println("<!-- right section upper part starts here -->");
pw.println("<div style='width:auto;height:45vh;margin:5px;border:1px solid black;overflow:scroll;padding:5px'>");
pw.println("<table border='1'>");
pw.println("<h2>Designations</h2>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:4px;'>");
pw.println("<a href='/styleone/getAddEmployeeForm'>Add Employee</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px'>S.No.</th>");
pw.println("<th style='width:200px'>Id.</th>");
pw.println("<th style='width:200px'>Name</th>");
pw.println("<th style='width:200px'>Designation</th>");
pw.println("<th style='width:100px'>Edit</th>");
pw.println("<th style='width:100px'>Delete</th>");
pw.println("</tr>");
i=1;
for(EmployeeDTO employee: employees)
{
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this, \""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+i+"</td>");
pw.println("<td>"+employee.getEmployeeId()+"</td>");
pw.println("<td>"+employee.getName()+"</td>");
pw.println("<td>"+employee.getDesignation()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
i++;
}
pw.println("</table>");
pw.println("</div> <!-- right section upper part ends here -->");
pw.println("<!-- right section lower part starts here -->");
pw.println("<div style='width:auto;height:20vh;margin:5px;border:1px solid black;padding:5px;'>");
pw.println("<label style='background-color:gray;color:white;padding: 0 5px'>Details: -</label>");
pw.println("<table style='width:100%'>");
pw.println("<tr>");
pw.println("<td>Employee Id: <span id='detailPanel_employeeId'></span></td>");
pw.println("<td>Name: <span id='detailPanel_name'></span></td>");
pw.println("<td>Designation: <span id='detailPanel_designation'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date Of Birth: <span id='detailPanel_dateOfBirth'></span></td>");
pw.println("<td>Gender: <span id='detailPanel_gender'></span></td>");
pw.println("<td>Is Indian: <span id='detailPanel_isIndian'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary: <span id='detailPanel_basicSalary'></span></td>");
pw.println("<td>Pan Number: <span id='detailPanel_panNumber'></span></td>");
pw.println("<td>Aadhar Card Number: <span id='detailPanel_aadharCardNumber'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div> <!-- right section lower part ends here -->");
pw.println("</div> <!-- right section ends here -->");
pw.println("</div> <!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;margin-top:20px;border:1px solid white;font-size:20px;text-align:center'>");
pw.println("&copyThinking Machines");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- main ends here -->");
pw.println("</body>");
pw.println("</html>");
} catch(DAOException daoe)
{
System.out.println(daoe);	// remove after testing
System.out.println("Here");	// remove after testing
}catch(Exception exception)
{
System.out.println(exception);	// remove after testing
}
}
}