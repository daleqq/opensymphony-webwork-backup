/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.webwork.validators;

import com.opensymphony.xwork.validator.validators.VisitorFieldValidator;

import java.util.Map;


/**
 * This validator adds client-side validation to make sure a required field has been filled in.
 * <p/>
 * <i>Implementation note:</i> this class is marked final because WebWork handles visitor field
 * validation as a special case to cache validator lookups.
 */
public final class JavaScriptVisitorFieldValidator extends VisitorFieldValidator implements ScriptValidationAware {
    //~ Instance fields ////////////////////////////////////////////////////////

    private Class validatedClass;

    //~ Methods ////////////////////////////////////////////////////////////////

    public void setClassName(String className) {
        try {
            validatedClass = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            log.error("Cannot find class '" + className + "'", ex);
        }
    }

    public void setValidatedClass(Class validatedClass) {
        this.validatedClass = validatedClass;
    }

    public Class getValidatedClass() {
        return validatedClass;
    }

    public String validationScript(Map parameters) {
        // Don't actually do anything.  The real work for this is done in
        // AbstractUITag.findScriptingValidators() since we want to cache child validators as well
        return "";
    }
}
