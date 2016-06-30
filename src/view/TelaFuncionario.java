package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import util.Leitor;

public class TelaFuncionario
{
    private static String sNome = "";
    private static String sSetor = "";
    private static long sTelefone = 0;
    private static String sDataNascimento;
    private static int sMatricula = 0;
    private static Hashtable<String, String> sCadastro = new Hashtable<>();

    public static void processar()
    {
        lerArquivo();
        while (true)
        {
            System.out.println();
            System.out.println("Manutenção de Funcionários");
            System.out.println(" 1 - Incluir");
            System.out.println(" 2 - Alterar");
            System.out.println(" 3 - Excluir");
            System.out.println(" 4 - Consultar");
            System.out.println(" 5 - Listar");
            System.out.println(" 9 - Fim");
            System.out.println();

            int tOpcao = Leitor.readInt("Entre com a opção desejada : ");
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
                    System.out.println("Opção inválida. Reentre...");
                    break;
            }
        }
        gravarArquivo();
    }

    private static void processarInclusao()
    {
        System.out.println();
        System.out.println("Manutenção de Funcionários");
        System.out.println("Inclusão de Funcionário");
        System.out.println();

        sNome = lerNome();
        if (sNome == null || sNome.isEmpty()) {
        	return;
        }
        	
        sSetor = lerSetor();
        if (sSetor == null || sSetor.isEmpty()) {
			return;
        }
        
        sTelefone = lerTelefone();
        if (sTelefone == 0) {
        	return;
		}
        
        sDataNascimento = lerDataNascimento();
        if (sDataNascimento == null) {
			return;
		}

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Funcionário cadastrado com sucesso");
            System.out.println();
            System.out.println("Nome......... : " + sNome);
            System.out.println("Setor......... : " + sSetor);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Data Nascimento......... : " + sDataNascimento);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.put(sNome, sSetor + "\b" + sTelefone + "\b" + sDataNascimento + "\b" + sMatricula);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarAlteracao()
    {
        System.out.println();
        System.out.println("Manutenção de Funcionário");
        System.out.println("Alteração de Funcionário");
        System.out.println();

        sNome = lerNome();
        if (sNome == null || sNome.isEmpty())
            return;

        if (!sCadastro.containsKey(sNome))
        {
            System.out.println("Nome não cadastrado");
            return;
        }

        recuperarFuncionario(sNome);

        System.out.println("Funcionário a ser alterado");
        System.out.println();
        System.out.println("Nome......... : " + sNome);
        System.out.println("Setor......... : " + sSetor);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Data Nascimento......... : " + sDataNascimento);
        System.out.println("Matrícula..... : " + sMatricula);

        System.out.println();
        sSetor = lerSetor();
        if (sSetor == null || sSetor.isEmpty()) {
			return;
        }
        
        sTelefone = lerTelefone();
        if (sTelefone == 0) {
        	return;
		}
        
        sDataNascimento = lerDataNascimento();
        if (sDataNascimento == null) {
			return;
		}

        sMatricula = lerMatricula();
        if (sMatricula == 0)
            return;

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
        	System.out.println("Funcionário alterado com sucesso");
            System.out.println();
            System.out.println("Nome......... : " + sNome);
            System.out.println("Setor......... : " + sSetor);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Data Nascimento......... : " + sDataNascimento);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.put(sNome, sSetor + "\b" + sTelefone + "\b" + sDataNascimento + "\b" + sMatricula);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarExclusao()
    {
        System.out.println();
        System.out.println("Manutenção de Funcionários");
        System.out.println("Exclusão de Funcionário");
        System.out.println();
        
        sNome = lerNome();
        if (sNome == null || sNome.isEmpty())
            return;

        if (!sCadastro.containsKey(sNome))
        {
            System.out.println("Nome não cadastrado");
            return;
        }

        recuperarFuncionario(sNome);

        System.out.println("Funcionário a ser excluído");
        System.out.println();
        System.out.println("Nome......... : " + sNome);
        System.out.println("Setor......... : " + sSetor);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Data Nascimento......... : " + sDataNascimento);
        System.out.println("Matrícula..... : " + sMatricula);

        System.out.println();
        char tConf = Leitor.readChar("Confirma (s/n)... : ");
        System.out.println();

        if (Character.toUpperCase(tConf) == 'S')
        {
            System.out.println("Funcionário excluído com sucesso");
            System.out.println();
            System.out.println("Nome......... : " + sNome);
            System.out.println("Setor......... : " + sSetor);
            System.out.println("Telefone......... : " + sTelefone);
            System.out.println("Data Nascimento......... : " + sDataNascimento);
            System.out.println("Matrícula..... : " + sMatricula);

            sCadastro.remove(sNome);
        }
        else
            System.out.println("Operação não realizada...");
    }

    private static void processarConsulta()
    {
        System.out.println();
        System.out.println("Manutenção de Funcionários");
        System.out.println("Consulta de Funcionário");
        System.out.println();

        sNome = lerNome();
        if (sNome == null || sNome.isEmpty())
            return;

        if (!sCadastro.containsKey(sNome))
        {
            System.out.println("Nome não cadastrado");
            return;
        }

        recuperarFuncionario(sNome);

        System.out.println("Funcionário cadastrado");
        System.out.println();
        System.out.println("Nome......... : " + sNome);
        System.out.println("Setor......... : " + sSetor);
        System.out.println("Telefone......... : " + sTelefone);
        System.out.println("Data Nascimento......... : " + sDataNascimento);
        System.out.println("Matrícula..... : " + sMatricula);
    }

    private static void processarRelacao()
    {
        System.out.println();
        System.out.println("Manutenção de Funcionários");
        System.out.println("Relação de Funcionários");
        System.out.println();

        System.out.println("Funcionários cadastrados");
        for (String tNome : sCadastro.keySet())
        {
            recuperarFuncionario(tNome);
            System.out.printf("%-10s - %-10s - %-10s - %-10s - %10d%n", sNome, sSetor, sTelefone, sDataNascimento, sMatricula);
        }
    }

    private static String lerNome()
    {
        // Código sem consistência
         return Leitor.readString("Nome......... : ", sNome).trim();

        // Código com consistência
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

    private static String lerSetor()
    {
        // Código sem consistência
         return Leitor.readString("Setor......... : ", sSetor).trim();

        // Código com consistência
//        while (true)
//        {
//            String tSetor = Leitor.readString("Setor......... : ", sSetor).trim();
//            if (tSetor.isEmpty())
//                break;
//            String tErro = SenhaUsuarioADT.consistir(tSetor);
//            if (tErro.isEmpty())
//                return tSetor;
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
        // Código sem consistência
         return Leitor.readLong("Telefone..... : ", sTelefone);

        // Código com consistência
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
    
    private static String lerDataNascimento()
    {
        // Código sem consistência
         return Leitor.readString("DataNascimento..... : ", sDataNascimento);

        // Código com consistência
//        while (true)
//        {
//            String tDataNascimento = Leitor.readString("Data de Nascimento..... : ", sDataNascimento);
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
    
    private static int lerMatricula()
    {
        // Código sem consistência
         return Leitor.readInt("Matrícula..... : ", sMatricula);

        // Código com consistência
//        while (true)
//        {
//            int tMatricula = Leitor.readInt("Matrícula..... : ", sMatricula);
//            if (tMatricula == 0)
//                break;
//            String tErro = MatriculaADT.consistir(tMatricula);
//            if (tErro.isEmpty())
//                return tMatricula;
//            else
//            {
//                System.out.println("Erro.......... : " + tErro);
//                System.out.println();
//            }
//        }
//        return 0;
    }

    private static void recuperarFuncionario(String pNome)
    {
        String tDados = sCadastro.get(pNome);
        if (tDados == null)
            return;
        String[] tCampos = tDados.split("\b");

        sNome = pNome;
        sSetor = tCampos[0];
        sTelefone = Long.parseLong(tCampos[1]);
        sDataNascimento = tCampos[2];
        sMatricula = Integer.parseInt(tCampos[3]);
    }

    private static void limparDados()
    {
        sNome = "";
        sSetor = "";
        sTelefone = 0;
        sDataNascimento = "";
        sMatricula = 0;
    }

    private static void lerArquivo()
    {
        File tArquivo = new File("CadastroFuncionario");
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
                sCadastro.put(tCampos[0], tCampos[1] + "\b" + tCampos[2] + "\b" + tCampos[3] + "\b" + tCampos[4]);
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
        File tArquivo = new File("CadastroFuncionario");

        try
        {
            PrintWriter tCad = new PrintWriter(tArquivo);
            for (String tNome : sCadastro.keySet())
            {
                String tDados = sCadastro.get(tNome);
                tCad.println(tNome + "\b" + tDados);
            }
            tCad.close();
        }
        catch (IOException tExcept)
        {
            tExcept.printStackTrace();
            System.out.println();
            System.out.println("Programa cancelado no processo de gravação do arquivo");
            System.exit(9);
        }
     }
}
