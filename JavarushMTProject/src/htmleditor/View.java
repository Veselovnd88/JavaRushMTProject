package htmleditor;

import htmleditor.listeners.FrameListener;
import htmleditor.listeners.TabbedPaneChangeListener;
import htmleditor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    private JTabbedPane tabbedPane = new JTabbedPane(2);
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public View(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//установка режима LookAndFeel
        }
        catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

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
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar,BorderLayout.NORTH);
    }
    public void selectedTabChanged(){
        if(tabbedPane.getSelectedIndex()==0){
           controller.setPlainText(plainTextPane.getText());
        }
        else if(tabbedPane.getSelectedIndex()==1){
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
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
        String action = e.getActionCommand();
        switch (action){
            case"Новый":
                controller.createNewDocument();
                break;
            case"Открыть":
                controller.openDocument();
                break;
            case"Сохранить":
                controller.saveDocument();
                break;
            case"Сохранить как":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case"О программе":
                this.showAbout();
        }

    }
    public boolean canUndo(){

        return undoManager.canUndo();}
    public  boolean canRedo(){
        return undoManager.canRedo();
    }
    public void undo(){
        try{
            undoManager.undo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public void redo(){
        try{
        undoManager.redo();}
        catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public UndoListener getUndoListener(){
        return undoListener;
    }
    public void resetUndo(){
        undoManager.discardAllEdits();
    }
    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex()==0;
    }
    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }
    public void update(){
        htmlTextPane.setDocument(controller.getModel());

    }
    public void showAbout() {
        JOptionPane.showMessageDialog(this, "Лучший HTML редактор", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
