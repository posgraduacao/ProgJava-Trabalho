package adt;

public class CodigoSalaADT
{

    public static String consistir(String pCodigo)
    {
        if (pCodigo == null)
            return "C�digo n�o pode ser nulo";
        if (pCodigo.length() != 4)
            return "C�digo deve ter entre 4 caracteres";
//        for (int i=0; i < pCodigo.length(); i++)
//        {
//            char tCh = pCodigo.charAt(i);
//            if (tCh <= ' ' || tCh > '~')
//                return "Senha somente deve ter somente caracteres digit�veis";
//        }
        return "";
    }

}
