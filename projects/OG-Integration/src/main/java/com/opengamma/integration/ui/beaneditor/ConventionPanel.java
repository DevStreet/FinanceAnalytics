/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.ui.beaneditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.joda.beans.Bean;
import org.joda.beans.ui.swing.JodaBeanSwingUI;
import org.joda.beans.ui.swing.component.SimpleListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractDocument;
import com.opengamma.master.convention.ConventionDocument;
import com.opengamma.master.convention.ConventionMaster;
import com.opengamma.master.convention.ConventionSearchRequest;
import com.opengamma.master.convention.ConventionSearchResult;

/**
 * Panel for editing conventions.
 */
public class ConventionPanel extends JPanel {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;
  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(ConventionPanel.class);

  private JTextField _searchTextField;
  private JButton _searchButton;
  private JSplitPane _splitPane;
  private JList<UniqueNamedItem> _selectionList;
  private JPanel _editPanel;

  /**
   * Initialize the contents of the panel.
   */
  ConventionPanel(final ConventionMaster master) {
    // search panel
    _searchTextField = new JTextField();
    _searchButton = new JButton("Search");
    JPanel searchPanel = new JPanel(new BorderLayout(4, 0));
    searchPanel.add(_searchTextField, BorderLayout.CENTER);
    searchPanel.add(_searchButton, BorderLayout.EAST);
    
    // selection list
    _selectionList = new JList<UniqueNamedItem>(new SimpleListModel<UniqueNamedItem>());
    _selectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    // selection panel
    JPanel selectionPanel = new JPanel(new BorderLayout(0, 4));
    JScrollPane scrollPane = new JScrollPane(_selectionList);
    selectionPanel.add(searchPanel, BorderLayout.NORTH);
    selectionPanel.add(scrollPane, BorderLayout.CENTER);
    selectionPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    
    // edit panel
    _editPanel = new JPanel(new BorderLayout());
    
    // split panel
    _splitPane = new JSplitPane();
    _splitPane.setLeftComponent(selectionPanel);
    _splitPane.setRightComponent(_editPanel);
    _splitPane.setDividerLocation(0.3d);
    
    // main panel
    setLayout(new BorderLayout());
    add(_splitPane);
    
    // events
    _searchButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ev) {
        final String search = _searchTextField.getText();
        if (search.length() < 2) {
          Toolkit.getDefaultToolkit().beep();
          return;
        }
        final SimpleListModel<UniqueNamedItem> model = (SimpleListModel<UniqueNamedItem>) _selectionList.getModel();
        model.clear();
        _selectionList.setEnabled(false);
        SwingWorker<List<UniqueNamedItem>, Void> sw = new SwingWorker<List<UniqueNamedItem>, Void>() {
          @Override
          protected List<UniqueNamedItem> doInBackground() throws Exception {
            ConventionSearchRequest request = new ConventionSearchRequest();
            request.setName("*" + search + "*");
            ConventionSearchResult result = master.search(request);
            List<UniqueNamedItem> items = new ArrayList<>();
            for (ConventionDocument doc : result.getDocuments()) {
              items.add(UniqueNamedItem.of(doc.getUniqueId(), doc.getName()));
            }
            return items;
          }
          @Override
          protected void done() {
            try {
              List<UniqueNamedItem> items = get();
              model.clear();
              model.addAll(items);
              _selectionList.setEnabled(true);
            } catch (Exception ex) {
              s_logger.info(ex.getMessage(), ex);
              model.clear();
            }
          }
        };
        sw.execute();
      }
    });
    
    _selectionList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent ev) {
        if (ev.getValueIsAdjusting()) {
          return;
        }
        int index = _selectionList.getSelectedIndex();
        _editPanel.removeAll();
        _editPanel.revalidate();
        _editPanel.repaint();
        if (index >= 0) {
          final UniqueId uniqueId = _selectionList.getSelectedValue().getUniqueId();
          SwingWorker<AbstractDocument, Void> sw = new SwingWorker<AbstractDocument, Void>() {
            @Override
            protected AbstractDocument doInBackground() throws Exception {
              return master.get(uniqueId);
            }
            @Override
            protected void done() {
              try {
                AbstractDocument doc = get();
                JPanel panel = new JodaBeanSwingUI().createForm((Bean) doc.getValue());
                _editPanel.add(panel);
              } catch (Exception ex) {
                s_logger.info(ex.getMessage(), ex);
                JLabel label = new JLabel(ex.getMessage());
                _editPanel.add(label);
              }
              _editPanel.revalidate();
              _editPanel.repaint();
            }
          };
          sw.execute();
        }
      }
    });
  }

}
