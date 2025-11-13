import tkinter as tk
from tkinter import messagebox

class Dama:
    def __init__(self, root):
        self.root = root
        self.root.title("Jogo de Dama")
        
        # Configurações do tabuleiro
        self.tamanho_celula = 70
        self.tamanho_tabuleiro = 8
        
        # Estados do jogo
        self.tabuleiro = [[None for _ in range(8)] for _ in range(8)]
        self.turno = 'red'  # 'red' ou 'black'
        self.peca_selecionada = None
        self.movimentos_obrigatorios = []
        
        # Canvas para desenhar o tabuleiro
        self.canvas = tk.Canvas(
            root, 
            width=self.tamanho_celula * self.tamanho_tabuleiro,
            height=self.tamanho_celula * self.tamanho_tabuleiro,
            bg='white'
        )
        self.canvas.pack()
        
        # Label para mostrar o turno
        cor_display = 'VERMELHO' if self.turno == 'red' else 'PRETO'
        self.label_turno = tk.Label(root, text=f"Turno: {cor_display}", font=('Arial', 14, 'bold'))
        self.label_turno.pack(pady=10)
        
        # Botão para reiniciar
        self.btn_reiniciar = tk.Button(root, text="Reiniciar Jogo", command=self.reiniciar_jogo, font=('Arial', 12))
        self.btn_reiniciar.pack(pady=5)
        
        # Bind para cliques
        self.canvas.bind("<Button-1>", self.clique_tabuleiro)
        
        # Inicializar jogo
        self.inicializar_tabuleiro()
        self.desenhar_tabuleiro()
    
    def inicializar_tabuleiro(self):
        """Inicializa as peças no tabuleiro"""
        # Peças vermelhas (parte superior)
        for linha in range(3):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    self.tabuleiro[linha][coluna] = {'cor': 'red', 'dama': False}
        
        # Peças pretas (parte inferior)
        for linha in range(5, 8):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    self.tabuleiro[linha][coluna] = {'cor': 'black', 'dama': False}
    
    def desenhar_tabuleiro(self):
        """Desenha o tabuleiro e as peças"""
        self.canvas.delete("all")
        
        # Desenhar células
        for linha in range(8):
            for coluna in range(8):
                x1 = coluna * self.tamanho_celula
                y1 = linha * self.tamanho_celula
                x2 = x1 + self.tamanho_celula
                y2 = y1 + self.tamanho_celula
                
                cor = '#D2B48C' if (linha + coluna) % 2 == 0 else '#8B4513'
                self.canvas.create_rectangle(x1, y1, x2, y2, fill=cor, outline='black')
                
                # Destacar célula selecionada
                if self.peca_selecionada and self.peca_selecionada == (linha, coluna):
                    self.canvas.create_rectangle(x1, y1, x2, y2, outline='yellow', width=4)
                
                # Desenhar peça
                peca = self.tabuleiro[linha][coluna]
                if peca:
                    centro_x = x1 + self.tamanho_celula // 2
                    centro_y = y1 + self.tamanho_celula // 2
                    raio = self.tamanho_celula // 3
                    
                    # Cor da peça
                    cor_peca = peca['cor']
                    cor_borda = 'black'
                    
                    self.canvas.create_oval(
                        centro_x - raio, centro_y - raio,
                        centro_x + raio, centro_y + raio,
                        fill=cor_peca, outline=cor_borda, width=2
                    )
                    
                    # Desenhar coroa se for dama
                    if peca['dama']:
                        self.canvas.create_text(
                            centro_x, centro_y,
                            text='♔', font=('Arial', 30), fill='gold'
                        )
        
        # Mostrar movimentos possíveis
        if self.peca_selecionada:
            movimentos = self.obter_movimentos_validos(self.peca_selecionada[0], self.peca_selecionada[1])
            for mov_linha, mov_coluna in movimentos:
                x1 = mov_coluna * self.tamanho_celula
                y1 = mov_linha * self.tamanho_celula
                x2 = x1 + self.tamanho_celula
                y2 = y1 + self.tamanho_celula
                self.canvas.create_rectangle(x1, y1, x2, y2, outline='lime', width=4)
    
    def clique_tabuleiro(self, event):
        """Processa cliques no tabuleiro"""
        coluna = event.x // self.tamanho_celula
        linha = event.y // self.tamanho_celula
        
        if 0 <= linha < 8 and 0 <= coluna < 8:
            if self.peca_selecionada:
                # Tentar mover a peça
                if self.mover_peca(self.peca_selecionada[0], self.peca_selecionada[1], linha, coluna):
                    self.peca_selecionada = None
                    self.verificar_vitoria()
                else:
                    # Selecionar outra peça
                    peca = self.tabuleiro[linha][coluna]
                    if peca and peca['cor'] == self.turno:
                        self.peca_selecionada = (linha, coluna)
            else:
                # Selecionar peça
                peca = self.tabuleiro[linha][coluna]
                if peca and peca['cor'] == self.turno:
                    self.peca_selecionada = (linha, coluna)
            
            self.desenhar_tabuleiro()
    
    def obter_movimentos_validos(self, linha, coluna):
        """Retorna lista de movimentos válidos para uma peça"""
        movimentos = []
        peca = self.tabuleiro[linha][coluna]
        
        if not peca:
            return movimentos
        
        # Verificar capturas primeiro (são obrigatórias)
        capturas = self.obter_capturas_possiveis(linha, coluna)
        if capturas:
            return capturas
        
        # Direções de movimento
        if peca['dama']:
            direcoes = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        else:
            if peca['cor'] == 'red':
                direcoes = [(1, -1), (1, 1)]  # Move para baixo
            else:
                direcoes = [(-1, -1), (-1, 1)]  # Move para cima
        
        # Verificar movimentos simples
        for d_linha, d_coluna in direcoes:
            nova_linha = linha + d_linha
            nova_coluna = coluna + d_coluna
            
            if 0 <= nova_linha < 8 and 0 <= nova_coluna < 8:
                if self.tabuleiro[nova_linha][nova_coluna] is None:
                    movimentos.append((nova_linha, nova_coluna))
        
        return movimentos
    
    def obter_capturas_possiveis(self, linha, coluna):
        """Retorna lista de capturas possíveis para uma peça"""
        capturas = []
        peca = self.tabuleiro[linha][coluna]
        
        if not peca:
            return capturas
        
        # Todas as direções diagonais
        direcoes = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        
        for d_linha, d_coluna in direcoes:
            # Se não for dama e movimento não é permitido, pular
            if not peca['dama']:
                if peca['cor'] == 'red' and d_linha < 0:
                    continue
                if peca['cor'] == 'black' and d_linha > 0:
                    continue
            
            alvo_linha = linha + d_linha
            alvo_coluna = coluna + d_coluna
            destino_linha = linha + 2 * d_linha
            destino_coluna = coluna + 2 * d_coluna
            
            # Verificar se a captura é válida
            if 0 <= destino_linha < 8 and 0 <= destino_coluna < 8:
                peca_alvo = self.tabuleiro[alvo_linha][alvo_coluna]
                peca_destino = self.tabuleiro[destino_linha][destino_coluna]
                
                if peca_alvo and peca_alvo['cor'] != peca['cor'] and peca_destino is None:
                    capturas.append((destino_linha, destino_coluna))
        
        return capturas
    
    def mover_peca(self, origem_linha, origem_coluna, destino_linha, destino_coluna):
        """Move uma peça se o movimento for válido"""
        movimentos_validos = self.obter_movimentos_validos(origem_linha, origem_coluna)
        
        if (destino_linha, destino_coluna) not in movimentos_validos:
            return False
        
        peca = self.tabuleiro[origem_linha][origem_coluna]
        
        # Verificar se é uma captura
        if abs(destino_linha - origem_linha) == 2:
            # Remover peça capturada
            meio_linha = (origem_linha + destino_linha) // 2
            meio_coluna = (origem_coluna + destino_coluna) // 2
            self.tabuleiro[meio_linha][meio_coluna] = None
            
            # Mover peça
            self.tabuleiro[destino_linha][destino_coluna] = peca
            self.tabuleiro[origem_linha][origem_coluna] = None
            
            # Verificar se pode capturar novamente
            capturas_adicionais = self.obter_capturas_possiveis(destino_linha, destino_coluna)
            if capturas_adicionais:
                self.peca_selecionada = (destino_linha, destino_coluna)
                self.promover_dama(destino_linha, destino_coluna)
                return False  # Não mudar turno ainda
        else:
            # Movimento simples
            self.tabuleiro[destino_linha][destino_coluna] = peca
            self.tabuleiro[origem_linha][origem_coluna] = None
        
        # Promover a dama se chegou ao final
        self.promover_dama(destino_linha, destino_coluna)
        
        # Trocar turno
        self.turno = 'black' if self.turno == 'red' else 'red'
        cor_display = 'VERMELHO' if self.turno == 'red' else 'PRETO'
        self.label_turno.config(text=f"Turno: {cor_display}")
        
        return True
    
    def promover_dama(self, linha, coluna):
        """Promove uma peça a dama se chegou ao final do tabuleiro"""
        peca = self.tabuleiro[linha][coluna]
        if peca and not peca['dama']:
            if peca['cor'] == 'red' and linha == 7:
                peca['dama'] = True
            elif peca['cor'] == 'black' and linha == 0:
                peca['dama'] = True
    
    def verificar_vitoria(self):
        """Verifica se algum jogador venceu"""
        pecas_vermelhas = 0
        pecas_pretas = 0
        
        for linha in range(8):
            for coluna in range(8):
                peca = self.tabuleiro[linha][coluna]
                if peca:
                    if peca['cor'] == 'red':
                        pecas_vermelhas += 1
                    else:
                        pecas_pretas += 1
        
        if pecas_vermelhas == 0:
            messagebox.showinfo("Fim de Jogo", "Jogador PRETO venceu!")
            self.reiniciar_jogo()
        elif pecas_pretas == 0:
            messagebox.showinfo("Fim de Jogo", "Jogador VERMELHO venceu!")
            self.reiniciar_jogo()
    
    def reiniciar_jogo(self):
        """Reinicia o jogo"""
        self.tabuleiro = [[None for _ in range(8)] for _ in range(8)]
        self.turno = 'red'
        self.peca_selecionada = None
        cor_display = 'VERMELHO' if self.turno == 'red' else 'PRETO'
        self.label_turno.config(text=f"Turno: {cor_display}")
        self.inicializar_tabuleiro()
        self.desenhar_tabuleiro()

# Criar janela principal
if __name__ == "__main__":
    root = tk.Tk()
    jogo = Dama(root)
    root.mainloop()