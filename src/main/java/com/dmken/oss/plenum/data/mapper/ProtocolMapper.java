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
    SortedSet<Protocol> findByPlenumId(@Param("plenumId") final int plenumId);

    void createProtocol(@Param("protocol") final Protocol protocol);

    void setName(@Param("id") final int id, @Param("name") final String name);

    void setDescription(@Param("id") final int id, @Param("description") final String description);

    void setUrl(@Param("id") final int id, @Param("url") final URL url);

    void setReadOnlyUrl(@Param("id") final int id, @Param("readOnlyURL") final URL readOnlyUrl);
}
