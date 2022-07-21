package htmleditor;

import htmleditor.listeners.FrameListener;
import htmleditor.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(2);
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();


    public Controller getController() {
        return controller;
    }
    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void initGui(){
        initMenuBar();
        initEditor();
        pack();//автоматическое формироание размеров исходя
    }
    public void initMenuBar(){

    }
    public void selectedTabChanged(){

    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");//тип контента
        JScrollPane scrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML",scrollPane);
        JScrollPane scrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст",scrollPane1);
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        tabbedPane.setPreferredSize(new Dimension(300,300));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
    public void exit(){
        controller.exit();}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
