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
package com.dmken.oss.plenum.ui;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.dmken.oss.plenum.data.service.PlenumService;
import com.dmken.oss.plenum.data.service.exception.NoSuchPlenumException;
import com.dmken.oss.plenum.ui.session.AdminSession;
import com.dmken.oss.plenum.ui.session.PlenumSession;
import com.dmken.oss.plenum.ui.util.NotificationUtil;
import com.dmken.oss.plenum.ui.util.UIUtil;
import com.dmken.oss.plenum.ui.view.AdminView;
import com.dmken.oss.plenum.ui.view.DefaultView;
import com.dmken.oss.plenum.ui.view.PlenumView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Main UI class using Vaadin.
 *
 */
@SpringComponent
@SpringUI
@Theme(ValoTheme.THEME_NAME)
@SpringViewDisplay
public class MainUI extends UI implements ViewDisplay {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -3256991069448490228L;

    /**
     * The {@link PlenumService}.
     *
     */
    @Autowired
    private PlenumService plenumService;
    /**
     * The {@link AdminSession}.
     *
     */
    @Autowired
    private AdminSession adminSession;
    /**
     * The {@link PlenumSession}.
     *
     */
    @Autowired
    private PlenumSession plenumSession;

    /**
     * The panel for displaying content.
     *
     */
    private Panel springViewDisplay;

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        this.setContent(root);

        final Label headerLabel = new Label("Plenum Manager");
        headerLabel.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(headerLabel);

        final HorizontalLayout navigation = new HorizontalLayout();
        navigation.setWidth(100.0F, Unit.PERCENTAGE);

        final Layout navigationBar = UIUtil.createNavigationBar();
        navigationBar.addComponent(UIUtil.createNavigationButton("Home", DefaultView.NAME, this::getUI));
        final Button navigationButtonPlenum = UIUtil.createNavigationButton("Plenum", PlenumView.NAME, this::getUI);
        final Button navigationButtonAdministration = UIUtil.createNavigationButton("Administration", AdminView.NAME,
                this::getUI);
        navigation.addComponent(navigationBar);
        navigation.setComponentAlignment(navigationBar, Alignment.MIDDLE_LEFT);

        final Layout loginBar = UIUtil.createNavigationBar();
        final Button loginButton = UIUtil.createNavigationButton("Login", AdminView.NAME, this::getUI);
        final Button logoutButton = UIUtil.createNavigationButton("Logout", DefaultView.NAME, this::getUI);
        logoutButton.addClickListener(event -> this.adminSession.setAuthenticated(false));
        loginBar.addComponent(loginButton);
        navigation.addComponent(loginBar);
        navigation.setComponentAlignment(loginBar, Alignment.MIDDLE_RIGHT);

        root.addComponent(navigation);

        this.springViewDisplay = new Panel();
        this.springViewDisplay.setSizeFull();
        root.addComponent(this.springViewDisplay);
        root.setExpandRatio(this.springViewDisplay, 1.0F);

        this.adminSession.getPropertyChangeSupport().addPropertyChangeListener(AdminSession.PROPERTY_AUTHENTICATED, event -> {
            if ((boolean) event.getNewValue()) {
                navigationBar.addComponent(navigationButtonAdministration);
                loginBar.removeComponent(loginButton);
                loginBar.addComponent(logoutButton);
            } else {
                navigationBar.removeComponent(navigationButtonAdministration);
                loginBar.removeComponent(logoutButton);
                loginBar.addComponent(loginButton);
            }
        });
        this.plenumSession.getPropertyChangeSupport().addPropertyChangeListener(PlenumSession.PROPERTY_PLENUM, event -> {
            if (event.getNewValue() == null) {
                navigationBar.removeComponent(navigationButtonPlenum);
            } else {
                navigationBar.addComponent(navigationButtonPlenum);
            }
        });

        this.loadPlenum(request);

        if (this.plenumSession.getPlenum() != null) {
            this.getUI().getNavigator().navigateTo(PlenumView.NAME);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.navigator.ViewDisplay#showView(com.vaadin.navigator.View)
     */
    @Override
    public void showView(final View view) {
        this.springViewDisplay.setContent((Component) view);
    }

    /**
     * Loads the plenum referenced in the given request (parameter
     * <code>ref</code>). If any error occurs (the given UUID is invalid, the
     * plenum does not exist), the user is informed using notifications.
     *
     * <p>
     * If the plenum loading is successful, the plenum is stored in the
     * {@link #plenumSession}.
     * </p>
     *
     * @param request
     *            The request to extract the plenum from.
     */
    private void loadPlenum(final VaadinRequest request) {
        final String refStr = request.getParameter("ref");
        if (refStr == null) {
            // No reference set.
            return;
        }

        final UUID ref;
        try {
            ref = UUID.fromString(refStr);
        } catch (final IllegalArgumentException ex) {
            NotificationUtil.chain()
                    .caption("Invalid Reference")
                    .description("The given reference is not a valid UUID!")
                    .type(Type.ERROR_MESSAGE)
                    .show();
            return;
        }
        try {
            this.plenumSession.setPlenum(this.plenumService.retrievePlenum(ref));
        } catch (final NoSuchPlenumException ex) {
            NotificationUtil.chain()
                    .caption("Unknown Reference")
                    .description("No plenum was found for the given reference!")
                    .type(Type.ERROR_MESSAGE)
                    .show();
            return;
        }
    }
}
