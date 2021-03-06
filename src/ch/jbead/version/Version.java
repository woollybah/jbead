/** jbead - http://www.jbead.ch
    Copyright (C) 2001-2012  Damian Brunold

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ch.jbead.version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Version implements Comparable<Version> {

    private int major = -1;
    private int minor = -1;
    private int build = -1;

    private static final Version INSTANCE = new Version();

    private Version() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/version.txt"), "UTF-8"));
            try {
                readVersionFile(reader);
            } finally {
              reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Version(int major, int minor, int build) {
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

    public Version(String version) {
        String[] parts = version.split(Pattern.quote("."));
        this.major = Integer.parseInt(parts[0]);
        this.minor = Integer.parseInt(parts[1]);
        this.build = Integer.parseInt(parts[2]);
    }

    private void readVersionFile(BufferedReader reader) throws IOException {
        String data = reader.readLine();
        if (data == null) return;
        String[] parts = data.split(Pattern.quote("."));
        if (parts == null) return;
        if (parts.length < 3) return;
        major = Integer.parseInt(parts[0]);
        minor = Integer.parseInt(parts[1]);
        build = Integer.parseInt(parts[2]);
    }

    public static Version getInstance() {
        return INSTANCE;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getBuild() {
        return build;
    }

    public String getVersionString() {
        return String.format("%d.%d.%d", major, minor, build);
    }

    public String getWinVersionString() {
        return String.format("%d.%d.0.%d", major, minor, build);
    }

    public String getShortVersionString() {
        return String.format("%d.%d", major, minor);
    }

    public boolean isOlderThan(String version) {
        return compareTo(new Version(version)) < 0;
    }

    public Version bump() {
        return new Version(major, minor, build + 1);
    }

    public Version bumpMinor() {
        return new Version(major, minor + 1, 0);
    }

    public Version bumpMajor() {
        return new Version(major + 1, 0, 0);
    }

    public int compareTo(Version version) {
        if (major < version.major) return -1;
        else if (major > version.major) return 1;
        else if (minor < version.minor) return -1;
        else if (minor > version.minor) return 1;
        else if (build < version.build) return -1;
        else if (build > version.build) return 1;
        else return 0;
    }

}
