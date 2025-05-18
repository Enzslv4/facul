import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Cadastro de Usuários");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Deletar Usuário");
        System.out.println("5. Sair");
        System.out.print("Opção: ");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int opcao = scanner.nextInt();
        scanner.nextLine();
        while (opcao != 5) {
            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    usuarios.add(new Usuario(nome, email, senha));
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Lista de Usuários:");
                    for (Usuario usuario : usuarios) {
                        System.out.println(usuario);
                    }
                    break;
                case 3:
                    System.out.print("ID do usuário a ser atualizado: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine();
                    if (idAtualizar >= 0 && idAtualizar < usuarios.size()) {
                        Usuario usuarioAtualizar = usuarios.get(idAtualizar);
                        System.out.print("Novo Nome (atual: " + usuarioAtualizar.getNome() + "): ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Novo Email (atual: " + usuarioAtualizar.getEmail() + "): ");
                        String novoEmail = scanner.nextLine();
                        System.out.print("Nova Senha (atual: " + usuarioAtualizar.getSenha() + "): ");
                        String novaSenha = scanner.nextLine();
                        usuarioAtualizar.setNome(novoNome);
                        usuarioAtualizar.setEmail(novoEmail);
                        usuarioAtualizar.setSenha(novaSenha);
                        System.out.println("Usuário atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("ID do usuário a ser deletado: ");
                    int idDeletar = scanner.nextInt();
                    if (idDeletar >= 0 && idDeletar < usuarios.size()) {
                        usuarios.remove(idDeletar);
                        System.out.println("Usuário deletado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
        }
    }
}
