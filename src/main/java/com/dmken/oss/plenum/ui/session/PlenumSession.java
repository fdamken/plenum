/*-
 * #%L
 * Plenum Manager
 * %%
 * Copyright (C) 2017 Fabian Damken
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.dmken.oss.plenum.ui.session;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.ui.util.UIUtil;
import com.dmken.oss.plenum.ui.view.PlenumView;
import com.dmken.oss.plenum.ui.window.LoginModal;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import lombok.Getter;

/**
 * Bean containing session data for the currently viewed plenum.
 *
 */
@UIScope
@Component("plenumSession")
public class PlenumSession implements Serializable {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 993953148901491895L;

    /**
     * Name used for events in the {@link #propertyChangeSupport} for
     * {@link #plenum}.
     *
     */
    public static final String PROPERTY_PLENUM = "property_plenum";
    /**
     * Name used for events in the {@link #propertyChangeSupport} for
     * {@link #authenticated}.
     *
     */
    public static final String PROPERTY_AUTHENTICATED = "property_authenticated";

    /**
     * The property change support.
     *
     */
    @Getter
    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * The plenum that is currently displayed to the user. Can be
     * <code>null</code> if the user does not view a plenum.
     *
     */
    @Getter
    private Plenum plenum;
    /**
     * Whether the user is authenticated/authorized to see the plenum data (e.g.
     * he has entered the password or the plenum has no password).
     *
     */
    @Getter
    private boolean authenticated;

    /**
     * Opens the login window for the current plenum in the given {@link UI}.
     *
     * @param ui
     *            {@link UI}.
     */
    public void openLoginWindow(final UI ui) {
        UIUtil.openRedirectWindow(new LoginModal(passwd -> {
            if (this.authenticate(passwd)) {
                ui.getNavigator().navigateTo(PlenumView.NAME);

                return true;
            }
            return false;
        }), ui);
    }

    /**
     * Checks the given password and sets {@link #authenticated} to
     * <code>true</code> if it is correct. Otherwise <code>false</code>.
     *
     * @param password
     *            The password to check
     * @return The new value of {@link #authenticated}.
     */
    public boolean authenticate(final String password) {
        if (this.plenum == null) {
            throw new IllegalStateException("Plenum is not initilized!");
        }

        this.setAuthenticated(this.plenum.getPassword() == null || this.plenum.getPassword().equals(password));

        return this.authenticated;
    }

    /**
     * Sets {@link #plenum}.
     *
     * @param plenum
     *            The {@link #plenum} to set.
     */
    public void setPlenum(final Plenum plenum) {
        final Plenum oldPlenum = this.plenum;
        this.plenum = plenum;

        this.propertyChangeSupport.firePropertyChange(PlenumSession.PROPERTY_PLENUM, oldPlenum, this.plenum);

        this.authenticate(null);
    }

    /**
     * Sets {@link #authenticated}.
     *
     * @param authenticated
     *            The {@link #authenticated} to set.
     */
    public void setAuthenticated(final boolean authenticated) {
        final boolean oldAuthenticated = this.authenticated;

        this.authenticated = authenticated;

        this.propertyChangeSupport.firePropertyChange(PlenumSession.PROPERTY_AUTHENTICATED, oldAuthenticated, authenticated);
    }
}
