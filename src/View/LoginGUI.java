package View;

import Helper.Config;
import Helper.Helper;
import Model.Admin;
import Model.Employee;
import Model.User;

import javax.swing.*;

public class LoginGUI extends JFrame{


    private JPanel wrapper;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;

    public LoginGUI(){
        add(wrapper);
        setSize(400,450);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_username)||Helper.isFieldEmpty(fld_password)){
                Helper.showMsg("fill");
            }else {
                User u=new User();
                u=User.getFetch(fld_username.getText(),fld_password.getText());
                if(u==null){
                    Helper.showMsg("Kullanıcı bulunamadı.");
                }else {
                    switch (u.getRole()){
                        case "admin":
                            AdminGUI admin= new AdminGUI((Admin) u);
                            break;
                        case "employee":
                            EmployeeGUI agencyEmployee=new EmployeeGUI((Employee) u);
                            break;
                    }
                    dispose();
                }
            }

        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI=new LoginGUI();
    }

}
