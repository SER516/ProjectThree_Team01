package client.gui.actions;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import client.gui.ClientHamburgerMenu;
import client.gui.ConnectToServerFrame;
import client.gui.EmotivControlPanel;

/**
 * This class is responsible for handling action events from the client
 * 
 * @author Nikita Bahl
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APRIL2018
 *
 */
public class ClientActionEvents implements ActionListener {
  private Component actionClass;
  private String item;

  /*
   * Constructor for this class to get the component name
   */
  public ClientActionEvents(Component actionClass) {
    this.actionClass = actionClass;
  }

  /*
   * Constructor for this class to get the component name and item type
   */
  public ClientActionEvents(Component actionClass, String item) {
    this.actionClass = actionClass;
    this.item = item;
  }

  /*
   * Method for Action listener event
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (actionClass instanceof ClientHamburgerMenu) {
      if (item == "EmoComposer") {
        server.gui.ServerMainHandler.main(null);
      } else if (item == "Facial Expressions") {
        client.gui.EmotivControlPanel showFacialGraphObject = EmotivControlPanel.getInstance();
        showFacialGraphObject.showFacialGraph();
      } else if (item == "Performance Metrics") {
        client.gui.EmotivControlPanel metricsObject = EmotivControlPanel.getInstance();
        metricsObject.showPerformanceMetric();
      } else if (item == "Connect Composer") {
        ConnectToServerFrame connectFrame = new ConnectToServerFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        connectFrame.setLocation(dim.width / 2 - connectFrame.getSize().width / 2,
            dim.height / 2 - connectFrame.getSize().height / 2);
        connectFrame.setVisible(true);
      } else if (item == "Git") {
        if (Desktop.isDesktopSupported()) {
          String url = "https://github.com/SER516/ProjectThree_Team01";
          Desktop desktop = Desktop.getDesktop();
          try {
            desktop.browse(new URI(url));
          } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
          }
        }
      }
    }

    if (actionClass instanceof ConnectToServerFrame) {
      if (item == "OK") {
        if (((ConnectToServerFrame) actionClass).connectToServer()) {
          ((ConnectToServerFrame) actionClass).setVisible(false);
          EmotivControlPanel.getInstance().updateSignalLabel(true);
        }
      } else if (item == "Cancel") {
        ((ConnectToServerFrame) actionClass).setVisible(false);
      }
    }
  }
}