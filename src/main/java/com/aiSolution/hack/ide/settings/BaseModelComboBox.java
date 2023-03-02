package com.aiSolution.hack.ide.settings;

import com.aiSolution.hack.client.BaseModel;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class BaseModelComboBox extends JComboBox<BaseModel> {

  public BaseModelComboBox(BaseModel selectedModel) {
    super(new BaseModel[] {
        BaseModel.DAVINCI,
        BaseModel.CURIE,
        BaseModel.BABBAGE,
        BaseModel.ADA,
    });
    setSelectedItem(selectedModel);
    setRenderer(getBasicComboBoxRenderer());
  }

  private BasicComboBoxRenderer getBasicComboBoxRenderer() {
    return new BasicComboBoxRenderer() {
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index,
            isSelected, cellHasFocus);

        if (value != null) {
          BaseModel model = (BaseModel) value;
          setText(model.getDescription());
        }
        return this;
      }
    };
  }
}
