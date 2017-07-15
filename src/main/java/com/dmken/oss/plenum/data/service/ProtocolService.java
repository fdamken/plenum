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

import java.net.URL;
import java.util.SortedSet;

import com.dmken.oss.plenum.data.service.exception.NoSuchProtocolException;
import com.dmken.oss.plenum.model.Protocol;

/**
 * Service for the {@link Protocol protocol}.
 *
 */
public interface ProtocolService {
    SortedSet<Protocol> retrieveProtocolsOfPlenum(final int plenumId);

    Protocol retrieveProtocol(final int protocolId) throws NoSuchProtocolException;

    Protocol createProtocol(final String name, final String description, final URL url, final URL readOnlyURL);

    Protocol setName(final int protocolId, final String name) throws NoSuchProtocolException;

    Protocol setDescription(final int protocolId, final String description) throws NoSuchProtocolException;

    Protocol setURL(final int protocolId, final URL url) throws NoSuchProtocolException;

    Protocol setReadOnlyURL(final int protocolId, final URL readOnlyUrl) throws NoSuchProtocolException;
}
