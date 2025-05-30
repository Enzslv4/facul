package roteiro10.parte4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class JanelaSemLayout extends JFrame implements ActionListener{
    private int largura = 500;
    private int altura = 500;
    private JButton btn01;
    private JButton btn02;
    private JButton btn03;
    private JButton btn04;
    private JButton btn05;
    private JButton btn06;
    public JanelaSemLayout() {
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(largura, altura);
        this.setTitle("Janela Sem Layout");

        this.setLayout(null);

        this.iniciarComponentes();

        this.setVisible(true);
    }
    public void iniciarComponentes() {
    btn01 = new JButton("Botão 01");
    btn02 = new JButton("Botão 02");
    btn03 = new JButton("Botão 03");
    btn04 = new JButton("Botão 04");
    btn05 = new JButton("Botão 05");
    btn06 = new JButton("Botão 06");

    btn01.setBounds(20, 50, 90, 30);   // (x, y, largura, altura)
    btn02.setBounds(130, 50, 90, 30);
    btn03.setBounds(30, 90, 90, 30);
    btn04.setBounds(140, 90, 90, 30);
    btn05.setBounds(40, 130, 90, 30);
    btn06.setBounds(150, 130, 90, 30);

    this.add(btn01);
    this.add(btn02);
    this.add(btn03);
    this.add(btn04);
    this.add(btn05);
    this.add(btn06);

    btn01.addActionListener(this);
    btn02.addActionListener(this);
    btn03.addActionListener(this);
    btn04.addActionListener(this);
    btn05.addActionListener(this);
    btn06.addActionListener(this);
}

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JButton b = (JButton)o;
        String nome = b.getText();
        System.out.println("O clique do mouse foi acionado por: " + nome + " da classe " + o.getClass());
    }
    
}