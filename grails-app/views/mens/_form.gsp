<%@ page import="joller.Mens" %>



<div class="fieldcontain ${hasErrors(bean: mensInstance, field: 'naam', 'error')} required">
	<label for="naam">
		<g:message code="mens.naam.label" default="Naam" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="naam" from="${mensInstance.constraints.naam.inList}" required="" value="${mensInstance?.naam}" valueMessagePrefix="mens.naam"/>
</div>

<div class="fieldcontain ${hasErrors(bean: mensInstance, field: 'geslag', 'error')} ">
	<label for="geslag">
		<g:message code="mens.geslag.label" default="Geslag" />
		
	</label>
	<g:textField name="geslag" value="${mensInstance?.geslag}"/>
</div>

