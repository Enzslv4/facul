import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Interface extends JFrame {
    private ArrayList<Usuario> usuarios;
    private JTextArea areaTexto;

    public Interface() {
        super("Gerenciador de Usuários");
        usuarios = new ArrayList<>();
        configurarInterface();
    }

    private void configurarInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Área de exibição
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);

        // Painel de botões
        JPanel botoesPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");

        botoesPanel.add(btnCadastrar);
        botoesPanel.add(btnListar);
        botoesPanel.add(btnAtualizar);
        botoesPanel.add(btnDeletar);

        add(botoesPanel, BorderLayout.SOUTH);

        // Ações
        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        btnListar.addActionListener(e -> listarUsuarios());
        btnAtualizar.addActionListener(e -> atualizarUsuario());
        btnDeletar.addActionListener(e -> deletarUsuario());
    }

    private void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog(this, "Nome:");
        if (nome == null || nome.isEmpty()) return;

        String email = JOptionPane.showInputDialog(this, "Email:");
        if (email == null || email.isEmpty()) return;

        String senha = JOptionPane.showInputDialog(this, "Senha:");
        if (senha == null || senha.isEmpty()) return;

        Usuario novo = new Usuario(nome, email, senha);
        usuarios.add(novo);
        listarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
    }

    private void listarUsuarios() {
        areaTexto.setText("");
        if (usuarios.isEmpty()) {
            areaTexto.setText("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : usuarios) {
                areaTexto.append(u.toString() + "\n");
            }
        }
    }

    private void atualizarUsuario() {
        String emailBusca = JOptionPane.showInputDialog(this, "Digite o email do usuário a ser atualizado:");
        if (emailBusca == null || emailBusca.isEmpty()) return;

        Usuario usuario = buscarUsuarioPorEmail(emailBusca);
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            return;
        }

        String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", usuario.getNome());
        if (novoNome != null && !novoNome.isEmpty()) {
            usuario.setNome(novoNome);
        }

        String novaSenha = JOptionPane.showInputDialog(this, "Nova senha:");
        if (novaSenha != null && !novaSenha.isEmpty()) {
            usuario.setSenha(novaSenha);
        }

        listarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuário atualizado.");
    }

    private void deletarUsuario() {
        String email = JOptionPane.showInputDialog(this, "Digite o email do usuário a ser deletado:");
        if (email == null || email.isEmpty()) return;

        Usuario usuario = buscarUsuarioPorEmail(email);
        if (usuario != null) {
            usuarios.remove(usuario);
            listarUsuarios();
            JOptionPane.showMessageDialog(this, "Usuário removido.");
        } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
        }
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}
