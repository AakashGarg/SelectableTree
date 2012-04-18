/**
 *
 * @author Aakash Garg
 */
package selectableTree;
 
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;

public class SelectableTree extends JFrame
        implements TreeSelectionListener {

    static File mainDir = new File("D:\\songs"); //main destination whose directory structure is to be shown

    public static void main(String[] args) {
        if(args.length != 0)
		mainDir = new File(args[0]);
		new SelectableTree();
    }
    private JTree tree;
    private JTextField currentSelectionField;

    public SelectableTree() {
        super("JTree Selections");
        WindowUtilities.setNativeLookAndFeel();
        addWindowListener(new ExitListener());
        Container content = getContentPane();

        String[] namesplitStrings = mainDir.toString().split("\\\\");
        String filename = namesplitStrings[namesplitStrings.length - 1];
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(filename);

        addChildren(root, mainDir);
        tree = new JTree(root);
        tree.addTreeSelectionListener(this);
        content.add(new JScrollPane(tree), BorderLayout.CENTER);
        currentSelectionField = new JTextField("Current Selection: NONE");
        content.add(currentSelectionField, BorderLayout.SOUTH);
        setSize(250, 275);
        setVisible(true);
    }

    void addChildren(DefaultMutableTreeNode parent, File parentDirFile) {

        for (File file : parentDirFile.listFiles()) {
            String[] namesplitStrings = file.toString().split("\\\\");
            String filename = namesplitStrings[namesplitStrings.length - 1];

            if (file.isDirectory()) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(filename);
                addChildren(child, file);
                parent.add(child);
            } else if (file.isFile()) {
                parent.add(new DefaultMutableTreeNode(filename));
            }
        }

    }

    @Override
    public void valueChanged(TreeSelectionEvent event) {
        currentSelectionField.setText(tree.getLastSelectedPathComponent().toString());
    }
}