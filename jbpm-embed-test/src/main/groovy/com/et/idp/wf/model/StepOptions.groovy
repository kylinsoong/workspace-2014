// Copyright 2012 Epsilon Data Management, LLC.  All rights reserved.

package com.et.idp.wf.model

/**
 * A set of options and parameters pertaining to a specific action
 * within the Workflow Manager.
 *
 * @author mimartin
 */
class StepOptions
{
    String stepName

    /**
     * Set properties of this object by copying them from another.
     *
     * @param options
     */
    protected void copyProperties( def options )
    {
        options.properties.each{ key, value ->
            if( this.hasProperty(key) && !(key in ['class', 'metaClass']) ){
                this[key] = value
            }
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return this.properties.findAll{ it.key != 'metaClass' && it.key != 'class' }.toString()
    }
}
