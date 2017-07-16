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

import org.springframework.beans.factory.annotation.Autowired;

import com.dmken.oss.plenum.data.service.PlenumService;
import com.dmken.oss.plenum.ui.session.AdminSession;
import com.dmken.oss.plenum.ui.session.PlenumSession;
import com.dmken.oss.plenum.ui.view.design.AdminDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

/**
 * View for administration.
 *
 */
@SpringView(name = AdminView.NAME)
public class AdminView extends AdminDesign implements View {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -69399053585212293L;

    /**
     * View name.
     *
     */
    public static final String NAME = "admin";

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
     * Invoked after bean was initialized.
     *
     * <p>
     * <b> NOTE: To be invoked by Spring! Do not invoke manually! </b>
     * </p>
     *
     */
    @PostConstruct
    public void onPostConstruct() {
        this.gridPlenums.addSelectionListener(event -> {
            event.getFirstSelectedItem().ifPresent(plenum -> {
                this.plenumSession.setPlenum(plenum);
                this.plenumSession.setAuthenticated(true);

                this.getUI().getNavigator().navigateTo(PlenumView.NAME);
            });
        });
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        if (!this.adminSession.isAuthenticated()) {
            this.adminSession.openLoginWindow(this.getUI());
            return;
        }

        this.refresh();
    }

    /**
     * Refreshes the data in the view.
     *
     */
    private void refresh() {
        this.gridPlenums.setItems(this.plenumService.retrievePlenums());
    }
}
