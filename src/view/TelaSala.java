package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import util.Leitor;

public class TelaSala
{
    private static String sCodigo = "";
	private static String sNome = "";
    private static int sCapacidade = 0;
    private static String sEndereco = "";
    private static Hashtable<String, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manuten��o de Salas");
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
        System.out.println("Manuten��o de Salas");
        System.out.println("Inclus�o de Sala");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty()) {
        	return;
        }
        
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
        	return;
        }
        
        sCapacidade = lerCapacidade();
        if (sCapacidade == 0)
            return;
        	
        sEndereco = lerEndereco();
        if (sEndereco == null || sEndereco.isEmpty()) {
			return;
        }

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Sala cadastrada com sucesso");
            System.out.println();
            System.out.println("Codigo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Capacidade......... : " + sCapacidade);
            System.out.println("Endereco......... : " + sEndereco);

            sCadastro.put(sCodigo, sNome + "\b" + sCapacidade + "\b" + sEndereco);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manuten��o de Sala");
        System.out.println("Altera��o de Sala");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarSala(sCodigo);

        System.out.println("Sala a ser alterada");
        System.out.println();
        System.out.println("Codigo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Capacidade......... : " + sCapacidade);
        System.out.println("Endereco......... : " + sEndereco);

        System.out.println();
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
        	return;
        }
        
        sCapacidade = lerCapacidade();
        if (sCapacidade == 0)
            return;
        	
        sEndereco = lerEndereco();
        if (sEndereco == null || sEndereco.isEmpty()) {
			return;
        }

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
        	System.out.println("Sala alterada com sucesso");
            System.out.println();
            System.out.println("C�digo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Capacidade........ : " + sCapacidade);
            System.out.println("Endereco......... : " + sEndereco);

            sCadastro.put(sCodigo, sNome + "\b" + sCapacidade + "\b" + sEndereco);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manuten��o de Salas");
        System.out.println("Exclus�o de Sala");
        System.out.println();
        
        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarSala(sCodigo);

        System.out.println("Sala a ser exclu�da");
        System.out.println();
        System.out.println("Codigo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Capacidade......... : " + sCapacidade);
        System.out.println("Endereco......... : " + sEndereco);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Sala exclu�da com sucesso");
            System.out.println();
            System.out.println("Codigo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Capacidade......... : " + sCapacidade);
            System.out.println("Endereco......... : " + sEndereco);

            sCadastro.remove(sCodigo);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manuten��o de Salas");
        System.out.println("Consulta de Sala");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarSala(sCodigo);

        System.out.println("Sala cadastrado");
        System.out.println();
        System.out.println("Codigo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Capacidade......... : " + sCapacidade);
        System.out.println("Endereco......... : " + sEndereco);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manuten��o de Salas");
        System.out.println("Rela��o de Salas");
        System.out.println();

        System.out.println("Salas cadastrados");
        for (String tCodigo : sCadastro.keySet())
        {
            recuperarSala(tCodigo);
            System.out.printf("%-10s - %-10s - %-10s - %10s%n", sCodigo, sNome, sCapacidade, sEndereco);
        }
    }

    private static String lerCodigo()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("C�digo......... : ", sCodigo).trim();

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tCodigo = Leitor.readString("C�digo......... : ", sCodigo).trim();
//            if (tCodigo.isEmpty())
//                break;
//            String tErro = CodigoSalaADT.consistir(tCodigo);
//            if (tErro.isEmpty())
//                return tCodigo;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return null;
    }
    
    private static String lerNome()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Nome......... : ", sNome).trim();

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tNome = Leitor.readString("Nome......... : ", sNome).trim();
//            if (tNome.isEmpty())
//                break;
//            String tErro = ContaUsuarioADT.consistir(tNome);
//            if (tErro.isEmpty())
//                return tNome;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return null;
    }

    private static int lerCapacidade()
    {
        // C�digo sem consist�ncia
         return Leitor.readInt("Capacidade..... : ", sCapacidade);

        // C�digo com consist�ncia
//        while (true)
//        {
//            int tCapacidade = Leitor.readInt("Capacidade..... : ", sCapacidade);
//            if (tCapacidade == 0)
//                break;
//            String tErro = CapacidadeSalaADT.consistir(tCapacidade);
//            if (tErro.isEmpty())
//                return tCapacidade;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return 0;
    }
    
    private static String lerEndereco()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Endere�o..... : ", sEndereco);

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tEndereco = Leitor.readString("Endere�o..... : ", sEndereco);
//            if (tEndereco == null)
//                break;
//            String tErro = EnderecoSalaADT.consistir(tEndereco);
//            if (tErro.isEmpty())
//                return tEndereco;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return null;
    }

    private static void recuperarSala(String pCodigo)
    {
        String tDados = sCadastro.get(pCodigo);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sCodigo = pCodigo;
        sNome = tCampos[0];
        sCapacidade = Integer.parseInt(tCampos[1]);
        sEndereco = tCampos[2];
    }

    private static void limparDados()
    {
        sCodigo = "";
    	sNome = "";
        sCapacidade = 0;
        sEndereco = "";
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroSala");
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
                sCadastro.put(tCampos[0], tCampos[1] + "\b" + tCampos[2] + "\b" + tCampos[3]);
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
        File tArquivo = new File("CadastroSala");

        try
        {
            PrintWriter tCad = new PrintWriter(tArquivo);
            for (String tCodigo : sCadastro.keySet())
            {
                String tDados = sCadastro.get(tCodigo);
                tCad.println(tCodigo + "\b" + tDados);
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
