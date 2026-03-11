/* Importação das classes */
// File
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

// Array List
import java.util.ArrayList;
import java.util.Comparator;

// Scanner e Random
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /* Importação da variável scan */
        Scanner scan = new Scanner(System.in);

        // Carrrgando Score
        System.out.println("Carregando Score...");
        FileExist();
        ReadScore();
        System.out.println(" ");

        /* Carrega Tela inicial do jogo */
        System.out.println("Carregando jogo:");
        InitialHistory();
        System.out.println(" "); /* Espaçamento de texto */

        // Variáveis para definir máximo de tentativas
        boolean defAttempts = true;
        int maxAttempts = 0;

        // Verifica se o máximo de tentativas é válido, e se é um número
        while(defAttempts){
            try {
                // Perguntas
                System.out.println("Você quer definir um número de tentativas ?");
                System.out.println("0 - Sem limite");
                System.out.println("Any - Limite definido");

                // Leitura do dado
                maxAttempts = Integer.parseInt(scan.nextLine()); // Lê a linha toda e converte
                defAttempts = false;
            } catch (NumberFormatException e) {
                // Erro de leitura
                System.out.println("Isso não parece um número de tentativa válido... tente novamente!");
            }
        }

        /* Gera um número aleatório */
        int randNumber = GenNumber();
        Sleep(5000);
        System.out.println(" "); /* Espaçamento de texto */

        /* Definição de veriáveis find e attempts */
        boolean find = true;
        int attempts = 0;

        /* Início do jogo */
        while (find && (maxAttempts == 0 || attempts < maxAttempts)) {
            try {
                attempts++; // Inicia o jogo contando como primeira tentativa
                System.out.print("Tentativa " + attempts + (maxAttempts > 0 ? "/" + maxAttempts : "") + ": ");
                int userNumber = Integer.parseInt(scan.nextLine());

                // Verifica se o número digitado está entre 1 e 100
                if (userNumber < 1 || userNumber > 100) {
                    // Caso contrário, é avisado para digitar um número válido
                    System.out.println("Ei! Fique dentro da floresta (1 a 100)!");
                    attempts--; // Não conta tentativa inválida
                    continue;
                }

                // Se Validation retornar false, significa que achou!
                if (!Validation(userNumber, randNumber)) {
                    find = false; // Isso vai parar o while
                }

            } catch (NumberFormatException e) {
                // Erro de leitura
                System.out.println("Isso não parece um número...");
                attempts--; // Não conta como tentativa
            }
        }

        /* Carrega a mensagem final com as tentativas */
        if (!find) {
            MessageFinalGame(attempts);
        } else {
            FinalHistory(attempts);
        }

        /* Fechamento do scan */
        scan.close();
    }

    static void InitialHistory(){
        /* Tela inicial do jogo com história */
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Em um certo dia na era medieval,");
        Sleep(2000);
        System.out.println("Um guerreiro destemido olha uma nave passando pelo céu");
        Sleep(2000);
        System.out.println("E pousa em uma floresta. O Guerreiro vai atrás para descorbrir");
        Sleep(2000);
        System.out.println("Quando chega na nave, não avista ninguém");
        Sleep(2000);
        System.out.println("E ele vai à procura do marciano");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        Sleep(2000);
        System.out.println("Você assume o guerreiro e tem uma missão!");
        Sleep(500);
        System.out.println("Achar o marciano, boa sorte!");
    }

    static void FinalHistory(int attempts){
        // Tela de morte do Guerreiro, caso não encontre em x tentativas (Caso tenha selecionado)
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Hm... Como você não me achou nas " + attempts + " tentativas, vou lhe pegar!");
        Sleep(2000);
        System.out.println("O marciano saca da cintura a arma e plasma");
        Sleep(2000);
        System.out.println("O cavalheiro começa a correr de medo");
        Sleep(5000);
        System.out.println("O cavalheiro é atingio por um raio e cai no chão");
        Sleep(2000);
        System.out.println("E ouve suas últimas palavras");
        Sleep(2000);
        System.out.println("'Dessa vez você não conseguiu, tente na próxima...'");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        Sleep(1000);
    }

    static int GenNumber(){
        /* Importação da variável random */
        Random random = new Random();

        /* Escolhendo um número */
        System.out.println("Você ouve uma voz ao fundo");
        System.out.println("'Venha atrás de mim!'");
        return random.nextInt(100) + 1;
    }

    static boolean Validation(int userNumber, int randNumber){
        // Verifica se o número digitado é igual ao número do marciano
        if(userNumber == randNumber){
            /* Caso o número digitado for igual, o usuário ganha */
            return false;
        } else if(userNumber > randNumber){
            /* Caso o número digitado for maior, ele está numa casa amior */
            Sleep(2000);
            System.out.println("Você ouviu uma voz à esquerda, vá atrás");
            return true;
        } else {
            /* Caso o número digitado for maior, ele está numa casa menor */
            Sleep(2000);
            System.out.println("Você ouviu uma voz à direita, vá atrás");
            return true;
        }
    }

    static void MessageFinalGame(int attempts){
        // Mensagem de encontro com o marciano
        Scanner scan = new Scanner(System.in);
        System.out.println("Que susto! Você me achou depois de " + attempts + " árvores!");

        /* mensagem de despedida */
        Sleep(1000);
        System.out.println("Foi um prazer jogar com você! Até a próxima!");

        // Pede o nome do guerreiro para salvar na história
        try {
            System.out.print("Digite o nome do guerreiro para continuar: ");
            String name = scan.nextLine();
            // Chama o metodo WriteScore para escrever no score
            WriteScore(name, attempts);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void FileExist(){
        try {
            // Verifica se o arquivo existe, se sim, fala que existe, caso contrário, ele cria um
            File arquivo = new File("score.txt"); // Create File object
            if (arquivo.createNewFile()) {           // Try to create the file
                System.out.println("File created: " + arquivo.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace(); // Print error details
        }
    }

    static void WriteScore(String name, int attempts) {
        // Este metodo grava o nome do guerreiro e o número de tentativas na história
        try (FileWriter myWriter = new FileWriter("score.txt", true)) {
            myWriter.write(name + ":" + attempts + "\n");
            System.out.println("Sua marca foi registrada na história!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar score.");
        }
    }

    static void ReadScore() {
        // Este metodo lê o arquivo de score da história e salva em um ArrayList
        File myObj = new File("score.txt");
        ArrayList<Ranking> lista = new ArrayList<>();

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                // Supondo que você salvou como "Nome:Tentativas"
                String[] partes = data.split(":");
                if (partes.length == 2) {
                    lista.add(new Ranking(partes[0], Integer.parseInt(partes[1])));
                }
            }

            // Ordenar: O menor número de tentativas vence!
            lista.sort(Comparator.comparingInt(r -> r.attempts));

            System.out.println("\n=== RANKING DOS GUERREIROS ===");
            for (int i = 0; i < Math.min(lista.size(), 5); i++) { // Mostra o Top 5
                System.out.println((i + 1) + "º " + lista.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ranking ainda vazio!");
        }
    }

    static void Sleep(int time){
        // Metodo para dar um intervalo de tempo durante as frases.
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}