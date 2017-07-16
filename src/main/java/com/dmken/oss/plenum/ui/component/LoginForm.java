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
package com.dmken.oss.plenum.ui.component;

import java.util.function.Consumer;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;

/**
 * Simple component providing a minimalistic login form containing only a
 * password field.
 *
 */
public class LoginForm extends CustomComponent {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -9057844761640771638L;

    /**
     * The password field.
     *
     */
    private final PasswordField passwordField;
    /**
     * The login button.
     *
     */
    private final Button loginButton;

    /**
     * Constructor of LoginForm.
     *
     */
    public LoginForm() {
        this.passwordField = new PasswordField("Password");
        this.loginButton = new Button("Login");

        final FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.addComponent(this.passwordField);
        layout.addComponent(this.loginButton);
        this.setCompositionRoot(layout);
    }

    /**
     * Adds the given consumer as a listener if the user clicks the
     * login-button.
     *
     * @param loginListener
     *            The listener consuming the password the user has entered.
     */
    public void addLoginListener(final Consumer<String> loginListener) {
        this.loginButton.addClickListener(event -> loginListener.accept(this.passwordField.getValue()));
    }
}
