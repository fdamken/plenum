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
package com.dmken.oss.plenum.ui.view;

import javax.annotation.PostConstruct;

import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.ui.util.FormUtil;
import com.dmken.oss.plenum.ui.util.NotificationUtil;
import com.dmken.oss.plenum.ui.view.design.CreatePlenumDesign;
import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification.Type;

/**
 * View for creating plenums.
 *
 */
@SpringView(name = CreatePlenumView.NAME)
public class CreatePlenumView extends CreatePlenumDesign implements View {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -2984835486867392066L;

    /**
     * View name.
     *
     */
    public static final String NAME = "create-plenum";

    private final Binder<Plenum> binder = new Binder<>();

    /**
     * Invoked after bean was initialized.
     *
     * <p>
     * <b> NOTE: To be invoked by Spring! Do not invoke manually! </b>
     * </p>
     *
     */
    @PostConstruct
    public void onPostConstruct() {
        this.binder.forField(this.inputName)
                .asRequired("Plenum must have a name.")
                .bind(Plenum::getName, Plenum::setName);
        this.binder.forField(this.inputDescription)
                .bind(Plenum::getDescription, Plenum::setDescription);

        this.buttonSave.addClickListener(event -> {
            final BinderValidationStatus<Plenum> validationStatus = this.binder.validate();
            if (validationStatus.hasErrors()) {
                NotificationUtil.chain()
                        .caption("Invalid Data")
                        .description("The given data is not valid!")
                        .type(Type.WARNING_MESSAGE)
                        .show();
                return;
            }
            NotificationUtil.chain()
                    .caption("Created")
                    .description("The plenum was created.")
                    .show();
            FormUtil.clear(this.inputName, this.inputDescription);
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        // Nothing to do.
    }
}
