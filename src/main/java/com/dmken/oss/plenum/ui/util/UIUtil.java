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
package com.dmken.oss.plenum.ui.util;

import java.util.function.Supplier;

import com.dmken.oss.plenum.ui.view.EmptyView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import lombok.experimental.UtilityClass;

/**
 * Utilities for the UI.
 *
 */
@UtilityClass
public class UIUtil {
    /**
     * Creates a navigation bar layout. Buttons created by
     * {@link #createNavigationButton(String, ClickListener)},
     * {@link #createNavigationButton(String, String, Supplier)} can be added to
     * it.
     *
     * @return The navigation bar layout.
     */
    public static Layout createNavigationBar() {
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        return navigationBar;
    }

    /**
     * Creates a navigation button with the given text on it.
     *
     * @param text
     *            The text on the button.
     * @param clickListener
     *            The listener to be executed when the user clicks on the
     *            button.
     * @return The newly created button.
     */
    public static Button createNavigationButton(final String text, final ClickListener clickListener) {
        final Button button = new Button(text);
        button.addClickListener(clickListener);
        return button;
    }

    /**
     * Creates a navigation button switching to the view with the given name.
     *
     * @param text
     *            The text on the button.
     * @param view
     *            The name of the view to switch to when the user clicks on the
     *            button.
     * @param uiSupplier
     *            A supplier for an {@link UI} object.
     * @return The newly created button.
     * @see #createNavigationButton(String, ClickListener)
     */
    public static Button createNavigationButton(final String text, final String view, final Supplier<UI> uiSupplier) {
        return UIUtil.createNavigationButton(text, event -> uiSupplier.get().getNavigator().navigateTo(view));
    }

    /**
     * Opens the given window after switching to an empty view.
     *
     * @param window
     *            The window to open.
     * @param ui
     *            {@link UI}.
     */
    public static void openRedirectWindow(final Window window, final UI ui) {
        ui.getNavigator().navigateTo(EmptyView.NAME);
        ui.addWindow(window);
    }
}
