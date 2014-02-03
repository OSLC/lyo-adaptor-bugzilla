/*******************************************************************************
 * Copyright (c) 2011, 2012 IBM Corporation.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *     Sam Padgett          - initial API and implementation
 *     Michael Fiedler      - adapted for OSLC4J
 *     Russell Boykin       - initial API and implementation
 *     Alberto Giammaria    - initial API and implementation
 *     Chris Peters         - initial API and implementation
 *     Gianluca Bernardini  - initial API and implementation
 *     Michael Fiedler      - Bugzilla adpater implementations
 *******************************************************************************/

package se.kth.md.it.bcm.services;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.eclipse.lyo.oslc4j.core.annotation.OslcCreationFactory;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDialog;
import org.eclipse.lyo.oslc4j.core.annotation.OslcDialogs;
import org.eclipse.lyo.oslc4j.core.annotation.OslcQueryCapability;
import org.eclipse.lyo.oslc4j.core.annotation.OslcService;
import org.eclipse.lyo.oslc4j.core.model.Compact;
import org.eclipse.lyo.oslc4j.core.model.OslcConstants;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.Preview;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;
import org.eclipse.lyo.oslc4j.core.model.Link;

import se.kth.md.it.bcm.BugzillaAdaptorManager;
import se.kth.md.it.bcm.BugzillaAdaptorConstants;
import se.kth.md.it.bcm.servlet.ServiceProviderCatalogSingleton;
import se.kth.md.it.bcm.resources.BugzillaChangeRequest;

import se.kth.md.it.bcm.resources.Type;	
import se.kth.md.it.bcm.resources.Person;	
import se.kth.md.it.bcm.resources.Person;	


// Start of user code imports
// End of user code


@OslcService(BugzillaAdaptorConstants.CHANGE_MANAGEMENT_DOMAIN)
@Path("{serviceProviderId}/bugzillaChangeRequests")
public class BugzillaChangeRequestService
	
{

	@Context private HttpServletRequest httpServletRequest;
	@Context private HttpServletResponse httpServletResponse;
	@Context private UriInfo uriInfo;
	
    public BugzillaChangeRequestService()
    {
        super();
    }

    /**
     * RDF/XML, XML and JSON representation of a change request collection
     * 
     * TODO:  add query support
     * 
     * @param productId
     * @param where
     * @param pageString
     * @return
     * @throws IOException
     * @throws ServletException
     */
    
  @OslcDialogs(
    {
        @OslcDialog
        (
             title = "Change Request Selection Dialog",
             label = "Change Request Selection Dialog",
             uri = "/{serviceProviderId}/bugzillaChangeRequests/selector",
             hintWidth = "525px",
             hintHeight = "325px",
             resourceTypes = {BugzillaAdaptorConstants.TYPE_BUGZILLACHANGEREQUEST},
             usages = {OslcConstants.OSLC_USAGE_DEFAULT}
        )
    })   

	    @OslcQueryCapability
	    (
	        title = "Change Request Query Capability",
	        label = "Change Request Catalog Query",
	        resourceShape = OslcConstants.PATH_RESOURCE_SHAPES + "/" + BugzillaAdaptorConstants.PATH_BUGZILLACHANGEREQUEST,
	        resourceTypes = {BugzillaAdaptorConstants.TYPE_BUGZILLACHANGEREQUEST},
	        usages = {OslcConstants.OSLC_USAGE_DEFAULT}
	    ) 
      
    @GET
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
    public BugzillaChangeRequest [] getBugzillaChangeRequests(@PathParam("serviceProviderId")   final String serviceProviderId,
    		                                 		 @QueryParam("oslc.where") final String where,
    		                                 		 @QueryParam("page")       final String pageString) throws IOException, ServletException 
    {
    	int page=0;  
    	int limit=999;
        
		// Start of user code (MUST_FILL_IN) getResourceCollection_init
		// End of user code

        final List<BugzillaChangeRequest> resources = BugzillaAdaptorManager.getBugzillaChangeRequests(httpServletRequest, serviceProviderId, page, limit);      
        return resources.toArray(new BugzillaChangeRequest [resources.size()]);
        

    }
    
