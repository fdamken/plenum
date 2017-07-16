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
package com.dmken.oss.plenum.data.mapper;

import java.net.URL;
import java.util.SortedSet;

import org.apache.ibatis.annotations.Param;

import com.dmken.oss.plenum.model.Protocol;

/**
 * Mapper for {@link Protocol}.
 *
 */
public interface ProtocolMapper extends BaseMapper<Protocol> {
    /**
     * Finds all protocols for the given plenum ID.
     *
     * @param plenumId
     *            The ID of the plenum to find the protocols for.
     * @return All protocols for the plenum.
     */
    SortedSet<Protocol> findByPlenumId(@Param("plenumId") final int plenumId);

    /**
     * Creates the given protocol with respect to the name, description, URL and
     * read-only URL values. Generates an ID and sets it.
     *
     * @param protocol
     *            The protocol to create.
     */
    void createProtocol(@Param("protocol") final Protocol protocol);

    /**
     * Sets the name of the protocol with the given ID.
     *
     * @param id
     *            The ID of the protocol to modify.
     * @param name
     *            The name to set.
     */
    void setName(@Param("id") final int id, @Param("name") final String name);

    /**
     * Sets the description of the protocol with the given ID.
     *
     * @param id
     *            The ID of the protocol to modify.
     * @param description
     *            The description to set.
     */
    void setDescription(@Param("id") final int id, @Param("description") final String description);

    /**
     * Sets the read-only URL of the protocol with the given ID.
     *
     * @param id
     *            The ID of the protocol to modify.
     * @param url
     *            The read-only URL to set.
     */
    void setUrl(@Param("id") final int id, @Param("url") final URL url);

    /**
     * Sets the read-only URL of the protocol with the given ID.
     *
     * @param id
     *            The ID of the protocol to modify.
     * @param readOnlyUrl
     *            The read-only URL to set.
     */
    void setReadOnlyUrl(@Param("id") final int id, @Param("readOnlyURL") final URL readOnlyUrl);
}
