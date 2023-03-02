package com.aiSolution.hack.ide.toolwindow.components;

import static com.aiSolution.hack.ide.toolwindow.ToolWindowUtil.createIconButton;

import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import icons.Icons;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.CaretStyle;

public class SyntaxTextArea extends RSyntaxTextArea {

  public SyntaxTextArea() {
    super("");
    setStyles();
    addDocumentListener();
  }

  public void displayCopyButton() {
    ComponentBorder cb = new ComponentBorder(createCopyButton());
    cb.setAlignment(TOP_ALIGNMENT);
    cb.install(this);
  }

  public void enableSelection() {
    setEditable(false);
    setEnabled(true);
  }

  public void changeStyleViaThemeXml() {
    var baseThemePath = "/org/fife/ui/rsyntaxtextarea/themes/";
    try {
      Theme theme = Theme.load(getClass().getResourceAsStream(
          UIUtil.isUnderDarcula() ? baseThemePath + "dark.xml" : baseThemePath + "idea.xml"));
      theme.apply(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setStyles() {
    setMargin(JBUI.insets(5));
    setAntiAliasingEnabled(true);
    setEnabled(false);
    setPaintTabLines(false);
    setHighlightCurrentLine(false);
    setLineWrap(true);
    setCaretStyle(0, CaretStyle.BLOCK_STYLE);
    getCaret().setVisible(true);
    setWrapStyleWord(true);
    setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MARKDOWN);
    changeStyleViaThemeXml();
  }

  private void copyToClipboard() {
    StringSelection stringSelection = new StringSelection(getText());
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
  }

  private JButton createCopyButton() {
    var button = createIconButton(Icons.CopyImageIcon);
    button.addActionListener(e -> {
      copyToClipboard();
      button.setIcon(Icons.DoubleTickImageIcon);
    });
    return button;
  }

  private void addDocumentListener() {
    getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        setCaretPosition(getText().length());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        setCaretPosition(getText().length());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        setCaretPosition(getText().length());
      }
    });
  }
}
