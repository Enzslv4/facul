package roteiro8.parte3;

public class Cliente {
    private String cpf_cnpj;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private Veiculo veiculoCliente;

    public Cliente (String pCpf_Cnpj, String pNome, String pEdereco, String pEmail, String pTelefone) {
        this.cpf_cnpj = pCpf_Cnpj;
        this.nome = pNome;
        this.endereco = pEdereco;
        this.telefone = pTelefone;
        this.email = pEmail;
    }

    public Veiculo getVeiculoCliente() {
        return veiculoCliente;
    }

    public void setVeiculoCliente(Veiculo veiculoCliente) {
        if (veiculoCliente != null) {
            this.veiculoCliente = veiculoCliente;
        } else {
            System.out.println("O cliente não tem um veículo.");
        }
    }
    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}