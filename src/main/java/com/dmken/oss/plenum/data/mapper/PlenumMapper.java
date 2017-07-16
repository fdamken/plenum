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

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;

import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.model.Speaker;

/**
 * Mapper for {@link Plenum}.
 *
 */
public interface PlenumMapper extends BaseMapper<Plenum> {
    /**
     * Retrieves the plenum with the given reference.
     *
     * @param reference
     *            The reference of the plenum to retrieve.
     * @return The plenum, if any.
     */
    Plenum retrievePlenum(@Param("reference") final UUID reference);

    /**
     * Creates the given plenum with respect to the name, description, password
     * and reference values. Generates an ID and sets it.
     *
     * @param plenum
     *            The plenum to create.
     */
    void createPlenum(@Param("plenum") final Plenum plenum);

    /**
     * Sets the name of the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to modify.
     * @param name
     *            The name to set.
     */
    void setName(@Param("id") final int id, @Param("name") final String name);

    /**
     * Sets the description of the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to modify.
     * @param description
     *            The description to set.
     */
    void setDescription(@Param("id") final int id, @Param("description") final String description);

    /**
     * Sets the password of the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to modify.
     * @param password
     *            The password to set.
     */
    void setPassword(@Param("id") final int id, @Param("password") final String password);

    /**
     * Sets the start date time of the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to modify.
     * @param start
     *            The start date time to set.
     */
    void setStart(@Param("id") final int id, @Param("start") final LocalDateTime start);

    /**
     * Sets the end date time of the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to modify.
     * @param end
     *            The end date time to set.
     */
    void setEnd(@Param("id") final int id, @Param("end") final LocalDateTime end);

    /**
     * Adds the given speaker to the plenum with the given ID with respect to
     * the number, display name and since values.
     *
     * @param id
     *            The ID of the plenum to add the speaker to.
     * @param speaker
     *            The speaker to add.
     */
    void addSpeaker(@Param("id") final int id, @Param("speaker") final Speaker speaker);

    /**
     * Removes the speaker with the given ID from the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to remove the speaker from.
     * @param speakerId
     *            The ID of the speaker to remove.
     */
    void removeSpeaker(@Param("plenumId") final int id, @Param("speakerId") final int speakerId);

    /**
     * Adds the given protocol to the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to add the protocol to.
     * @param protocolId
     *            The ID of the protocol to add.
     */
    void addProtocol(@Param("id") final int id, @Param("protocolId") final int protocolId);

    /**
     * Removes the protocol with the given ID from the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to remove the speaker from.
     * @param protocolId
     *            The ID of the protocol to remove.
     */
    void removeProtocol(@Param("id") final int id, @Param("protocolId") final int protocolId);

    /**
     * Adds the given speak list to the plenum with the given ID.
     *
     * @param id
     *            The ID of the plenum to add the speak list to.
     * @param speakListId
     *            The ID of the speak list to add.
     */
    void addSpeakList(@Param("id") final int id, @Param("speakListId") final int speakListId);

    /**
     * Removes the speak list with the given ID from the plenum with the given
     * ID.
     *
     * @param id
     *            The ID of the plenum to remove the speak list from.
     * @param speakListId
     *            The ID of the speak list to remove.
     */
    void removeSpeakList(@Param("id") final int id, @Param("speakListId") final int speakListId);

    /**
     * Checks whether the plenum with the given ID contains a speaker with the
     * given number or the given display name.
     *
     * @param id
     *            The ID of the plenum to check.
     * @param number
     *            The number to check.
     * @param displayName
     *            The display name to check.
     * @return Whether the plenum with the given ID contains a speaker that has
     *         the given number or the given display name.
     */
    boolean hasSpeaker(@Param("id") final int id, @Param("number") final int number,
            @Param("displayName") final String displayName);
}
