package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import util.Leitor;

public class TelaCurso
{
    private static String sCodigo = "";
    private static String sNome = "";
    private static String sDescricao = "";
    private static int sCargaHoraria = 0;
    private static Hashtable<String, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manuten��o de Cursos");
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
        System.out.println("Manuten��o de Cursos");
        System.out.println("Inclus�o de Curso");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty()) {
        	return;
        }
        	
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
			return;
        }
        
        sDescricao = lerDescricao();
        if (sDescricao == null) {
        	return;
		}

        sCargaHoraria = lerCargaHoraria();
        if (sCargaHoraria == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Curso cadastrado com sucesso");
            System.out.println();
            System.out.println("Codigo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Descri��o......... : " + sDescricao);
            System.out.println("Carga Hor�ria..... : " + sCargaHoraria);

            sCadastro.put(sCodigo, sNome + "\b" + sDescricao + "\b" + sCargaHoraria);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manuten��o de Curso");
        System.out.println("Altera��o de Curso");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarFuncionario(sCodigo);

        System.out.println("Curso a ser alterado");
        System.out.println();
        System.out.println("C�digo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Descri��o......... : " + sDescricao);
        System.out.println("Carga Hor�ria..... : " + sCargaHoraria);

        System.out.println();
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
			return;
        }
        
        sDescricao = lerDescricao();
        if (sDescricao == null) {
        	return;
		}

        sCargaHoraria = lerCargaHoraria();
        if (sCargaHoraria == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
        	System.out.println("Curso alterado com sucesso");
            System.out.println();
            System.out.println("C�digo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Descri��o......... : " + sDescricao);
            System.out.println("Carga Hor�ria..... : " + sCargaHoraria);

            sCadastro.put(sCodigo, sNome + "\b" + sDescricao + "\b" + sCargaHoraria);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manuten��o de Cursos");
        System.out.println("Exclus�o de Curso");
        System.out.println();
        
        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarFuncionario(sCodigo);

        System.out.println("Curso a ser exclu�do");
        System.out.println();
        System.out.println("C�digo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Descri��o......... : " + sDescricao);
        System.out.println("Carga Hor�ria..... : " + sCargaHoraria);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Curso exclu�do com sucesso");
            System.out.println();
            System.out.println("C�digo......... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Descri��o......... : " + sDescricao);
            System.out.println("Carga Hor�ria..... : " + sCargaHoraria);

            sCadastro.remove(sCodigo);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manuten��o de Cursos");
        System.out.println("Consulta de Curso");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == null || sCodigo.isEmpty())
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarFuncionario(sCodigo);

        System.out.println("Curso cadastrado");
        System.out.println();
        System.out.println("C�digo......... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Descri��o......... : " + sDescricao);
        System.out.println("Carga Hor�ria..... : " + sCargaHoraria);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manuten��o de Cursos");
        System.out.println("Rela��o de Cursos");
        System.out.println();

        System.out.println("Cursos cadastrados");
        for (String tNome : sCadastro.keySet())
        {
            recuperarFuncionario(tNome);
            System.out.printf("%-10s - %-10s - %-10s - %10d%n", sCodigo, sNome, sDescricao, sCargaHoraria);
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
//            String tErro = CodigoCursoADT.consistir(tCodigo);
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
//            String tErro = SenhaUsuarioADT.consistir(tNome);
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

    private static String lerDescricao()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Descri��o..... : ", sDescricao);

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tDescricao = Leitor.readString("Descri��o..... : ", sDescricao);
//            if (tDescricao == null)
//                break;
//            String tErro = DescricaoCursoADT.consistir(tDescricao);
//            if (tErro.isEmpty())
//                return tDescricao;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return "";
    }
    
    private static int lerCargaHoraria()
    {
        // C�digo sem consist�ncia
         return Leitor.readInt("Carga Hor�ria..... : ", sCargaHoraria);

        // C�digo com consist�ncia
//        while (true)
//        {
//            int tCargaHoraria = Leitor.readInt("Carga Hor�ria..... : ", sCargaHoraria);
//            if (tCargaHoraria == 0)
//                break;
//            String tErro = CargaHorariaCursoADT.consistir(tCargaHoraria);
//            if (tErro.isEmpty())
//                return tCargaHoraria;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return 0;
    }

    private static void recuperarFuncionario(String pCodigo)
    {
        String tDados = sCadastro.get(pCodigo);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sCodigo = pCodigo;
        sNome = tCampos[0];
        sDescricao = tCampos[1];
        sCargaHoraria = Integer.parseInt(tCampos[2]);
    }

    private static void limparDados()
    {
        sCodigo = "";
        sNome = "";
        sDescricao = "";
        sCargaHoraria = 0;
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroCurso");
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
        File tArquivo = new File("CadastroCurso");

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
