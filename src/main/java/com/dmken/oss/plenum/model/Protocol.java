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
package com.dmken.oss.plenum.model;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * One protocol of the {@link Plenum plenum}
 *
 */
@Data
@Builder
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class Protocol implements Model, Comparable<Protocol> {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 5476676695344477512L;

    /**
     * ID.
     *
     */
    private Integer id;
    /**
     * Name.
     *
     */
    private String name;
    /**
     * Description.
     *
     */
    private String description;
    /**
     * URL where the protocol can be edited (e.g. an etherpad link).
     *
     */
    private URL url;
    /**
     * URL where the protocol can be reviewed but not edited (e.g. HackMD
     * presentation mode).
     *
     */
    private URL readOnlyURL;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Protocol that) {
        return this.name.compareTo(that.name);
    }
}
