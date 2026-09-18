// Importar recursos
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// Classe principal
public class Main {
	// Variáveis globais
	// 	Variáveis de estilização
	public static String resetarCorDoTextoParaPadrao = "\u001B[0m";
	public static String corDoTextoVermelho = "\u001B[91m";
	public static String corDoTextoNegrito = "\u001B[1m";
	public static String corDoTextoAmarelo = "\u001B[33m";
	public static String corDoTextoVerde = "\u001B[32m";
	//  Variáveis de import
	public static Scanner scanner = new Scanner(System.in);
	public static Random random = new Random();
	//	Variáveis padrão
	//	 Int
	public static int entradaDoUsuarioInt = 0;
	//	 String
	public static String entradaDoUsuarioString = null;
	//   Boolean
	public static boolean entradaValida = false;
	// Listas globais
	public static ArrayList<Integer> listaDeComandosAtuais = new ArrayList<> ();
	// Método principal
	public static void main(String[] args) {
		// Pega a entrada do usuário
		while (!entradaValida) {
			// Exibi o menu de boas-vindas
			listarAcoes(1);
			// Pede ao usuário uma entrada
			print(2, "");
			print(1, "Digite uma opção válida e aperte enter:");
			// Pega a entrada em uma String
			entradaDoUsuarioString = scanner.nextLine().trim();
			// Válida a entrada do usuário
			try {
				// Tenta colocar a String em um int (Conversão)
				entradaDoUsuarioInt = Integer.parseInt(entradaDoUsuarioString);
				// Se conseguiu, verifica a opção escolhida
				if (listaDeComandosAtuais.contains(entradaDoUsuarioInt)) {
					// Caso a opção seja válida
					//  Verifica a opção escolhida
					switch (entradaDoUsuarioInt) {
						case 1:
							// Caso seja 1, inicia o jogo
							new jogoPrincipal();
							break;
						case 2:
							// Caso seja 1, diz tchau e fecha o programa
							print(5, "Volte sempre 👋👋👋");
							System.exit(0);
							break;
						default:
							// Caso não seja nenhum acima, dá um erro vermelho
							print(3, "Você escolheu uma opção inválida");
							break;
					}
					entradaValida = true;
				} else {
					// Lança um erro caso seja inválida
					throw new IllegalArgumentException("Você tem que digitar uma opção válida");
				}
			// Trata dos erros
			} catch (NumberFormatException e) {
				// Erro NumberFormatException tratado
				print(2, "");
				print(3, "Você tem que digitar um número inteiro!!!");
				print(1, "");
			} catch (IllegalArgumentException e) {
				// Erro IllegalArgumentException tratado
				print(2, "");
				print(3, "Você não escolheu uma opção válida!!!");
				print(1, "");
			}
		}
	}
	// Método para exibir ações
	public static void listarAcoes(int acao) {
		if (acao == 1) {
			// Se a ação for 1, atualiza os comandos
			for (int i = 0; i < 2; i++) {
				listaDeComandosAtuais.add((i + 1));
			}
			// Exibi as opções e dá as boas vindas
			System.out.print(corDoTextoNegrito);
			print(5, "**********************");
			print(5, "BEM-VINDO A O NÁUFRAGO");
			print(5, "**********************");
			System.out.print(resetarCorDoTextoParaPadrao);
			print(1, "1 - Jogar");
			print(1, "2 - Sair");
		}
	}
	// Método de imprimir mensagens (Apenas texto)
	public static void print(int tipo, String texto) {
		if (tipo == 1) {
			// Mensagem padrão
			System.out.println(texto);
		} else if (tipo == 2) {
			// Separador de texto
			print(1, "");
			print(1, "********************************************");
			print(1, "");
		} else if (tipo == 3) {
			// Para texto vermelho
			System.err.println(corDoTextoVermelho + texto + resetarCorDoTextoParaPadrao);
		} else if (tipo == 4) {
			// Para texto verde
			System.out.println(corDoTextoVerde + texto + resetarCorDoTextoParaPadrao);
		} else if (tipo == 5) {
			// Para texto em negrito
			System.out.println(corDoTextoNegrito + texto + resetarCorDoTextoParaPadrao);
		}
	}
}

