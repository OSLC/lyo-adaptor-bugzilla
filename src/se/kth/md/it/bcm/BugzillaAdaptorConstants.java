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

package se.kth.md.it.bcm;

import org.eclipse.lyo.oslc4j.core.model.OslcConstants;

// Start of user code imports
// End of user code

public interface BugzillaAdaptorConstants
{
	// Start of user code user constants
	// End of user code

		public static String OSLC_CORE_DOMAIN = "http://open-services.net/ns/core#";
public static String OSLC_CORE_NAMSPACE = "http://open-services.net/ns/core#";
public static String OSLC_CORE_NAMSPACE_PREFIX = "oslc";

		public static String QUALITY_MANAGEMENT_DOMAIN = "http://open-services.net/ns/qm#";
public static String QUALITY_MANAGEMENT_NAMSPACE = "http://open-services.net/ns/qm#";
public static String QUALITY_MANAGEMENT_NAMSPACE_PREFIX = "oslc_qm";

		public static String CHANGE_MANAGEMENT_DOMAIN = "http://open-services.net/ns/cm#";
public static String CHANGE_MANAGEMENT_NAMSPACE = "http://open-services.net/ns/cm#";
public static String CHANGE_MANAGEMENT_NAMSPACE_PREFIX = "oslc_cm";

		public static String FOAF_DOMAIN = "http://xmlns.com/foaf/0.1#";
public static String FOAF_NAMSPACE = "http://xmlns.com/foaf/0.1#";
public static String FOAF_NAMSPACE_PREFIX = "foaf";

		public static String DUBLIN_CORE_DOMAIN = "http://purl.org/dc/terms#";
public static String DUBLIN_CORE_NAMSPACE = "http://purl.org/dc/terms#";
public static String DUBLIN_CORE_NAMSPACE_PREFIX = "dcterms";

		public static String RDF_DOMAIN = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
public static String RDF_NAMSPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
public static String RDF_NAMSPACE_PREFIX = "rdf";

		public static String RDFS_DOMAIN = "http://www.w3.org/2000/01/rdf-schema#";
public static String RDFS_NAMSPACE = "http://www.w3.org/2000/01/rdf-schema#";
public static String RDFS_NAMSPACE_PREFIX = "rdfs";

		public static String BUGZILLA_DOMAIN = "http://www.bugzilla.org/rdf#";
public static String BUGZILLA_NAMSPACE = "http://www.bugzilla.org/rdf#";
public static String BUGZILLA_NAMSPACE_PREFIX = "bugz";


	    public static String TYPE = "type";
public static String PATH_TYPE = "type";
public static String TYPE_TYPE = BUGZILLA_NAMSPACE + "Type";
	    public static String PERSON = "person";
public static String PATH_PERSON = "person";
public static String TYPE_PERSON = FOAF_NAMSPACE + "Person";
	    public static String BUGZILLACHANGEREQUEST = "bugzillaChangeRequest";
public static String PATH_BUGZILLACHANGEREQUEST = "bugzillaChangeRequest";
public static String TYPE_BUGZILLACHANGEREQUEST = BUGZILLA_NAMSPACE + "BugzillaChangeRequest";
	    public static String CHANGEREQUEST = "changeRequest";
public static String PATH_CHANGEREQUEST = "changeRequest";
public static String TYPE_CHANGEREQUEST = CHANGE_MANAGEMENT_NAMSPACE + "ChangeRequest";

    public static final String HDR_OSLC_VERSION = "OSLC-Core-Version";

}

