package view;

import util.Leitor;

/**
 * Classe respons�vel pela valida��o do Usu�rio no sistema.<br>
 *
 * @author Jos� Augusto Martins Nieviadonski
 *
 */
public class TelaLogin
{
    public static void realizarLogin()
    {
        while (true)
        {
            System.out.println();
            System.out.println("Valida��o de Usu�rio");
            System.out.println();

            String tConta = Leitor.readString("Conta......... : ").trim();
            if (tConta.isEmpty())
            {
                System.out.println();
                System.out.println("Sistema encerrado");
                System.exit(9);
            }
            String tSenha = Leitor.readString("Senha......... : ").trim();

            System.out.println();
            if (tConta.equals("admin") && tSenha.equals("admin"))
            {
                System.out.println("Usu�rio validado com sucesso");
                return;
            }
            else
            {
                System.out.println("Usu�rio inv�lido, reentre...");
            }
        }
    }
}
