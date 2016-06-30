package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import util.Leitor;

public class TelaInstrutor
{
    private static Integer sCodigo = 0;
	private static String sNome = "";
    private static String sEmpresa = "";
    private static long sTelefone = 0;
    private static String sDocumento;
    private static String sTipoDocumento = "";
    private static Hashtable<Integer, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manuten��o de Instrutores");
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
        System.out.println("Manuten��o de Instrutores");
        System.out.println("Inclus�o de Instrutor");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == 0)
            return;
        
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
        	return;
        }
        	
        sEmpresa = lerEmpresa();
        if (sEmpresa == null || sEmpresa.isEmpty()) {
			return;
        }
        
        sTelefone = lerTelefone();
        if (sTelefone == 0) {
        	return;
		}
        
        sDocumento = lerDocumento();
        if (sDocumento == null) {
			return;
		}

        sTipoDocumento = lerTipoDocumento();
        if (sTipoDocumento == null)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Instrutor cadastrado com sucesso");
            System.out.println();
            System.out.println("C�digo....... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Empresa......... : " + sEmpresa);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Documento......... : " + sDocumento);
            System.out.println("Tipo Documento..... : " + sTipoDocumento);

            sCadastro.put(sCodigo, sNome + "\b" + sEmpresa + "\b" + sTelefone + "\b" + sDocumento + "\b" + sTipoDocumento);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manuten��o de Instrutor");
        System.out.println("Altera��o de Instrutor");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == 0)
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarInstrutor(sCodigo);

        System.out.println("Instrutor a ser alterado");
        System.out.println();
        System.out.println("C�digo....... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Empresa......... : " + sEmpresa);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Documento......... : " + sDocumento);
        System.out.println("Tipo Documento..... : " + sTipoDocumento);

        System.out.println();
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
        	return;
        }
        
        sEmpresa = lerEmpresa();
        if (sEmpresa == null || sEmpresa.isEmpty()) {
			return;
        }
        
        sTelefone = lerTelefone();
        if (sTelefone == 0) {
        	return;
		}
        
        sDocumento = lerDocumento();
        if (sDocumento == null) {
			return;
		}

        sTipoDocumento = lerTipoDocumento();
        if (sTipoDocumento == null)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
        	System.out.println("Instrutor alterado com sucesso");
            System.out.println();
            System.out.println("C�digo....... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Empresa......... : " + sEmpresa);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Documento......... : " + sDocumento);
            System.out.println("Tipo Documento..... : " + sTipoDocumento);

            sCadastro.put(sCodigo, sNome + "\b" + sEmpresa + "\b" + sTelefone + "\b" + sDocumento + "\b" + sTipoDocumento);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manuten��o de Instrutores");
        System.out.println("Exclus�o de Instrutor");
        System.out.println();
        
        sCodigo = lerCodigo();
        if (sCodigo == 0)
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarInstrutor(sCodigo);

        System.out.println("Instrutor a ser exclu�do");
        System.out.println();
        System.out.println("C�digo....... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Empresa......... : " + sEmpresa);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Documento......... : " + sDocumento);
        System.out.println("Tipo Documento..... : " + sTipoDocumento);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Instrutor exclu�do com sucesso");
            System.out.println();
            System.out.println("C�digo....... : " + sCodigo);
            System.out.println("Nome......... : " + sNome);
            System.out.println("Empresa......... : " + sEmpresa);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Documento......... : " + sDocumento);
            System.out.println("Tipo Documento..... : " + sTipoDocumento);

            sCadastro.remove(sCodigo);
        }
        else
            System.out.println("Opera��o n�o realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manuten��o de Instrutores");
        System.out.println("Consulta de Instrutor");
        System.out.println();

        sCodigo = lerCodigo();
        if (sCodigo == 0)
            return;

        if (!sCadastro.containsKey(sCodigo))
        {
            System.out.println("C�digo n�o cadastrado");
            return;
        }

        recuperarInstrutor(sCodigo);

        System.out.println("Instrutor cadastrado");
        System.out.println();
        System.out.println("C�digo....... : " + sCodigo);
        System.out.println("Nome......... : " + sNome);
        System.out.println("Empresa......... : " + sEmpresa);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Documento......... : " + sDocumento);
        System.out.println("Tipo Documento..... : " + sTipoDocumento);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manuten��o de Instrutores");
        System.out.println("Rela��o de Instrutores");
        System.out.println();

        System.out.println("Instrutores cadastrados");
        for (Integer tCodigo : sCadastro.keySet())
        {
            recuperarInstrutor(tCodigo);
            System.out.printf("%-10s - %-10s - %-10s - %-10s - %-10s -%10s%n", sCodigo, sNome, sEmpresa, sTelefone, sDocumento, sTipoDocumento);
        }
    }

    private static int lerCodigo()
    {
        // C�digo sem consist�ncia
         return Leitor.readInt("C�digo..... : ", sCodigo);

        // C�digo com consist�ncia
//        while (true)
//        {
//            int tCodigo = Leitor.readInt("C�digo..... : ", sCodigo);
//            if (tCodigo == 0)
//                break;
//            String tErro = CodigoInstrutorADT.consistir(tCodigo);
//            if (tErro.isEmpty())
//                return tCodigo;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return 0;
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

    private static String lerEmpresa()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Empresa......... : ", sEmpresa).trim();

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tEmpresa = Leitor.readString("Empresa......... : ", sEmpresa).trim();
//            if (tEmpresa.isEmpty())
//                break;
//            String tErro = SenhaUsuarioADT.consistir(tEmpresa);
//            if (tErro.isEmpty())
//                return tEmpresa;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return null;
    }

    private static long lerTelefone()
    {
        // C�digo sem consist�ncia
         return Leitor.readLong("Telefone..... : ", sTelefone);

        // C�digo com consist�ncia
//        while (true)
//        {
//            long tTelefone = Leitor.readLong("Telefone..... : ", sTelefone);
//            if (tTelefone == 0)
//                break;
//            String tErro = TelefoneADT.consistir(tTelefone);
//            if (tErro.isEmpty())
//                return tTelefone;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return 0;
    }
    
    private static String lerDocumento()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Documento..... : ", sDocumento);

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tDataNascimento = Leitor.readString("Documento..... : ", sDocumento);
//            if (tDataNascimento == null)
//                break;
//            String tErro = DataNascimentoADT.consistir(tDataNascimento);
//            if (tErro.isEmpty())
//                return tDataNascimento;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return null;
    }
    
    private static String lerTipoDocumento()
    {
        // C�digo sem consist�ncia
         return Leitor.readString("Tipo Documento..... : ", sTipoDocumento);

        // C�digo com consist�ncia
//        while (true)
//        {
//            String tTipoDocumento = Leitor.readString("Tipo Documento..... : ", sTipoDocumento);
//            if (tTipoDocumento == "")
//                break;
//            String tErro = TipoDocumentoInstrutorADT.consistir(tTipoDocumento);
//            if (tErro.isEmpty())
//                return tTipoDocumento;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return "";
    }

    private static void recuperarInstrutor(Integer pCodigo)
    {
        String tDados = sCadastro.get(pCodigo);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sCodigo = pCodigo;
        sNome = tCampos[0];
        sEmpresa = tCampos[1];
        sTelefone = Long.parseLong(tCampos[2]);
        sDocumento = tCampos[3];
        sTipoDocumento = tCampos[4];
    }

    private static void limparDados()
    {
        sCodigo = 0;
    	sNome = "";
        sEmpresa = "";
        sTelefone = 0;
        sDocumento = "";
        sTipoDocumento = "";
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroInstrutor");
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
                Integer tCodigo = Integer.parseInt(tCampos[0]);
                sCadastro.put(tCodigo, tCampos[1] + "\b" + tCampos[2] + "\b" + tCampos[3] + "\b" + tCampos[4] + "\b" + tCampos[5]);
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
        File tArquivo = new File("CadastroInstrutor");

        try
        {
            PrintWriter tCad = new PrintWriter(tArquivo);
            for (Integer tCodigo : sCadastro.keySet())
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
