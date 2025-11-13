import numpy as np
import tkinter as tk
from tkinter import messagebox
import random
from collections import defaultdict

class DecisionTreeNode:
    def __init__(self):
        self.feature_index = None
        self.threshold = None
        self.left = None
        self.right = None
        self.value = None
        self.is_leaf = False

class DecisionTree:
    def __init__(self, max_depth=10, min_samples_split=2):
        self.max_depth = max_depth
        self.min_samples_split = min_samples_split
        self.root = None
    
    def fit(self, X, y):
        """Treina a árvore de decisão"""
        self.root = self._build_tree(X, y, depth=0)
    
    def predict(self, X):
        """Faz predições para um conjunto de features"""
        if len(X.shape) == 1:
            X = X.reshape(1, -1)
        return np.array([self._predict_sample(x, self.root) for x in X])
    
    def _predict_sample(self, x, node):
        """Prediz para uma única amostra"""
        if node.is_leaf:
            return node.value
        
        if x[node.feature_index] <= node.threshold:
            return self._predict_sample(x, node.left)
        else:
            return self._predict_sample(x, node.right)
    
    def _build_tree(self, X, y, depth):
        """Constrói a árvore recursivamente"""
        # Verificar se há dados
        if len(X) == 0 or len(y) == 0:
            node = DecisionTreeNode()
            node.is_leaf = True
            node.value = 0  # Valor padrão
            return node
            
        n_samples, n_features = X.shape
        n_classes = len(np.unique(y))
        
        node = DecisionTreeNode()
        
        # Condições de parada
        if (depth >= self.max_depth or 
            n_samples < self.min_samples_split or 
            n_classes == 1):
            node.is_leaf = True
            node.value = self._most_common_label(y)
            return node
        
        # Encontrar a melhor divisão
        best_feature, best_threshold = self._find_best_split(X, y)
        
        if best_feature is None:
            node.is_leaf = True
            node.value = self._most_common_label(y)
            return node
        
        node.feature_index = best_feature
        node.threshold = best_threshold
        
        # Dividir dados
        left_indices = X[:, best_feature] <= best_threshold
        right_indices = ~left_indices
        
        # Verificar se ambas as divisões têm dados
        if np.sum(left_indices) == 0 or np.sum(right_indices) == 0:
            node.is_leaf = True
            node.value = self._most_common_label(y)
            return node
        
        node.left = self._build_tree(X[left_indices], y[left_indices], depth + 1)
        node.right = self._build_tree(X[right_indices], y[right_indices], depth + 1)
        
        return node
    
    def _find_best_split(self, X, y):
        """Encontra a melhor divisão usando Information Gain"""
        best_gain = -1
        best_feature = None
        best_threshold = None
        
        for feature_idx in range(X.shape[1]):
            thresholds = np.unique(X[:, feature_idx])
            
            for threshold in thresholds:
                gain = self._information_gain(X[:, feature_idx], y, threshold)
                
                if gain > best_gain:
                    best_gain = gain
                    best_feature = feature_idx
                    best_threshold = threshold
        
        return best_feature, best_threshold
    
    def _information_gain(self, feature_values, y, threshold):
        """Calcula o Information Gain para uma divisão"""
        parent_entropy = self._entropy(y)
        
        left_mask = feature_values <= threshold
        right_mask = ~left_mask
        
        if np.sum(left_mask) == 0 or np.sum(right_mask) == 0:
            return 0
        
        left_entropy = self._entropy(y[left_mask])
        right_entropy = self._entropy(y[right_mask])
        
        n = len(y)
        n_left, n_right = np.sum(left_mask), np.sum(right_mask)
        
        child_entropy = (n_left / n) * left_entropy + (n_right / n) * right_entropy
        
        return parent_entropy - child_entropy
    
    def _entropy(self, y):
        """Calcula a entropia"""
        if len(y) == 0:
            return 0
        
        _, counts = np.unique(y, return_counts=True)
        probabilities = counts / len(y)
        
        return -np.sum(probabilities * np.log2(probabilities + 1e-10))
    
    def _most_common_label(self, y):
        """Retorna o label mais comum"""
        if len(y) == 0:
            return 0  # Valor padrão se não há dados
        unique, counts = np.unique(y, return_counts=True)
        return unique[np.argmax(counts)]

