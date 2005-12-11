/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.views.util;

import com.opensymphony.webwork.ServletActionContext;

import com.opensymphony.xwork.util.OgnlValueStack;
import com.opensymphony.xwork.util.TextParseUtil;

import java.net.URLEncoder;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * UrlHelper
 * @author Jason Carreira
 * Created Apr 19, 2003 9:32:19 PM
 */
public class UrlHelper {
    //~ Methods ////////////////////////////////////////////////////////////////

    public static String buildUrl(String action, HttpServletRequest request, HttpServletResponse response, Map params) {
        StringBuffer link = new StringBuffer();

        if (action != null) {
            // Check if context path needs to be added
            // Add path to absolute links
            if (action.startsWith("/")) {
                link.append(request.getContextPath());
            }

            // Add page
            link.append(action);
        } else {
            // Go to "same page"
            link.append(request.getRequestURI());
        }

        //if the action was not explicitly set grab the params from the request
        if ((params != null) && (params.size() > 0)) {
            if (link.toString().indexOf("?") == -1) {
                link.append("?");
            } else {
                link.append("&amp;");
            }

            // Set params
            Iterator enum = params.entrySet().iterator();

            while (enum.hasNext()) {
                Map.Entry entry = (Map.Entry) enum.next();
                String name = (String) entry.getKey();
                Object value = entry.getValue();

                if (value != null) {
                    link.append(name);
                    link.append('=');
                    link.append(translateAndEncode(value.toString()));
                }

                if (enum.hasNext()) {
                    link.append("&amp;");
                }
            }
        }

        String result;

        try {
            result = response.encodeURL(link.toString());
        } catch (Exception ex) {
            // Could not encode the URL for some reason
            // Use it unchanged
            result = link.toString();
        }

        return result;
    }

    /**
    * Translates any script expressions using {@link com.opensymphony.xwork.util.TextParseUtil#translateVariables} and
    * encodes the URL using {@link java.net.URLEncoder#encode}
    * @param input
    * @return the translated and encoded string
    */
    public static String translateAndEncode(String input) {
        OgnlValueStack valueStack = ServletActionContext.getContext().getValueStack();
        String output = TextParseUtil.translateVariables(input, valueStack);

        return URLEncoder.encode(output);
    }
}