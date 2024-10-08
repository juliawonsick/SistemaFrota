// TRABALHO EM DULPA: JÚLIA WONSICK RA: 1136562 E LAURA CEMIN IORA RA: 1131015


import java.util.List;
import java.util.Scanner;

public class CadVeiculo {
    private static Scanner scan;
    private static VeiculoService veiculoService;

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        veiculoService = new VeiculoService();
        int opcao;
        do {
            clear();
            System.out.println("Sistema de Gerenciamento de Frotas");
            System.out.println("MENU DE OPÇÕES:");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Listar todos os Veículos");
            System.out.println("3 - Pesquisar Veículo por Placa");
            System.out.println("4 - Remover Veículo");
            System.out.println("0 - Sair");

            opcao = promptForOption();

            switch (opcao) {
                case 1:
                    save();
                    break;
                case 2:
                    imprimirVeiculos();
                    break;
                case 3:
                    pesquisarVeiculos();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println("Volte logo!");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static int promptForOption() {
        int opcao;
        while (true) {
            System.out.print("Digite a opção desejada: ");
            String entrada = scan.nextLine();

            if (!entrada.trim().isEmpty()) {
                try {
                    opcao = Integer.parseInt(entrada);
                    if (opcao >= 0 && opcao <= 4) {
                        return opcao;
                    } else {
                        clear();
                        System.out.println("Digite um número dentro das opções acima.");
                    }
                } catch (NumberFormatException e) {
                    clear();
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
            } else {
                clear(); 
                System.out.println("Campo não pode estar em branco. Por favor, escolha uma opção");
            } System.out.println("Sistema de Gerenciamento de Frotas");
            System.out.println("MENU DE OPÇÕES:");
            System.out.println("1 - Cadastrar Novo Veículo");
            System.out.println("2 - Listar todos os Veículos");
            System.out.println("3 - Pesquisar Veículo por Placa");
            System.out.println("4 - Remover Veículo");
            System.out.println("0 - Sair");
        }
    }

    public static void save() {
        clear();
        Veiculo veiculoAdd;
        int tipoVeiculo = promptForVehicleType();
        clear();

        String marca = promptForNonEmptyInput("Digite a marca: ");
        String modelo = promptForNonEmptyInput("Digite o modelo: ");
        int ano = promptForValidYear("Digite o ano: ");
        String placa = promptForValidPlate("Digite a placa (7 caracteres): ");

        // Verificar se a placa já existe
        if (veiculoService.pesquisarVeiculos(placa) != null) {
            System.out.println("Já existe um veículo cadastrado com a mesma placa.");
            System.out.print("Pressione Enter para voltar ao Menu inicial");
            scan.nextLine();
            return;
        }

        int numeroPortas = 0;
        if (tipoVeiculo == 1) {
            numeroPortas = promptForNumberOfDoors();
        }

        boolean partida = false;
        if (tipoVeiculo == 2) {
            partida = promptForPartidaEletrica();
        }

        veiculoAdd = tipoVeiculo == 1 ? new Carro(marca, modelo, ano, placa, numeroPortas) : new Moto(marca, modelo, ano, placa, partida);

        try {
            veiculoService.save(veiculoAdd);
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        }

        System.out.print("Pressione Enter para voltar ao Menu inicial");
        scan.nextLine();
    }

    private static int promptForVehicleType() {
        int tipoVeiculo;
        while (true) {
            System.out.println("Qual o tipo de veículo");
            System.out.println("1 - Carro");
            System.out.println("2 - Moto");
            System.out.print("Digite a opção desejada: ");
            
        

            String entrada = scan.nextLine();
            if (!entrada.trim().isEmpty()) {
                try {
                    tipoVeiculo = Integer.parseInt(entrada);
                    if (tipoVeiculo >= 1 && tipoVeiculo <= 2) {
                        return tipoVeiculo;
                    } else {
                        clear();
                        System.out.println("Opção inválida. Por favor, escolha 1 ou 2.");
                    }
                } catch (NumberFormatException e) {
                    clear(); 
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                }
            } else {
                clear(); 
                System.out.println("Campo não pode estar em branco. Por favor, escolha um tipo de veículo.");
            }
        }
    }

    private static String promptForNonEmptyInput(String mensagem) {
        String entrada;
        while (true) {
            System.out.print(mensagem);
            entrada = scan.nextLine();
            if (!entrada.trim().isEmpty()) {
                return entrada;
            } else {
                clear(); 
                System.out.println("Campo não pode estar em branco.");
            }
        }
    }

    private static int promptForValidYear(String mensagem) {
        int ano;
        while (true) {
            System.out.print(mensagem);
            try {
                ano = Integer.parseInt(scan.nextLine());
                if (ano > 0) {
                    return ano;
                } else {
                    System.out.println("Ano deve ser maior que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido para o ano.");
            }
        }
    }

    private static String promptForValidPlate(String mensagem) {
        String placa;
        while (true) {
            System.out.print(mensagem);
            placa = scan.nextLine().trim();

            if (placa.length() == 7) {
                return placa;
            } else {
                clear(); 
                System.out.println("A placa deve ter exatamente 7 caracteres.");
            }
        }
    }

    private static int promptForNumberOfDoors() {
        int numeroPortas;
        while (true) {
            System.out.print("Digite o número de portas: ");
            try {
                numeroPortas = Integer.parseInt(scan.nextLine());
                if (numeroPortas > 0) {
                    return numeroPortas;
                } else {
                    System.out.println("Número de portas deve ser maior que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }
    }

    private static boolean promptForPartidaEletrica() {
        int partidaEletrica;
        while (true) {
            System.out.print("A moto possui partida elétrica? (1-Sim, 2-Não) ");
            if (scan.hasNextInt()) {
                partidaEletrica = scan.nextInt();
                scan.nextLine(); 
                if (partidaEletrica == 1) {
                    return true;
                } else if (partidaEletrica == 2) {
                    return false;
                } else {
                    System.out.print("Opção inválida. Digite 1 ou 2: ");
                }
            } else {
                System.out.print("Entrada inválida. Digite 1 ou 2: ");
                scan.nextLine(); 
            }
        }
    }

    private static void imprimirVeiculos() {
        clear();
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        int indice = 1;
        for (Veiculo veiculo : veiculos) {
            String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
            System.out.println("Veículo " + indice++ + " - Tipo: " + tipo + " - " + veiculo);
        }
        System.out.print("Pressione Enter para voltar ao Menu inicial");
        scan.nextLine();
    }

    public static void pesquisarVeiculos() {
        clear();
        String placa = promptForNonEmptyInput("Digite a placa que deseja pesquisar: ");

        Veiculo veiculo = veiculoService.pesquisarVeiculos(placa);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado com a placa informada.");
        } else {
            String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
            System.out.println("Veículo encontrado - Tipo: " + tipo + " - " + veiculo);
        }

        System.out.print("Pressione Enter para voltar ao Menu inicial");
        scan.nextLine();
    }

    public static void removerVeiculo() {
        clear();
        List<Veiculo> veiculos = veiculoService.getVeiculosDB();
        int indice = 1;

        System.out.println("Lista de veículos:");
        for (Veiculo veiculo : veiculos) {
            String tipo = veiculo instanceof Carro ? "Carro" : "Moto";
            System.out.println("Veículo " + indice++ + " - Tipo: " + tipo + " - " + veiculo);
        }

        String placa = promptForNonEmptyInput("Informe a placa do veículo que deseja REMOVER: ");

        Veiculo veiculo = veiculoService.removerVeiculo(placa);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado com a placa informada.");
        } else {
            System.out.println("Veículo removido com sucesso!");
        }
        

        System.out.print("Pressione Enter para voltar ao Menu inicial");
        scan.nextLine();
    }
}
