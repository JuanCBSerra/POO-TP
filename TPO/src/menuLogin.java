import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class menuLogin extends JFrame {

    private JPanel pnlLogin;
    private JTextField textFieldMail;
    private JTextField textFieldPassword;
    private JTextField textFieldName;
    private JLabel Mail;
    private JLabel Nombre;
    private JLabel Contraseña;
    private JButton loginButton;
    private JButton exitButton;

    private static final String DB_FILE = new File("").getAbsolutePath() + "/TPO/src/db/dbUsuarios.txt";
    private Map<String, String[]> usersDatabase;

    public menuLogin(String titulo) {
        super(titulo);
        setContentPane(pnlLogin);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        usersDatabase = new HashMap<>();
        loadDatabase();

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textFieldName.getText().toLowerCase(); // Convertir el nombre a minúsculas
                String mail = textFieldMail.getText().toLowerCase(); // Convertir el correo a minúsculas
                String password = textFieldPassword.getText(); //
                if (authenticateUser(name, mail, password)) { // Verificar nombre, correo electrónico y contraseña
                    redirectUser(usersDatabase.get(mail)[2]); // El rango está en la posición 2
                } else {
                    JOptionPane.showMessageDialog(null, "Correo, nombre o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    usersDatabase.put(parts[1].toLowerCase(), new String[]{parts[0].toLowerCase(), parts[2].toLowerCase(), parts[3]});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticateUser(String name, String mail, String password) {
        if (usersDatabase.containsKey(mail)) {
            String[] userData = usersDatabase.get(mail);
            return userData[0].equals(name) && userData[1].equals(password);
        }
        return false;
    }

    private void redirectUser(String rank) {
        switch (rank) {
            case "Administrador":
                // Redirigir a la sección de administrador
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador", "Login", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Laboratorista":
                // Redirigir a la sección de laboratorista
                JOptionPane.showMessageDialog(null, "Bienvenido Laboratorista", "Login", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Recepcionista":
                // Redirigir a la sección de recepcionista
                JOptionPane.showMessageDialog(null, "Bienvenido Recepcionista", "Login", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Rango desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void addUserToDatabase(String name, String mail, String password, String rank) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DB_FILE, true))) {
            bw.write(name + "," + mail + "," + password + "," + rank);
            bw.newLine();
            usersDatabase.put(mail.toLowerCase(), new String[]{name.toLowerCase(), password.toLowerCase(), rank});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        menuLogin frame = new menuLogin("Sistema de Gestión de Laboratorio");
        frame.setVisible(true);
    }
}
