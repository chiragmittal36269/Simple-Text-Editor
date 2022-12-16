import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;

public class SimpleTextEditor implements ActionListener {

    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem closeEditor;

    SimpleTextEditor()
    {
        // Creating the Frame.
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0, 0, 800, 1000);

        // Initializing the text area
        jTextArea = new JTextArea("Welcome to the Editor");

        // creating the menu bar
        jMenuBar = new JMenuBar();

        // creating different menu
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");

        //creating menu items for File
        NewFile = new JMenuItem("New");
        //actionListener is used to perform some action when press that button(NewFile)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        NewFile.addActionListener(this);

        OpenFile = new JMenuItem("Open");
        //actionListener is used to perform some action when press that button(OpenFile)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        //actionListener is used to perform some action when press that button(SaveFile)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        SaveFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        //actionListener is used to perform some action when press that button(Print)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        PrintFile.addActionListener(this);

        //creating menu items for Edit
        cut = new JMenuItem("Cut");
        //actionListener is used to perform some action when press that button(Cut)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        cut.addActionListener(this);

        copy = new JMenuItem("Copy");
        //actionListener is used to perform some action when press that button(Copy)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        copy.addActionListener(this);

        paste = new JMenuItem("Paste");
        //actionListener is used to perform some action when press that button(Paste)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        paste.addActionListener(this);

        //creating menu items for Close
        closeEditor = new JMenuItem("Close");
        //actionListener is used to perform some action when press that button(Close)
        // & 'this' keyword is used to shift the whole attention of the program to that button.
        closeEditor.addActionListener(this);

        //adding menu items into the File menu
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);

        //adding menu items into Edit menu
        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);

        //adding menu items into Close menu
        Close.add(closeEditor);

        //adding menu into the menu bar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        //adding menu bar into the frame
        frame.setJMenuBar(jMenuBar);

        //adding text area into the frame
        frame.add(jTextArea);

        //use to stop the execution of the code(Close Feature)
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //To make frame visible
        frame.setVisible(true);
    }
    public static void main(String[] args) {

        SimpleTextEditor editor = new SimpleTextEditor();
    }

    //when 'this' keyword line hits then it could override everything and
    // perform the tasks which was written in the actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        // String s is storing the name of the button like Cut, Copy, Close, NewFile etc.
        // with the help of e.getActionCommand()
        String s = e.getActionCommand();
        if(s.equals("Copy"))
        {
            jTextArea.copy();   //it copies the text which we have selected.
        }
        else if(s.equals("Cut"))
        {
            jTextArea.cut();    //it cut the text which we have selected.
        }
        else if(s.equals("Paste"))
        {
            jTextArea.paste();  //it pastes the text which we have cut or copy.
        }
        else if(s.equals("Print"))
        {
            try {
                jTextArea.print();
                // it directly connects to the printers
            }
            catch (PrinterException ex)
            {
                throw new RuntimeException(ex);
                //when printer is not present then it show error to handle that error we write this exception handling
            }
        }
        else if(s.equals("New"))
        {
            jTextArea.setText("");  // it clears the text area i.e. blank the text area.
        }
        else if(s.equals("Close"))
        {
//            frame.setVisible(false);  // it disable the frame but because of this the execution of code is still running.
            System.exit(1); //so we use this to stop the execution of the code and also close the editor.
        }
        else if(s.equals("Open"))
        {
            //JFileChooser is helping to find and choose the file which we want to open.
            JFileChooser jFileChooser = new JFileChooser("C:");

            //int ans stores the option which show in the dialog box like open or cancel.
            int ans = jFileChooser.showOpenDialog(null);
            //if ans is open then if statement executes.
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                //create the File object so that file will store the absolute the address of the selected file
                // using GetSelectedFile which was chosen by JFileChooser
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                //String are used to read the data present in the file which we want to open.
                String s1 = "", s2 = "";
                try {
                    //BufferReader is used to read the file data with the help of FileReader.
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    // s2 read 1st line of file data with the help of bufferReader.readLine. and auto move to next line \n
                    s2=bufferedReader.readLine();
                    // remaining reading work was done by the s1 until bufferReader.readLine reach null.
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }
                    // this will show the data of s2 into the text area
                    jTextArea.setText(s2);
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(s.equals("Save"))
        {
            //JFileChooser is helping to find and choose the space where we can save the file.
            JFileChooser jFileChooser = new JFileChooser("C:");

            //int ans stores the option which show in the dialog box like open or cancel.
            int ans  = jFileChooser.showOpenDialog(null);
            //if ans is open then if statement executes.
            if(ans == JFileChooser.APPROVE_OPTION)
            {
                //create the file object so that file will store the absolute the address of the selected file
                // using GetSelectedFile which was chosen by JFileChooser
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                // we use bufferWriter here because we want to write the data.
                BufferedWriter writer = null;
                try {
                    // by the help of the writer we write the data into the specific location with the help of file
                    // because file store the address which we want to write our data
                    //here false because at starting no need to write anything until writer is not having any data
                    writer = new BufferedWriter(new FileWriter(file, false));
                    // it gets the text from text area and write it to the location.
                    writer.write((jTextArea.getText()));
                    // it is flush the writer to remove the existing text present in the writer.
                    writer.flush();
                    // at the end when work is complete remove the writer to save the memory.
                    writer.close();
                }
                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