class GameStateAnalyzer:
    """Analisa o estado do jogo e extrai features para a árvore de decisão"""
    
    @staticmethod
    def extract_features(board, color):
        """Extrai features do estado do jogo"""
        features = []
        
        # Contagem de peças
        my_pieces = 0
        enemy_pieces = 0
        my_kings = 0
        enemy_kings = 0
        
        # Posições estratégicas
        center_control = 0
        edge_pieces = 0
        protected_pieces = 0
        
        for row in range(8):
            for col in range(8):
                piece = board[row][col]
                if piece:
                    if piece['cor'] == color:
                        my_pieces += 1
                        if piece['dama']:
                            my_kings += 1
                        
                        # Controle do centro
                        if 2 <= row <= 5 and 2 <= col <= 5:
                            center_control += 1
                        
                        # Peças na borda
                        if row == 0 or row == 7 or col == 0 or col == 7:
                            edge_pieces += 1
                        
                        # Peças protegidas
                        if GameStateAnalyzer._is_protected(board, row, col, color):
                            protected_pieces += 1
                    
                    else:
                        enemy_pieces += 1
                        if piece['dama']:
                            enemy_kings += 1
        
        # Features baseadas em proporções
        total_my = max(my_pieces, 1)
        total_enemy = max(enemy_pieces, 1)
        
        features.extend([
            my_pieces,
            enemy_pieces,
            my_kings,
            enemy_kings,
            my_pieces - enemy_pieces,  # Diferença de peças
            my_kings - enemy_kings,    # Diferença de damas
            center_control / total_my,
            edge_pieces / total_my,
            protected_pieces / total_my,
            my_pieces / (my_pieces + enemy_pieces + 1e-10)  # Proporção de peças
        ])
        
        return np.array(features)
    
    @staticmethod
    def _is_protected(board, row, col, color):
        """Verifica se uma peça está protegida"""
        directions = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        
        for dr, dc in directions:
            protect_row, protect_col = row + dr, col + dc
            if (0 <= protect_row < 8 and 0 <= protect_col < 8):
                protector = board[protect_row][protect_col]
                if protector and protector['cor'] == color:
                    return True
        
        return False

