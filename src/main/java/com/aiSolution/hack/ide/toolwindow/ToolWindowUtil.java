package com.aiSolution.hack.ide.toolwindow;

import com.intellij.ui.JBColor;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ToolWindowUtil {

  public static JTextArea createTextArea(String selectedText, boolean isItalicFont) {
    var textArea = new JTextArea();
    textArea.append(selectedText);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    var font = textArea.getFont();
    textArea.setFont(font.deriveFont(isItalicFont ? Font.ITALIC : Font.PLAIN));
    textArea.setWrapStyleWord(true);
    textArea.setBackground(JBColor.PanelBackground);
    // textArea.setBorder(new MatteBorder(0, 2, 0, 0, JBColor.RED));
    return textArea;
  }

  public static JLabel createIconLabel(ImageIcon imageIcon, String text) {
    var iconLabel = new JLabel(imageIcon);
    iconLabel.setText(text);
    iconLabel.setFont(iconLabel.getFont().deriveFont(iconLabel.getFont().getStyle() | Font.BOLD));
    iconLabel.setIconTextGap(8);
    return iconLabel;
  }

  public static JButton createIconButton(ImageIcon imageIcon) {
    var button = new JButton(imageIcon);
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setContentAreaFilled(false);
    button.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
    return button;
  }

  public static Box justifyLeft(Component component) {
    Box box = Box.createHorizontalBox();
    box.add(component);
    box.add(Box.createHorizontalGlue());
    return box;
  }
}

