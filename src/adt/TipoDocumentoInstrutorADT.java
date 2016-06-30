package adt;

public class TipoDocumentoInstrutorADT
{

    public static String consistir(String pTipoDocumento)
    {
        if (pTipoDocumento == null)
            return "Código não pode ser nulo";
//        if (pTipoDocumento.length() != 4)
//            return "Código deve ter entre 4 caracteres";
//        for (int i=0; i < pCodigo.length(); i++)
//        {
//            char tCh = pCodigo.charAt(i);
//            if (tCh <= ' ' || tCh > '~')
//                return "Senha somente deve ter somente caracteres digitáveis";
//        }
        return "";
    }

}
