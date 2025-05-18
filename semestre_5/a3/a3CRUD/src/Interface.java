import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Interface extends JFrame {
    private JTextArea areaTexto;
    private UsuarioDAO usuarioDAO;

    public Interface() {
        super("Gerenciador de Usuários (com DB)");
        usuarioDAO = new UsuarioDAO();
        configurarInterface();
    }

    private void configurarInterface() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        add(scroll, BorderLayout.CENTER);

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

        Usuario usuario = new Usuario(nome, email, senha);
        usuarioDAO.adicionar(usuario);
        listarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
    }

    private void listarUsuarios() {
        ArrayList<Usuario> lista = usuarioDAO.listar();
        areaTexto.setText("");
        if (lista.isEmpty()) {
            areaTexto.setText("Nenhum usuário no banco.");
        } else {
            for (Usuario u : lista) {
                areaTexto.append(u.toString() + "\n");
            }
        }
    }

    private void atualizarUsuario() {
        String email = JOptionPane.showInputDialog(this, "Email do usuário a atualizar:");
        if (email == null || email.isEmpty()) return;

        Usuario usuario = usuarioDAO.buscarPorEmail(email);
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            return;
        }

        String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", usuario.getNome());
        if (novoNome != null && !novoNome.isEmpty()) usuario.setNome(novoNome);

        String novaSenha = JOptionPane.showInputDialog(this, "Nova senha:");
        if (novaSenha != null && !novaSenha.isEmpty()) usuario.setSenha(novaSenha);

        usuarioDAO.atualizar(usuario);
        listarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuário atualizado!");
    }

    private void deletarUsuario() {
        String email = JOptionPane.showInputDialog(this, "Email do usuário a deletar:");
        if (email == null || email.isEmpty()) return;

        usuarioDAO.deletar(email);
        listarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuário removido!");
    }
}
