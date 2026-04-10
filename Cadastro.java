import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

public class Cadastro extends JFrame {

    // ===== CONFIGURAÇÃO DO BANCO =====
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro_clientes";
    private static final String USER = "root";
    private static final String PASSWORD = "Senai@118"; // coloque sua senha do MySQL

    // ===== CAMPOS =====
    private JTextField campoNome;
    private JTextField campoEmail;
    private JFormattedTextField campoTelefone;
    private JTextField campoCPF;

    private JButton botaoSalvar;
    private JButton botaoLimpar;

    public Cadastro(){

        setTitle("Cadastro de Clientes");
        setSize(420,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== NOME =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        campoNome = new JTextField(20);
        add(campoNome, gbc);

        // ===== EMAIL =====
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        campoEmail = new JTextField(20);
        add(campoEmail, gbc);

        // ===== TELEFONE =====
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;

        try {

            MaskFormatter mascaraTelefone =
                    new MaskFormatter("(##) #####-####");

            mascaraTelefone.setPlaceholderCharacter('_');

            campoTelefone =
                    new JFormattedTextField(mascaraTelefone);

            add(campoTelefone, gbc);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // ===== CPF =====
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("CPF:"), gbc);

        gbc.gridx = 1;
        campoCPF = new JTextField(20);
        add(campoCPF, gbc);

        // ===== BOTÕES =====
        botaoSalvar = new JButton("Salvar");
        botaoLimpar = new JButton("Limpar");

        JPanel painel = new JPanel();

        painel.add(botaoSalvar);
        painel.add(botaoLimpar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;

        add(painel, gbc);

        // ===== EVENTO SALVAR =====
        botaoSalvar.addActionListener(e -> salvarCliente());

        // ===== EVENTO LIMPAR =====
        botaoLimpar.addActionListener(e -> limparCampos());
    }

    // ===== CONEXÃO COM BANCO =====
    private Connection conectar(){

        try {

            Connection conn =
                    DriverManager.getConnection(URL,USER,PASSWORD);

            System.out.println("Conectado ao banco!");

            return conn;

        } catch (SQLException e){

            JOptionPane.showMessageDialog(null,
                    "Erro ao conectar ao banco!");

            e.printStackTrace();

            return null;
        }
    }

    // ===== SALVAR CLIENTE =====
    private void salvarCliente(){

        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String telefone = campoTelefone.getText();
        String cpf = campoCPF.getText();

        if(!validarCPF(cpf)){

            JOptionPane.showMessageDialog(null,
                    "CPF inválido!");

            return;
        }

        String sql =
                "INSERT INTO clientes (nome,email,telefone,cpf) VALUES (?,?,?,?)";

        try{

            Connection conn = conectar();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1,nome);
            stmt.setString(2,email);
            stmt.setString(3,telefone);
            stmt.setString(4,cpf);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Cliente salvo no banco!");

            stmt.close();
            conn.close();

        }catch(SQLException e){

            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar cliente!");

            e.printStackTrace();
        }

        limparCampos();
    }

    // ===== LIMPAR CAMPOS =====
    private void limparCampos(){

        campoNome.setText("");
        campoEmail.setText("");
        campoTelefone.setText("");
        campoCPF.setText("");

    }

    // ===== VALIDAÇÃO CPF =====
    private boolean validarCPF(String cpf){

        cpf = cpf.replaceAll("[^0-9]","");

        if(cpf.length()!=11) return false;

        if(cpf.matches("(\\d)\\1{10}")) return false;

        try{

            int soma = 0;

            for(int i=0;i<9;i++){
                soma += (cpf.charAt(i)-'0')*(10-i);
            }

            int dig1 = 11-(soma%11);
            if(dig1>=10) dig1=0;

            soma = 0;

            for(int i=0;i<10;i++){
                soma += (cpf.charAt(i)-'0')*(11-i);
            }

            int dig2 = 11-(soma%11);
            if(dig2>=10) dig2=0;

            return dig1==(cpf.charAt(9)-'0') &&
                    dig2==(cpf.charAt(10)-'0');

        }catch(Exception e){
            return false;
        }
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Cadastro().setVisible(true);

        });

    }
}