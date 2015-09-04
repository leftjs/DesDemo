import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by jason on 9/4/15.
 */
public class MainUI {
    private JPanel mainPanel;
    private JTextField encryptKey;
    private JButton chooseWillEncryptFile;
    private JTextField didEncryptSaveDir;
    private JButton chooseEncryptSaveDirButton;
    private JTextField willEncryptFilePath;
    private JButton makeEncryptButton;
    private JTextField decryptKey;
    private JTextField willDecryptFilePath;
    private JButton chooseDecryptSaveIDirButton;
    private JButton makeDecryptButton;
    private JButton chooseWillDecryptFileButton;
    private JTextField didDecryptFileSaveDir;


    private JFrame frame;




    public MainUI() {


        chooseWillEncryptFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                willEncryptFilePath.setText(chooseFile());
            }
        });

        chooseEncryptSaveDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                didEncryptSaveDir.setText(chooseDir());

            }
        });

        chooseWillDecryptFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                willDecryptFilePath.setText(chooseFile());

            }
        });

        chooseDecryptSaveIDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                didDecryptFileSaveDir.setText(chooseDir());
            }
        });


        makeEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (encryptKey.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "请输入加密密钥");
                    return;
                }
                if (willEncryptFilePath.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "请选择要加密的文件路径");
                    return;
                }
                if (didEncryptSaveDir.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "请输入加密文件保存路径");
                    return;
                }
                DesUtils desUtils = null;
                try {
                    desUtils = new DesUtils(encryptKey.getText().toString().trim());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (desUtils != null) {
                    try {
                        desUtils.doEncryptFile(willEncryptFilePath.getText().toString().trim(), didEncryptSaveDir.getText().toString().trim());
                        JOptionPane.showMessageDialog(frame, "加密成功");
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "加密失败");

                    }
                }

            }
        });

        makeDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (decryptKey.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "请输入解密密钥");
                    return;
                }
                if (willDecryptFilePath.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "请选择需要解密文件路径");
                    return;
                }
                if (didDecryptFileSaveDir.getText().equals("")) {

                    JOptionPane.showMessageDialog(frame, "请选择解密后文件的保存路径");
                    return;
                }

                DesUtils desUtils = null;
                try {
                    desUtils = new DesUtils(decryptKey.getText().toString().trim());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (desUtils != null) {
                    try {
                        desUtils.doDecryptFile(willDecryptFilePath.getText().toString().trim(), didDecryptFileSaveDir.getText().toString().trim());
                        JOptionPane.showMessageDialog(frame, "解密成功");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "解密失败");

                    }
                }
            }
        });


        frame = new JFrame("MainUI");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public String chooseDir() {

        String path = "";

        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        if (jfc.getSelectedFile() == null) {
            return "";
        }
        path = jfc.getSelectedFile().getAbsolutePath();


       return path;
    }

    public String chooseFile() {

        String path = "";

        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.showDialog(new JLabel(), "选择");

        File file=jfc.getSelectedFile();
        if (file == null) {
            return "";
        }
        if(file.isFile()) {
            path = file.getAbsolutePath();
        }
        return path;
    }

    public static void main(String[] args) {
        new MainUI();
    }


}
