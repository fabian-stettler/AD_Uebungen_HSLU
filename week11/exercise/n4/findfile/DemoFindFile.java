/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
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
 */
package Uebungen_AD.week11.exercise.n4.findfile;

import java.io.File;
import java.nio.file.Path;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Codevorlage für eine Dateisuche.
 */
public final class DemoFindFile {

    private static final Logger LOG = LoggerFactory.getLogger(DemoFindFile.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFindFile() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        final String search = "findADFile.md";
        final File rootDir = new File("C:/Users/fabia");

        final FindFileTask root = new FindFileTask(search, rootDir);
        LOG.info("root task started");
        long t21 = System.currentTimeMillis();
        LOG.info(root.invoke());
        long t22 = System.currentTimeMillis();
        LOG.info("Find '{}' concurrent in '{}'", search, root.getRawResult());
        LOG.info("Found in {} msec.", t22-t21);

        LOG.info("Start searching '{}' recursive in '{}'", search, rootDir);
        long t1 = System.currentTimeMillis();
        FindFile.findFile(search, rootDir);
        long t2 = System.currentTimeMillis();
        LOG.info("Found in {} msec.", t2-t1);

    }
}
