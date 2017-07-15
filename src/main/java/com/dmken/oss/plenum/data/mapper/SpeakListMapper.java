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

import java.util.SortedSet;

import org.apache.ibatis.annotations.Param;

import com.dmken.oss.plenum.model.SpeakList;
import com.dmken.oss.plenum.model.SpeakListEntry;
import com.dmken.oss.plenum.model.Speaker;

/**
 * Mapper for {@link SpeakList}.
 *
 */
public interface SpeakListMapper extends BaseMapper<SpeakList> {
    SortedSet<SpeakList> findByPlenumId(@Param("plenumId") final int plenumId);

    Speaker findSpeaker(@Param("speakListId") final int speakListId, @Param("speakerId") final int speakerId);

    void createSpeakList(@Param("speakList") final SpeakList speakList);

    void setName(@Param("id") final int id, @Param("name") final String name);

    void setDescription(@Param("id") final int id, @Param("description") final String description);

    void setSpeaking(@Param("id") final int id, @Param("speakListEntryId") final int speakListEntryId);

    void removeSpeaking(@Param("id") final int id);

    void addSpeaker(@Param("id") final int id, @Param("speakListEntry") final SpeakListEntry speakListEntry);

    void removeSpeaker(@Param("id") final int id, @Param("speakListEntryId") final int speakListEntryId);

    void setSpoke(@Param("id") final int id, @Param("speakListEntryId") final int speakListEntryId);
}
