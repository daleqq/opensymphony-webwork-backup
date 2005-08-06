package com.opensymphony.webwork.dispatcher.multipart;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.util.OgnlValueStack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * All WebWork requests are wrapped with class, which provides simple JSTL accessibility.
 * This is because JSTL works with request attributes, so this class delegates to the
 * value stack except for a few cases where required to prevent infinite loops. Namely,
 * we don't let any attribute name with "#" in it delegate out to the value stack, as
 * it could potentially cause an infinite loop. For example, an infinite loop would
 * take place if you called: request.getAttribute("#attr.foo").
 *
 * @since 2.2
 */
public class WebWorkRequestWrapper extends HttpServletRequestWrapper {
    public WebWorkRequestWrapper(HttpServletRequest req) {
        super(req);
    }

    public Object getAttribute(String s) {
        Object attribute = super.getAttribute(s);

        // note: we don't let # come through or else a request for
        // #attr.foo or #request.foo could cause an endless loop
        if (attribute == null && s.indexOf("#") == -1) {
            // If not found, then try the ValueStack
            OgnlValueStack stack = ActionContext.getContext().getValueStack();
            if (stack != null) {
                attribute = stack.findValue(s);
            }
        }
        return attribute;
    }
}
