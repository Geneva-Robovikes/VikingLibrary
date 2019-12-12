
package robotbuilder.actions;

import java.awt.event.ActionEvent;

import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import robotbuilder.ActionsClass;
import robotbuilder.MainFrame;
import robotbuilder.RobotBuilder;

/**
 *
 * @author brad
 */
public class SaveAsAction extends AbstractAction {

    ActionsClass saveAsAction;

    JFileChooser fileChooser = new JFileChooser();

    public SaveAsAction() {
        putValue(Action.NAME, "Save as...");
        putValue(Action.SHORT_DESCRIPTION, "Save robot map to a new file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("YAML save file", RobotBuilder.SAVE_FILE_TYPE));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (MainFrame.getInstance().getCurrentRobotTree().getFilePath() != null) {
            fileChooser.setSelectedFile(new File(MainFrame.getInstance().getCurrentRobotTree().getFilePath()));
        }
        String filePath;
        int result = fileChooser.showSaveDialog(MainFrame.getInstance());
        if (result == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getPath();
            if (!filePath.endsWith(RobotBuilder.SAVE_FILE_TYPE)) {
                filePath += "." + RobotBuilder.SAVE_FILE_TYPE;
            }

            MainFrame.getInstance().getCurrentRobotTree().save(filePath);
        }
    }

}