    /**
     * HTML representation of change request collection
     * 
     * Forwards to changerequest_collection_html.jsp to build the html page
     * 
     * @param productId
     * @param changeRequestId
     * @param pageString
     * @return
     * @throws ServletException
     * @throws IOException
     */
    


	@GET
	@Produces({ MediaType.TEXT_HTML })
	public Response getBugzillaChangeRequestsAsHtml(@PathParam("serviceProviderId")       final String serviceProviderId,
			                          @PathParam("bugzillaChangeRequestId") final String bugzillaChangeRequestId,
			                          @QueryParam("page")           final String pageString) throws ServletException, IOException
	{
		int page=0;
		int limit=20;
		
		if (null != pageString) {
			page = Integer.parseInt(pageString);
		}

		// Start of user code (MUST_FILL_IN) getResourceCollectionAsHTML_init
		// End of user code

        final List<BugzillaChangeRequest> resources = BugzillaAdaptorManager.getBugzillaChangeRequests(httpServletRequest, serviceProviderId, page, limit);      
		
        if (resources!= null) {
        	httpServletRequest.setAttribute("resources", resources);
			// Start of user code (RECOMMENDED) getResourceCollectionAsHTML_setAttributes
			// End of user code

        	httpServletRequest.setAttribute("queryUri", 
                    uriInfo.getAbsolutePath().toString() + "?oslc.paging=true");
        	if (resources.size() > limit) {
        		resources.remove(resources.size() - 1);
        		httpServletRequest.setAttribute("nextPageUri", 
        				uriInfo.getAbsolutePath().toString() + "?oslc.paging=true&amp;page=" + (page + 1));
        	}
        	
        	ServiceProvider serviceProvider = ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, serviceProviderId);
        	httpServletRequest.setAttribute("serviceProvider", serviceProvider);

        	RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/se/kth/md/it/bcm/bugzillachangerequestcollection_html.jsp");
			
        	rd.forward(httpServletRequest,httpServletResponse);
        }
		
