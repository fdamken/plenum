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
    Plenum retrievePlenum(@Param("reference") final UUID reference);

    void createPlenum(@Param("plenum") final Plenum plenum);

    void setName(@Param("id") final int id, @Param("name") final String name);

    void setDescription(@Param("id") final int id, @Param("description") final String description);

    void setStart(@Param("id") final int id, @Param("start") final LocalDateTime start);

    void setEnd(@Param("id") final int id, @Param("end") final LocalDateTime end);

    void addSpeaker(@Param("id") final int id, @Param("speaker") final Speaker speaker);

    void removeSpeaker(@Param("plenumId") final int id, @Param("speakerId") final int speakerId);

    void addProtocol(@Param("id") final int id, @Param("protocolId") final int protocolId);

    void removeProtocol(@Param("id") final int id, @Param("protocolId") final int protocolId);

    void addSpeakList(@Param("id") final int id, @Param("speakListId") final int speakListId);

    void removeSpeakList(@Param("id") final int id, @Param("speakListId") final int speakListId);

    boolean hasSpeaker(@Param("id") final int id, @Param("number") final int number,
            @Param("displayName") final String displayName);
}