class DamaAI:
    def __init__(self):
        self.decision_tree = DecisionTree(max_depth=8, min_samples_split=3)
        self.training_data = []
        self.is_trained = False
        
    def generate_training_data(self, num_games=100):
        """Gera dados de treinamento através de simulações"""
        print("Gerando dados de treinamento...")
        
        for game_num in range(num_games):
            try:
                game_states, outcomes = self._simulate_game()
                if game_states and outcomes and len(game_states) == len(outcomes):
                    self.training_data.extend(zip(game_states, outcomes))
                
                if game_num % 20 == 0:
                    print(f"Jogos simulados: {game_num + 1}/{num_games}")
            except Exception as e:
                print(f"Erro no jogo {game_num}: {e}")
                continue
        
        self._train_model()
        print(f"Treinamento concluído com {len(self.training_data)} estados!")
    
    def _simulate_game(self):
        """Simula um jogo completo para gerar dados"""
        board = [[None for _ in range(8)] for _ in range(8)]
        self._initialize_board(board)
        
        states = []
        outcomes = []
        current_player = 'red'
        moves_count = 0
        
        while moves_count < 200:  # Limite para evitar jogos infinitos
            possible_moves = self._get_all_possible_moves(board, current_player)
            
            if not possible_moves:
                break
            
            # Escolher movimento aleatório
            move = random.choice(possible_moves)
            
            # Salvar estado antes do movimento
            features = GameStateAnalyzer.extract_features(board, current_player)
            states.append(features)
            
            # Executar movimento
            self._execute_move(board, move)
            
            # Verificar fim de jogo
            winner = self._check_winner(board)
            if winner:
                # Atribuir resultados baseados no vencedor
                for i, state in enumerate(states):
                    player_turn = 'red' if i % 2 == 0 else 'black'
                    if player_turn == winner:
                        outcomes.append(1)  # Vitória
                    else:
                        outcomes.append(0)  # Derrota
                break
            
            current_player = 'black' if current_player == 'red' else 'red'
            moves_count += 1
        
        # Se não houve vencedor, atribuir empate
        if len(outcomes) == 0:
            outcomes = [0.5] * len(states)
        
        return states, outcomes
    
    def _initialize_board(self, board):
        """Inicializa o tabuleiro"""
        for linha in range(3):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    board[linha][coluna] = {'cor': 'red', 'dama': False}
        
        for linha in range(5, 8):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    board[linha][coluna] = {'cor': 'black', 'dama': False}
    
    def _get_all_possible_moves(self, board, color):
        """Obtém todos os movimentos possíveis para um jogador"""
        moves = []
        
        for row in range(8):
            for col in range(8):
                piece = board[row][col]
                if piece and piece['cor'] == color:
                    piece_moves = self._get_piece_moves(board, row, col)
                    for move in piece_moves:
                        moves.append({
                            'from': (row, col),
                            'to': move,
                            'capture': abs(move[0] - row) == 2
                        })
        
        return moves
    
    def _get_piece_moves(self, board, row, col):
        """Obtém movimentos possíveis para uma peça específica"""
        moves = []
        piece = board[row][col]
        
        if not piece:
            return moves
        
        # Verificar capturas primeiro
        captures = self._get_captures(board, row, col)
        if captures:
            return captures
        
        # Movimentos simples
        if piece['dama']:
            directions = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        else:
            if piece['cor'] == 'red':
                directions = [(1, -1), (1, 1)]
            else:
                directions = [(-1, -1), (-1, 1)]
        
        for dr, dc in directions:
            new_row, new_col = row + dr, col + dc
            if (0 <= new_row < 8 and 0 <= new_col < 8 and 
                board[new_row][new_col] is None):
                moves.append((new_row, new_col))
        
        return moves
    
    def _get_captures(self, board, row, col):
        """Obtém capturas possíveis para uma peça"""
        captures = []
        piece = board[row][col]
        
        if not piece:
            return captures
        
        directions = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        
        for dr, dc in directions:
            if not piece['dama']:
                if piece['cor'] == 'red' and dr < 0:
                    continue
                if piece['cor'] == 'black' and dr > 0:
                    continue
            
            target_row, target_col = row + dr, col + dc
            dest_row, dest_col = row + 2 * dr, col + 2 * dc
            
            if (0 <= dest_row < 8 and 0 <= dest_col < 8):
                target_piece = board[target_row][target_col]
                dest_piece = board[dest_row][dest_col]
                
                if (target_piece and target_piece['cor'] != piece['cor'] and 
                    dest_piece is None):
                    captures.append((dest_row, dest_col))
        
        return captures
    
    def _execute_move(self, board, move):
        """Executa um movimento no tabuleiro"""
        from_pos = move['from']
        to_pos = move['to']
        
        piece = board[from_pos[0]][from_pos[1]]
        board[to_pos[0]][to_pos[1]] = piece
        board[from_pos[0]][from_pos[1]] = None
        
        # Se é captura, remover peça capturada
        if move['capture']:
            mid_row = (from_pos[0] + to_pos[0]) // 2
            mid_col = (from_pos[1] + to_pos[1]) // 2
            board[mid_row][mid_col] = None
        
        # Promover a dama
        if piece['cor'] == 'red' and to_pos[0] == 7:
            piece['dama'] = True
        elif piece['cor'] == 'black' and to_pos[0] == 0:
            piece['dama'] = True
    
    def _check_winner(self, board):
        """Verifica se há um vencedor"""
        red_pieces = 0
        black_pieces = 0
        
        for row in range(8):
            for col in range(8):
                piece = board[row][col]
                if piece:
                    if piece['cor'] == 'red':
                        red_pieces += 1
                    else:
                        black_pieces += 1
        
        if red_pieces == 0:
            return 'black'
        elif black_pieces == 0:
            return 'red'
        
        return None
    
    def _train_model(self):
        """Treina o modelo com os dados coletados"""
        if not self.training_data:
            print("Nenhum dado de treinamento disponível")
            return
        
        # Filtrar dados válidos
        valid_data = [(state, outcome) for state, outcome in self.training_data 
                     if len(state) == 10 and not np.any(np.isnan(state))]
        
        if len(valid_data) < 10:
            print(f"Dados insuficientes para treinamento: {len(valid_data)}")
            return
        
        X = np.array([state for state, _ in valid_data])
        y = np.array([outcome for _, outcome in valid_data])
        
        # Converter outcomes para classes
        y_classes = np.where(y >= 0.7, 1, np.where(y <= 0.3, 0, 2))  # 1=win, 0=loss, 2=draw
        
        print(f"Treinando com {len(X)} amostras...")
        
        self.decision_tree.fit(X, y_classes)
        self.is_trained = True
    
    def get_best_move(self, board, color):
        """Obtém o melhor movimento usando a árvore de decisão"""
        if not self.is_trained:
            # Se não treinado, usar estratégia simples
            return self._get_random_move(board, color)
        
        possible_moves = self._get_all_possible_moves(board, color)
        
        if not possible_moves:
            return None
        
        best_move = None
        best_score = -1
        
        for move in possible_moves:
            # Simular movimento
            board_copy = self._copy_board(board)
            self._execute_move(board_copy, move)
            
            # Avaliar posição resultante
            features = GameStateAnalyzer.extract_features(board_copy, color)
            prediction = self.decision_tree.predict(features.reshape(1, -1))[0]
            
            # Priorizar capturas e movimentos que levam à vitória
            score = prediction
            if move['capture']:
                score += 0.3
            
            if score > best_score:
                best_score = score
                best_move = move
        
        return best_move
    
    def _get_random_move(self, board, color):
        """Movimento aleatório como fallback"""
        moves = self._get_all_possible_moves(board, color)
        return random.choice(moves) if moves else None
    
    def _copy_board(self, board):
        """Cria uma cópia do tabuleiro"""
        return [[piece.copy() if piece else None for piece in row] for row in board]

