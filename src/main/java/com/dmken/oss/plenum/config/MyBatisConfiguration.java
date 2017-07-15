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
package com.dmken.oss.plenum.config;

import javax.annotation.PostConstruct;

import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.dmken.oss.plenum.data.mapper.BaseMapper;

/**
 * MyBatis configuration.
 *
 */
@Configuration
@MapperScan(basePackageClasses = BaseMapper.class,
            markerInterface = BaseMapper.class)
public class MyBatisConfiguration {
    /**
     * Invoked after bean was initialized.
     *
     * <p>
     * <b> NOTE: To be invoked by Spring! Do not invoke manually! </b>
     * </p>
     *
     */
    @PostConstruct
    public void onPostConstruct() {
        LogFactory.useSlf4jLogging();
    }
}
