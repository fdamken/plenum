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

import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

/**
 * Utilities for {@link Notification notifications}.
 *
 */
@UtilityClass
public class NotificationUtil {
    /**
     * Creates a new {@link NotificationBuilder}.
     *
     * @return The newly created {@link NotificationBuilder};
     */
    public static NotificationBuilder chain() {
        return new NotificationBuilder();
    }

    /**
     * Builder for {@link Notification notfications} to allow inline creation of
     * them.
     *
     * @see Notification
     */
    @Setter
    @Accessors(chain = true,
               fluent = true)
    public static class NotificationBuilder {
        /**
         * Caption.
         *
         */
        private String caption = null;
        /**
         * Description.
         *
         */
        private String description = null;
        /**
         * Type.
         *
         */
        private Type type = Type.HUMANIZED_MESSAGE;
        /**
         * Icon.
         *
         */
        private Resource icon = null;
        /**
         * Position.
         *
         */
        private Position position = Position.TOP_CENTER;
        /**
         * Delay in ms.
         *
         */
        private Integer delayMsec = null;
        /**
         * Style name.
         *
         */
        private String styleName = null;
        /**
         * Whether HTML content is allowed.
         *
         */
        private Boolean htmlContentAllowed = null;

        /**
         * Constructor of NotificationBuilder.
         *
         */
        private NotificationBuilder() {
            // Nothing to do.
        }

        /**
         * Builds a {@link Notification notification} using {@link #caption},
         * {@link #description}, {@link #type}, {@link #icon},
         * {@link #position}, {@link #delayMsec}, {@link #styleName} and
         * {@link #htmlContentAllowed}.
         *
         * @return The newly created {@link Notification notification}.
         */
        public Notification build() {
            final Notification notification = new Notification(this.caption, this.type);
            if (this.description != null) {
                notification.setDescription(this.description);
            }
            if (this.icon != null) {
                notification.setIcon(this.icon);
            }
            if (this.position != null) {
                notification.setPosition(this.position);
            }
            if (this.delayMsec != null) {
                notification.setDelayMsec(this.delayMsec);
            }
            if (this.styleName != null) {
                notification.setStyleName(this.styleName);
            }
            if (this.htmlContentAllowed != null) {
                notification.setHtmlContentAllowed(this.htmlContentAllowed);
            }
            return notification;
        }

        /**
         * Shows the notifcation built using {@link #build()} on the current
         * page.
         *
         */
        public void show() {
            this.build().show(Page.getCurrent());
        }
    }
}
