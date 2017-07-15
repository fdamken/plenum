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
package com.dmken.oss.plenum.data.service;

import java.util.SortedSet;

import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakListException;
import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakerException;
import com.dmken.oss.plenum.model.SpeakList;
import com.dmken.oss.plenum.model.SpeakListEntry;
import com.dmken.oss.plenum.model.Speaker;
import com.dmken.oss.plenum.util.Priority;

/**
 * Service for the {@link SpeakList speak list}.
 *
 */
public interface SpeakListService {
    SortedSet<SpeakList> retrieveSpeakListsOfPlenum(final int plenumId);

    Speaker retrieveSpeaker(final int speakListId, final int speakerId) throws NoSuchSpeakerException;

    SpeakList retrieveSpeakList(final int speakListId) throws NoSuchSpeakListException;

    SpeakList createSpeakList(final String name, final String description);

    SpeakList setName(final int speakListId, final String name) throws NoSuchSpeakListException;

    SpeakList setDescription(final int speakListId, final String description) throws NoSuchSpeakListException;

    SpeakList pushSpeaker(final int speakListId, final int speakerId, final Priority priority)
            throws NoSuchSpeakListException, NoSuchSpeakerException;

    SpeakListEntry nextSpeaker(final int speakListId) throws NoSuchSpeakListException;

    SpeakList removeSpeaker(final int speakListId, final int speakListEntryId) throws NoSuchSpeakListException;
}
