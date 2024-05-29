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
public final class FindFile {

    private static final Logger LOG = LoggerFactory.getLogger(FindFile.class);

    /**
     * Sucht ein File in einem Verzeichnis.
     *
     * @param name Name des Files.
     * @param dir Verzeichnis.
     */
    public static String findFile(final String name, final File dir) {
        final File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String found = findFile(name, file);  // Recursive call
                    if (found != null) {
                        return found;  // Return the result if found in the directory
                    }
                } else if (name.equalsIgnoreCase(file.getName())) {
                    LOG.info("Found: " + file.getAbsolutePath());  // Log the find
                    return file.getAbsolutePath();  // Return the path of the file
                }
            }
        }
        return null;  // Return null if the file is not found
    }
}
