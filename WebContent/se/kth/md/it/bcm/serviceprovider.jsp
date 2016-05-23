<!DOCTYPE html>
<%--
 Copyright (c) 2011, 2012 IBM Corporation and others.

 All rights reserved. This program and the accompanying materials
 are made available under the terms of the Eclipse Public License v1.0
 and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 
 The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 and the Eclipse Distribution License is available at
 http://www.eclipse.org/org/documents/edl-v10.php.
 
 Contributors:
 
    Sam Padgett		 - initial API and implementation
    Michael Fiedler	 - adapted for OSLC4J
    Jad El-khoury    - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)

 This file is generated by org.eclipse.lyo.oslc4j.codegenerator
--%>

<%@ page import="java.net.URI" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.Service" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.ServiceProvider" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.Dialog" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.CreationFactory" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.ResourceShape" %>
<%@ page import="org.eclipse.lyo.oslc4j.core.model.QueryCapability" %>
<%--
Start of user code imports
--%>
<%-- 
End of user code 
--%>

<%@ page contentType="text/html" language="java" pageEncoding="UTF-8" %>

<%
ServiceProvider serviceProvider = (ServiceProvider)request.getAttribute("serviceProvider");
Service[] services = (Service[])request.getAttribute("services");
%>
<%--
Start of user code getRequestAttributes
--%>
<%
String bugzillaUri = (String) request.getAttribute("bugzillaUri");
%>
<%--
End of user code
--%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title><%= serviceProvider.getTitle() %></title>
		<%-- 
Start of user code (RECOMMENDED) headStuff 
		--%>
		<link href="<%= bugzillaUri %>/skins/standard/global.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/index.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/global.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%= bugzillaUri %>/skins/standard/index.css" rel="alternate stylesheet" title="Classic" type="text/css">
		<link href="<%= bugzillaUri %>/skins/contrib/Dusk/global.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%= bugzillaUri %>/skins/contrib/Dusk/index.css" rel="stylesheet" title="Dusk" type="text/css">
		<link href="<%= bugzillaUri %>/skins/custom/global.css" rel="stylesheet" type="text/css">
		<link href="<%= bugzillaUri %>/skins/custom/index.css" rel="stylesheet" type="text/css">
		<link rel="shortcut icon" href="<%= bugzillaUri %>/images/favicon.ico">
		<%-- 
End of user code 
		--%>
	</head>
	<body onload="">
		<div id="header">
			<div id="banner"></div>
			<table border="0" cellspacing="0" cellpadding="0" id="titles">
				<tr>
					<td id="title">
						<p>
							<%= serviceProvider.getTitle() %></title>
						</p>
					</td>
					<td id="information">
						<p class="header_addl_info">
							version 0.1
						</p>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="bugzilla-body">
			<div id="page-index">
				<%-- 
Start of user code (RECOMMENDED) bodyStuff1 
				--%>
				<img src="../../images/resources/bugzilla.gif" alt="icon" width="80" height="80" />
				<%-- 
End of user code 
				--%>
	
				<h1><%= serviceProvider.getTitle() %></title></h1>
				
				<p><%= serviceProvider.getDescription() %></p>

	            <table>
		            <tr>
			            <td><b>This document</b>:</td>
			            <td><a href="<%= serviceProvider.getAbout() %>">
			            <%= serviceProvider.getAbout() %></a></td>
		            </tr>
		            <tr>
						<%-- 
Start of user code (RECOMMENDED) bodyStuff2 
						--%>
			            <td><b>Bugzilla</b>:</td>
			            <td><a href="<%= bugzillaUri %>"><%= bugzillaUri %></a></td>
						<%-- 
End of user code 
						--%>
		            </tr>
					<tr>
						<td><b>Adapter Publisher</b>:</td>
						<td>Eclipse Lyo</td>
					</tr>
					<tr>
						<td><b>Adapter Identity</b>:</td>
						<td>urn:oslc:ServiceProvider</td>
					</tr>
	            </table>

				<h2>Services</h2>
				<%for (int serviceIndex = 0; serviceIndex < services.length; serviceIndex++) {%>
				
					<h3>Service <%= serviceIndex %> </h3>	                 
					<h4>Resource Selector Dialog(s)</h4>
					<%
					Dialog[] selectionDialogs = services[serviceIndex].getSelectionDialogs();
					for (int selectionDialogIndex = 0; selectionDialogIndex < selectionDialogs.length; selectionDialogIndex++) {
						String selectionDialog = selectionDialogs[selectionDialogIndex].getDialog().toString();
					%>
						<p><a href="<%= selectionDialog %>"><%= selectionDialog %></a></p>
					<%}%>
	
					<h4>Resource Creator Dialog(s)</h4>
					<%
					Dialog[] creationDialogs = services[serviceIndex].getCreationDialogs();
					for (int creationDialogIndex = 0; creationDialogIndex < creationDialogs.length; creationDialogIndex++) {
						String creationDialog = creationDialogs[creationDialogIndex].getDialog().toString();
					%>
						<p><a href="<%= creationDialog %>"><%= creationDialog %></a></p>
					<%}%>

					<h4>Resource Creation Factory(s) and Resource Shape(s)</h4>
					<%
					CreationFactory[] creationFactories = services[serviceIndex].getCreationFactories();
					for (int creationFactoryIndex = 0; creationFactoryIndex < creationFactories.length; creationFactoryIndex++) {
						String creationFactory = creationFactories[creationFactoryIndex].getCreation().toString();
						URI[] creationShapes = creationFactories[creationFactoryIndex].getResourceShapes();
						String creationShape = creationShapes[0].toString();
					%>
						<p><a href="<%= creationFactory %>"><%= creationFactory %></a></p>
						<p><a href="<%= creationShape %>"><%= creationShape %></a></p>
					<%}%>

					<h4>Resource Query Capability(s) and Resource Shape(s)</h4>
					<%
					QueryCapability[] queryCapabilities= services[serviceIndex].getQueryCapabilities();
					for (int queryCapabilityIndex = 0; queryCapabilityIndex < queryCapabilities.length; queryCapabilityIndex++) {
						String queryCapability = queryCapabilities[queryCapabilityIndex].getQueryBase().toString();
						String queryShape = queryCapabilities[queryCapabilityIndex].getResourceShape().toString();
					%>

						<p><a href="<%= queryCapability %>"><%= queryCapability %></a></p>
						<p><a href="<%= queryShape %>"><%= queryShape %></a></p>
					<%}%>
				<%}%>
				            
			</div>
		</div>
		
		<div id="footer">
			<div class="intro"></div>
			<div class="outro">
				<div style="margin: 0 1em 1em 1em; line-height: 1.6em; text-align: left">
					<b>OSLC Tools Adapter Server 0.1</b> brought to you by <a href="http://eclipse.org/lyo">Eclipse Lyo</a><br>
				</div>
			</div>
		</div>
	</body>
</html>
