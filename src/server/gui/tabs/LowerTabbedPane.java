package server.gui.tabs;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import server.gui.panels.EmoStatePanel;
import server.gui.panels.FacialPanel;
import server.gui.panels.LogPanel;
import server.gui.panels.TrainingResults;
import server.sys.SubjectImplementation;

/**
 * This purpose of this class is to abstract the Tab implementation and its component
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APR2018
 *
 */
public class LowerTabbedPane extends JTabbedPane {

  private static final long serialVersionUID = 5206845224218178653L;

  private SubjectImplementation er;
  private JPanel detectionPanel;
  private EmoStatePanel emoStatePanel;
  private FacialPanel emoFacialPanel;
  private TrainingResults trainingResults;
  private LogPanel emoLogPanel;
  private JPanel qualityPanel;

  public LowerTabbedPane(SubjectImplementation er) {
    setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    setBounds(0, 0, 443, 555);

    this.er = er;
    initialize();
  }

  private void initialize() {
    qualityPanel = new JPanel();
    qualityPanel.setLayout(null);
    qualityPanel.setEnabled(false);

    emoFacialPanel = new FacialPanel(er);
    emoFacialPanel.setBounds(0, 175, 440, 150);

    emoStatePanel = new EmoStatePanel(er);

    trainingResults = new TrainingResults(er);
    trainingResults.setBounds(0, 325, 180, 195);

    emoLogPanel = new LogPanel();
    emoLogPanel.setBounds(190, 325, 245, 195);

    detectionPanel = new JPanel();
    detectionPanel.setLayout(null);

    detectionPanel.add(emoFacialPanel);
    detectionPanel.add(emoStatePanel);
    detectionPanel.add(trainingResults);
    detectionPanel.add(emoLogPanel);

    addTab("Contact Quality", null, qualityPanel, null);
    addTab("Detection", null, detectionPanel, null);

    setSelectedIndex(1);
    setEnabledAt(0, false);
  }
}
