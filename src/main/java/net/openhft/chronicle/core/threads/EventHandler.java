/*
 *     Copyright (C) 2015  higherfrequencytrading.com
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.openhft.chronicle.core.threads;

/**
 * Created by peter.lawrey on 22/01/15.
 */
@FunctionalInterface
public interface EventHandler {
    default void eventLoop(EventLoop eventLoop) {
    }

    default HandlerPriority priority() {
        return HandlerPriority.MEDIUM;
    }

    /**
     * perform all tasks once and return ASAP.
     *
     * @return true if you expect more work very soon.
     * @throws InvalidEventHandlerException when it is not longer valid.
     */
    boolean action() throws InvalidEventHandlerException, InterruptedException;
}
