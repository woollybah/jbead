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

package ch.jbead;

import java.util.Iterator;

public class RectIterator implements Iterator<Point> {

    private Point begin;
    private Point end;
    private Point next;

    public RectIterator(Point begin, Point end) {
        this.begin = new Point(Math.min(begin.getX(), end.getX()), Math.min(begin.getY(), end.getY()));
        this.end = new Point(Math.max(begin.getX(), end.getX()), Math.max(begin.getY(), end.getY()));
        if (this.begin.getX() < 0 || this.end.getY() < 0) {
            this.next = null;
        } else {
            this.next = this.begin;
        }
    }

    public boolean hasNext() {
        return next != null;
    }

    public Point next() {
        Point result = next;
        next = next.nextRight();
        if (next.getX() > end.getX()) {
            next = new Point(begin.getX(), next.getY() + 1);
        }
        if (next.getY() > end.getY()) {
            next = null;
        }
        return result;
    }

    public void remove() {
        // ignore
    }

}
