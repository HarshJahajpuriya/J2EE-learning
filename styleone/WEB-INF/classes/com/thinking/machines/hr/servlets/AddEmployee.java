package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.text.*;
import java.io.*;

public class AddEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
response.setContentType("text/html");
PrintWriter pw = response.getWriter();
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
boolean designationCodeExists, panNumberExists, aadharCardNumberExists;
// Perform checks for
DesignationDAO designationDAO = new DesignationDAO();
// 1. Designation should exist
designationCodeExists = designationDAO.designationCodeExists(designationCode);
EmployeeDAO employeeDAO = new EmployeeDAO();
// 2. PAN Number should not exist
panNumberExists = employeeDAO.panNumberExists(panNumber);
// 3. Aadhar Card Number should not exist
aadharCardNumberExists = employeeDAO.aadharCardNumberExists(aadharCardNumber);
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
pw.println("<h3>Add Employee (Module)</h3>");
pw.println("<br>");
pw.println("<form method='POST' action='/styleone/addEmployee' onsubmit='return validateForm(this)'>");
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
pw.println("<button type='submit'> Add </button>&nbsp;&nbsp;");
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

EmployeeDTO employee = new EmployeeDTO();
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian.equals("Y"));
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
employeeDAO.add(employee);
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
pw.println("<h3> Employee Added </h3>");
pw.println("Add more?");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");
pw.println("<form action='/styleone/getAddEmployeeForm'>");
pw.println("<button type='submit'> Yes </button>");
pw.println("</form>");
pw.println("</td>");
pw.println("<td>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'> No </button>");
pw.println("</form>");
pw.println("</tr>");
pw.println("</table>");
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
}