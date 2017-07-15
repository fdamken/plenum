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
package com.dmken.oss.plenum.data.service.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmken.oss.plenum.data.mapper.SpeakListMapper;
import com.dmken.oss.plenum.data.service.SpeakListService;
import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakListException;
import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakerException;
import com.dmken.oss.plenum.model.SpeakList;
import com.dmken.oss.plenum.model.SpeakListEntry;
import com.dmken.oss.plenum.model.Speaker;
import com.dmken.oss.plenum.util.Priority;

/**
 * Implementation of {@link SpeakListService}.
 *
 */
@Service
public class SpeakListServiceImpl implements SpeakListService {
    /**
     * {@link SpeakListMapper}.
     *
     */
    @Autowired
    private SpeakListMapper speakListMapper;

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#retrieveSpeakListsOfPlenum(int)
     */
    @Override
    public SortedSet<SpeakList> retrieveSpeakListsOfPlenum(final int plenumId) {
        return new TreeSet<>(this.speakListMapper.findByPlenumId(plenumId));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#retrieveSpeaker(int,
     *      int)
     */
    @Override
    public Speaker retrieveSpeaker(final int speakListId, final int speakerId) throws NoSuchSpeakerException {
        final Speaker speaker = this.speakListMapper.findSpeaker(speakListId, speakerId);
        if (speaker == null) {
            throw new NoSuchSpeakerException(
                    "Unable to find speaker with id=" + speakerId + " in speak list with id=" + speakListId + "!");
        }
        return speaker;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#retrieveSpeakList(int)
     */
    @Override
    public SpeakList retrieveSpeakList(final int speakListId) throws NoSuchSpeakListException {
        final SpeakList speakList = this.speakListMapper.findById(speakListId);
        if (speakList == null) {
            throw new NoSuchSpeakListException("Unable to find speak list with id=" + speakListId + "!");
        }
        return speakList;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#createSpeakList(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public SpeakList createSpeakList(final String name, final String description) {
        final SpeakList speakList = SpeakList.builder() //
                .name(name) //
                .description(description) //
                .build();
        this.speakListMapper.createSpeakList(speakList);
        return speakList;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#setName(int,
     *      java.lang.String)
     */
    @Override
    public SpeakList setName(final int speakListId, final String name) throws NoSuchSpeakListException {
        final SpeakList speakList = this.retrieveSpeakList(speakListId);
        this.speakListMapper.setName(speakListId, name);
        return speakList;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#setDescription(int,
     *      java.lang.String)
     */
    @Override
    public SpeakList setDescription(final int speakListId, final String description) throws NoSuchSpeakListException {
        final SpeakList speakList = this.retrieveSpeakList(speakListId);
        this.speakListMapper.setDescription(speakListId, description);
        return speakList;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#pushSpeaker(int,
     *      int, com.dmken.oss.plenum.util.Priority)
     */
    @Override
    public SpeakList pushSpeaker(final int speakListId, final int speakerId, final Priority priority)
            throws NoSuchSpeakListException, NoSuchSpeakerException {
        final SpeakList speakList = this.retrieveSpeakList(speakListId);
        final Speaker speaker = this.retrieveSpeaker(speakListId, speakerId);
        final SpeakListEntry speakListEntry = SpeakListEntry.builder() //
                .speaker(speaker) //
                .priority(priority) //
                .build();
        this.speakListMapper.addSpeaker(speakListId, speakListEntry);
        speakList.getEntries().offer(speakListEntry);
        return speakList;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#nextSpeaker(int)
     */
    @Override
    public SpeakListEntry nextSpeaker(final int speakListId) throws NoSuchSpeakListException {
        final SpeakList speakList = this.retrieveSpeakList(speakListId);
        final SpeakListEntry speakListEntry = speakList.next();
        if (speakListEntry == null) {
            this.speakListMapper.removeSpeaking(speakListId);
        } else {
            this.speakListMapper.setSpeaking(speakListId, speakListEntry.getId());
            this.speakListMapper.removeSpeaker(speakListId, speakListEntry.getId());
        }
        return speakListEntry;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.SpeakListService#removeSpeaker(int,
     *      int)
     */
    @Override
    public SpeakList removeSpeaker(final int speakListId, final int speakListEntryId) throws NoSuchSpeakListException {
        final SpeakList speakList = this.retrieveSpeakList(speakListId);
        this.speakListMapper.removeSpeaker(speakListId, speakListEntryId);
        speakList.getEntries().removeIf(speakListEntry -> speakListEntry.getId() == speakListEntryId);
        return speakList;
    }
}
