package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import adt.ContaUsuarioADT;
import adt.MatriculaADT;
import adt.SenhaUsuarioADT;
import util.Leitor;

public class TelaUsuario
{
    private static String sConta = "";
    private static String sSenha = "";
    private static int    sMatricula = 0;
    private static Hashtable<String, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manuten��o de Usu�rios");
            System.out.println(" 1 - Incluir");
            System.out.println(" 2 - Alterar");
            System.out.println(" 3 - Excluir");
            System.out.println(" 4 - Consultar");
            System.out.println(" 5 - Listar");
            System.out.println(" 9 - Fim");
            System.out.println();

            int tOpcao = Leitor.readInt("Entre com a op��o desejada : ");
            if (tOpcao == 9)
                break;

            limparDados();
            switch (tOpcao)
            {
                case 1:
                    processarInclusao();
                    break;
                case 2:
                    processarAlteracao();
                    break;
                case 3:
                    processarExclusao();
                    break;
                case 4:
                    processarConsulta();
                    break;
                case 5:
                    processarRelacao();
                    break;
                default:
                    System.out.println("Op��o inv�lida. Reentre...");
                    break;
            }
        }
        gravarArquivo();
    }

    private static void processarInclusao()
    {
        System.out.println();
        System.out.println("Manuten��o de Usu�rios");
        System.out.println("Inclus�o de Usu�rio");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        sSenha = lerSenha();
        if (sSenha == null || sSenha.isEmpty())
            return;

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usu�rio cadastrado com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matr�cula..... : " + sMatricula);

            sCadastro.put(sConta, sSenha + "\b" + sMatricula);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manuten��o de Usu�rios");
        System.out.println("Altera��o de Usu�rio");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta n�o cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usu�rio a ser alterado");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matr�cula..... : " + sMatricula);

        System.out.println();
        sSenha = lerSenha();
        if (sSenha == null || sSenha.isEmpty())
            return;

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usu�rio alterado com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matr�cula..... : " + sMatricula);

            sCadastro.put(sConta, sSenha + "\b" + sMatricula);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manuten��o de Usu�rios");
        System.out.println("Exclus�o de Usu�rio");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta n�o cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usu�rio a ser exclu�do");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matr�cula..... : " + sMatricula);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Usu�rio exclu�do com sucesso");
            System.out.println();
            System.out.println("Conta......... : " + sConta);
            System.out.println("Senha......... : " + sSenha);
            System.out.println("Matr�cula..... : " + sMatricula);

            sCadastro.remove(sConta);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manuten��o de Usu�rios");
        System.out.println("Consulta de Usu�rio");
        System.out.println();

        sConta = lerConta();
        if (sConta == null || sConta.isEmpty())
            return;

        if (!sCadastro.containsKey(sConta))
        {
            System.out.println("Conta n�o cadastrada");
            return;
        }

        recuperarUsuario(sConta);

        System.out.println("Usu�rio cadastrado");
        System.out.println();
        System.out.println("Conta......... : " + sConta);
        System.out.println("Senha......... : " + sSenha);
        System.out.println("Matr�cula..... : " + sMatricula);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manuten��o de Usu�rios");
        System.out.println("Rela��o de Usu�rios");
        System.out.println();

        System.out.println("Usu�rios cadastrados");
        for (String tConta : sCadastro.keySet())
        {
            recuperarUsuario(tConta);
            System.out.printf("%-10s - %-10s - %10d%n", sConta, sSenha, sMatricula);
        }
    }

    private static String lerConta()
    {
        // C�digo sem consist�ncia
        // return Leitor.readString("Conta......... : ", sConta).trim();

        // C�digo com consist�ncia
        while (true)
        {
            String tConta = Leitor.readString("Conta......... : ", sConta).trim();
            if (tConta.isEmpty())
                break;
            String tErro = ContaUsuarioADT.consistir(tConta);
            if (tErro.isEmpty())
                return tConta;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }

    private static String lerSenha()
    {
        // C�digo sem consist�ncia
        // return Leitor.readString("Senha......... : ", sSenha).trim();

        // C�digo com consist�ncia
        while (true)
        {
            String tSenha = Leitor.readString("Senha......... : ", sSenha).trim();
            if (tSenha.isEmpty())
                break;
            String tErro = SenhaUsuarioADT.consistir(tSenha);
            if (tErro.isEmpty())
                return tSenha;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }

    private static int lerMatricula()
    {
        // C�digo sem consist�ncia
        // return Leitor.readInt("Matr�cula..... : ", sMatricula);

        // C�digo com consist�ncia
        while (true)
        {
            int tMatricula = Leitor.readInt("Matr�cula..... : ", sMatricula);
            if (tMatricula == 0)
                break;
            String tErro = MatriculaADT.consistir(tMatricula);
            if (tErro.isEmpty())
                return tMatricula;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return 0;
    }

    private static void recuperarUsuario(String pConta)
    {
        String tDados = sCadastro.get(pConta);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sConta = pConta;
        sSenha = tCampos[0];
        sMatricula = Integer.parseInt(tCampos[1]);
    }

    private static void limparDados()
    {
        sConta = "";
        sSenha = "";
        sMatricula = 0;
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroUsuario");
        if (!tArquivo.exists())
            return;

        try
        {
            BufferedReader tCad = new BufferedReader(new FileReader(tArquivo));
            while(true)
            {
                String tLinha = tCad.readLine();
                if (tLinha == null)
                    break;
                String[] tCampos = tLinha.split("\b");
                sCadastro.put(tCampos[0], tCampos[1] + "\b" + tCampos[2]);
            }
            tCad.close();
        }
        catch (IOException tExcept)
        {
            tExcept.printStackTrace();
            System.out.println();
            System.out.println("Programa cancelado no processo de leitura do arquivo");
            System.exit(9);
        }
     }

    private static void gravarArquivo()
    {
        File tArquivo = new File("CadastroUsuario");

        try
        {
            PrintWriter tCad = new PrintWriter(tArquivo);
            for (String tConta : sCadastro.keySet())
            {
                String tDados = sCadastro.get(tConta);
                tCad.println(tConta + "\b" + tDados);
            }
            tCad.close();
        }
        catch (IOException tExcept)
        {
            tExcept.printStackTrace();
            System.out.println();
            System.out.println("Programa cancelado no processo de grava��o do arquivo");
            System.exit(9);
        }
     }
}
