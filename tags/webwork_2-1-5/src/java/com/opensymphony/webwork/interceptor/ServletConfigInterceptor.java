/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.interceptor;

import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.Action;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.interceptor.AroundInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An interceptor which sets action properties based on the interfaces an action implements.
 * For example, if the action implements {@link ParameterAware} then the action
 * context's parameter map will be set on it. <p>
 * <p/>
 * This interceptor is designed to set all properties an action needs if it's aware of servlet
 * parametes, the servlet context, the session, etc.
 *
 * @author Patrick Lightbody
 * @author Bill Lynch (docs)
 */
public class ServletConfigInterceptor extends AroundInterceptor implements WebWorkStatics {
    //~ Methods ////////////////////////////////////////////////////////////////

    /**
     * Does nothing.
     */
    protected void after(ActionInvocation dispatcher, String result) throws Exception {
    }

    /**
     * Sets action properties based on the interfaces an action implements. Things like
     * application properties, parameters, session attributes, etc are set based on
     * the implementing interface.
     *
     * @param invocation an encapsulation of the action execution state.
     * @throws Exception if an error occurs when setting action properties.
     */
    protected void before(ActionInvocation invocation) throws Exception {
        Action action = invocation.getAction();
        ActionContext context = ActionContext.getContext();

        if (action instanceof ServletRequestAware) {
            HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
            ((ServletRequestAware) action).setServletRequest(request);
        }

        if (action instanceof ServletResponseAware) {
            HttpServletResponse response = (HttpServletResponse) context.get(HTTP_RESPONSE);
            ((ServletResponseAware) action).setServletResponse(response);
        }

        if (action instanceof ParameterAware) {
            ((ParameterAware) action).setParameters(context.getParameters());
        }

        if (action instanceof SessionAware) {
            ((SessionAware) action).setSession(context.getSession());
        }

        if (action instanceof ApplicationAware) {
            ((ApplicationAware) action).setApplication(context.getApplication());
        }
    }
}