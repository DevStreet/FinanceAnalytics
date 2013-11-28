/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.ui.beaneditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.integration.tool.IntegrationToolContext;
import com.opengamma.scripts.Scriptable;

/**
 * Editor of Joda-Bean objects.
 */
@Scriptable
public class BeanEditor extends AbstractTool<IntegrationToolContext> {

  /**
   * The application frame.
   */
  private JFrame _frame;
  private JTabbedPane _tabs;

  /**
   * Launch the application.
   * 
   * @param args  the main method arguments
   */
  public static void main(String[] args) {
    new BeanEditor().initAndRun(args, IntegrationToolContext.class);
  }

  //-------------------------------------------------------------------------
  @Override
  protected void doRun() throws Exception {
    final SynchronousQueue<Void> endQueue = new SynchronousQueue<>();
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        createUI();
        _frame.addWindowStateListener(new WindowStateListener() {
          @Override
          public void windowStateChanged(WindowEvent e) {
            if (e.getNewState() == WindowEvent.WINDOW_CLOSED) {
              endQueue.add(null);
            }
          }
        });
        _frame.setVisible(true);
      }
    });
    endQueue.take();
  }

  //-------------------------------------------------------------------------
  /**
   * Initialize the contents of the frame.
   */
  private void createUI() {
    _frame = new JFrame();
    _frame.setTitle("Bean editor");
    _frame.setPreferredSize(new Dimension(1000, 700));
    _frame.setLocationByPlatform(true);
    _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // tabs
    _tabs = new JTabbedPane();
    SecurityPanel secPanel = new SecurityPanel(getToolContext().getSecurityMaster());
    ConventionPanel convPanel = new ConventionPanel(getToolContext().getConventionMaster());
    _tabs.addTab("Securities", secPanel);
    _tabs.addTab("Conventions", convPanel);
    
    // main panel
    JPanel mainPanel = new JPanel(new BorderLayout());
    _frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
    mainPanel.add(_tabs);
    _frame.pack();
  }

}
