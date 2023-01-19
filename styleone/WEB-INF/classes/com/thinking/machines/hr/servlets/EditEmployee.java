package com.thinking.machines.hr.servlets;

import com.thinking.machines.hr.dl.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.math.*;
import java.text.*;
import java.io.*;

public class EditEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try 
{
response.setContentType("text/html");
PrintWriter pw = response.getWriter();

String employeeId = request.getParameter("employeeId");
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO;
employeeDTO = employeeDAO.getByEmployeeId(employeeId);

String name = employeeDTO.getName();
int designationCode = employeeDTO.getDesignationCode();
Date dateOfBirth = employeeDTO.getDateOfBirth();
String gender = employeeDTO.getGender();
Boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
String panNumber = employeeDTO.getPANNumber();
String aadharCardNumber = employeeDTO.getAadharCardNumber();

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
DesignationDAO designationDAO = new DesignationDAO();
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
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
if(isIndian)
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

}catch(Exception exception)
{
System.out.println("Here"+exception);	// remove after testing
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request, response);
}
}