		throw new WebApplicationException(Status.NOT_FOUND);	
	}


    
	/**
	 * RDF/XML, XML and JSON representation of a single change request
	 * 
	 * @param productId
	 * @param changeRequestId
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 * @throws URISyntaxException
	 */
    


    @GET
    @Path("{bugzillaChangeRequestId}")
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
    public BugzillaChangeRequest getBugzillaChangeRequest(@PathParam("serviceProviderId")       final String serviceProviderId,
                                                  @PathParam("bugzillaChangeRequestId") final String bugzillaChangeRequestId) throws IOException, ServletException, URISyntaxException
    {
	
		// Start of user code (MUST_FILL_IN) getResource_init
		// End of user code

        final BugzillaChangeRequest aBugzillaChangeRequest = BugzillaAdaptorManager.getBugzillaChangeRequest(httpServletRequest, bugzillaChangeRequestId, serviceProviderId);

        if (aBugzillaChangeRequest != null) {
			// Start of user code (RECOMMENDED) getResource_body
			// End of user code

            return aBugzillaChangeRequest;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }



    
    /**
     * 
     * HTML representation for a single change request  - redirect the request directly to Bugzilla
     * 
     * @param productId
     * @param changeRequestId
     * @throws ServletException
     * @throws IOException
     * @throws URISyntaxException
     */
    


	@GET
	@Path("{bugzillaChangeRequestId}")
	@Produces({ MediaType.TEXT_HTML })
	public Response getBugzillaChangeRequestAsHtml(@PathParam("serviceProviderId")       final String serviceProviderId,
			                         @PathParam("bugzillaChangeRequestId") final String bugzillaChangeRequestId) throws ServletException, IOException, URISyntaxException
	{	
		// Start of user code (MUST_FILL_IN) getResourceAsHTML_init
		// End of user code

        final BugzillaChangeRequest aBugzillaChangeRequest = BugzillaAdaptorManager.getBugzillaChangeRequest(httpServletRequest, bugzillaChangeRequestId, serviceProviderId);

        if (aBugzillaChangeRequest != null) {
        	httpServletRequest.setAttribute("aBugzillaChangeRequest", aBugzillaChangeRequest);
			// Start of user code getResourceAsHTML_setAttributes
			// End of user code

         	ServiceProvider serviceProvider = ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, serviceProviderId);
        	httpServletRequest.setAttribute("serviceProvider", serviceProvider);

        	RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/se/kth/md/it/bcm/bugzillachangerequest_html.jsp");
			
        	rd.forward(httpServletRequest,httpServletResponse);


		}

        throw new WebApplicationException(Status.NOT_FOUND);
	}

	
	/**
	 * OSLC delegated selection dialog for change requests
	 * 
	 * If called without a "terms" parameter, forwards to changerequest_selector.jsp to 
	 * build the html for the IFrame
	 * 
	 * If called with a "terms" parameter, sends a Bug search to Bugzilla and then 
	 * forwards to changerequest_filtered_json.jsp to build a JSON response
	 * 
	 * 
	 * @param terms
	 * @param productId
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@GET
	@Path("selector")
	@Consumes({ MediaType.TEXT_HTML, MediaType.WILDCARD })
	public void bugzillaChangeRequestSelector(@QueryParam("terms")     final String terms,
						              @PathParam("serviceProviderId")  final String serviceProviderId) throws ServletException, IOException
	{
		try {
			// Start of user code (MUST_FILL_IN) resourceSelector_init
			// End of user code

			ServiceProvider serviceProvider = ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, serviceProviderId);
			httpServletRequest.setAttribute("serviceProvider", serviceProvider);
			httpServletRequest.setAttribute("selectionUri",uriInfo.getAbsolutePath().toString());
			// Start of user code (RECOMMENDED) resourceSelector_setAttributes
			// End of user code

			if (terms != null ) {
				httpServletRequest.setAttribute("terms", terms);
				final List<BugzillaChangeRequest> resources = BugzillaAdaptorManager.searchBugzillaChangeRequests(httpServletRequest, serviceProviderId, terms);      
				if (resources!= null) {
							httpServletRequest.setAttribute("resources", resources);
							RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/se/kth/md/it/bcm/bugzillachangerequestfilteredcollection_json.jsp"); 
							
							rd.forward(httpServletRequest, httpServletResponse);
				}
				//a empty search should return an empty list and not NULL!
				throw new WebApplicationException(Status.NOT_FOUND);	
			
			} else {
				try {	
					RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/se/kth/md/it/bcm/bugzillachangerequestselector_html.jsp"); 
					
					rd.forward(httpServletRequest, httpServletResponse);
					
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}

	}
	
    
    /**
     * OSLC delegated creation dialog for a single change request
     * 
     * Forwards to changerequest_creator.jsp to build the html form
     * 
     * @param productId
     * @throws IOException
     * @throws ServletException
     */
	
    @GET
    @Path("creator") 
    @Consumes({MediaType.WILDCARD})
    public void bugzillaChangeRequestCreatorAsHtml(@PathParam("serviceProviderId") final String serviceProviderId) throws IOException, ServletException
    {
		// Start of user code (MUST_FILL_IN) resourceCreatorAsHTML_init
		// End of user code

		ServiceProvider serviceProvider = ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, serviceProviderId);
		httpServletRequest.setAttribute("serviceProvider", serviceProvider);

		RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/se/kth/md/it/bcm/bugzillachangerequestcreator_html.jsp");
		
		rd.forward(httpServletRequest, httpServletResponse);
    }
	

    /**
     * Backend creator for the OSLC delegated creation dialog. 
     * 
     * Accepts the input in FormParams and returns a small JSON response
     * 
     * @param productId
     * @param component
     * @param version
     * @param summary
     * @param op_sys
     * @param platform
     * @param description
     */
    @POST
    @Path("creator") 
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED})
    public void createBugzillaChangeRequest(@PathParam("serviceProviderId")   final String serviceProviderId)
    {
    	try {
    		BugzillaChangeRequest aBugzillaChangeRequest = new BugzillaChangeRequest();

    		String[] paramValues;

				paramValues = httpServletRequest.getParameterValues("affectedByDefect");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addAffectedByDefect(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("created");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setCreated(new SimpleDateFormat().parse(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("version");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setVersion(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("tracksRequirement");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addTracksRequirement(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("tracksChangeSet");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addTracksChangeSet(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("relatedTestPlan");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addRelatedTestPlan(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("platform");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setPlatform(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("inprogress");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setInprogress(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("title");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setTitle(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("closeDate");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setCloseDate(new SimpleDateFormat().parse(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("testedByTestCase");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addTestedByTestCase(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("serviceProvider");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setServiceProvider(new URI(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("affectsRequirement");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addAffectsRequirement(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("priority");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setPriority(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("type");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addType(new Type(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("relatedTestExecutionRecord");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addRelatedTestExecutionRecord(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("approved");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setApproved(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("affectsTestResult");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addAffectsTestResult(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("affectsPlanItem");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addAffectsPlanItem(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("relatedChangeRequest");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addRelatedChangeRequest(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("reviewed");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setReviewed(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("component");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setComponent(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("contributor");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addContributor(new Person(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("type");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addType(new URI(paramValues[i]));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("creator");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addCreator(new Person(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("instanceShape");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setInstanceShape(new Link(new URI(paramValues[0])));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("closed");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setClosed(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("product");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setProduct(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("identifier");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setIdentifier(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("fixed");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setFixed(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("modified");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setModified(new SimpleDateFormat().parse(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("blocksTestExecutionRecord");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addBlocksTestExecutionRecord(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("shortTitle");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setShortTitle(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("description");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setDescription(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("subject");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addSubject(paramValues[i]);
						}
				}			
				paramValues = httpServletRequest.getParameterValues("discussedBy");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setDiscussedBy(new Link(new URI(paramValues[0])));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("relatedTestCase");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addRelatedTestCase(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("operatingSystem");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setOperatingSystem(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("relatedTestScript");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addRelatedTestScript(new Link(new URI(paramValues[i])));
						}
				}			
				paramValues = httpServletRequest.getParameterValues("verified");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setVerified(new Boolean(paramValues[0]));
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("status");
				if (paramValues != null) {
						if (paramValues.length == 1) {
							if (paramValues[0].length() != 0)
								aBugzillaChangeRequest.setStatus(paramValues[0]);
							// else, there is an empty value for that parameter, and hence ignore since the parameter is not actually set.
						} 
					
				}			
				paramValues = httpServletRequest.getParameterValues("implementsRequirement");
				if (paramValues != null) {
			    		for(int i=0; i<paramValues.length; i++) {
							aBugzillaChangeRequest.addImplementsRequirement(new Link(new URI(paramValues[i])));
						}
				}			

      
    		final BugzillaChangeRequest newBugzillaChangeRequest = BugzillaAdaptorManager.createBugzillaChangeRequest(httpServletRequest, aBugzillaChangeRequest, serviceProviderId);
   		
    		httpServletRequest.setAttribute("newResource", newBugzillaChangeRequest);
    		httpServletRequest.setAttribute("newResourceUri", newBugzillaChangeRequest.getAbout().toString());

    		// Send back to the form a small JSON response
    		httpServletResponse.setContentType("application/json");
    		httpServletResponse.setStatus(Status.CREATED.getStatusCode());
    		httpServletResponse.addHeader("Location", newBugzillaChangeRequest.getAbout().toString());
    		PrintWriter out = httpServletResponse.getWriter();
    		out.print("{" + "\"resource\" : \"" + newBugzillaChangeRequest.getAbout().toString() + "\"}");
    		out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new WebApplicationException(e);
    	}

    }

	
    /**
     * OSLC Compact representation of a single change request
     * 
     * Contains a reference to the smallPreview method in this class for the preview document
     * 
     * @param productId
     * @param changeRequestId
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ServletException
     */
    
    /* LAB 4 - Uncomment this method which provides an OSLC compact XML representation of a BugzillaChangeRequest

    @GET
    @Path("{changeRequestId}")
    @Produces({OslcMediaType.APPLICATION_X_OSLC_COMPACT_XML})
    public Compact getCompact(@PathParam("productId") final String productId,
                              @PathParam("changeRequestId") final String changeRequestId)
           throws URISyntaxException, IOException, ServletException
    {
    	final Bug bug = BugzillaManager.getBugById(httpServletRequest, changeRequestId);
        

        if (bug != null) {      	
            final Compact compact = new Compact();
            
        	BugzillaChangeRequest changeRequest = null;

       		changeRequest = BugzillaChangeRequest.fromBug(bug);
 	
            compact.setAbout(getAboutURI(productId + "/changeRequests/" + changeRequest.getIdentifier()));
            compact.setTitle(changeRequest.getTitle());
            
            String iconUri = BugzillaManager.getBugzillaUri() + "/images/favicon.ico";
            compact.setIcon(new URI(iconUri));
 
            //Create and set attributes for OSLC preview resource
            final Preview smallPreview = new Preview();
            smallPreview.setHintHeight("11em");
            smallPreview.setHintWidth("45em");
            smallPreview.setDocument(new URI(compact.getAbout().toString() + "/smallPreview"));
            compact.setSmallPreview(smallPreview);

            return compact;
        }

        throw new WebApplicationException(Status.NOT_FOUND);
    }
    
    */
	
	/**
	 * OSLC small preview for a single change request
	 * 
	 * Forwards to changerequest_preview_small.jsp to build the html
	 * 
	 * @param productId
	 * @param changeRequestId
	 * @throws ServletException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	
	/* LAB 4 - Uncomment this method which provides an OSLC small preview of a BugzillaChangeRequest

	@GET
	@Path("{changeRequestId}/smallPreview")
	@Produces({ MediaType.TEXT_HTML })
	public void smallPreview(@PathParam("productId")       final String productId,
			                 @PathParam("changeRequestId") final String changeRequestId) throws ServletException, IOException, URISyntaxException
	{
		
        final Bug bug = BugzillaManager.getBugById(httpServletRequest, changeRequestId);
        
        if (bug != null) {
        	
        	BugzillaChangeRequest changeRequest = BugzillaChangeRequest.fromBug(bug);
        	changeRequest.setServiceProvider(ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, productId).getAbout());
        	changeRequest.setAbout(getAboutURI(productId + "/changeRequests/" + changeRequest.getIdentifier()));
        	
        	final String bugzillaUri = BugzillaManager.getBugzillaUri().toString();
        	httpServletRequest.setAttribute("changeRequest", changeRequest);
        	httpServletRequest.setAttribute("bugzillaUri", bugzillaUri);
        	
        	RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/cm/changerequest_preview_small.jsp");
            rd.forward(httpServletRequest,httpServletResponse);
        	return;
        }
		
        throw new WebApplicationException(Status.NOT_FOUND);
			
	}
    */
  

	/**
	 * OSLC large preview for a single change request
	 * 
	 * Forwards to changerequest_preview_large.jsp to build the html
	 * 
	 * @param productId
	 * @param changeRequestId
	 * @throws ServletException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
  
	/* LAB 4 - Uncomment this method which provides an OSLC large preview of a BugzillaChangeRequest

	@GET
	@Path("{changeRequestId}/largePreview")
	@Produces({ MediaType.TEXT_HTML })
	public void getLargePreview(@PathParam("productId")       final String productId,
			                         @PathParam("changeRequestId") final String changeRequestId) throws ServletException, IOException, URISyntaxException
	{	
		final Bug bug = BugzillaManager.getBugById(httpServletRequest, changeRequestId);

      if (bug != null) {
      	BugzillaChangeRequest changeRequest = null;

      	changeRequest = BugzillaChangeRequest.fromBug(bug);           
      	changeRequest.setServiceProvider(ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, productId).getAbout());
      	changeRequest.setAbout(getAboutURI(productId + "/changeRequests/" + changeRequest.getIdentifier()));

      	final String bugzillaUri = BugzillaManager.getBugzillaUri().toString();
      	
      	httpServletRequest.setAttribute("changeRequest", changeRequest);
      	httpServletRequest.setAttribute("bugzillaUri", bugzillaUri);
      	httpServletRequest.setAttribute("bugUri", bugzillaUri + "/show_bug.cgi?id=" + Integer.toString(bug.getID()));
      	
      	RequestDispatcher rd = httpServletRequest.getRequestDispatcher("/cm/changerequest_preview_large.jsp");
      	rd.forward(httpServletRequest,httpServletResponse);
      }
		
		throw new WebApplicationException(Status.NOT_FOUND);
		
	}
	
	*/
	
	/**
	 * Create a single BugzillaChangeRequest via RDF/XML, XML or JSON POST
	 * @param productId
	 * @param changeRequest
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */

		 @OslcDialog
		(
             title = "Change Request Creation Dialog",
             label = "Change Request Creation Dialog",
             uri = "/{serviceProviderId}/bugzillaChangeRequests/creator",
             hintWidth = "600px",
             hintHeight = "375px",
             resourceTypes = {BugzillaAdaptorConstants.TYPE_BUGZILLACHANGEREQUEST},
             usages = {OslcConstants.OSLC_USAGE_DEFAULT}
		)

		@OslcCreationFactory
		(
			 title = "Change Request Creation Factory",
			 label = "Change Request Creation",
			 resourceShapes = {OslcConstants.PATH_RESOURCE_SHAPES + "/" + BugzillaAdaptorConstants.PATH_BUGZILLACHANGEREQUEST},
			 resourceTypes = {BugzillaAdaptorConstants.TYPE_BUGZILLACHANGEREQUEST},
			 usages = {OslcConstants.OSLC_USAGE_DEFAULT}
		)

    @POST
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
    @Produces({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
    public Response addChangeRequest(@PathParam("productId") final String productId,
                                                             final BugzillaChangeRequest aBugzillaChangeRequest) throws IOException, ServletException

    {
    	
    	/* LAB 5 - Uncomment this code which creates a BugzillaChangeRequest from a POSTed RDF/XML, XML or JSON representation

    	//Create a new Bug from the incoming change request, retrieve the bug and then convert to a BugzillaChangeRequest
        final String newBugId = BugzillaManager.createBug(httpServletRequest, changeRequest, productId);     
        final Bug newBug = BugzillaManager.getBugById(httpServletRequest, newBugId);
        
        BugzillaChangeRequest newChangeRequest;
        
		try {
			newChangeRequest = BugzillaChangeRequest.fromBug(newBug);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
        URI about = getAboutURI(productId + "/changeRequests/" + newChangeRequest.getIdentifier());
        newChangeRequest.setServiceProvider(ServiceProviderCatalogSingleton.getServiceProvider(httpServletRequest, productId).getAbout());
    	newChangeRequest.setAbout(about);
        setETagHeader(getETagFromChangeRequest(newChangeRequest), httpServletResponse);

        return Response.created(about).entity(changeRequest).build();
        
        */
    	return null; //LAB 5 - remove this line
    }

    /**
     * Updates a single change request via RDF/XML, XML or JSON PUT
     * 
     * Currently, update only supports adding OSLC CM link attributes to a
     * Bug in the Bug comments
     * 
     * @param eTagHeader
     * @param changeRequestId
     * @param changeRequest
     * @return
     * @throws IOException
     * @throws ServletException
     */
    
    /* LAB 5 - Uncomment this method which allows the update of a BugzillaChangeRequest with links
     
    @PUT
    @Consumes({OslcMediaType.APPLICATION_RDF_XML, OslcMediaType.APPLICATION_XML, OslcMediaType.APPLICATION_JSON})
    @Path("{changeRequestId}")
    public Response updateChangeRequest(@HeaderParam("If-Match")      final String              eTagHeader,
                                        @PathParam("changeRequestId") final String              changeRequestId,
    		                                                          final BugzillaChangeRequest       changeRequest) throws IOException, ServletException
    {
    	
    	//Only adding links is supported right now
    	
        final Bug originalBug = BugzillaManager.getBugById(httpServletRequest, changeRequestId);
        
        if (originalBug != null)  {
        	try {
        		final BugzillaChangeRequest originalChangeRequest = BugzillaChangeRequest.fromBug(originalBug);
        		final String originalETag = getETagFromChangeRequest(originalChangeRequest);
        		
                if ((eTagHeader == null) || (originalETag.equals(eTagHeader))) {
                	
                	BugzillaManager.updateBug(httpServletRequest,changeRequest);
        	        final Bug updatedBug = BugzillaManager.getBugById(httpServletRequest, changeRequestId);
        	        final BugzillaChangeRequest updatedChangeRequest = BugzillaChangeRequest.fromBug(updatedBug);
        	        
        	        setETagHeader(getETagFromChangeRequest(updatedChangeRequest),httpServletResponse);
                } else {
                	throw new WebApplicationException(Status.PRECONDITION_FAILED);
                }
        		
        	} catch (Exception e) {
        		throw new WebApplicationException(e);
        	}
        } else {
        	throw new WebApplicationException(Status.NOT_FOUND);
        }

        return Response.ok().build();
    }
    */

	// Start of user code (RECOMMENDED) functions
	// End of user code

}


