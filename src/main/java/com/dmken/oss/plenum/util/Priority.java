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
package com.dmken.oss.plenum.util;

import lombok.Value;

/**
 * Represents a priority using integer for comparison.
 *
 */
@Value
public class Priority implements Comparable<Priority> {
    /**
     * Very Low.
     *
     */
    public static final Priority VERY_LOW = new Priority("Very Low (0)", 0);
    /**
     * Low.
     *
     */
    public static final Priority LOW = new Priority("Low", 50);
    /**
     * Normal.
     *
     */
    public static final Priority NORMAL = new Priority("Normal", 100);
    /**
     * High.
     *
     */
    public static final Priority HIGH = new Priority("High", 150);
    /**
     * Very High.
     *
     */
    public static final Priority VERY_HIGH = new Priority("Very High", 200);

    /**
     * Name of the priority.
     *
     */
    private final String name;
    /**
     * A numerical representation of the priority. Used to ease sorting.
     *
     */
    private final int priority;

    /**
     * Creates the {@link Priority} with the given priority value. If it is
     * <code>0</code>, <code>50</code>, <code>100</code>, <code>150</code> or
     * <code>200</code>, the corresponding constants are returned.
     *
     * @param priority
     *            The priority value.
     * @return The corresponding {@link Priority} constant or the newly created
     *         {@link Priority}.
     */
    public static Priority of(final int priority) {
        switch (priority) {
            case 0:
                return Priority.VERY_LOW;
            case 50:
                return Priority.LOW;
            case 100:
                return Priority.NORMAL;
            case 150:
                return Priority.HIGH;
            case 200:
                return Priority.VERY_HIGH;
            default:
                return new Priority(String.valueOf(priority), priority);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * "Greater" means that it has a higher priority (e.g. {@link #HIGH} is
     * greater than {@link #NORMAL}).
     * </p>
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final Priority that) {
        return Integer.compare(this.getPriority(), that.getPriority());
    }
}