class jogoPrincipal {
	// Variáveis globais
	// 	Int
	public static int quantidadeDeMadeira = 0;
	public static int nivelDaFogueira = 100;
	public static int vidaDoJogador = 100;
	public static int valorAleatorio = Main.random.nextInt(100) + 1;
	// Construtor
	public jogoPrincipal() {
		// 	Boolean
		boolean jogoRodando = true;
		boolean entradaValida = false;
		while (jogoRodando) {
			// Reduz algumas estatisticas
			vidaDoJogador -= 5;
			nivelDaFogueira -= 10;
			// Muda entradaValida para false
			entradaValida = false;
			// Mostra os status atuais
			mostrarStatus();
			// Exibi as escolhas do usuário
			exibirOpcoes();
			// Loop de verificação de entrada válida
			while (!entradaValida) {
				// Pede ao usuário uma entrada
				Main.print(2, "");
				Main.print(1, "Digite uma opção válida e aperte enter:");
				// Pega a entrada em uma String
				Main.entradaDoUsuarioString = Main.scanner.nextLine().trim();
				// Válida a entrada do usuário
				try {
					// Tenta colocar a String em um int (Conversão)
					Main.entradaDoUsuarioInt = Integer.parseInt(Main.entradaDoUsuarioString);
					// Verifica se a opção é válida
					if (Main.entradaDoUsuarioInt >= 1 && Main.entradaDoUsuarioInt <= 3) {
						// Torna a entrada válida para sair do loop
						entradaValida = true;
						// Se é válida, verifica a opção escolhida
						switch (Main.entradaDoUsuarioInt) {
							// Caso 1, coleta madeira
							case 1:
								coletarMadeira();
								break;
							// Caso 2, alimenta a fogueira
							case 2:
								alimentarFogueira();
								break;
							// Caso 3, recupera vida
							case 3:
								recuperarVida();
								break;
							// Caso padrão
							default:
								Main.print(2, "");
								Main.print(3, "Você não digitou uma entrada válida");
								break;
						}
					} else {
						throw new IllegalArgumentException("Você tem que digitar uma opção válida");
					}
				// Trata dos erros
				} catch (NumberFormatException e) {
					// Erro NumberFormatException tratado
					Main.print(2, "");
					Main.print(3, "Você tem que digitar um número inteiro!!!");
					Main.print(1, "");
				} catch (IllegalArgumentException e) {
					// Erro IllegalArgumentException tratado
					Main.print(2, "");
					Main.print(3, "Você não escolheu uma opção válida!!!");
					Main.print(1, "");
				}
			}
		}
	}
	// Método para mostrar as estatisticas
	public static void mostrarStatus() {
		Main.print(2, "");
		System.out.println(Main.corDoTextoNegrito + "Nível da fogueira: " + nivelDaFogueira);
		System.out.println("Vida do player: " + vidaDoJogador);
		System.out.println("Quantidade de madeira: " + quantidadeDeMadeira + Main.resetarCorDoTextoParaPadrao);
		Main.print(2, "");
		Main.print(1, "Esses são seus status atuais");
	}
	// Método para exibir opções
	public static void exibirOpcoes() {
		Main.print(2, "");
		Main.print(5, "1 - Coletar madeira");
		Main.print(5, "2 - Alimentar fogueira");
		Main.print(5, "3 - Recuperar vida");
	}
	// Método para adicionar madeira
	public static void coletarMadeira() {
		valorAleatorio = Main.random.nextInt(3);
		quantidadeDeMadeira =+ valorAleatorio;
	}
	// Método para aumentar o nivel do fogo
	public static void alimentarFogueira() {
		if (quantidadeDeMadeira >= 1) {
			nivelDaFogueira += 15;
			quantidadeDeMadeira -= 1;
		} else {
			Main.print(2, "");
			Main.print(3, "Você não tem madeira suficiente!");
		}
	}
	// Método para recuperar vida
	public static void recuperarVida() {
		valorAleatorio = Main.random.nextInt(100) + 1;
		if (valorAleatorio > vidaDoJogador) {
			Main.print(2, "");
			Main.print(1, "Sua vida está em 100");
			vidaDoJogador = 100;
		} else {
			vidaDoJogador += valorAleatorio;
			Main.print(2, "");
			System.out.println("Você recuperou " + valorAleatorio + " de vida");
		}
	}
	// Método para verificar se o player está vivo
	public static void verificarSobrevivencia() {
		valorAleatorio = Main.random.nextInt(3);
		if (vidaDoJogador <= 0) {
			Main.print(2, "");
			Main.print(3, "Fim de jogo! Você morreu por falta de saúde 💔💔💔");
		} else if (nivelDaFogueira <= 0) {
			Main.print(2, "");
			Main.print(3, "Fim de jogo! Você morreu de frio 🥶🥶🥶");
		} else if (valorAleatorio == 1) {
			Main.print(2, "");
			Main.print(5, "Você foi resgatado!");
		}
	}
}
