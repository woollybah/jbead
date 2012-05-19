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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */
public class AboutBox extends JDialog {
    private static final long serialVersionUID = 1L;

    public AboutBox(Localization localization) {
        setTitle(localization.getString("aboutbox.title"));
        setIconImage(ImageFactory.getImage("jbead-16"));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
        setModal(true);
        setLayout(new BorderLayout());
        add(new JLabel(localization.getString("aboutbox.text")), BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        JButton ok = new JButton(localization.getString("ok"));
        buttons.add(ok);
        add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutBox.this.dispose();
            }
        });
    }

//    private void setGermanText() {
//        String t = "<html><h1>jbead</h1>" + "<p>Dies ist <b>jbead</b>, ein Programm, das Ihnen beim Entwurf von gehäkelten "
//                + "Perlenketten helfen soll. Die Erstellung solcher Ketten wird beispielsweise "
//                + "im Buch 'Gehäkelte Glasperlenketten' von Lotti Gygax beschrieben. Die Arbeit ist aufwändig und "
//                + "langwierig. Das Resultat entschädigt aber für die erlittene Mühsal. " + "<p>&nbsp;</p> "
//                + "<p>Mit <b>jbead</b> können Sie schon vor Beginn der Arbeit simulieren, wie Ihr "
//                + "Entwurf als Kette dann aussehen wird. Direkt am Bildschirm können Sie " + "Änderungen vornehmen.\\par " + "<p>&nbsp;</p> "
//                + "<p>Wenn Sie zufrieden mit dem Entwurf sind, können Sie alle notwendigen "
//                + "Daten ausdrucken lassen, inklusive einer 'Fädelliste', die hilfreich "
//                + "für das Auffädeln der Perlen auf das Häkelgarn ist.\\par " + "<p>&nbsp;</p> "
//                + "<p><b>jbead</b> wurde von Damian Brunold geschrieben. Es steht unter der Lizenz "
//                + "GPL v3, was bedeutet, dass Sie es kostenlos verwenden, kopieren und ändern "
//                + "dürfen. Dafür übernimmt Damian Brunold absolut keine " + "Haftung für Fehler und Schäden durch Benutzung des Programmes. "
//                + "Sie müssen selber entscheiden, ob das Programm für Sie nützlich " + "ist oder nicht.\\par " + "<p>&nbsp;</p> "
//                + "<p>Weitere Informationen erhalten Sie unter http://www.brunoldsoftware.ch "
//                + "oder per E-Mail an info@brunoldsoftware.ch. An diese Adresse können "
//                + "Sie auch Fehler oder Verbesserungsvorschläge melden.\\par " + "<p>&nbsp;</p> " + "<p>Viel Spass mit dem Programm</p>"
//                + "<p>Damian Brunold</p>";
//        text.setText(t);
//    }

}
