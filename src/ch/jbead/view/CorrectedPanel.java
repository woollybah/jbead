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

package ch.jbead.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ch.jbead.BeadPainter;
import ch.jbead.CoordinateCalculator;
import ch.jbead.JBeadFrame;
import ch.jbead.Model;
import ch.jbead.Point;
import ch.jbead.Selection;
import ch.jbead.ViewListener;

public class CorrectedPanel extends BasePanel implements ViewListener, CoordinateCalculator {

    private static final long serialVersionUID = 1L;

    private int offsetx;
    private int left;
    private Font symbolfont;

    public CorrectedPanel(Model model, Selection selection, final JBeadFrame frame) {
        super(model, frame, selection);
        model.addListener(this);
        frame.addListener(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.correctedMouseUp(e);
            }
        });
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension((model.getWidth() + 2) * gridx, 3 * gridy);
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getMinimumSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        symbolfont = new Font("SansSerif", Font.PLAIN, gridy - 1);
        offsetx = getOffsetX();
        left = getLeft();
        paintBeads(g);
    }

    private int getLeft() {
        return offsetx < 0 ? gridx / 2 : offsetx;
    }

    private int getOffsetX() {
        return gridx / 2 + (getWidth() - 1 - model.getWidth() * gridx - gridx / 2) / 2;
    }

    public int x(Point pt) {
        return left + pt.getX() * gridx;
    }

    public int y(Point pt) {
        return getHeight() - 1 - (pt.getY() + 1) * gridy;
    }

    @Override
    public int dx(int j) {
        if ((j + scroll) % 2 == 0) {
            return 0;
        } else {
            return gridx / 2;
        }
    }

    private void paintBeads(Graphics g) {
        if (scroll > model.getHeight() - 1) return;
        BeadPainter painter = new BeadPainter(this, model, view, symbolfont);
        g.setFont(symbolfont);
        for (Point pt : model.getRect(scroll, model.getHeight() - 1)) {
            byte c = model.get(pt);
            pt = model.correct(pt.unscrolled(scroll));
            if (aboveTop(pt)) break;
            painter.paint(g, pt, c);
        }
    }

    private boolean aboveTop(Point pt) {
        return y(pt) < -gridy;
    }

    @Override
    public void redraw(Point pt) {
        if (!isVisible()) return;
        Point _pt = pt.unscrolled(scroll);
        byte c = model.get(pt);
        _pt = model.correct(_pt);
        BeadPainter painter = new BeadPainter(this, model, view, symbolfont);
        Graphics g = getGraphics();
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(symbolfont);
        painter.paint(g, _pt, c);
        g.dispose();
    }

    public Point mouseToField(Point pt) {
        int j = (getHeight() - pt.getY()) / gridy;
        if (pt.getX() < offsetx - dx(j) || pt.getX() > offsetx + model.getWidth() * gridx + dx(j)) return null;
        int i = (pt.getX() - offsetx + dx(j)) / gridx;
        return new Point(i, j);
    }

    public void togglePoint(Point pt) {
        pt = mouseToField(pt);
        if (pt == null) return;
        int idx = model.getCorrectedIndex(pt);
        pt = model.getPoint(idx);
        selection.clear();
        model.setPoint(pt.unscrolled(scroll));
    }

    public void fillLine(Point pt) {
        pt = mouseToField(pt);
        if (pt == null) return;
        int idx = model.getCorrectedIndex(pt);
        pt = model.getPoint(idx);
        selection.clear();
        model.fillLine(pt.unscrolled(scroll));
    }

    public void drawColorsChanged(boolean drawColors) {
        repaint();
    }

    public void drawSymbolsChanged(boolean drawSymbols) {
        repaint();
    }

}
