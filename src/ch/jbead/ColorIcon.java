/** jbead - http://www.brunoldsoftware.ch
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

package ch.jbead;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * 
 */
public class ColorIcon implements Icon {

    private ColorTable table;
    private int index;

    public ColorIcon(ColorTable colorTable, int colorIndex) {
        this.table = colorTable;
        this.index = colorIndex;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(table.getColor(index));
        g.fillRect(x, y, getIconWidth(), getIconHeight());
    }

    @Override
    public int getIconWidth() {
        return 16;
    }

    @Override
    public int getIconHeight() {
        return 16;
    }

}