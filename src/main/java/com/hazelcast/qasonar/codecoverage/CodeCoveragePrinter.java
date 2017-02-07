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

package com.hazelcast.qasonar.codecoverage;

import com.hazelcast.qasonar.utils.CommandLineOptions;
import com.hazelcast.qasonar.utils.PropertyReader;

import java.io.IOException;
import java.util.Map;

import static com.hazelcast.qasonar.utils.DebugUtils.print;
import static com.hazelcast.qasonar.utils.Utils.writeToFile;

class CodeCoveragePrinter {

    private final AbstractPrinter printer;

    CodeCoveragePrinter(Map<Integer, PullRequestStatus> pullRequests, Map<String, FileContainer> files,
                               PropertyReader props, CommandLineOptions cliOptions) {
        if (cliOptions.isPlainOutput()) {
            printer = new PlainPrinter(pullRequests, files, props, cliOptions);
        } else {
            printer = new MarkupPrinter(pullRequests, files, props, cliOptions);
        }
    }

    public void run() throws IOException {
        String outputFile = printer.getProps().getOutputFile();
        if (outputFile != null) {
            writeToFile(outputFile, printer.run());
        } else {
            print(printer.run().toString());
        }
    }
}
