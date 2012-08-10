
<%@ page import="joller.Mens" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mens.label', default: 'Mens')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-mens" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-mens" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list mens">
			
				<g:if test="${mensInstance?.naam}">
				<li class="fieldcontain">
					<span id="naam-label" class="property-label"><g:message code="mens.naam.label" default="Naam" /></span>
					
						<span class="property-value" aria-labelledby="naam-label"><g:fieldValue bean="${mensInstance}" field="naam"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${mensInstance?.geslag}">
				<li class="fieldcontain">
					<span id="geslag-label" class="property-label"><g:message code="mens.geslag.label" default="Geslag" /></span>
					
						<span class="property-value" aria-labelledby="geslag-label"><g:fieldValue bean="${mensInstance}" field="geslag"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${mensInstance?.id}" />
					<g:link class="edit" action="edit" id="${mensInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
