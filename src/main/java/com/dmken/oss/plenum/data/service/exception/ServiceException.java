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
package com.dmken.oss.plenum.data.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Root exception of all exceptions thrown by services. They must be annotated
 * with {@link ResponseStatus @ResponseStatus} allowing REST resources to simple
 * declare the exception in the <code>throws</code>-clause with delivering the
 * correct status codes.
 *
 */
@SuppressWarnings("javadoc")
public abstract class ServiceException extends Exception {
    private static final long serialVersionUID = 9081690787316767402L;

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServiceException(final String message) {
        super(message);
    }
}
