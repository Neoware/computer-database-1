<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jslt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="title.editComputer" var="i18nEditTitle" />
<spring:message code="computer.name" var="i18nComputerName" />
<spring:message code="computer.introduced" var="i18nComputerIntroduced" />
<spring:message code="computer.discontinued" var="i18nComputerDiscontinued" />
<spring:message code="computer.company" var="i18nComputerCompany" />
<spring:message code="button.edit" var="i18nButtonEdit" />
<spring:message code="button.cancel" var="i18nButtonCancel" />
<spring:message code="computer.placehorlder.name" var="i18nPlaceholderName" />
<spring:message code="computer.placehorlder.introduced" var="i18nPlaceholderIntroduced" />
<spring:message code="computer.placehorlder.discontinued" var="i18nPlaceholderDiscontinued" />
                 	
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/jquery-ui.min.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    
    <div id="errorMsg" class="alert alert-warning text-center" style="${not empty updateStatus ? 'display: block;' : 'display: none;'}">
		<p>
			${updateStatus}
		</p>
	</div>
    
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computerDTO.id}
                    </div>
                    <h1>
                    	${i18nEditTitle}
                    </h1>

                    <form:form commandName="computerDTO" action="edit_computer" onsubmit="return validateForm();" method="POST">
                        <form:errors path="*" cssClass="errorBlock" element="div" />
                        <input name="id" type="hidden" id="id" value="${computerDTO.id}" />
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">
                                	${i18nComputerName}
                                </label>
                                <form:input path="name"  name="computerName" type="text" class="form-control" id="computerName" placeholder="${i18nPlaceholderName}" />
                            	<form:errors path="name" cssClass="error" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">
                                	${i18nComputerDiscontinued}
                                </label>
                                <form:input path="introduced" name="introduced" type="text" class="form-control" id="introduced" placeholder="${i18nPlaceholderIntroduced}" />
                                <form:errors path="introduced" cssClass="error" />
                            </div>
                            <div class="form-group">
                                <label for="discontinued">
                                	${i18nComputerDiscontinued}
                                </label>
                                <form:input path="discontinued" name="discontinued" type="text" class="form-control" id="discontinued" placeholder="${i18nPlaceholderDiscontinued}" />
                                <form:errors path="discontinued" cssClass="error" />
                            </div>
                            <div class="form-group">
                                <label for="companyId">
									${i18nComputerCompany}
								</label>
                                <form:select path="companyId" name="companyId" class="form-control" id="companyId" >
                                    <option value="0">--</option>
                                    <jslt:forEach var="company" items="${companies}">
                                		<option value="${company.id}" ${(computerDTO.companyId eq company.id) ? ' selected' : ''}>${company.name}</option>
                                	</jslt:forEach>
                                </form:select>
                                <form:errors path="companyId" cssClass="error" />
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="${i18nButtonEdit}" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">${i18nButtonCancel}</a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/validateComputerForm.js"></script>
	<script>
		$(function() {
		  $( "#introduced" ).datepicker({ dateFormat: 'dd/mm/yy' }).val();
		  $( "#discontinued" ).datepicker({ dateFormat: 'dd/mm/yy' }).val();
		});
	</script>
</body>
</html>