package htmleditor;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }
    public void init(){
        createNewDocument();
    }
    public void exit(){
        System.exit(0);//выход
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }
    public HTMLDocument getModel(){
        return document;
    }
    public void resetDocument(){
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        this.document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        this.document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try{
            new HTMLEditorKit().read(stringReader,this.document,0);
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public String getPlainText(){
        StringWriter sw = new StringWriter();
        try{
            new HTMLEditorKit().write(sw,this.document,0,this.document.getLength());
        }
        catch (Exception e){
            ExceptionHandler.log(e);
        }
        return sw.toString();
    }
    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("Документ ХТМЛ");
        currentFile=null;

    }
    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        if (chooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());}
        try{
        FileReader fr = new FileReader(currentFile);
        new HTMLEditorKit().read(fr,document,0);}
        catch (Exception e){
            ExceptionHandler.log(e);
        }

        view.resetUndo();
    }
    public void saveDocument(){
        view.selectHtmlTab();
        if(currentFile==null){
            saveDocumentAs();
        }
        try{
            FileWriter fw = new FileWriter(currentFile);
            new HTMLEditorKit().write(fw,document,0,document.getLength());}
        catch (Exception e){
            ExceptionHandler.log(e);
        }
    }
    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new HTMLFileFilter());
        int choice = chooser.showSaveDialog(view);
        if(choice==JFileChooser.APPROVE_OPTION){
           currentFile= chooser.getSelectedFile();
           view.setTitle(currentFile.getName());
           try{
            FileWriter fw = new FileWriter(currentFile);
            new HTMLEditorKit().write(fw,document,0,document.getLength());}
           catch (Exception e){
               ExceptionHandler.log(e);
           }
        }

    }

}
