<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
        <link rel="stylesheet" href="../css/bootstrap.min.css">   		
        <script src="../js/bootstrap.min.js"></script>       
    </head>

    <body>
    	<div class="container">
    		<h2>  Test Modules</h2>

    		<!-- search bar -->
    		<form action="/testmate" method="get" id=id="seachEmployeeForm" role="form">
    			<input type="hidden" id="searchAction" name="searchAction" value="searchByName">
    			<div class="form-group col-xs-5">
                    <input type="text" name="testModuleName" id="testModuleName" class="form-control" required="true" placeholder="Type the name of the Test Module"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Search
                </button>
                <br></br>
                <br></br>
            </form>


            <!-- Search list -->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
            <form action="/testmate" method="post" id="employeeForm" role="form" >
                <input type="hidden" id="idTestModule" name="idTestModule">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty testModuleList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Last Job Name</td>
                                    <td>Last Job Timestamp</td>
                                    <td>Total Tests</td>
                                    <td>Tests Passed</td>
                                    <td>Tests Failed</td>
                                    <td>Run Tests</td>
                                </tr>
                            </thead>
                            <c:forEach var="testModule" items="${testModuleList}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${idTestModule == testModule.name}">                        	
                                    <c:set var="classSucess" value="info"/>
                                </c:if>
                                <tr class="${classSucess}">
                                    <!-- to implement editing test modules -->
                                    <!--<td>
                                        <a href="/employee?idTestModule=${employee.id}&searchAction=searchById">${employee.id}</a>
                                    </td> -->                                   
                                    <td>${testModule.name}</td>
                                    <td>${testModule.latestTestJobName}</td>
                                    <td>${testModule.latestTestJobTimestamp}</td>
                                    <td>${testModule.totalTests}</td>
                                    <td>${testModule.testsPassed}</td>
                                    <td>${testModule.testsFailed}</td>   
                                    <!-- implement running the testmodule here -->
                                    <td><a href="#" id="run" 
                                           onclick="document.getElementById('action').value = 'run';document.getElementById('idTestModule').value = '${testModule.name}';

                                               document.getElementById('employeeForm').submit();"> 
                                            
                                            <span class="glyphicon glyphicon-play"/>
                                        </a>
                                                   
                                    </td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No Test Modules found matching your search criteria
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
            <form action ="jsp/new-testmodule.jsp">            
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">New test module</button> 
            </form>

       </div>
    </body>
</html>


