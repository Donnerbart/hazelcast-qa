/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.qamatch.match;

import org.apache.maven.shared.invoker.InvocationOutputHandler;

import java.util.LinkedList;
import java.util.List;

class BufferingOutputHandler implements InvocationOutputHandler {

    private final List<String> lines = new LinkedList<>();

    @Override
    public void consumeLine(String line) {
        lines.add(line);
    }

    void printErrors() {
        for (String line : lines) {
            if (line.contains("ERROR")) {
                System.err.println(line);
            }
        }
    }

    void clear() {
        lines.clear();
    }
}
