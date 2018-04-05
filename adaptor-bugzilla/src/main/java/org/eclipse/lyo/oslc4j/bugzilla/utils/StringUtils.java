/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.php.
 *  
 *  Contributors:
 *  
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.lyo.oslc4j.bugzilla.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class StringUtils {
	
    private static final String LS = System.getProperty("line.separator");
    
    public static String streamToString(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
            sb.append(LS);
        }
        return sb.toString();
    }
	
	public static String forHtml(String expr) {
		// convert each line to a paragraph
		StringTokenizer st = new StringTokenizer(expr, "\n"); //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		while( st.hasMoreTokens()  ){
			sb.append(XmlUtils.encode(st.nextToken()) + "<br/>"); //$NON-NLS-1$ 
		}
		return sb.toString();
	}
	
	public static String rfc2822(Date date ) {
		String pattern = "EEE, dd MMM yyyy HH:mm:ss Z"; //$NON-NLS-1$
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static Date rfc2822(String dateStr) throws ParseException {
		String pattern = "EEE, dd MMM yyyy HH:mm:ss Z"; //$NON-NLS-1$
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateStr);
	}

	@SuppressWarnings("nls")
	public static String stringEscape(String title) {
		return title.replaceAll("\\\\", "\\\\\\\\");
	}
	
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	/**
	 * From  org.codehaus.jettison / jettison (under Apache 2.0)
	 * @param string
	 * @return
	 */
	public static String quote(String string) {
		if (string == null || string.length() == 0) {
			return "\"\"";
		}

		char         c = 0;
		int          i;
		int          len = string.length();
		StringBuilder sb = new StringBuilder(len + 4);
		String       t;

		sb.append('"');
		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
				case '\\':
				case '"':
					sb.append('\\');
					sb.append(c);
					break;
				case '/':
					//                if (b == '<') {
					sb.append('\\');
					//                }
					sb.append(c);
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\r':
					sb.append("\\r");
					break;
				default:
					if (c < ' ') {
						t = "000" + Integer.toHexString(c);
						sb.append("\\u" + t.substring(t.length() - 4));
					} else {
						sb.append(c);
					}
			}
		}
		sb.append('"');
		return sb.toString();
	}
	
}