class DamaComIA(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Jogo de Dama com IA (Decision Tree)")
        
        # Configurações do tabuleiro
        self.tamanho_celula = 70
        self.tamanho_tabuleiro = 8
        
        # Estados do jogo
        self.tabuleiro = [[None for _ in range(8)] for _ in range(8)]
        self.turno = 'red'
        self.peca_selecionada = None
        self.modo_ia = False
        
        # IA
        self.ia = DamaAI()
        
        # Interface
        self.setup_ui()
        self.inicializar_tabuleiro()
        self.desenhar_tabuleiro()
    
    def setup_ui(self):
        """Configura a interface do usuário"""
        # Frame para controles
        control_frame = tk.Frame(self)
        control_frame.pack(pady=10)
        
        # Botões
        tk.Button(control_frame, text="Treinar IA", 
                 command=self.treinar_ia, font=('Arial', 12)).pack(side=tk.LEFT, padx=5)
        
        tk.Button(control_frame, text="Jogar vs IA", 
                 command=self.toggle_ia, font=('Arial', 12)).pack(side=tk.LEFT, padx=5)
        
        tk.Button(control_frame, text="Reiniciar", 
                 command=self.reiniciar_jogo, font=('Arial', 12)).pack(side=tk.LEFT, padx=5)
        
        # Canvas
        self.canvas = tk.Canvas(
            self, 
            width=self.tamanho_celula * self.tamanho_tabuleiro,
            height=self.tamanho_celula * self.tamanho_tabuleiro,
            bg='white'
        )
        self.canvas.pack()
        
        # Labels
        self.label_turno = tk.Label(self, text="Turno: VERMELHO", font=('Arial', 14, 'bold'))
        self.label_turno.pack(pady=5)
        
        self.label_status = tk.Label(self, text="Modo: Humano vs Humano", font=('Arial', 12))
        self.label_status.pack(pady=5)
        
        # Bind para cliques
        self.canvas.bind("<Button-1>", self.clique_tabuleiro)
    
    def treinar_ia(self):
        """Treina a IA"""
        self.label_status.config(text="Treinando IA... Aguarde...")
        self.update()
        
        self.ia.generate_training_data(num_games=50)
        
        self.label_status.config(text="IA treinada! Pronta para jogar.")
    
    def toggle_ia(self):
        """Alterna entre modo humano e IA"""
        self.modo_ia = not self.modo_ia
        if self.modo_ia:
            self.label_status.config(text="Modo: Humano vs IA")
        else:
            self.label_status.config(text="Modo: Humano vs Humano")
    
    def inicializar_tabuleiro(self):
        """Inicializa o tabuleiro"""
        for linha in range(3):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    self.tabuleiro[linha][coluna] = {'cor': 'red', 'dama': False}
        
        for linha in range(5, 8):
            for coluna in range(8):
                if (linha + coluna) % 2 == 1:
                    self.tabuleiro[linha][coluna] = {'cor': 'black', 'dama': False}
    
    def desenhar_tabuleiro(self):
        """Desenha o tabuleiro"""
        self.canvas.delete("all")
        
        for linha in range(8):
            for coluna in range(8):
                x1 = coluna * self.tamanho_celula
                y1 = linha * self.tamanho_celula
                x2 = x1 + self.tamanho_celula
                y2 = y1 + self.tamanho_celula
                
                cor = '#D2B48C' if (linha + coluna) % 2 == 0 else '#8B4513'
                self.canvas.create_rectangle(x1, y1, x2, y2, fill=cor, outline='black')
                
                if self.peca_selecionada and self.peca_selecionada == (linha, coluna):
                    self.canvas.create_rectangle(x1, y1, x2, y2, outline='yellow', width=4)
                
                peca = self.tabuleiro[linha][coluna]
                if peca:
                    centro_x = x1 + self.tamanho_celula // 2
                    centro_y = y1 + self.tamanho_celula // 2
                    raio = self.tamanho_celula // 3
                    
                    self.canvas.create_oval(
                        centro_x - raio, centro_y - raio,
                        centro_x + raio, centro_y + raio,
                        fill=peca['cor'], outline='black', width=2
                    )
                    
                    if peca['dama']:
                        self.canvas.create_text(
                            centro_x, centro_y,
                            text='♔', font=('Arial', 30), fill='gold'
                        )
    
    def clique_tabuleiro(self, event):
        """Processa cliques no tabuleiro"""
        if self.modo_ia and self.turno == 'black':
            return  # IA está jogando
        
        coluna = event.x // self.tamanho_celula
        linha = event.y // self.tamanho_celula
        
        if 0 <= linha < 8 and 0 <= coluna < 8:
            if self.peca_selecionada:
                if self.tentar_mover(self.peca_selecionada[0], self.peca_selecionada[1], linha, coluna):
                    self.peca_selecionada = None
                    self.verificar_vitoria()
                    
                    if self.modo_ia and self.turno == 'black':
                        self.after(500, self.ia_jogar)
                else:
                    peca = self.tabuleiro[linha][coluna]
                    if peca and peca['cor'] == self.turno:
                        self.peca_selecionada = (linha, coluna)
            else:
                peca = self.tabuleiro[linha][coluna]
                if peca and peca['cor'] == self.turno:
                    self.peca_selecionada = (linha, coluna)
            
            self.desenhar_tabuleiro()
    
    def ia_jogar(self):
        """IA faz seu movimento"""
        if not self.ia.is_trained:
            messagebox.showwarning("Aviso", "IA não foi treinada ainda!")
            return
        
        melhor_movimento = self.ia.get_best_move(self.tabuleiro, 'black')
        
        if melhor_movimento:
            from_pos = melhor_movimento['from']
            to_pos = melhor_movimento['to']
            
            if self.tentar_mover(from_pos[0], from_pos[1], to_pos[0], to_pos[1]):
                self.verificar_vitoria()
                self.desenhar_tabuleiro()
    
    def tentar_mover(self, origem_linha, origem_coluna, destino_linha, destino_coluna):
        """Tenta mover uma peça"""
        movimentos_validos = self.obter_movimentos_validos(origem_linha, origem_coluna)
        
        if (destino_linha, destino_coluna) not in movimentos_validos:
            return False
        
        peca = self.tabuleiro[origem_linha][origem_coluna]
        
        if abs(destino_linha - origem_linha) == 2:
            meio_linha = (origem_linha + destino_linha) // 2
            meio_coluna = (origem_coluna + destino_coluna) // 2
            self.tabuleiro[meio_linha][meio_coluna] = None
            
            self.tabuleiro[destino_linha][destino_coluna] = peca
            self.tabuleiro[origem_linha][origem_coluna] = None
            
            capturas_adicionais = self.obter_capturas_possiveis(destino_linha, destino_coluna)
            if capturas_adicionais:
                self.peca_selecionada = (destino_linha, destino_coluna)
                self.promover_dama(destino_linha, destino_coluna)
                return False
        else:
            self.tabuleiro[destino_linha][destino_coluna] = peca
            self.tabuleiro[origem_linha][origem_coluna] = None
        
        self.promover_dama(destino_linha, destino_coluna)
        
        self.turno = 'black' if self.turno == 'red' else 'red'
        cor_display = 'VERMELHO' if self.turno == 'red' else 'PRETO'
        self.label_turno.config(text=f"Turno: {cor_display}")
        
        return True
    
    def obter_movimentos_validos(self, linha, coluna):
        """Retorna movimentos válidos para uma peça"""
        movimentos = []
        peca = self.tabuleiro[linha][coluna]
        
        if not peca:
            return movimentos
        
        capturas = self.obter_capturas_possiveis(linha, coluna)
        if capturas:
            return capturas
        
        if peca['dama']:
            direcoes = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        else:
            if peca['cor'] == 'red':
                direcoes = [(1, -1), (1, 1)]
            else:
                direcoes = [(-1, -1), (-1, 1)]
        
        for d_linha, d_coluna in direcoes:
            nova_linha = linha + d_linha
            nova_coluna = coluna + d_coluna
            
            if 0 <= nova_linha < 8 and 0 <= nova_coluna < 8:
                if self.tabuleiro[nova_linha][nova_coluna] is None:
                    movimentos.append((nova_linha, nova_coluna))
        
        return movimentos
    
    def obter_capturas_possiveis(self, linha, coluna):
        """Retorna capturas possíveis para uma peça"""
        capturas = []
        peca = self.tabuleiro[linha][coluna]
        
        if not peca:
            return capturas
        
        direcoes = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        
        for d_linha, d_coluna in direcoes:
            if not peca['dama']:
                if peca['cor'] == 'red' and d_linha < 0:
                    continue
                if peca['cor'] == 'black' and d_linha > 0:
                    continue
            
            alvo_linha = linha + d_linha
            alvo_coluna = coluna + d_coluna
            destino_linha = linha + 2 * d_linha
            destino_coluna = coluna + 2 * d_coluna
            
            if 0 <= destino_linha < 8 and 0 <= destino_coluna < 8:
                peca_alvo = self.tabuleiro[alvo_linha][alvo_coluna]
                peca_destino = self.tabuleiro[destino_linha][destino_coluna]
                
                if peca_alvo and peca_alvo['cor'] != peca['cor'] and peca_destino is None:
                    capturas.append((destino_linha, destino_coluna))
        
        return capturas
    
    def promover_dama(self, linha, coluna):
        """Promove peça a dama"""
        peca = self.tabuleiro[linha][coluna]
        if peca and not peca['dama']:
            if peca['cor'] == 'red' and linha == 7:
                peca['dama'] = True
            elif peca['cor'] == 'black' and linha == 0:
                peca['dama'] = True
    
    def verificar_vitoria(self):
        """Verifica condições de vitória"""
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
        self.label_turno.config(text="Turno: VERMELHO")
        self.inicializar_tabuleiro()
        self.desenhar_tabuleiro()

if __name__ == "__main__":
    jogo = DamaComIA()
    jogo.mainloop()