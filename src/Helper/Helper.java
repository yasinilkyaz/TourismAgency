package Helper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class Helper {
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static int screenCenter(String eksen, Dimension size){
        int point=0;

        switch (eksen){
            case "x":
                point=(Toolkit.getDefaultToolkit().getScreenSize().width -size.width) / 2;
                break;
            case "y":
                point=(Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point=0;
        }
        return point;
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                title="Hata!";
                msg="Lütfen tüm alanları doldurunuz!";
                break;
            case "done":
                msg="İşlem Başarılı !";
                title="Sonuç";
                break;
            case "error":
                msg="Bir Hata Oluştu !";
                title="Hata!";
                break;
            default:
                msg=str;
                title="Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(String str) {
        String msg;

        switch (str){
            case "sure":
                msg="Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
                break;
            default:
                msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Son kararın mı ?",JOptionPane.YES_NO_OPTION)==0;
    }

    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }


    public static boolean isFieldInt(JTextField field) {
        try {
            int value = Integer.parseInt(field.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isFieldDouble(JTextField field) {
        try {
            double value = Double.parseDouble(field.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFieldLocalDate(JTextField field) {
        try {
            LocalDate date = LocalDate.parse(field.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
