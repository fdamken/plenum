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

import com.dmken.oss.plenum.ui.view.DefaultView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
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

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(this.createNavigationButton("Home", DefaultView.NAME));
        root.addComponent(navigationBar);

        this.springViewDisplay = new Panel();
        this.springViewDisplay.setSizeFull();
        root.addComponent(this.springViewDisplay);
        root.setExpandRatio(this.springViewDisplay, 1.0F);
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
     * Creates a navigation {@link Button button} with the given text for the
     * given view.
     *
     * @param text
     *            The text to show on the {@link Button button}.
     * @param view
     *            The name of the view to show when clicking on the
     *            {@link Button button}.
     * @return The newly created {@link Button}.
     */
    private Button createNavigationButton(final String text, final String view) {
        final Button button = new Button(text);
        button.addClickListener(event -> this.getUI().getNavigator().navigateTo(view));
        return button;
    }
}
