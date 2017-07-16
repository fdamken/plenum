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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dmken.oss.plenum.ui.util.UIUtil;
import com.dmken.oss.plenum.ui.view.AdminView;
import com.dmken.oss.plenum.ui.window.LoginModal;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

import lombok.Getter;

/**
 * Bean containing session data for the administration UI.
 *
 */
@UIScope
@Component
public class AdminSession implements Serializable {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 2268061351638165694L;

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
     * The administration password.
     *
     */
    @Value("${plenum.admin.password}")
    private String password;

    /**
     * Whether the current user is authenticated/authorized to the use
     * administration UI.
     *
     */
    @Getter
    private boolean authenticated;

    /**
     * Opens the login window for the administration view in the given
     * {@link UI}.
     *
     * @param ui
     *            {@link UI}.
     */
    public void openLoginWindow(final UI ui) {
        UIUtil.openRedirectWindow(new LoginModal(passwd -> {
            if (this.authenticate(passwd)) {
                ui.getNavigator().navigateTo(AdminView.NAME);

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
        if (this.password.equals(password)) {
            this.setAuthenticated(true);
        }

        return this.authenticated;
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

        this.propertyChangeSupport.firePropertyChange(AdminSession.PROPERTY_AUTHENTICATED, oldAuthenticated, this.authenticated);
    }
}
