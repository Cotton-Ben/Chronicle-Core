/*
 * Copyright 2016-2020 Chronicle Software
 *
 * https://chronicle.software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.core.values;

import net.openhft.chronicle.core.Jvm;

public interface LongValue {
    long getValue();

    void setValue(long value);

    long getVolatileValue();

    /**
     * Value to return if the underlying resource isn't available.
     */
    default long getVolatileValue(long closedValue) {
        return getVolatileValue();
    }

    void setVolatileValue(long value);

    void setOrderedValue(long value);

    long addValue(long delta);

    long addAtomicValue(long delta);

    boolean compareAndSwapValue(long expected, long value);

    default void setMaxValue(long value) {
        for (; ; ) {
            long pos = getVolatileValue();
            if (pos >= value)
                break;
            if (compareAndSwapValue(pos, value))
                break;
            Jvm.nanoPause();
        }
    }

    default void setMinValue(long value) {
        for (; ; ) {
            long pos = getVolatileValue();
            if (pos <= value)
                break;
            if (compareAndSwapValue(pos, value))
                break;
            Jvm.nanoPause();
        }
    }
}
