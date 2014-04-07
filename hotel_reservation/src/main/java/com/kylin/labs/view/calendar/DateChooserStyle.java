package com.kylin.labs.view.calendar;

import java.util.Locale;


/**
 * <p>Class containing all style information on the GUI elements used
 * by DateChooser. The preferred way to override these settings is to
 * use the methods provided by JPopupCalendar to modify the
 * look and feel of the swing components or create custom instances of
 * DateChooserButton, DateChooserLabel and/or DateChooserPanel.</p>
 * <p>If there is a need to override (repeatedly maybe) a large list of
 * configuration settings it is recommended to subclass DateChooserStyle,
 * avoid creating any constructors and simply define all component settings
 * in initOverride.
 * @see DateChooser
 * @see DateChooserButton
 * @see DateChooserLabel
 * @see DateChooserPanel
 */
public abstract class DateChooserStyle {
    static int daySize = 20;
    protected Locale locale = Locale.getDefault();

    public DateChooserStyle() {
        initDefaults();
        initOverride();
    }

    /**
     * Set the default values for the various swing components used by
     * DateChooser.
     */
    protected abstract void initDefaults();

    /**
     * Method to allow user defined subclasses to add their own style
     * settings without having to copy the original defaults settings.
     */
    protected void initOverride() {
    };

    protected void setLocale(Locale locale) {
      this.locale = locale;
    }
}
