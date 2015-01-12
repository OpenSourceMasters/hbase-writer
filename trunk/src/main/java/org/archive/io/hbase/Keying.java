package org.archive.io.hbase;

/**
 * Copyright 2010 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility creating hbase friendly keys. Use fabricating row names or column
 * qualifiers.
 * <p>
 * TODO: Add createSchemeless key, a key that doesn't care if scheme is http or
 * https.
 * 
 * @see Bytes#split(byte[], byte[], int)
 */
public class Keying {
	public static final String REFERER_URL_SCHEME = "r:";

	private static final Pattern URI_RE_PARSER = Pattern.compile("^([^:/?#]+://(?:[^/?#@]+@)?)([^:/?#]+)(.*)$");

	public static final String DOMAIN_NAME_DELIMITER = ".";

	/**
	 * Makes a key out of passed URI for use as row name or column qualifier.
	 * 
	 * This method runs transforms on the passed URI so it sits better as a key
	 * (or portion-of-a-key) in hbase. The <code>host</code> portion of the URI
	 * authority is reversed so subdomains sort under their parent domain. The
	 * returned String is an opaque URI of an artificial <code>r:</code> scheme
	 * to prevent the result being considered an URI of the original scheme.
	 * Here is an example of the transform: The url
	 * <code>http://lucene.apache.org/index.html?query=something#middle<code> is
	 * returned as
	 * <code>r:http://org.apache.lucene/index.html?query=something#middle</code>
	 * The transforms are reversible. No transform is done if passed URI is not
	 * hierarchical.
	 * 
	 * <p>
	 * If authority <code>userinfo</code> is present, will mess up the sort
	 * (until we do more work).
	 * </p>
	 * 
	 * @param u
	 *            URL to transform.
	 * @return An opaque URI of artificial 'r' scheme with host portion of URI
	 *         authority reversed (if present).
	 * @see #keyToUri(String)
	 * @see <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC2396</a>
	 */

	public static String createKey(final String u, String scheme, final boolean reverseIPAddressesToo) {
		if (scheme != null && scheme.length() > 0 && u.startsWith(scheme)) {
			throw new IllegalArgumentException("Key already starts with a scheme: " + scheme);
		}
		Matcher m = getURIMatcher(u);
		if (m == null || !m.matches()) {
			// If no match, return original String.
			return u;
		}
		return scheme + m.group(1) + reverseHostname(m.group(2), reverseIPAddressesToo) + m.group(3);
	}

	/**
	 * Reverse the {@link #createKey(String)} transform.
	 * 
	 * @param s
	 *            <code>URI</code> made by {@link #createKey(String)}.
	 * @return 'Restored' URI made by reversing the {@link #createKey(String)}
	 *         transform.
	 */
	public static String keyToUri(final String s, final String scheme, final boolean reverseIPAddressesToo) {
		if (scheme == null || s == null) {
			return s;
		} else if (!s.toLowerCase().startsWith(scheme.toLowerCase())) {
			return s;
		}
		// here we have a matching scheme
		Matcher uriMatchObject = getURIMatcher(s.substring(scheme.length()));
		if (uriMatchObject == null || !uriMatchObject.matches()) {
			// If no match, return original String.
			return s;
		}
		// only return a modified key if we have a matching scheme and both
		// arguments are not null
		return uriMatchObject.group(1) + reverseHostname(uriMatchObject.group(2), reverseIPAddressesToo) + uriMatchObject.group(3);
	}

	private static Matcher getURIMatcher(final String uriText) {
		if (uriText == null || uriText.length() <= 0) {
			return null;
		}
		return URI_RE_PARSER.matcher(uriText);
	}

	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String reverseHostname(final String hostname, final boolean reverseIPAddressesToo) {
		if (hostname == null) {
			return "";
		}
		// check for an IP addres,s if we find one, return it, Ip addresses sort
		// nicely the way they are, no needed to reverse their domain name
		// tokens.
		if (!reverseIPAddressesToo) {
			// check for ip
			String[] hostNameTokens = hostname.split("\\.");
			if (hostNameTokens.length == 4) {
				if (isInteger(hostNameTokens[0]) && isInteger(hostNameTokens[1]) && isInteger(hostNameTokens[2]) && isInteger(hostNameTokens[3])) {
					return hostname;
				}
			}

		}
		StringBuilder sb = new StringBuilder(hostname.length());
		Object next;
		for (StringTokenizer st = new StringTokenizer(hostname, DOMAIN_NAME_DELIMITER, false); st.hasMoreElements();) {
			next = st.nextElement();
			// prepend each element to the string buffer object to return a
			// revered list of the input.
			if (sb.length() > 0) {
				sb.insert(0, DOMAIN_NAME_DELIMITER);
			}
			sb.insert(0, next);
		}
		if (sb.length() != hostname.length()) {
			throw new RuntimeException("given hostname: " + hostname + " was reversed to reflect a revers'ed hostname: " + sb.toString()
			        + " but input and output string lengths do not match.  Please debug and fix immediately.");
		}
		return sb.toString();
	}
}