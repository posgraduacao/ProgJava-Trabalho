package view;

import util.Leitor;

public class Aplica��o
{
    public static void main(String[] args)
    {
        TelaLogin.realizarLogin();

        while (true)
        {
            System.out.println();
            System.out.println(" 1 - Usu�rios");
            System.out.println(" 2 - Funcion�rios");
            System.out.println(" 3 - Salas");
            System.out.println(" 4 - Instrutores");
            System.out.println(" 5 - Cursos");
            System.out.println(" 6 - Turmas");
            System.out.println(" 7 - Inscri��o de Funcion�rios");
            System.out.println(" 9 - Fim");
            System.out.println();

            int tOpcao = Leitor.readInt("Entre com a op��o desejada : ");
            if (tOpcao == 9)
                break;
            switch (tOpcao)
            {
                case 1:
                    TelaUsuario.processar();
                    break;
                case 2:
                    TelaFuncionario.processar();
                    break;
                case 3:
                    TelaSala.processar();
                    break;
                case 4:
                    TelaInstrutor.processar();
                    break;
                case 5:
                    TelaCurso.processar();
                    break;
                case 6:
                    // TODO - Cadastro de turmas
                    break;
                case 7:
                    // TODO - Inscri��o de funcion�rios em cursos
                    break;
                default:
                    System.out.println("Op��o inv�lida. Reentre...");
                    break;
            }
        }
    }
}
