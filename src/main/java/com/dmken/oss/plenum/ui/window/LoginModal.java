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
package com.dmken.oss.plenum.ui.window;

import java.util.function.Function;

import com.dmken.oss.plenum.ui.component.LoginForm;
import com.vaadin.ui.Window;

/**
 * A simple window providing a simple login form accepting a password only.
 *
 */
public class LoginModal extends Window {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 8299184648132370232L;

    /**
     * The login form.
     *
     */
    private final LoginForm loginForm = new LoginForm();

    /**
     * Constructor of AdminLoginWindow.
     *
     * @param action
     *            Function for validating the password and executing stuff.
     */
    public LoginModal(final Function<String, Boolean> action) {
        this.setContent(this.loginForm);
        this.setCaption("Login");
        this.setModal(true);
        this.setWidth(23.0F, Unit.EM);
        this.setHeightUndefined();
        this.center();

        this.loginForm.setWidth(100, Unit.PERCENTAGE);

        this.loginForm.addLoginListener(password -> {
            if (action.apply(password)) {
                this.close();
            }
        });
    }
}
